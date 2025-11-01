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
import com.jmoordb.core.ui.fileupload.FileStorageExternal;
import fish.payara.config.ConfigurationProperties;
import com.jmoordb.core.ui.fileupload.FileUploadRequest;
import com.jmoordb.core.ui.fileupload.FileUploadResponse;
import com.jmoordb.core.ui.fileupload.FileUploadResponseExternal;
import fish.payara.restclient.jaxrs.FileUploaderExternal;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("files")
@RequestScoped

public class FileUploadExternalController {

    @Inject
    ConfigurationProperties configurationProperties;
    @Inject
    private FileStorageExternal fileStorageExternal;
    @Inject
    private FileStorage fileStorage;

    @Inject
    FileUploaderExternal fileUploaderExternal;

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
            

            FileUploadResponse response = processResponse(uploadedInputStream, fileDetail);
            System.out.println("\t FileUploadResponse "+response.toString());

            return Response.ok(response).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar el archivo: " + e.getMessage())
                    .build();
        }
    }
   
    
    public FileUploadResponse processResponse( InputStream uploadedInputStream,
            FormDataContentDisposition fileDetail) {

        try {
            java.nio.file.Path uploadPath = Paths.get(System.getProperty("user.home"), configurationProperties.getImageDirectory());

            String fileId
                    = fileStorage.saveFile(
                            uploadedInputStream,
                            fileDetail.getFileName(),
                            uploadPath
                    );
            String fileRemoteId = "";
            if (fileId == "") {
                //No lo guardo

            } else {
                //Enviarlo al servidor remoto
                /**
                 * Envia la imagen a un endpoint en otro microservicio
                 */

                Boolean uploadToExternal = Boolean.TRUE;
                if (uploadToExternal) {

                    File file1 = new File(fileStorage.getTargetPath(fileId, fileDetail.getFileName(), uploadPath));
                    List<File> filesToUpload = new ArrayList<>();
                    filesToUpload.add(file1);
                    fileRemoteId = fileUploaderExternal.uploadImages(filesToUpload);
                }

            }

            FileUploadResponse response = new FileUploadResponse(
                    fileId, fileDetail.getFileName(), fileRemoteId, configurationProperties.getIaUrlImage());
            

            return response;

        } catch (Exception e) {
            e.printStackTrace();
            return new FileUploadResponse("","", "", "");
        }
    }
