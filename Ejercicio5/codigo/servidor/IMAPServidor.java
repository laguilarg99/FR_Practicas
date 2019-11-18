import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket; 
import servicio.controlador;

public class IMAPServidor extends Thread {
	public static void main(String[] args) {
	
		int port=5756;
		byte []buffer=new byte[256];
		int bytesLeidos=0;
		ServerSocket socketServidor = null;
		Socket socketServicio = null;

		try {

			socketServidor = new ServerSocket(port);

			do {
				
			    socketServicio= socketServidor.accept();
				new Thread(new controlador(socketServicio)).start();;
				
			} while (true);
			
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}