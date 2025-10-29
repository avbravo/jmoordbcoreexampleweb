/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.restclient.image;

import jakarta.inject.Inject;
import java.io.File;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author avbravo
 */
public class ImageUploaderService {
    @Inject
    @RestClient
    private ImagenProcessorClient processorClient;

    public String sendImageForProcessing(String localFilePath) {
        try {
            File imageFile = new File(localFilePath);
            if (!imageFile.exists()) {
                return "Error: Archivo no encontrado.";
            }

            // 1. Crear el formulario con el archivo
            ImageUploadForm formData = new ImageUploadForm(imageFile);
            
            // 2. Llamar al endpoint POST
            String responseJson = processorClient.procesarImagen(formData);
            
            System.out.println("Respuesta del servidor: " + responseJson);
            return responseJson;
            
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al procesar la imagen: " + e.getMessage();
        }
    }
}
