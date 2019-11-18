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
import java.net.DatagramPacket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.UnknownHostException;

public class YodafyClienteTCP {

	public static void main(String[] args) {
		
		byte []buferEnvio;
		byte []buferRecepcion=new byte[1024];
		int bytesLeidos=0;
		
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
		
		// Socket para la conexión TCP
		DatagramSocket socketServicio=null;
		DatagramPacket paquete;
		
		try {
			// Creamos un socket que se conecte a "hist" y "port":
			/////////////////////////////////////////////////////
			InetAddress direccion = InetAddress.getByName(host);
			socketServicio = new DatagramSocket();
			//////////////////////////////////////////////////////
			
			// Si queremos enviar una cadena de caracteres por un OutputStream, hay que pasarla primero
			// a un array de bytes:
			buferEnvio="Al monte del volcán debes ir sin demora".getBytes();

			paquete = new DatagramPacket(buferEnvio, buferEnvio.length, direccion, port);
			socketServicio.send(paquete);


			paquete = new DatagramPacket(buferRecepcion, buferRecepcion.length);
			socketServicio.receive(paquete);
			//System.out.println(paquete.getData());
			//socketServicio.close();
			//bytesLeidos= inputStream.read(buferRecepcion);
			// bytesLeidos ... .read... buferRecepcion ; (Completar)
			//////////////////////////////////////////////////////
			String respuesta=new String(paquete.getData());
			//System.out.println(new String(bufferRecepcion));
			// MOstremos la cadena de caracteres recibidos:
			System.out.println("Recibido: ");
			System.out.println(respuesta);
			socketServicio.close();
			
			
			// Una vez terminado el servicio, cerramos el socket (automáticamente se cierran
			// el inpuStream  y el outputStream)
			//////////////////////////////////////////////////////
			// ... close(); (Completar)
			//socketServicio.close();
			//////////////////////////////////////////////////////
			
			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
