/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package fish.payara.fileupload.remote;

import jakarta.json.bind.annotation.JsonbProperty;
import java.util.List;

/**
 *
 * @author avbravo
 */
public record FileUploadJsonResponseExternal(
        @JsonbProperty("procesamiento de imagenes")
        List<FileUploadJsonPropertyExternal> fileUploadIAResult) {

}
