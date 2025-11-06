/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.view;

import com.jmoordb.core.ui.Button;
import com.jmoordb.core.ui.Div;
import com.jmoordb.core.ui.Script;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.alert.NotificationModal;
import com.jmoordb.core.ui.dashboard.DashboardLayout;
import com.jmoordb.core.ui.jettra.JettraView;
import com.jmoordb.core.ui.menu.MenuLink;
import com.jmoordb.core.ui.model.WebModelSession;
import com.jmoordb.core.ui.panel.Panel;
import fish.payara.dashboard.MenuSideBar;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Path;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

 /*
 * @author avbravo
 */
@Path("analisis-view") // ⭐ Define la URL final: /api/profile-view
@RequestScoped
public class AnalisisView extends JettraView{
    WebModelSession webModel = new WebModelSession();
    List<Tag> headers = new ArrayList<>();

   

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
        
        WebComponent mainContent = new Tag("div").withText("Vista generada con JAX-RS y WebComponents para: " + username);

        
        
         NotificationModal infoModal = new NotificationModal(
                    "infoModal",// 
                    "Prueba de Notificación",
                    "Este es un mensaje de prueba con nivel de Información. Puedes usarlo para éxito o mensajes generales.",
                    "Informacion" // Probar con "Error", "Advertencia", "Critico"
            );

            // Modal de Error (Rojo/X)
            NotificationModal errorModal = new NotificationModal(
                    "errorModal", // ID único
                    "Error Crítico",
                    "No se pudo conectar a la base de datos. Por favor, revisa la conexión.",
                    "Error"
            );

            Button buttonInfo = new Button()
                    .text("Mostrar Modal")
                    .color("blue")
                    .onClick("openInfoModalEvent()");

            Script scriptCloseInfoModal = new Script()
                    .closeModal("infoModal", "openInfoModalEvent");

            Button buttonError = new Button()
                    .text("Error")
                    .color("red")
                    .onClick("openErrorModal()");

            Script scriptCloseError = new Script().closeModal("errorModal", "openErrorModal");

// 3. CONSTRUCCIÓN DEL CONTENIDO PRINCIPAL 
            WebComponent webContent = new Div().withClass("p-8 min-h-screen flex flex-col items-center justify-center bg-gray-100 dark:bg-gray-900")
                    .withClass(webModel.getIsTailwind() ? "space-y-4" : "") // Añadir espaciado de Tailwind
                    .add(infoModal)
                    .add(buttonInfo)
                    .add(scriptCloseInfoModal)
                    .add(errorModal)
                    .add(buttonError)
                    .add(scriptCloseError);

            // Se anade modalTaildwindConfiguration al header para el modal mediante un Script para otros casos no es necesario
            headers = new ArrayList<>();
//            headers.add(new Script().modalTailwindConfig());
            headers.add(new Script().modalTailwindConfig());

            mainContent = new Panel("Modal", webContent, request);
        
        
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

    @Override
    protected WebComponent content(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
