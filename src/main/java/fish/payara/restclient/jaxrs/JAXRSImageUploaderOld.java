/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.restclient.jaxrs;

/**
 *
 * @author avbravo
 */
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.EntityPart;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

@ApplicationScoped
public class JAXRSImageUploaderOld {

    private final String ENDPOINT_URL = "http://localhost/procesar-imagen"; 
//    private final String ENDPOINT_URL = "http://localhost/docs#/default/procesar_imagen_procesar_imagen__post/"; 

    /**
     * Sube múltiples archivos al endpoint que espera 'files' como un array.
     * @param imageFiles Una lista de objetos File a subir.
     * @return La respuesta del servidor (JSON).
     */
    public String uploadImages(List<File> imageFiles) {
        
        Client client = null;
        Response response = null;
        
        try {
            System.out.println("\t paso 1");
            client = ClientBuilder.newClient()
            .register(MultiPartFeature.class);
            System.out.println("\t paso 2");
            WebTarget target = client.target(ENDPOINT_URL);
            System.out.println("\t paso 3");
            // 1. Crear una lista para todas las partes del formulario
            List<EntityPart> multipartParts = new ArrayList<>();
            
            // 2. Iterar sobre la lista de archivos y crear un EntityPart para cada uno
            for (File imageFile : imageFiles) {
                System.out.println("\t paso 4");
                // IMPORTANTE: El nombre del campo DEBE ser "files" para cada parte del archivo
                EntityPart filePart = EntityPart.withName("files") 
                                                .content(Files.newInputStream(imageFile.toPath(), StandardOpenOption.READ))
                                                .mediaType(MediaType.APPLICATION_OCTET_STREAM_TYPE) // O MediaType.IMAGE_JPEG_TYPE
                                                .fileName(imageFile.getName())
                                                .build();
                System.out.println("\t paso 5");
                multipartParts.add(filePart);
                System.out.println("\t paso 6");
            }

            // 3. Crear la entidad con la lista completa de EntityPart
            Entity<List<EntityPart>> multipartEntity = 
                Entity.entity(multipartParts, MediaType.MULTIPART_FORM_DATA_TYPE);
System.out.println("\t paso 7");
            // 4. Enviar la petición POST
            response = target.request(MediaType.APPLICATION_JSON)
                             .post(multipartEntity);
System.out.println("\t paso 8");
            // 5. Procesar la respuesta
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                String result = response.readEntity(String.class); 
                System.out.println("Archivos subidos exitosamente. Respuesta del servidor: " + result);
                return result;
            } else {
                System.err.println("Error al subir los archivos. Código de respuesta: " + response.getStatus());
                String errorBody = response.readEntity(String.class);
                return "ERROR: " + response.getStatus() + " - " + errorBody;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR de conexión: " + e.getMessage();
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