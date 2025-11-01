/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.fileupload;

/**
 *
 * @author avbravo
 */

import com.jmoordb.core.ui.fileupload.FileUploadIdExternal;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Respuesta de la subida de archivo con el ID generado.")
public class FileUploadResponseExternal {
    @Schema(description = "Id de la imagen almacenada localmente",example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
    private String fileId;
    
    @Schema(description = "Nombre original de la imagen",example = "mi_imagen.jpg")
    private String originalFileName;
    
    @Schema(description ="Id devuelto por el algoritmo AI", example = "afdf1b2c3d4-e5f6-7890-12r34-567890abcdef")
    private String photo_id;
    
    @Schema(description ="El path de la imagen procesada en el algoritmo", example = "afdf1b2c3d4-e5f6-7890-12r34-567890abcdef")
    private  String img_base_url;


   public FileUploadResponseExternal(FileUploadIdExternal imageGeneration, String originalFileName) {
        this.fileId = imageGeneration.fileId();
        this.originalFileName = originalFileName;
        this.photo_id=imageGeneration.photo_id();
        this.img_base_url = imageGeneration.img_base_url();
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public String getImg_base_url() {
        return img_base_url;
    }

    public void setImg_base_url(String img_base_url) {
        this.img_base_url = img_base_url;
    }

    

   
   
    public String getFileId() { return fileId; }
    public void setFileId(String fileId) { this.fileId = fileId; }
    public String getOriginalFileName() { return originalFileName; }
    public void setOriginalFileName(String originalFileName) { this.originalFileName = originalFileName; }
}