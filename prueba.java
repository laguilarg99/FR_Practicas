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
        try {

			socketServicio = new Socket(host,port);			
			PrintWriter outPrintWriter = new PrintWriter(socketServicio.getOutputStream(),true);
			BufferedReader inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
        
            String peticion = "#AUTH#coreo#1234#";
            
            outPrintWriter.print(peticion + "\r\n" );
			outPrintWriter.flush();
			String respuesta=new String(inReader.readLine());
            System.out.println("Recibido: ");
            System.out.println(respuesta);
            //inReader.readLine().trim();


            Boolean cerrado = false;

            while(!cerrado)
            {
                System.out.println("Intoduzca accion");
                Scanner scanner = new Scanner(System.in);
                int opcion =  scanner.nextInt();
                switch (opcion)
                {  
                    case 1:
                        peticion = "#MENTRADA#";
                        outPrintWriter.print(peticion + "\r\n" );
                        outPrintWriter.flush();
                        respuesta= inReader.readLine();
                        System.out.println("Recibido: ");
                        System.out.println(respuesta);
                    break; 
                    case 2:
                        peticion = "#MSALIDA#";
                        outPrintWriter.print(peticion + "\r\n" );
                        outPrintWriter.flush();
                        respuesta= inReader.readLine();
                        System.out.println("Recibido: ");
                        System.out.println(respuesta);
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
                        System.out.println("Recibido: ");
                        System.out.println(respuesta);
                        socketServicio.close();
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