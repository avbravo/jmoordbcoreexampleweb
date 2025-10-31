/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.fileupload;

/**
 *
 * @author avbravo
 */
import fish.payara.config.ConfigurationProperties;
import fish.payara.restclient.jaxrs.ImageGeneration;
import fish.payara.restclient.jaxrs.JAXRSImageUploader;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class FileStorage {
//
//    @Inject
//    @ConfigProperty(name = "image.Directory")
//    private String imageDirectory;
    
    @Inject
    ConfigurationProperties configurationProperties;
    @Inject
    JAXRSImageUploader jAXRSImageUploader;

    public ImageGeneration saveAndRenameImage(InputStream fileStream, String originalFileName, Boolean uploadToIA) throws IOException {
        String fileId = UUID.randomUUID().toString();
        String fileExtension = "";
        int dotIndex = originalFileName.lastIndexOf('.');
        if (dotIndex > 0) {
            fileExtension = originalFileName.substring(dotIndex);
        }

        String newFileName = fileId + fileExtension;

        Path uploadPath = Paths.get(System.getProperty("user.home"), configurationProperties.getImageDirectory());

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path targetPath = uploadPath.resolve(newFileName);

        Files.copy(fileStream, targetPath, StandardCopyOption.REPLACE_EXISTING);

        String fileIdAI = "";
        if (uploadToIA) {
//            JAXRSImageUploader j = new JAXRSImageUploader();
            File file1 = new File(targetPath.toString());
            List<File> filesToUpload = new ArrayList<>();
            filesToUpload.add(file1);
            fileIdAI = jAXRSImageUploader.uploadImages(filesToUpload);
        }

        return new ImageGeneration(fileId, fileIdAI,configurationProperties.getIaUrlVerContour());
    }

    /**
     * Devuelve la imagen (bytes) asociada a un ID.
     *
     * @param id El ID de la imagen.
     * @return Los bytes de la imagen o null si no se encuentra.
     */
    public byte[] getImage(String id) throws IOException {
        Path directorio = Paths.get(System.getProperty("user.home"),configurationProperties.getImageDirectory());

        //  Path path = imageMap.get(id);
        for (Path archivo : Files.newDirectoryStream(directorio)) {
            String nombreArchivoCompleto = archivo.getFileName().toString();
            String nombreArchivoSinExtension = nombreArchivoCompleto;
            int indicePunto = nombreArchivoCompleto.lastIndexOf('.');
            if (indicePunto != -1) {
                nombreArchivoSinExtension = nombreArchivoCompleto.substring(0, indicePunto);
            }
            if (nombreArchivoSinExtension.equals(id)) {
                System.out.println("Archivo encontrado: " + archivo);
                return Files.readAllBytes(archivo);
            }
        }

        return null;
    }

    public Set<String> getAllIds() throws IOException {
        Set<String> result = new HashSet<>();;
        Path directorio = Paths.get(System.getProperty("user.home"), configurationProperties.getImageDirectory());

        for (Path archivo : Files.newDirectoryStream(directorio)) {
            String nombreArchivoCompleto = archivo.getFileName().toString();
            String nombreArchivoSinExtension = nombreArchivoCompleto;
            int indicePunto = nombreArchivoCompleto.lastIndexOf('.');
            if (indicePunto != -1) {
                nombreArchivoSinExtension = nombreArchivoCompleto.substring(0, indicePunto);
                result.add(nombreArchivoSinExtension);
            }

        }

        return result;
    }

}
