/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fish.payara.restclient.jaxrs.ui;

import fish.payara.restclient.jaxrs.ui.UploadResult;

/**
 *
 * @author avbravo
 */
@FunctionalInterface
public interface ResultExtractor <R, T> {
    // R es la respuesta completa (FileUploadResponseIA)
    // T es el tipo de resultado de cada item (FileUploadIAJsonProperty)
    UploadResult<T> extract(R response);
    
}
