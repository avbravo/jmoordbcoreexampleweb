/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.controller;

import fish.payara.controller.formulario.FormularioViewModel;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
    public Response saveFormulario(@BeanParam FormularioViewModel formulario) {
        System.out.println("\t <<<llego al metodo save>>>");
        // Lógica para guardar los datos en la base de datos...
        // --- LOG DE VERIFICACIÓN ---
        System.out.println("--- Datos Recibidos del Formulario ---");
        System.out.println("NHRC: " + formulario.getNhrc());
        // System.out.println("FechaRegistro: " + formulario.getFechaRegistro());
        LocalDate fechaRecibida = null;
        System.out.println("formulario.getFechaRegistroString() "+formulario.getFechaRegistroString());
        String fechaString = formulario.getFechaRegistroString(); // Obtiene la cadena "AAAA-MM-DD"
System.out.println(" fechaStrng  "+fechaString);
        try {
            if (fechaString != null && !fechaString.trim().isEmpty()) {
                // Conversión de String ("2025-11-03") a LocalDate
                fechaRecibida = LocalDate.parse(fechaString);
            }
        } catch (DateTimeParseException e) {
            System.err.println("ERROR DE PARSEO: La cadena de fecha no tiene el formato esperado (AAAA-MM-DD): " + fechaString);
            return Response.status(400) // Bad Request
                    .entity("{\"error\": \"Formato de fecha de registro inválido.\"}")
                    .build();
        }

        // --- Verificación final del valor ---
        if (fechaRecibida != null) {
            System.out.println("Fecha de Registro recibida (String): " + fechaString);
            System.out.println("Fecha de Registro convertida (LocalDate): " + fechaRecibida);

            // Aquí puedes usar 'fechaRecibida' para tu lógica de negocio
        } else {
            System.out.println("ADVERTENCIA: fechaRegistroString llegó vacía o nula. Esto puede ser válido si el campo no es requerido.");
        }
        System.out.println("Numeromuestra(): " + formulario.getNumeromuestra());
        System.out.println("Edad: " + formulario.getEdad());
        System.out.println("Motivo  "+formulario.getMotivo());
        
        System.out.println("-----------------------------------------------------------");
        System.out.println("File ID 1: " + formulario.getFileId1());
        System.out.println("formulario.getOriginalFileName1(): " + formulario.getOriginalFileName1());
        System.out.println("formulario.getFileRemoteId1(): " + formulario.getFileRemoteId1());
        System.out.println("-----------------------------------------------------------");
        System.out.println("File ID: " + formulario.getFileId2());
        System.out.println("formulario.getOriginalFileName2(): " + formulario.getOriginalFileName2());
        System.out.println("formulario.getFileRemoteId2(): " + formulario.getFileRemoteId2());

        System.out.println("-----------------------------------------------------------");
        System.out.println("File ID: " + formulario.getFileId3());
        System.out.println("formulario.getOriginalFileName3(): " + formulario.getOriginalFileName3());
        System.out.println("formulario.getFileRemoteId3(): " + formulario.getFileRemoteId3());

        System.out.println("---------------------[4]--------------------------------------");
        System.out.println("File ID: " + formulario.getFileId4());
        System.out.println("formulario.getOriginalFileName4(): " + formulario.getOriginalFileName4());
        System.out.println("formulario.getFileRemoteId4(): " + formulario.getFileRemoteId4());

        return Response.ok("Datos guardados correctamente.").build();
    }
}
