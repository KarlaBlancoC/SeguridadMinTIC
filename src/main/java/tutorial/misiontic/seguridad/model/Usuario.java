package tutorial.misiontic.seguridad.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Usuario {

    private String _id;
    private String seudonimo;
    private String correo;
    private String contrasena;


    public Usuario(String seudonimo, String correo, String contrasena)
    {
        this.seudonimo = seudonimo;
        this.correo = correo;
        this.contrasena = contrasena;
    }
}
