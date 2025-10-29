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
import java.io.File;
import java.util.List;

// Importaciones específicas de Jersey para MultiPart
import org.glassfish.jersey.media.multipart.FormDataBodyPart; 
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart; // Útil para adjuntar un File

@ApplicationScoped
public class JAXRSImageUploader {

    // Asegúrate de que la URL termine en "/" si el endpoint de FastAPI la requiere,
    // o ajústala si es necesario. Basado en tu OpenAPI, la ruta es "/procesar-imagen/".
    // El cURL usa 'http://localhost/procesar-imagen/'
    private final String ENDPOINT_URL = "http://localhost/procesar-imagen/"; 

    /**
     * Sube múltiples archivos al endpoint que espera 'files' como un array.
     * @param imageFiles Una lista de objetos File a subir.
     * @return La respuesta del servidor (JSON).
     */
    public String uploadImages(List<File> imageFiles) {
        
        Client client = null;
        Response response = null;
        
        try {
            System.out.println("\t[JAXRS] Paso 1: Inicializando Cliente JAX-RS");
            // Se registra MultiPartFeature para soportar el content-type multipart/form-data
            client = ClientBuilder.newClient()
                .register(MultiPartFeature.class); 
            
            WebTarget target = client.target(ENDPOINT_URL);
            
            // ** 1. Usar MultiPart de Jersey en lugar de List<EntityPart> **
            MultiPart multiPart = new MultiPart();
            multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

            System.out.println("\t[JAXRS] Paso 2: Creando partes del formulario");
            
            // 2. Iterar sobre la lista de archivos
            for (File imageFile : imageFiles) {
                // Usamos FileDataBodyPart, que es más conveniente para enviar un File
                // IMPORTANTE: El nombre del campo DEBE ser "files" para que FastAPI lo reconozca
                FileDataBodyPart filePart = new FileDataBodyPart("files", imageFile); 
                
                // Opcional: Establecer el tipo de medio específico para el archivo
                // filePart.setMediaType(MediaType.valueOf("image/jpeg")); 

                multiPart.bodyPart(filePart);
                System.out.println("\t[JAXRS] Archivo adjunto: " + imageFile.getName());
            }

            // 3. Enviar la entidad MultiPart
            Entity<MultiPart> multipartEntity = Entity.entity(multiPart, multiPart.getMediaType());
            
            System.out.println("\t[JAXRS] Paso 3: Enviando POST al microservicio FastAPI");

            // 4. Enviar la petición POST
            response = target.request(MediaType.APPLICATION_JSON)
                            .post(multipartEntity);

            // 5. Procesar la respuesta
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                String result = response.readEntity(String.class);  
                System.out.println("\t[JAXRS] Archivos subidos exitosamente. Respuesta del servidor: " + result);
                return result;
            } else {
                System.err.println("\t[JAXRS] Error al subir los archivos. Código de respuesta: " + response.getStatus());
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