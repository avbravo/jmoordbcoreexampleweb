/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.restclient.jaxrs;

/**
 *
 * @author avbravo
 */
import fish.payara.config.ConfigurationProperties;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.File;
import java.util.List;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

@Named
@ApplicationScoped
public class FileUploaderExternalIA {

    @Inject
    ConfigurationProperties configurationProperties;

    public String uploadImages(List<File> imageFiles) {

        Client client = null;
        Response response = null;
        String resultadoProcesamiento = "Error al deserializar la respuesta o lista vacía.";

        try {
            // 1. Registrar MultiPartFeature en el cliente
            client = ClientBuilder.newClient()
                    .register(MultiPartFeature.class);
            WebTarget target = client.target(configurationProperties.getIaUrlProcesarimagen());

            // 2. Usar MultiPart de Jersey para crear la entidad de la petición
            MultiPart multiPart = new MultiPart();
            multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

            // 3. Iterar sobre la lista de archivos y crear una parte para cada uno
            for (File imageFile : imageFiles) {
                // Usamos "files" para coincidir con el campo de FastAPI
                FileDataBodyPart filePart = new FileDataBodyPart("files", imageFile);
                multiPart.bodyPart(filePart);

            }

            // 4. Crear la entidad de la petición
            Entity<MultiPart> multipartEntity = Entity.entity(multiPart, multiPart.getMediaType());

            // 5. Enviar la petición POST
            response = target.request(MediaType.APPLICATION_JSON)
                    .post(multipartEntity);

            // 6. Procesar la respuesta y mapearla a la clase RespuestaIA (usando JSON-B)
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {

                // Deserializa el JSON a nuestro Record RespuestaIA.
                FileUploadResponseIA respuesta = response.readEntity(FileUploadResponseIA.class);

                // Extraemos el primer ID y mensaje para usar como resultado String 
                List<FileUploadIAJsonProperty> resultados = respuesta.fileUploadIAResult();
                if (resultados != null && !resultados.isEmpty()) {
                    FileUploadIAJsonProperty primeraImagen = resultados.get(0);
                    resultadoProcesamiento = primeraImagen.imgid();

                    return resultadoProcesamiento; // Devuelve el ID generado
                } else {
                    return "Respuesta exitosa, pero la lista de procesamiento está vacía.";
                }
            } else {

                String errorBody = response.readEntity(String.class);
                return "ERROR: " + response.getStatus() + " - " + errorBody;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR de conexión o deserialización: " + e.getMessage();
        } finally {
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
        }
    }
}
