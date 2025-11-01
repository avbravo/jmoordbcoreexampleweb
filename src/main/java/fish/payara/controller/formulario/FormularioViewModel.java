/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package fish.payara.controller.formulario;

/**
 *
 * @author avbravo
 */

// Usando tipos de datos apropiados (String para nhrc y foto IDs)



// Importaciones de Jakarta EE:
import jakarta.ws.rs.FormParam;
import jakarta.xml.bind.annotation.XmlRootElement; 

@XmlRootElement
public class FormularioViewModel {
    
    // El valor dentro de @FormParam DEBE coincidir exactamente con el atributo 'name' del HTML
    
    // -----------------------------------------------------------------
    // CAMPOS DEL PACIENTE (Nuevos campos del formulario HTML)
    // -----------------------------------------------------------------
    @FormParam("nhrc") 
    private String nhrc;
    
    @FormParam("prioridad")
    private String prioridad;
    
    // Usamos Integer en lugar de int para permitir que el valor sea null si no se envía o es inválido.
    @FormParam("edad")
    private int edad; 
    
    // -----------------------------------------------------------------
    // CAMPOS DE IMAGEN 1 (Coinciden con name="...")
    // -----------------------------------------------------------------
    @FormParam("fileId1")
    private String fileId1;
    
    @FormParam("fileRemoteId1")
    private String fileRemoteId1;
    
    @FormParam("originalFileName1")
    private String originalFileName1;
    
    // -----------------------------------------------------------------
    // CAMPOS DE IMAGEN 2 (Coinciden con name="...")
    // -----------------------------------------------------------------
    @FormParam("fileId2")
    private String fileId2;
    
    @FormParam("fileRemoteId2")
    private String fileRemoteId2;
    
    @FormParam("originalFileName2")
    private String originalFileName2;

    // ***************************************************************
    // 1. CONSTRUCTOR POR DEFECTO (NECESARIO para JAX-RS/Payara)
    // ***************************************************************
    public FormularioViewModel() {
        // Constructor por defecto
    }

    // ***************************************************************
    // 2. GETTERS Y SETTERS (NECESARIOS para inyección por @BeanParam)
    // ***************************************************************

    // Getters y Setters para nhrc
    public String getNhrc() { return nhrc; }
    public void setNhrc(String nhrc) { this.nhrc = nhrc; }

    // Getters y Setters para prioridad
    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }

//    // Getters y Setters para edad (Ahora Integer)
//    public Integer getEdad() { return edad; }
//    public void setEdad(Integer edad) { this.edad = edad; }

    
    
    // Getters y Setters para Imagen 1
    public String getFileId1() { return fileId1; }
    public void setFileId1(String fileId1) { this.fileId1 = fileId1; }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFileRemoteId1() { return fileRemoteId1; }
    public void setFileRemoteId1(String fileRemoteId1) { this.fileRemoteId1 = fileRemoteId1; }

    public String getOriginalFileName1() { return originalFileName1; }
    public void setOriginalFileName1(String originalFileName1) { this.originalFileName1 = originalFileName1; }

    // Getters y Setters para Imagen 2
    public String getFileId2() { return fileId2; }
    public void setFileId2(String fileId2) { this.fileId2 = fileId2; }

    public String getFileRemoteId2() { return fileRemoteId2; }
    public void setFileRemoteId2(String fileRemoteId2) { this.fileRemoteId2 = fileRemoteId2; }

    public String getOriginalFileName2() { return originalFileName2; }
    public void setOriginalFileName2(String originalFileName2) { this.originalFileName2 = originalFileName2; }
}