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
import java.util.List;
import java.util.Map;

public class RespuestaProcesamiento {

    // El campo raíz es un mapa que contiene la clave "procesamiento de imagenes"
    // que a su vez contiene una lista de objetos ProcesoImagen.
    @JsonbProperty("procesamiento de imagenes")
    private List<ProcesoImagen> procesamientoDeImagenes;

    // Getters & Setters
    public List<ProcesoImagen> getProcesamientoDeImagenes() {
        return procesamientoDeImagenes;
    }

    public void setProcesamientoDeImagenes(List<ProcesoImagen> procesamientoDeImagenes) {
        this.procesamientoDeImagenes = procesamientoDeImagenes;
    }
}