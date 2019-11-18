//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
package Procesador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;


//
// Nota: si esta clase extendiera la clase Thread, y el procesamiento lo hiciera el método "run()",
// ¡Podríamos realizar un procesado concurrente! 
//
public class ProcesadorYodafy implements Runnable{
	// Referencia a un socket para enviar/recibir las peticiones/respuestas
	private Socket socketServicio;
	// Para que la respuesta sea siempre diferente, usamos un generador de números aleatorios.
	private Random random;
	
	// Constructor que tiene como parámetro una referencia al socket abierto en por otra clase
	public ProcesadorYodafy(Socket socketServicio) {
		this.socketServicio=socketServicio;
		random=new Random();
	}
	
	
	// Aquí es donde se realiza el procesamiento realmente:
	public void run(){
		
		
		try {
			
			PrintWriter outPrintWriter = new PrintWriter(socketServicio.getOutputStream(),true);
			BufferedReader inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));

			// Lee la frase a Yodaficar:

			////////////////////////////////////////////////////////
			// read ... datosRecibidos.. (Completar)
			////////////////////////////////////////////////////////
			//bytesRecibidos = inputStream.read(datosRecibidos);
			
			// Yoda hace su magia:
			// Creamos un String a partir de un array de bytes de tamaño "bytesRecibidos":
			//String peticion=new String(datosRecibidos);
			// Yoda reinterpreta el mensaje:
			//String respuesta=yodaDo(peticion);
			//System.out.println(peticion);
			//System.out.println(respuesta);
			//System.out.println(respuesta);
			// Convertimos el String de respuesta en una array de bytes:

			//datosEnviar=respuesta.getBytes();
			String peticion = new String(inReader.readLine());
			String respuesta=yodaDo(peticion);
			outPrintWriter.print(respuesta + "\r\n" );
			outPrintWriter.flush();
			//outputStream.write(datosEnviar,0,datosEnviar.length);
			//outputStream.flush();
			
			// Enviamos la traducción de Yoda:
			////////////////////////////////////////////////////////
			// ... write ... datosEnviar... datosEnviar.length ... (Completar)
			////////////////////////////////////////////////////////
			
			
			
		} catch (IOException e) {
			System.err.println("Error al obtener los flujso de entrada/salida.");
		}

	}

	// Yoda interpreta una frase y la devuelve en su "dialecto":
	private String yodaDo(String peticion) {
		// Desordenamos las palabras:
		String[] s = peticion.split(" ");
		String resultado="";
		
		for(int i=0;i<s.length;i++){
			int j=random.nextInt(s.length);
			int k=random.nextInt(s.length);
			String tmp=s[j];
			
			s[j]=s[k];
			s[k]=tmp;
		}
		
		resultado=s[0];
		for(int i=1;i<s.length;i++){
		  resultado+=" "+s[i];
		}
		
		return resultado;
	}
}
