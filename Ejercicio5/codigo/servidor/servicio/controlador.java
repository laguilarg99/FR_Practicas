package servicio;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import servicio.modelo;

public class controlador implements Runnable
{
    private Socket socketServicio;
	
    public controlador(Socket socketServicio) 
    {
        this.socketServicio=socketServicio;
	}
	
    public void run()
    {	
		try {
			PrintWriter outPrintWriter = new PrintWriter(socketServicio.getOutputStream(),true);
			BufferedReader inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
            String peticion = null;

            if((peticion = inReader.readLine()) != null){
                String[] Argumento = obtenerArgumentos(peticion);
                int usuario = -1;
                if(Argumento[0].equals("AUTH")){
                    if(Argumento.length == 3){
                        if((usuario = modelo.autenticar(Argumento[1], Argumento[2])) != -1){
                            System.out.println(LocalDateTime.now() + " " + usuario + " Login correcto");
                            outPrintWriter.print("#AUTH#OK#Autenticacion correcta#\r\n");
                            outPrintWriter.flush(); 
                            Boolean closed = false;
                            String respuesta = null;
                            while(!closed)
                            {
                                if((peticion = inReader.readLine()) != null){
                                    Argumento = obtenerArgumentos(peticion);
                                    System.out.println(LocalDateTime.now() + " PETICION " +  usuario + " " + peticion);

                                    switch(Argumento[0])
                                    {
                                        case "MENTRADA":
                                            if(Argumento.length == 1){
                                                respuesta = modelo.Mostrar_Bandeja_Entrada(usuario);
                                            } else  {
                                                respuesta = "#PARAMERROR#No se he introducido el numero correcto de parametros#";
                                            }
                                        break;
                                        case "MSALIDA":
                                            if(Argumento.length == 1){
                                                respuesta = modelo.Mostrar_Bandeja_Salida(usuario);
                                            } else  {
                                                respuesta = "#PARAMERROR#No se he introducido el numero correcto de parametros#";                                        
                                            }
                                        break;
                                        case "LEERCORREO":
                                            if(Argumento.length == 2){
                                                respuesta = modelo.Leer_correo(Integer.parseInt(Argumento[1]), usuario); 
                                            } else  {
                                                respuesta = "#PARAMERROR#No se he introducido el numero correcto de parametros#";
                                            }
                                        break;
                                        case "CLOSE":
                                            respuesta = modelo.cerrar_conexion();
                                            closed = true;
                                        break;
                                        default: 
                                            respuesta = "#PARAMERROR#No se han introducido parametros correctos#";
                                    }
                                    respuesta+="\r\n";
                                    
                                    System.out.println(LocalDateTime.now() + " RESPUESTA " + usuario + " " + respuesta);
                                    outPrintWriter.print(respuesta);
                                    outPrintWriter.flush(); 
                                    
                                } else {
                                    System.out.println(LocalDateTime.now() +" " + usuario + " #PARAMERROR#No se han introducido parametros#");
                                    outPrintWriter.print("#PARAMERROR#No se han introducido parametros#\r\n");
                                    outPrintWriter.flush(); 
                                    closed = true;
                                } 
                            } 
                            socketServicio.close();
                        } else {
                            System.out.println(LocalDateTime.now() + " Intento de login " + peticion);
                            outPrintWriter.print("#AUTH#ERROR#Usuario o contraseña incorrectos#");
                            outPrintWriter.flush();
                            socketServicio.close();
                        }
                    } else {
                        System.out.println(LocalDateTime.now() + " Intento de login " + peticion);
                        outPrintWriter.print("#AUTH#ERROR#Numero de argumentos incorrecto#");
                        outPrintWriter.flush();
                    }
                } else {
                    System.err.println("Error al obtener los flujo de entrada/salida.");
                    socketServicio.close();
                }
            }
        } catch (IOException e) {
			System.err.println("Error al obtener los flujo de entrada/salida.");
	    }

    }
    
    private String[] obtenerArgumentos(String Cadena)
    {

        Cadena = (Cadena.substring(0,Cadena.length()-1)).substring(1);
        String[] args = Cadena.split("#");
        return args; 
    }
}