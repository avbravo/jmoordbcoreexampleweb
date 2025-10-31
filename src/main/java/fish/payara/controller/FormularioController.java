/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.controller;

import fish.payara.controller.formulario.Formulario;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author avbravo
 */
@Path("/FormularioController")
public class FormularioController {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) // O MediaType.APPLICATION_JSON
//    @Consumes( MediaType.APPLICATION_JSON) // O MediaType.APPLICATION_JSON
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveFormulario(@BeanParam Formulario formulario) {
        System.out.println("\t llego al metodo save");
        // Lógica para guardar los datos en la base de datos...
        // --- LOG DE VERIFICACIÓN ---
        System.out.println("--- Datos Recibidos del Formulario ---");
        System.out.println("NHRC: " + formulario.getNhrc());
        System.out.println("Prioridad: " + formulario.getPrioridad());
        System.out.println("Edad: " + formulario.getEdad());
        System.out.println("-----------------------------------------------------------");
        System.out.println("File ID 1: " + formulario.getFileId1());
        System.out.println("formulario.getOriginalFileName1(): " + formulario.getOriginalFileName1());
        System.out.println("formulario.getPhotoId1(): " + formulario.getPhotoId1());
        System.out.println("-----------------------------------------------------------");
        System.out.println("Photo ID 2: " + formulario.getPhotoId2());
        System.out.println("formulario.getOriginalFileName2(): " + formulario.getOriginalFileName2());
        System.out.println("formulario.getPhotoId2(): " + formulario.getPhotoId2());
        return Response.ok("Datos guardados correctamente.").build();
    }
}
