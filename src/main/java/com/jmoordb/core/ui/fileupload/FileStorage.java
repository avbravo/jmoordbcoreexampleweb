/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.fileupload;

/**
 *
 * @author avbravo
 */
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class FileStorage {

  

    public FileUploadId saveAndRenameImage(InputStream fileStream, String originalFileName,Path uploadPath) throws IOException {
        String fileId = UUID.randomUUID().toString();
        String fileExtension = "";
        int dotIndex = originalFileName.lastIndexOf('.');
        if (dotIndex > 0) {
            fileExtension = originalFileName.substring(dotIndex);
        }

        String newFileName = fileId + fileExtension;

       

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path targetPath = uploadPath.resolve(newFileName);

        Files.copy(fileStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
/**
 * Envia la imagen a un endpoint en otro microservicio
 */


        return new FileUploadId(fileId);
    }

    /**
     * Devuelve la imagen (bytes) asociada a un ID.
     *
     * @param id El ID de la imagen.
     * @return Los bytes de la imagen o null si no se encuentra.
     */
    public byte[] getImage(String id,Path uploadPath) throws IOException {
        

        //  Path path = imageMap.get(id);
        for (Path archivo : Files.newDirectoryStream(uploadPath)) {
            String nombreArchivoCompleto = archivo.getFileName().toString();
            String nombreArchivoSinExtension = nombreArchivoCompleto;
            int indicePunto = nombreArchivoCompleto.lastIndexOf('.');
            if (indicePunto != -1) {
                nombreArchivoSinExtension = nombreArchivoCompleto.substring(0, indicePunto);
            }
            if (nombreArchivoSinExtension.equals(id)) {
                return Files.readAllBytes(archivo);
            }
        }

        return null;
    }

    public Set<String> getAllIds(Path uploadPath) throws IOException {
        Set<String> result = new HashSet<>();;
        

        for (Path archivo : Files.newDirectoryStream(uploadPath)) {
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
