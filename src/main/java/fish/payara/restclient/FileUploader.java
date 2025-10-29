/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.restclient;

/**
 *
 * @author avbravo
 */
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import java.io.File;
import java.net.URL; // Opcional, si usas la configuración por URL

public class FileUploader {

     public String sendImageAndGetId(String filePath, String baseUrl) {
        
        File imageFile = new File(filePath);

        if (!imageFile.exists()) {
            System.err.println("Error: El archivo no existe.");
            return null;
        }

        try {
            // 1. Crear el RestClientBuilder con la URL base
            RestClientBuilder builder = RestClientBuilder.newBuilder()
                    .baseUrl(new URL(baseUrl));
            
            // 2. REGISTRAR EL FEATURE DE MULTIPART DE JERSEY
            // Esto es crucial para que el cliente sepa cómo serializar los FormParam binarios
            builder.register(MultiPartFeature.class);

            // 3. Construir el cliente e invocar el método
            FileUploadClient client = builder.build(FileUploadClient.class);
            
            System.out.println("Enviando archivo: " + imageFile.getName() + "...");

            // El cliente automáticamente construye el cuerpo multipart/form-data
           RespuestaProcesamiento respuesta = client.uploadImage(
                imageFile
            );
            
           // 2. Navegar en el objeto para extraer el IMG ID
            if (respuesta != null && respuesta.getProcesamientoDeImagenes() != null && !respuesta.getProcesamientoDeImagenes().isEmpty()) {
                
                // Asumimos que solo hay un elemento en la lista.
                ProcesoImagen imagen = respuesta.getProcesamientoDeImagenes().get(0);
                String imgId = imagen.getImgId();
                
                System.out.println("✅ Subida exitosa. ID de la imagen devuelto: " + imgId);
                return imgId;
            } else {
                System.out.println("Respuesta del servidor vacía o inesperada.");
                return null;
            }

        } catch (Exception e) {
            System.err.println("❌ Error al subir el archivo: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
   
}