package servicio;
import servicio.correo;
import java.util.ArrayList;

public class usuario
{
    private String nombre;
    private String contraseña;
    private ArrayList<correo> correos = new ArrayList<correo>();

    public usuario(String nombre, String contraseña){
        this.nombre  = nombre;
        this.contraseña = contraseña;
    }

    public String getNombre(){
        return nombre;
    }

    public Boolean comprobarContraseña(String contraseña){
        return this.contraseña.equals(contraseña);
    }

    public void setUsuario(String nombre, String contraseña){
        this.nombre  = nombre;
        this.contraseña = contraseña;
    }

    public void setCorreo(String asunto, String remitente, String receptor, String cuerpo){
        correos.add(new correo(asunto, remitente, receptor, cuerpo));
    }

    public ArrayList<correo> getCorreos(){
        return correos;
    }
}