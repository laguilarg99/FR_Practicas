package servicio;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

                if(Argumento[0] == "AUTH"){
                    if(Argumento.length == 3){
                        if(modelo.autenticar(Argumento[1], Argumento[2])){
                            outPrintWriter.print("#AUTH#OK#Autenticacion correcta#");
                            outPrintWriter.flush(); 
                            Boolean closed = false;
                            String respuesta = null;
                            do {
                                if((peticion = inReader.readLine()) != null){
                                    Argumento = obtenerArgumentos(peticion);
                                    switch(Argumento[0]) {
                                        case "MENTRADA":
                                            if(Argumento.length == 3){
                                                respuesta = modelo.Mostrar_Bandeja_Entrada(Integer.parseInt(Argumento[2]));
                                            } else  {
                                                respuesta = "#PARAMERROR#No se he introducido el numero correcto de parametros#";
                                            }
                                        break;
                                        case "MSALIDA":
                                            if(Argumento.length == 3){
                                                respuesta = modelo.Mostrar_Bandeja_Salida(Integer.parseInt(Argumento[2]));
                                            } else  {
                                                respuesta = "#PARAMERROR#No se he introducido el numero correcto de parametros#";                                        
                                            }
                                        break;
                                        case "LEERCORREO":
                                            if(Argumento.length == 3){
                                                respuesta = modelo.Leer_correo(Integer.parseInt(Argumento[2])); 
                                            } else  {
                                                respuesta = "#PARAMERROR#No se he introducido el numero correcto de parametros#";
                                                outPrintWriter.flush();                                           
                                            }
                                        break;
                                        case "CLOSE":
                                            respuesta = modelo.cerrar_conexion();
                                            closed = true;
                                        break;
                                        default: 
                                            respuesta = "#PARAMERROR#No se han introducido parametros#";
                                    }

                                    outPrintWriter.print(respuesta);
                                    outPrintWriter.flush(); 
                                    
                                } else {
                                    System.err.println("#PARAMERROR#No se han introducido parametros#");
                                    outPrintWriter.flush(); 
                                } 
                            } while(!closed);
                                socketServicio.close();
                        } else {
                            outPrintWriter.print("#AUTH#ERROR#Usuario o contraseña incorrectos#");
                            outPrintWriter.flush();
                        }
                    } else {
                        outPrintWriter.print("#AUTH#ERROR#Numero de argumentos incorrecto#");
                        outPrintWriter.flush();
                    }
                } else {
                    outPrintWriter.print("#AUTH#ERROR#Usted tiene que identificarse en el servidor#");
                    outPrintWriter.flush();
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