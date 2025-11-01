/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.restclient.jaxrs.impl;

import fish.payara.config.ConfigurationProperties;
import fish.payara.restclient.jaxrs.FileUploadJsonProperty;
import fish.payara.restclient.jaxrs.FileUploadJsonResponse;
import fish.payara.restclient.jaxrs.ui.FileUploadExternalGeneric;
import fish.payara.restclient.jaxrs.ui.UploadResult;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.File;
import java.util.List;

/**
 *
 * @author avbravo
 */
@Named
@RequestScoped
public class ImageService {
    @Inject
    FileUploadExternalGeneric fileUploaderExternal;
    
       @Inject
    ConfigurationProperties configurationProperties;

 //   configurationProperties.getIaUrlProcesarimagen()
    public String processImages(List<File> images) {
        
        // 1. Define el extractor para tus tipos específicos
        FileUploadExternalExtractor extractor = new FileUploadExternalExtractor();
        
        // 2. Llama al método genérico, pasando la clase de la respuesta y el extractor
        UploadResult<FileUploadJsonProperty> resultado = fileUploaderExternal.uploadImages(images, 
                FileUploadJsonResponse.class, // Clase de la respuesta completa (R)
                extractor                  // Extractor para obtener el item (T)
                ,configurationProperties.getIaUrlProcesarimagen()
        );

        // 3. Procesa el resultado
        if (resultado.success()) {
            // El resultado es un FileUploadIAJsonProperty:
            FileUploadJsonProperty item = resultado.resultItem(); 
            // El mensaje ya está en el resultado.
            return resultado.message() + " - Mensaje de IA: " + item.mensaje();
        } else {
            return resultado.message();
        }
    }
}
