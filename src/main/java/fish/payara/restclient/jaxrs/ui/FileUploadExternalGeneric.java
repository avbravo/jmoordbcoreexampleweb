/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.restclient.jaxrs.ui;

/**
 *
 * @author avbravo
 */

import fish.payara.restclient.jaxrs.ui.ResultExtractor;
import fish.payara.restclient.jaxrs.ui.UploadResult;
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

// Usamos <T, R> para tipificar los records que se esperan.
@Named
@ApplicationScoped
public class FileUploadExternalGeneric {

   @Inject
    ConfigurationProperties configurationProperties;

    // Se ha hecho genérico y se recibe la clase de la respuesta esperada
    // Se ha modificado el valor de retorno para mayor flexibilidad
    public <T, R> UploadResult<T> uploadImages(
            List<File> imageFiles,
            Class<R> responseClass,
            ResultExtractor<R, T> extractor, String urlExternal) {

        Client client = null;
        Response response = null;
        UploadResult<T> uploadResult = new UploadResult<>("Error general", null, false);

        try {
            // 1. Registrar MultiPartFeature en el cliente
            client = ClientBuilder.newClient()
                    .register(MultiPartFeature.class);
            WebTarget target = client.target(urlExternal);

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

            // 6. Procesar la respuesta
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                // Deserializa el JSON a la clase genérica R.
                R apiResponse = response.readEntity(responseClass);

                // Usa el extractor para obtener el resultado tipado (T) y el mensaje.
                return extractor.extract(apiResponse);
                
            } else {
                String errorBody = response.readEntity(String.class);
                return new UploadResult<>("ERROR: " + response.getStatus() + " - " + errorBody, null, false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new UploadResult<>("ERROR de conexión o deserialización: " + e.getMessage(), null, false);
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