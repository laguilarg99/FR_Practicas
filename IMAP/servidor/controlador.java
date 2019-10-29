package servicio;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class controlador implements Runnable
{
	private Socket socketServicio;
	
	public controlador(Socket socketServicio) {
		this.socketServicio=socketServicio;
	}
	
	public void run(){		
		try {
			
			PrintWriter outPrintWriter = new PrintWriter(socketServicio.getOutputStream(),true);
			BufferedReader inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));

            String peticion = new String(inReader.readLine());
			String respuesta=yodaDo(peticion);
			outPrintWriter.print(respuesta + "\r\n" );
			outPrintWriter.flush();
			
		} catch (IOException e) {
			System.err.println("Error al obtener los flujso de entrada/salida.");
		}

	}
}