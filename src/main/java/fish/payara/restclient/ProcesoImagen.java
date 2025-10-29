/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.restclient;

/**
 *
 * @author avbravo
 */
import jakarta.json.bind.annotation.JsonbProperty;

public class ProcesoImagen {

    @JsonbProperty("IMG ID") // Mapea el campo "IMG ID"
    private String imgId;

    private String mensaje;

    // Constructor vacío (necesario para la deserialización)
    public ProcesoImagen() {} 

    // Getters
    public String getImgId() {
        return imgId;
    }

    public String getMensaje() {
        return mensaje;
    }

    // Setters (opcionales, pero recomendados para deserialización)
    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}