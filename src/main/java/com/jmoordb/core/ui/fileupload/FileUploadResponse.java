/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.fileupload;

/**
 *
 * @author avbravo
 */

import com.jmoordb.core.ui.fileupload.FileUploadId;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Respuesta de la subida de archivo con el ID generado.")
public class FileUploadResponse {
    @Schema(description = "Id de la imagen almacenada localmente",example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
    private String fileId;
    
    @Schema(description = "Nombre original de la imagen",example = "mi_imagen.jpg")
    private String originalFileName;
    
    @Schema(description ="Id devuelto por el algoritmo AI", example = "afdf1b2c3d4-e5f6-7890-12r34-567890abcdef")
    private String fileRemoteId;
    
    @Schema(description ="El path de la imagen procesada en el algoritmo", example = "afdf1b2c3d4-e5f6-7890-12r34-567890abcdef")
    private  String urlRemote;

   public FileUploadResponse(String fileId, String originalFileName,String fileRemoteId,String urlRemote) {
        this.fileId = fileId;
        this.originalFileName = originalFileName;
        this.fileRemoteId = fileRemoteId;
        this.urlRemote = urlRemote;
      
    }

    public String getFileRemoteId() {
        return fileRemoteId;
    }

    public void setFileRemoteId(String fileRemoteId) {
        this.fileRemoteId = fileRemoteId;
    }

    public String getUrlRemote() {
        return urlRemote;
    }

    public void setUrlRemote(String urlRemote) {
        this.urlRemote = urlRemote;
    }

    @Override
    public String toString() {
        return "FileUploadResponse{" + "fileId=" + fileId + ", originalFileName=" + originalFileName + ", fileRemoteId=" + fileRemoteId + ", urlRemote=" + urlRemote + '}';
    }

   
    

   
   
    public String getFileId() { return fileId; }
    public void setFileId(String fileId) { this.fileId = fileId; }
    public String getOriginalFileName() { return originalFileName; }
    public void setOriginalFileName(String originalFileName) { this.originalFileName = originalFileName; }
}