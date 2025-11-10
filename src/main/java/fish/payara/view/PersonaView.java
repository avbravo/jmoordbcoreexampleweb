/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.view;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.dashboard.DashboardLayout;
import com.jmoordb.core.ui.jettra.JettraView;
import com.jmoordb.core.ui.model.WebModelSession;
import com.jmoordb.core.ui.panel.Panel;
import fish.payara.config.ConfigurationProperties;
import fish.payara.crud.form.persona.PersonaPage;
import fish.payara.crud.form.persona.PersonaService;
import fish.payara.dashboard.MenuSideBar;
import fish.payara.model.Persona;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Path;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

/*
 * @author avbravo
 */
@Path("persona-view") // ⭐ Define la URL final: /api/profile-view
@RequestScoped
public class PersonaView extends JettraView {

    // <editor-fold defaultstate="collapsed" desc="attributes()">
    WebModelSession webModelSession = new WebModelSession();
    List<Tag> headers = new ArrayList<>();
    @Inject
    ConfigurationProperties configurationProperties;
    private final PersonaService personaService = new PersonaService(); 
    private final int DEFAULT_PAGE_SIZE = 10;
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String init()">
    @Override
    protected String init() {

        webModelSession = webModelOfSession(request);

        return DashboardLayout.buildPage(
                request,
                webModelSession.getUsername(),
                content(request),
                MenuSideBar.getSidebarSections(
                        this.getClass().getSimpleName(),
                        webModelSession
                ),
                "Persona View",
                configurationProperties.getDashboardFooterText() + " | " + webModelSession.getUserRol(),
                headers
        );

    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="WebComponent content(HttpServletRequest request)">

    @Override
    public WebComponent content(HttpServletRequest request) {
        WebComponent mainContent = null;
        try {

 // --- 1. Parámetros de Paginación ---
        int pageSize = DEFAULT_PAGE_SIZE;
        int currentPage = 1;
        
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                // El navegador envía el nuevo número de página al hacer clic
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                // Mantener 1
            }
        }
        
        // --- 2. Obtener Datos y Paginación ---
        List<Persona> allPersonas = personaService.findAll();
        long totalRecords = personaService.count(); 
        
        // ⭐ Lógica de paginación en memoria (CRÍTICA)
        int startIndex = Math.max(0, (currentPage - 1) * pageSize);
        int endIndex = Math.min(startIndex + pageSize, (int) totalRecords);
        
        // Aseguramos que la página no esté vacía si el usuario pone un número alto manualmente
        if (startIndex >= endIndex && totalRecords > 0) {
            currentPage = 1;
            startIndex = 0;
            endIndex = Math.min(pageSize, (int) totalRecords);
        }
        
        List<Persona> pageClients = allPersonas.subList(startIndex, endIndex);
        
        // Persona para el formulario
        Persona currentPersona = getSelectedPersona(request, allPersonas);

        // --- 3. URL Base para Paginación ---
        String paginationBaseUrl = request.getContextPath() + "/api/persona-view?page="; 
        
        // --- 4. Crear el componente de página ---
        WebComponent personasPageContent = new PersonaPage(
            pageClients, 
            currentPersona, 
            pageSize, 
            currentPage, 
            totalRecords, 
            paginationBaseUrl
        );


            mainContent = new Panel("Persona View", personasPageContent, request);
        } catch (Exception e) {
            System.out.println("\t content() " + e.getLocalizedMessage());
        }
        return mainContent;
    }
    // </editor-fold>

     // El método getSelectedPersona se mantiene igual que en la respuesta anterior.
    private Persona getSelectedPersona(HttpServletRequest request, List<Persona> allClients) {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                Long selectedId = Long.parseLong(idParam);
                return allClients.stream()
                        .filter(c -> c.getId().equals(selectedId))
                        .findFirst()
                        .orElseGet(() -> new Persona(0L, "", 0.0, 0.0));
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return new Persona(0L, "", 0.0, 0.0);
    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // 1. Obtener los parámetros del formulario
        String idParam = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String latitude = request.getParameter("latitude");
        String longitudeParam = request.getParameter("longitude");
        
        // 2. Convertir y validar (Manejo básico de errores)
        Long id = 0L;
        Double monto = 0.0;
        Double longitude = 0.0;
        
        try {
            // El ID es 0 o null si es una nueva creación
            if (idParam != null && !idParam.isEmpty()) {
                id = Long.parseLong(idParam);
            }
            if (latitude != null) monto = Double.parseDouble(latitude);
            if (longitudeParam != null) longitude = Double.parseDouble(longitudeParam);
        } catch (NumberFormatException e) {
            // Manejo de error: podrías redirigir a la misma página con un mensaje de error
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos numéricos inválidos.");
            return;
        }

        // 3. Crear el objeto Persona
        Persona persona = new Persona(id, nombre, monto, longitude);
        
        // 4. Determinar la operación (Crear o Actualizar)
        if (id == 0L) {
            // ⭐ OPERACIÓN DE CREACIÓN
          Persona savedPersona=  personaService.save(persona);
            String redirectUrl = request.getContextPath() + "/api/persona-view?status=saved&id=" + savedPersona.getId();
    response.sendRedirect(redirectUrl);

        } else {
            // OPERACIÓN DE ACTUALIZACIÓN (Asumimos que el mismo botón manejará la actualización en el futuro)
            // Por ahora, solo queremos que funcione la creación.
            personaService.save(persona);
        }

        // 5. Redirigir al usuario a la página principal del CRUD (para ver la lista actualizada)
        response.sendRedirect(request.getContextPath() + "/api/persona-view");
    }
    @Override
    protected String javaScriptCode() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
