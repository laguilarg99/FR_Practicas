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
	
    public controlador(Socket socketServicio) 
    {
		this.socketServicio=socketServicio;
	}
	
    public void run()
    {		
		try {
			PrintWriter outPrintWriter = new PrintWriter(socketServicio.getOutputStream(),true);
			BufferedReader inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));

            if((peticion = inReader.readLine()) != null){
                String[] Argumento = obtenerArgumentos(peticion);

                switch(Argumento[0]) {
                    case "AUTH":
                      // code block
                      break;
                    case "MEntra":
                      // code block
                      break;
                    default:
                      // code block
                  }
            } else {
			    System.err.println("No se han recibido datos.");
            }
            
            /*
            outPrintWriter.print(respuesta + "\r\n" );
			outPrintWriter.flush();
			*/
        } catch (IOException e)
            {
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