/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.fileupload;

/**
 *
 * @author avbravo
 */
import fish.payara.config.ConfigurationProperties;
import com.jmoordb.core.ui.fileupload.FileUploadIdIA;
import fish.payara.restclient.jaxrs.FileUploaderExternalIA;
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

@ApplicationScoped
public class FileStorageIA {

    @Inject
    FileUploaderExternalIA jAXRSImageUploader;

    public FileUploadIdIA saveAndRenameImage(InputStream fileStream, String originalFileName, Boolean uploadToIA,Path uploadPath, String iaUrlImage) throws IOException {
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
        String fileIdAI = "";
        if (uploadToIA) {
            File file1 = new File(targetPath.toString());
            List<File> filesToUpload = new ArrayList<>();
            filesToUpload.add(file1);
            fileIdAI = jAXRSImageUploader.uploadImages(filesToUpload);
        }

        return new FileUploadIdIA(fileId, fileIdAI,iaUrlImage);
    }

    /**
     * Devuelve la imagen (bytes) asociada a un ID.
     *
     * @param id El ID de la imagen.
     * @return Los bytes de la imagen o null si no se encuentra.
     */
    public byte[] getImage(String id,Path uploadPath) throws IOException {

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
