/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.model;

import java.util.Objects;

/**
 *
 * @author avbravo
 */
public class Persona {
     private Long id;
 private String nombre;
 private Double latitude;
 private Double longitude;

    public Persona() {
    }

    public Persona(Long id, String nombre, Double latitude, Double longitude) {
        this.id = id;
        this.nombre = nombre;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.nombre);
        hash = 13 * hash + Objects.hashCode(this.latitude);
        hash = 13 * hash + Objects.hashCode(this.longitude);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        return Objects.equals(this.longitude, other.longitude);
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }
 
 
 
 
 
}
