/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.restclient.jaxrs.ui;

/**
 *
 * @author avbravo
 */
public record UploadResult<T>(String message, T resultItem, boolean success) {
}