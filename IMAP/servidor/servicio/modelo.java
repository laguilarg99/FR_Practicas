package servicio;
import servicio.datos;

public class modelo {
    private static datos Datos = new datos();
    
    public static int autenticar(String correo, String contraseña)
    {
        int index;
        if((index = Datos.getIndex(correo)) != -1){
            if(Datos.autenticar(index, contraseña)){
                return index;
            }
        }
        //Datos.usuarios.indexOf(nombre)
        //Datos.usuarios.get(index)
        return index; //autenticará al usuario y devolverá un true si se hace de forma correcta
    }

    public static String Leer_correo(int id_correo, int usuario)
    {
        return "#LEERCORREO#titulocorreo1#remitente#asunto#texto#";
    }

    public static String Mostrar_Bandeja_Entrada(int usuario)
    {
        return "#MENTRADA#idcorreo1#Estado lectura #titulocorreo1#...#idcorreon#titulocorreon#";
    }

    public static String Mostrar_Bandeja_Salida(int usuario)
    {
        return "#MSALIDA" + Datos.correosSalida(usuario);
    }

    public static String cerrar_conexion()
    {
        return "#CLOSE#";
    }
}
