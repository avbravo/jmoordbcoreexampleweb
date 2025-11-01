/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.restclient.jaxrs.impl;

/**
 *
 * @author avbravo
 */

import java.util.List;

import fish.payara.restclient.jaxrs.FileUploadJsonProperty;
import fish.payara.restclient.jaxrs.FileUploadJsonResponse;
import fish.payara.restclient.jaxrs.ui.ResultExtractor;
import fish.payara.restclient.jaxrs.ui.UploadResult;
import java.util.List;

/**
 * Extractor específico para las clases FileUploadResponseIA y FileUploadIAJsonProperty.
 */
public class FileUploadExternalExtractor implements ResultExtractor<FileUploadJsonResponse, FileUploadJsonProperty> {

    @Override
    public UploadResult<FileUploadJsonProperty> extract(FileUploadJsonResponse response) {
        List<FileUploadJsonProperty> resultados = response.fileUploadIAResult();
        
        if (resultados != null && !resultados.isEmpty()) {
            FileUploadJsonProperty primeraImagen = resultados.get(0);
            
            // Puedes devolver el ID o todo el objeto, según lo que necesites.
            // Aquí devolvemos el objeto y un mensaje basado en el ID.
            String message = "ID Procesado: " + primeraImagen.imgid();
            return new UploadResult<>(message, primeraImagen, true); 
        } else {
            return new UploadResult<>("Respuesta exitosa, pero la lista de procesamiento está vacía.", null, false);
        }
    }
}