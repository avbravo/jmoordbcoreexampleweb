/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.restclient.image;

/**
 *
 * @author avbravo
 */
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

// 1. Define la clave de configuración y la ruta base del recurso
@RegisterRestClient(configKey="imagen-processor-api")
@Path("/procesar-imagen/") // <-- ¡El path del endpoint es crucial!
public interface ImagenProcessorClient {

    /**
     * Envía los datos multipart/form-data al endpoint.
     * * @param formData El objeto que encapsula la imagen y otros campos del formulario.
     * @return Asumiendo que el servidor devuelve un objeto JSON, puedes usar String, 
     * o un DTO (Data Transfer Object) si conoces la estructura exacta del JSON.
     */
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    String procesarImagen(ImageUploadForm formData);
}