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

    }

    public int getIndex(String usuario){
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getNombre().equals(usuario)){
                System.out.println(usuarios.get(i).getNombre());
                return i;
            }
        }
        System.out.println(usuarios.get(0).getNombre());
        return -1;
    }

    public Boolean autenticar(int index, String contraseña){
        return usuarios.get(index).comprobarContraseña(contraseña);
    }

    public String correosSalida(int index){
        String salida = "#";
        System.out.println(usuarios.get(index).getNombre());
        for(int i = 0; i < usuarios.get(index).getCorreos().size(); i++)
        {
            if(usuarios.get(index).getCorreos().get(i).getRemitente().equals(usuarios.get(index).getNombre()))
            {
                System.out.println("\t"+usuarios.get(index).getCorreos().get(index).getVistaPrevia());
                salida += i + "#" + usuarios.get(index).getCorreos().get(index).getVistaPrevia();
            }
        } 
        return salida;   
    }

}