package servicio;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;

public class modelo {
    public static Boolean autenticar(String correo, String contraseña)
    {
        Boolean correcto = true;
        return correcto; //autenticará al usuario y devolverá un true si se hace de forma correcta
    }

    public static String Leer_correo(int id_correo, String usuario)
    {
        return "#LEERCORREO#titulocorreo1#remitente#asunto#texto#";
    }

    public static String Mostrar_Bandeja_Entrada(String usuario)
    {
        return "#MENTRADA#idcorreo1#Estado lectura #titulocorreo1#...#idcorreon#titulocorreon#";
    }

    public static String Mostrar_Bandeja_Salida(String usuario)
    {
        return "#MSALIDA#idcorreo1#titulocorreo1#...#idcorreon#titulocorreon#";
    }

    public static String cerrar_conexion()
    {
        return "#CLOSE#";
    }
}
