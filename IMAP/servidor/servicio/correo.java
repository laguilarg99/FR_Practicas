package servicio;
public class correo{
    private String asunto;
    private String remitente;
    private String receptor;
    private String cuerpo;

    public correo(String asunto, String remitente, String receptor, String cuerpo){
        this.asunto = asunto;
        this.remitente = remitente;
        this.receptor = receptor;
        this.cuerpo = cuerpo;
    }

    public String getVistaPrevia(){
        return asunto+"#"+remitente+"#"+receptor+"#";
    }

    public String getAsunto(){
        return asunto;
    }

    public String getRemitente(){
        return remitente;
    }

    public String getReceptor(){
        return receptor;
    }

    public String getCuerpo(){
        return cuerpo;
    }

    public String getCorreo(){
        return asunto+"#"+remitente+"#"+receptor+"#"+cuerpo+"#";
    }

    public void setCorreo(String asunto, String remitente, String receptor, String cuerpo){
        this.asunto = asunto;
        this.remitente = remitente;
        this.receptor = receptor;
        this.cuerpo = cuerpo;
    }
}