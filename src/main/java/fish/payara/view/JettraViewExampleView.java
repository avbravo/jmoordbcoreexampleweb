/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.view;

import com.jmoordb.core.ui.Div;
import com.jmoordb.core.ui.dashboard.DashboardLayout;
import com.jmoordb.core.ui.jettra.JettraView;
import com.jmoordb.core.ui.menu.MenuLink;
import fish.payara.dashboard.MenuSideBar;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Path;

import java.util.List;
import java.util.Map;

 /*
 * @author avbravo
 */
@Path("jettra-view") // ⭐ Define la URL final: /api/profile-view
@RequestScoped
public class JettraViewExampleView extends JettraView{

   

    @Override
    protected String init() {
         // Acceso a la sesión vía el @Context inyectado en la clase base
        String username = (String) request.getSession().getAttribute("usuario");
        String userRol = (String) request.getSession().getAttribute("rol");
        if (userRol == null) userRol = "ADMIN";
        if (username == null) username = "Invitado";
        
        // --- Lógica de la Vista ---
        
        Map<String, List<MenuLink>> sidebarSections = MenuSideBar.getSidebarSections(
            this.getClass().getSimpleName(), username, userRol
        );
        
        Div mainContent = new Div().text("Vista generada con JAX-RS y WebComponents para: " + username);

        // --- Generación del Layout ---
        String htmlCompleto = DashboardLayout.buildPage(
                request, 
                username, 
                mainContent,
                sidebarSections, 
                "JAX-RS HTML View",
                null
        );
        
        return htmlCompleto; // ⭐ Devuelve el String HTML completo
    }

    
    
        
    // Si quisieras manejar un POST, lo defines aquí mismo:
    /*
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response handlePost(@FormParam("username") String newUsername) {
        request.getSession().setAttribute("usuario", newUsername);
        
        // Redirigir usando JAX-RS
        return Response.seeOther(URI.create(request.getContextPath() + "/api/profile-view")).build();
    }
    */

}
