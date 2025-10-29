/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.restclient.image;

/**
 *
 * @author avbravo
 */
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.EntityPart;
import jakarta.ws.rs.core.MediaType;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// Esta clase mapea los campos del formulario multipart
public class ImageUploadForm {
    
    // Campo del archivo (EntityPart es la forma moderna en Jakarta EE 10+)
    @FormParam("file") // <-- Asegúrate de que el nombre del FormParam coincida con lo esperado por el servidor
    public EntityPart filePart;
    
    // (Opcional) Si el servidor espera otros campos (e.g., "model_name", "user_id"), 
    // debes añadirlos aquí con @FormParam:
    // @FormParam("campo_adicional") 
    // public String campoAdicional;

    // Constructor de ayuda para simplificar la creación del objeto
    public ImageUploadForm(File imageFile) throws Exception {
        // Creación del EntityPart
        this.filePart = EntityPart.withName("files") // Debe coincidir con @FormParam y el nombre del campo en el servidor
                                  .content(Files.newInputStream(imageFile.toPath(), StandardOpenOption.READ))
                                  .mediaType(MediaType.APPLICATION_OCTET_STREAM_TYPE) // Tipo MIME binario genérico
                                  .fileName(imageFile.getName())
                                  .build();
    }
}