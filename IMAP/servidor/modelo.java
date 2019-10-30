package servicio;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class modelo {
    public static Boolean autenticar(String correo, String contraseña)
    {
        Boolean correcto = false;
        return correcto;
    }

    public static String Leer_correo(int id_correo)
    {
        return "";
    }

    public static String Mostrar_Bandeja_Entrada(int Numero_Correos)
    {
        return "";
    }

    public static String Mostrar_Bandeja_Salida(int Numero_Correos)
    {
        return "";
    }

    public static String cerrar_conexion()
    {
        return "#CLOSE#";
    }
}
