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
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

@ApplicationScoped
public class JAXRSImageUploader {

    // Ruta del endpoint de FastAPI. Ajusta si es necesario.
    private final String ENDPOINT_URL = "http://localhost/procesar-imagen/"; 

    /**
     * Sube múltiples archivos al endpoint de FastAPI que espera el campo 'files'.
     * Mapea la respuesta JSON a Java Records usando JSON-B.
     * * @param imageFiles Una lista de objetos File a subir.
     * @return El IMG ID de la primera imagen procesada (String) o un mensaje de error.
     */
    public String uploadImages(List<File> imageFiles) {
        
        Client client = null;
        Response response = null;
        String resultadoProcesamiento = "Error al deserializar la respuesta o lista vacía.";
        
        try {

            client = ClientBuilder.newClient()
                .register(MultiPartFeature.class); 
            
            WebTarget target = client.target(ENDPOINT_URL);

            MultiPart multiPart = new MultiPart();
            multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE); 

            for (File imageFile : imageFiles) {
                FileDataBodyPart filePart = new FileDataBodyPart("files", imageFile); 
                multiPart.bodyPart(filePart);
            }


            Entity<MultiPart> multipartEntity = Entity.entity(multiPart, multiPart.getMediaType());
            
            response = target.request(MediaType.APPLICATION_JSON)
                            .post(multipartEntity);

            // 6. Procesar la respuesta y mapearla a la clase RespuestaIA (usando JSON-B)
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                
                RespuestaIA respuesta = response.readEntity(RespuestaIA.class);  
                
                System.out.println("\t[JAXRS] Archivos subidos exitosamente.");
              //  System.out.println("\t >> RespuestaIA "+ respuesta.toString());

                // Extraemos el primer ID y mensaje para usar como resultado String 
                List<ProcesamientoImagen> resultados = respuesta.procesamientoDeImagenes();
                if (resultados != null && !resultados.isEmpty()) {
                    ProcesamientoImagen primeraImagen = resultados.get(0);
                    resultadoProcesamiento = primeraImagen.imgid();
                    
                    System.out.println("\t[JAXRS] Total de imágenes procesadas: " + resultados.size());
                    System.out.println("\t[JAXRS] ID Generado (primera imagen): " + primeraImagen.imgid());
                    System.out.println("\t[JAXRS] Mensaje: " + primeraImagen.mensaje());
                    
                    return resultadoProcesamiento; // Devuelve el ID generado
                } else {
                     return "Respuesta exitosa, pero la lista de procesamiento está vacía.";
                }
            } else {
                System.err.println("\t[JAXRS] Error al subir los archivos. Código de respuesta: " + response.getStatus());
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