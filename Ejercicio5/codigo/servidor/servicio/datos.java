package servicio;
import servicio.usuario;
import java.util.ArrayList;

public class datos{
    private ArrayList<usuario> usuarios = new ArrayList<usuario>();
    //usuarios.add(new usuario("usuario1@prueba.net","1234pass"));
    
    public datos(){
        usuarios.add(new usuario("usuario1@prueba.net","1234pass"));
        usuarios.get(0).setCorreo("correo1", "usuario2@prueba.net", "usuario1@prueba.net", "Esto es una prueba.");
        usuarios.get(0).setCorreo("correo2", "usuario3@prueba.net", "usuario1@prueba.net", "Esto es el segundo correo.");
        usuarios.get(0).setCorreo("correo3", "usuario1@prueba.net", "usuario2@prueba.net", "Esto es el tercero correo.");
        usuarios.get(0).setCorreo("correo4", "usuario1@prueba.net", "usuario3@prueba.net", "Esto es el cuarto correo.");

        usuarios.add(new usuario("usuario2@prueba.net","1234pass"));
        usuarios.get(1).setCorreo("correo1", "usuario2@prueba.net", "usuario1@prueba.net", "Esto es una prueba.");
        usuarios.get(1).setCorreo("correo2", "usuario2@prueba.net", "usuario3@prueba.net", "Esto es el segundo correo.");
        usuarios.get(1).setCorreo("correo3", "usuario1@prueba.net", "usuario2@prueba.net", "Esto es el tercero correo.");
        usuarios.get(1).setCorreo("correo4", "usuario3@prueba.net", "usuario2@prueba.net", "Esto es el cuarto correo.");

        usuarios.add(new usuario("usuario3@prueba.net","1234pass"));
        usuarios.get(2).setCorreo("correo1", "usuario3@prueba.net", "usuario1@prueba.net", "Esto es una prueba.");
        usuarios.get(2).setCorreo("correo2", "usuario3@prueba.net", "usuario2@prueba.net", "Esto es el segundo correo.");
        usuarios.get(2).setCorreo("correo3", "usuario1@prueba.net", "usuario3@prueba.net", "Esto es el tercero correo.");
        usuarios.get(2).setCorreo("correo4", "usuario2@prueba.net", "usuario3@prueba.net", "Esto es el cuarto correo.");
    }

    public int getIndex(String usuario){
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getNombre().equals(usuario)){
                return i;
            }
        }
        return -1;
    }

    public Boolean autenticar(int index, String contraseña){
        return usuarios.get(index).comprobarContraseña(contraseña);
    }

    public String correosSalida(int index){
        String salida = "#";
        for(int i = 0; i < usuarios.get(index).getCorreos().size(); i++)
        {
            if(usuarios.get(index).getCorreos().get(i).getRemitente().equals(usuarios.get(index).getNombre()))
            {
                salida += i + "#" + usuarios.get(index).getCorreos().get(i).getVistaPrevia();
            }
        } 
        return salida;   
    }

    public String correosEntrada(int index){
        String salida = "#";
        for(int i = 0; i < usuarios.get(index).getCorreos().size(); i++)
        {
            if(usuarios.get(index).getCorreos().get(i).getReceptor().equals(usuarios.get(index).getNombre()))
            {
                salida += i + "#" + usuarios.get(index).getCorreos().get(i).getVistaPrevia();
            }
        } 
        return salida;   
    }

    public String obtenerCorreo(int correo, int usuario){
        String salida = "";
        if(usuarios.get(usuario).getCorreos().size() > correo){
            salida += usuarios.get(usuario).getCorreos().get(correo).getCorreoCompleto();
        }
        return salida;
    }
}