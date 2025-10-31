/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.fileupload;

/**
 *
 * @author avbravo
 */


import jakarta.ws.rs.FormParam;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Estructura de la petición de subida de archivo.")
public class FileUploadRequest {
    @FormParam("file")
    @Schema(type = SchemaType.STRING, format = "binary", description = "El archivo de imagen a subir.")
    public byte[] file; // El tipo es solo para la documentación en OpenAPI
}