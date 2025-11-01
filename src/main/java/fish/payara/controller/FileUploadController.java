/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.controller;

/**
 *
 * @author avbravo
 */
import com.jmoordb.core.ui.fileupload.FileStorage;
import fish.payara.config.ConfigurationProperties;
import com.jmoordb.core.ui.fileupload.FileUploadRequest;
import com.jmoordb.core.ui.fileupload.FileUploadResponse;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Set;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("filesuploadsimple")
@RequestScoped

public class FileUploadController {
@Inject
    ConfigurationProperties configurationProperties;
    @Inject
    private FileStorage fileStorage;

    @POST
    @Path("/upload1")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Sube una imagen, la renombra y devuelve el ID generado")
    @APIResponse(responseCode = "200", description = "ID del archivo generado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FileUploadResponse.class)))
    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = FileUploadRequest.class)))
    public Response upload1(
            // El nombre 'file' debe coincidir con el campo en el formulario/OpenAPI
            @FormDataParam("fileUpload1") InputStream uploadedInputStream,
            @FormDataParam("fileUpload1") FormDataContentDisposition fileDetail) {

        try {
            java.nio.file.Path uploadPath = Paths.get(System.getProperty("user.home"), configurationProperties.getImageDirectory());
            FileUploadResponse response = new FileUploadResponse(fileStorage.saveAndRenameImage(
                    uploadedInputStream,
                    fileDetail.getFileName(),
                    uploadPath
            ), fileDetail.getFileName());

            return Response.ok(response).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar el archivo: " + e.getMessage())
                    .build();
        }
    }

    @OPTIONS
    @Path("/upload")
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleOptions() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                .build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Devuelve la imagen", description = "Devuelve la imagen")
    @APIResponse(responseCode = "201", description = "Cuando se retorna imagen")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("id") String id) {
        try {
            java.nio.file.Path uploadPath = Paths.get(System.getProperty("user.home"), configurationProperties.getImageDirectory());
            byte[] imageBytes = fileStorage.getImage(id,uploadPath);

            if (imageBytes != null) {
                return Response.ok(imageBytes)
                        .header("Content-Disposition", "attachment; filename=\"" + id + ".jpg\"")
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Imagen no encontrada con ID: " + id).build();
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al recuperar la imagen.")
                    .build();
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON) // Usamos JSON para una colección de datos
    @Operation(summary = "Devuelve la lista de id de las imagenes", description = "Devuelve la lista de id de las imagenes")
    @APIResponse(responseCode = "201", description = "Cuando se retorna la lista de id")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Set<String> getAllImageIds() throws IOException {
        java.nio.file.Path uploadPath = Paths.get(System.getProperty("user.home"), configurationProperties.getImageDirectory());
        return fileStorage.getAllIds(uploadPath);
    }
}
