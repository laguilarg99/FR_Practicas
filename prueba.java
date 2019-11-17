import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class prueba{
	public static void main(String[] args) {

        String host="localhost";
        int port=5756;
        Socket socketServicio = null;
        Boolean cerrado = true;

        try {

			socketServicio = new Socket(host,port);			
			PrintWriter outPrintWriter = new PrintWriter(socketServicio.getOutputStream(),true);
			BufferedReader inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
            
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nIntroduzca Usuario: ");
            String User =  scanner.nextLine();
            
            System.out.println("Introduzca Contraseña: ");
            String Password =  scanner.nextLine();

            String peticion = "#AUTH#" + User + "#" + Password + "#";
            //String peticion = "#AUTH#usuario1@prueba.net#1234pass#";

            outPrintWriter.print(peticion + "\r\n" );
			outPrintWriter.flush();
			String respuesta=new String(inReader.readLine());
            String[] Autenticacion = obtenerArgumentos(respuesta);

            if(Autenticacion[1].equals("OK")){
                System.out.println("\nDatos correctos\nIniciando sesion...\nSesion iniciada\n");
                cerrado = false;
            }
            else{
                System.out.println("Datos incorrectos, sesion no iniciada\n");
            }

            while(!cerrado)
            {
                System.out.println("\n\t1. Visualizar bandeja de entrada\n\n\t2. Visualizar bandeja de salida\n\n\t3. Leer correo (introduzca posicion en la lista)\n\n\t4. Cerrar sesion\n\nIntroduzca la acción a realizar:");
                int opcion =  scanner.nextInt();
                switch (opcion)
                {  
                    case 1:
                        peticion = "#MENTRADA#";
                        outPrintWriter.print(peticion + "\r\n" );
                        outPrintWriter.flush();
                        respuesta= inReader.readLine();
                        String[] BandejaEntrada = obtenerArgumentos(respuesta);
                        System.out.println("\nIndice\tAsunto\tRemitente\n");
                        for(int i = 1; i < BandejaEntrada.length; i+=4){
                            System.out.println(BandejaEntrada[i]+"\t"+BandejaEntrada[i+1]+"\t"+BandejaEntrada[i+2]+"\n");
                        }
                    break; 
                    case 2:
                        peticion = "#MSALIDA#";
                        outPrintWriter.print(peticion + "\r\n" );
                        outPrintWriter.flush();
                        respuesta= inReader.readLine();
                        String[] BandejaSalida = obtenerArgumentos(respuesta);
                        System.out.println("\nIndice\tAsunto\tReceptor\n");
                        for(int i = 1; i < BandejaSalida.length; i+=4){
                            System.out.println(BandejaSalida[i]+"\t"+BandejaSalida[i+1]+"\t"+BandejaSalida[i+3]+"\n");
                        }
                    break; 
                    case 3:
                        peticion = "#LEERCORREO#1#";
                        outPrintWriter.print(peticion + "\r\n" );
                        outPrintWriter.flush();
                        respuesta= inReader.readLine();
                        System.out.println("Recibido: ");
                        System.out.println(respuesta);
                    break; 
                    default:   
                        peticion = "#CLOSE#";
                        outPrintWriter.print(peticion + "\r\n" );
                        outPrintWriter.flush();
                        respuesta= inReader.readLine();
                        //inReader.readLine().trim();
                        System.out.println("\nSESION FINALIZADA\n");
                        socketServicio.close();
                        scanner.close();
                        cerrado = true;
                }  
        }
		} catch (UnknownHostException e) {
            System.err.println("Error: Nombre de host no encontrado.");
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al abrir el socket.");
        }
	}

    private static String[] obtenerArgumentos(String Cadena)
    {
        Cadena = (Cadena.substring(0,Cadena.length()-1)).substring(1);
        String[] args = Cadena.split("#");
        return args;
    }
}