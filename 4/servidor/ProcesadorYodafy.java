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
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.util.Random;


//
// Nota: si esta clase extendiera la clase Thread, y el procesamiento lo hiciera el método "run()",
// ¡Podríamos realizar un procesado concurrente! 
//
public class ProcesadorYodafy {
	// Referencia a un socket para enviar/recibir las peticiones/respuestas
	private DatagramSocket socketServicio;
	// Para que la respuesta sea siempre diferente, usamos un generador de números aleatorios.
	private Random random;
	
	// Constructor que tiene como parámetro una referencia al socket abierto en por otra clase
	public ProcesadorYodafy(DatagramSocket socketServicio) {
		this.socketServicio=socketServicio;
		random=new Random();
	}
	
	
	// Aquí es donde se realiza el procesamiento realmente:
	public void procesar(){
		
		
		try {
			byte [] datosRecibidos=new byte[1024];
			byte [] datosEnviar;

			DatagramPacket paquete;
			paquete = new DatagramPacket(datosRecibidos,datosRecibidos.length);
			socketServicio.receive(paquete);
			String recibido = new String(paquete.getData());
			datosEnviar = yodaDo(recibido).getBytes();
			DatagramPacket paqueteEnviar;
			paqueteEnviar = new DatagramPacket(datosEnviar, datosEnviar.length, paquete.getAddress(), paquete.getPort());
			socketServicio.send(paqueteEnviar);
		} catch (IOException e) {
			System.err.println("Error al obtener los flujo de entrada/salida.");
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
