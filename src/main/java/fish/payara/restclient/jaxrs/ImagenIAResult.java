/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.restclient.jaxrs;

import jakarta.json.bind.annotation.JsonbProperty;

/**
 *
 * @author avbravo
 */
public record ImagenIAResult(@JsonbProperty("IMG ID")String imgid, String mensaje) {

}
