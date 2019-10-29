//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class IMAPCliente{

	public static void main(String[] args) {
		
		
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
        int port=5756;
		
		// Socket para la conexión TCP
		Socket socketServicio=null;
		
		try {

			// Creamos un socket que se conecte a "hist" y "port":
			/////////////////////////////////////////////////////
			socketServicio = new Socket(host,port);
			//////////////////////////////////////////////////////			
            
            //Creamos los objetos para compartir información:
            /////////////////////////////////////////////////////
            PrintWriter outPrintWriter = new PrintWriter(socketServicio.getOutputStream(),true);
			BufferedReader inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
            ////////////////////////////////////////////////////////
            //Intentos
            int intentos = 0;

            //Controlador de opciones
            String controlador = null;
            
            //Iniciando conexión con autenticación
            System.out.println("Iniciando conexion con autenticación...\n");
            
            //CONEXIÓN
            do{
                // Si queremos enviar una cadena de caracteres por un OutputStream, hay que pasarla primero
                // a un array de bytes:
                System.out.println("Usuario: ");
                String Usuario =  System.console().readLine();
                System.out.println("Contraseña: ");
                String password =  System.console().readLine();
                String Envio = "#AUTM#" + Usuario + "#" + password + "#";
                
                // Enviamos el array por el outprinterwriter;
                //////////////////////////////////////////////////////
                // ... .write ... (Completar)
                outPrintWriter.print(Envio + "\r\n" );
                //////////////////////////////////////////////////////
                
                // Aunque le indiquemos a TCP que queremos enviar varios arrays de bytes, sólo
                // los enviará efectivamente cuando considere que tiene suficientes datos que enviar...
                // Podemos usar "flush()" para obligar a TCP a que no espere para hacer el envío:
                //////////////////////////////////////////////////////
                outPrintWriter.flush();
                //////////////////////////////////////////////////////

                String respuesta=new String(inReader.readLine());
                // Mostramos el inicio de sesión correcto o cerramos la sesión:
                if(respuesta == "#AUTM#OK#"){
                    System.out.println("Bienvenido al servicio de correo\n");
                    controlador = "IN";
                }
                else{
                    //CERRAR CONEXION
                    System.out.println("Credenciales erróneas\n");
                    intentos++;
                    if(intentos == 2){
                        System.out.println("Queda UN solo intento\n");
                    }                   
                    else if (intentos == 3){
                        System.out.println("Intentos permitidos agotados\n");
                        // Una vez terminado el servicio, cerramos el socket (automáticamente se cierran
                        // el inpuStream  y el outputStream)
                        //////////////////////////////////////////////////////
                        socketServicio.close();
                        controlador = "CLOSE";
                    }
                }
                
            }while(controlador != "CLOSE" || controlador != "IN");

             //MENU DE OPCIONES
             do{

                // Si queremos enviar una cadena de caracteres por un OutputStream, hay que pasarla primero
                // a un array de bytes:
                String numeroMensajes = "0";
                System.out.println("Que desea hacer:\n\t Leer correo: LC \n\t Mostrar Bandeja Entrada: BE \n\t Mostrar Bandeja Salida: BS \n\t Cerrar conexión: CC \n\t Introduzca Opción: ");
                String Opcion =  System.console().readLine();

                switch (Opcion) {
                    case "LC":
                        String numeroCorreo;
                        System.out.println("Introduzca el numero de correo que desea visualizar:\n");
                        numeroCorreo = System.console().readLine();
                        if((int)numeroCorreo > (int)numeroMensajes){
                            System.out.println("Correo no existente\n");
                            break;
                        }
                        else if((int)numeroCorreo < 0){
                            System.out.println("Correo no existente\n");
                            break;
                        }
                        else
                            Envio = "#" + Opcion + "#" + numeroCorreo + "#";

                        // Enviamos el array por el outprinterwriter;
                        //////////////////////////////////////////////////////
                        // ... .write ... (Completar)
                        outPrintWriter.print(Envio + "\r\n" );
                        //////////////////////////////////////////////////////  
                
                        // Aunque le indiquemos a TCP que queremos enviar varios arrays de bytes, sólo
                        // los enviará efectivamente cuando considere que tiene suficientes datos que enviar...
                        // Podemos usar "flush()" para obligar a TCP a que no espere para hacer el envío:
                        //////////////////////////////////////////////////////
                        outPrintWriter.flush();
                        //////////////////////////////////////////////////////

                        respuesta=new String(inReader.readLine());
                        System.out.println(respuesta);

                        break;
                    
                    case "BE":
                        System.out.println("Mostrando Bandeja de entrada...\n");
                        Envio = "#MENTRADA#";
                        // Enviamos el array por el outprinterwriter;
                        //////////////////////////////////////////////////////
                        // ... .write ... (Completar)
                        outPrintWriter.print(Envio + "\r\n" );
                        //////////////////////////////////////////////////////  
                
                        // Aunque le indiquemos a TCP que queremos enviar varios arrays de bytes, sólo
                        // los enviará efectivamente cuando considere que tiene suficientes datos que enviar...
                        // Podemos usar "flush()" para obligar a TCP a que no espere para hacer el envío:
                        //////////////////////////////////////////////////////
                        outPrintWriter.flush();
                        //////////////////////////////////////////////////////

                        respuesta=new String(inReader.readLine());
                        System.out.println(respuesta);


                        break;

                    case "BS":
                        System.out.println("Mostrando Bandeja de salida...\n");
                        String Envio = "#MSALIDA#";

                        // Enviamos el array por el outprinterwriter;
                        //////////////////////////////////////////////////////
                        // ... .write ... (Completar)
                        outPrintWriter.print(Envio + "\r\n" );
                        //////////////////////////////////////////////////////  
                
                        // Aunque le indiquemos a TCP que queremos enviar varios arrays de bytes, sólo
                        // los enviará efectivamente cuando considere que tiene suficientes datos que enviar...
                        // Podemos usar "flush()" para obligar a TCP a que no espere para hacer el envío:
                        //////////////////////////////////////////////////////
                        outPrintWriter.flush();
                        //////////////////////////////////////////////////////

                        respuesta=new String(inReader.readLine());
                        System.out.println(respuesta);

                        break;

                    case "CC":
                        System.out.println("Conexión finalizada\n");
                        socketServicio.close();
                        controlador = "CLOSE";
                        break;

                    default:
                        System.out.println("No se identifica la opción introducida\n");
                        break;
                }
    
                
            }while(controlador == "IN");

			//////////////////////////////////////////////////////
			
			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
