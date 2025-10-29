/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.fileupload;

/**
 *
 * @author avbravo
 */

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Respuesta de la subida de archivo con el ID generado.")
public class FileUploadResponse {
    @Schema(example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
    private String fileId;
    
    @Schema(example = "mi_imagen.jpg")
    private String originalFileName;

    // Constructor, getters y setters (omitidos por brevedad)
    public FileUploadResponse(String fileId, String originalFileName) {
        this.fileId = fileId;
        this.originalFileName = originalFileName;
    }

    public String getFileId() { return fileId; }
    public void setFileId(String fileId) { this.fileId = fileId; }
    public String getOriginalFileName() { return originalFileName; }
    public void setOriginalFileName(String originalFileName) { this.originalFileName = originalFileName; }
}