//    @POST
//    @Path("/upload1")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
//    @Operation(summary = "Sube una imagen, la renombra y devuelve el ID generado")
//    @APIResponse(responseCode = "200", description = "ID del archivo generado exitosamente",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FileUploadResponse.class)))
//    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = FileUploadRequest.class)))
//    public Response upload1(
//            // El nombre 'file' debe coincidir con el campo en el formulario/OpenAPI
//            @FormDataParam("fileUpload1") InputStream uploadedInputStream,
//            @FormDataParam("fileUpload1") FormDataContentDisposition fileDetail) {
//
//        try {
//            java.nio.file.Path uploadPath = Paths.get(System.getProperty("user.home"), configurationProperties.getImageDirectory());
//            FileUploadResponseExternal response = new FileUploadResponseExternal(fileStorageExternal.saveAndRenameImage(
//                    uploadedInputStream,
//                    fileDetail.getFileName(),
//                    Boolean.TRUE,
//                    uploadPath,
//                    configurationProperties.getIaUrlImage()
//            ), fileDetail.getFileName());
//
//            return Response.ok(response).build();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                    .entity("Error al procesar el archivo: " + e.getMessage())
//                    .build();
//        }
//    }

    @POST
    @Path("/upload2")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Sube una imagen, la renombra y devuelve el ID generado")
    @APIResponse(responseCode = "200", description = "ID del archivo generado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FileUploadResponse.class)))
    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = FileUploadRequest.class)))
    public Response upload2(
            @FormDataParam("fileUpload2") InputStream uploadedInputStream,
            @FormDataParam("fileUpload2") FormDataContentDisposition fileDetail) {
        try {
             FileUploadResponse response = processResponse(uploadedInputStream, fileDetail);
            return Response.ok(response).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar el archivo: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/upload3")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Sube una imagen, la renombra y devuelve el ID generado")
    @APIResponse(responseCode = "200", description = "ID del archivo generado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FileUploadResponse.class)))
    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = FileUploadRequest.class)))
    public Response upload3(
            @FormDataParam("fileUpload3") InputStream uploadedInputStream,
            @FormDataParam("fileUpload3") FormDataContentDisposition fileDetail) {
        try {
          FileUploadResponse response = processResponse(uploadedInputStream, fileDetail);

            return Response.ok(response).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar el archivo: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/upload4")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Sube una imagen, la renombra y devuelve el ID generado")
    @APIResponse(responseCode = "200", description = "ID del archivo generado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FileUploadResponse.class)))
    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = FileUploadRequest.class)))
    public Response upload4(
            @FormDataParam("fileUpload4") InputStream uploadedInputStream,
            @FormDataParam("fileUpload4") FormDataContentDisposition fileDetail) {
        try {
           FileUploadResponse response = processResponse(uploadedInputStream, fileDetail);
            return Response.ok(response).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar el archivo: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/upload5")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Sube una imagen, la renombra y devuelve el ID generado")
    @APIResponse(responseCode = "200", description = "ID del archivo generado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FileUploadResponse.class)))
    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = FileUploadRequest.class)))
    public Response upload5(
            @FormDataParam("fileUpload5") InputStream uploadedInputStream,
            @FormDataParam("fileUpload5") FormDataContentDisposition fileDetail) {
        try {
            FileUploadResponse response = processResponse(uploadedInputStream, fileDetail);

            return Response.ok(response).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar el archivo: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/upload6")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Sube una imagen, la renombra y devuelve el ID generado")
    @APIResponse(responseCode = "200", description = "ID del archivo generado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FileUploadResponse.class)))
    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = FileUploadRequest.class)))
    public Response upload6(
            @FormDataParam("fileUpload6") InputStream uploadedInputStream,
            @FormDataParam("fileUpload6") FormDataContentDisposition fileDetail) {
        try {
             FileUploadResponse response = processResponse(uploadedInputStream, fileDetail);
            return Response.ok(response).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar el archivo: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/upload7")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Sube una imagen, la renombra y devuelve el ID generado")
    @APIResponse(responseCode = "200", description = "ID del archivo generado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FileUploadResponse.class)))
    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = FileUploadRequest.class)))
    public Response upload7(
            @FormDataParam("fileUpload7") InputStream uploadedInputStream,
            @FormDataParam("fileUpload7") FormDataContentDisposition fileDetail) {
        try {
            FileUploadResponse response = processResponse(uploadedInputStream, fileDetail);

            return Response.ok(response).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar el archivo: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/upload8")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Sube una imagen, la renombra y devuelve el ID generado")
    @APIResponse(responseCode = "200", description = "ID del archivo generado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FileUploadResponse.class)))
    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = FileUploadRequest.class)))
    public Response upload8(
            @FormDataParam("fileUpload8") InputStream uploadedInputStream,
            @FormDataParam("fileUpload8") FormDataContentDisposition fileDetail) {
        try {
           FileUploadResponse response = processResponse(uploadedInputStream, fileDetail);

            return Response.ok(response).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar el archivo: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/upload9")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Sube una imagen, la renombra y devuelve el ID generado")
    @APIResponse(responseCode = "200", description = "ID del archivo generado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FileUploadResponse.class)))
    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = FileUploadRequest.class)))
    public Response upload9(
            @FormDataParam("fileUpload9") InputStream uploadedInputStream,
            @FormDataParam("fileUpload9") FormDataContentDisposition fileDetail) {
        try {
           FileUploadResponse response = processResponse(uploadedInputStream, fileDetail);

            return Response.ok(response).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar el archivo: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/upload10")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Sube una imagen, la renombra y devuelve el ID generado")
    @APIResponse(responseCode = "200", description = "ID del archivo generado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FileUploadResponse.class)))
    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = FileUploadRequest.class)))
    public Response upload10(
            @FormDataParam("fileUpload10") InputStream uploadedInputStream,
            @FormDataParam("fileUpload10") FormDataContentDisposition fileDetail) {
        try {
           FileUploadResponse response = processResponse(uploadedInputStream, fileDetail);
            return Response.ok(response).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar el archivo: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/upload11")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Sube una imagen, la renombra y devuelve el ID generado")
    @APIResponse(responseCode = "200", description = "ID del archivo generado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FileUploadResponse.class)))
    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = FileUploadRequest.class)))
    public Response upload11(
            @FormDataParam("fileUpload11") InputStream uploadedInputStream,
            @FormDataParam("fileUpload11") FormDataContentDisposition fileDetail) {
        try {
            FileUploadResponse response = processResponse(uploadedInputStream, fileDetail);
            return Response.ok(response).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar el archivo: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/upload12")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Sube una imagen, la renombra y devuelve el ID generado")
    @APIResponse(responseCode = "200", description = "ID del archivo generado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FileUploadResponse.class)))
    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = FileUploadRequest.class)))
    public Response upload12(
            @FormDataParam("fileUpload12") InputStream uploadedInputStream,
            @FormDataParam("fileUpload12") FormDataContentDisposition fileDetail) {
        try {
            FileUploadResponse response = processResponse(uploadedInputStream, fileDetail);
            return Response.ok(response).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar el archivo: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/upload13")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Sube una imagen, la renombra y devuelve el ID generado")
    @APIResponse(responseCode = "200", description = "ID del archivo generado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FileUploadResponse.class)))
    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = FileUploadRequest.class)))
    public Response upload13(
            @FormDataParam("fileUpload13") InputStream uploadedInputStream,
            @FormDataParam("fileUpload13") FormDataContentDisposition fileDetail) {
        try {

            FileUploadResponse response = processResponse(uploadedInputStream, fileDetail);
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
            byte[] imageBytes = fileStorageExternal.getImage(id, uploadPath);

            if (imageBytes != null) {
                // Devuelve los bytes con un Content-Type apropiado. 
                // Usaremos octet-stream para ser genéricos, pero se podría inferir el tipo.
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

    // --- 3. Método para devolver todos los IDs generados ---
    @GET
    @Produces(MediaType.APPLICATION_JSON) // Usamos JSON para una colección de datos
    @Operation(summary = "Devuelve la lista de id de las imagenes", description = "Devuelve la lista de id de las imagenes")
    @APIResponse(responseCode = "201", description = "Cuando se retorna la lista de id")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Set<String> getAllImageIds() throws IOException {
        java.nio.file.Path uploadPath = Paths.get(System.getProperty("user.home"), configurationProperties.getImageDirectory());
        return fileStorageExternal.getAllIds(uploadPath);
    }
}
