/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.restclient;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import java.io.File;
import java.io.InputStream;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author josecollado
 */

@RegisterRestClient()
@Path("/")
public interface FileUploadClient {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("procesar-imagen")
    RespuestaProcesamiento uploadImage(
            @FormParam("files") File fileToUpload
    );

    @GET
    @Path("ver-contour/{photo_id}")
    // @Produces (opcional): No siempre es necesario, pero podría ser MediaType.APPLICATION_OCTET_STREAM
    InputStream downloadImage(@PathParam("id") String id);
}
