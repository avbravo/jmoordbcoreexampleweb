/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.controller.modal;

/**
 *
 * @author avbravo
 */


import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.alert.NotificationModal;
import com.jmoordb.core.ui.dashboard.DashboardLayout;
import com.jmoordb.core.ui.modal.NotificationModal;
import com.jmoordb.core.ui.menu.MenuLink; // Importar MenuLink para la Sidebar
import com.jmoordb.core.ui.panel.Panel;
import fish.payara.dashboard.MenuService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ModalController", urlPatterns = {"/modal-test"})
public class ModalController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
          HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        // --- Setup Mínimo para el Dashboard ---
     String username = (String) session.getAttribute("usuario");
         String userRol = (String) session.getAttribute("rol"); 
        if (userRol == null) userRol = "ADMIN"; 
        String title = "Prueba de Modal en Dashboard";

        // Simular las secciones del menú lateral (requerido por DashboardLayout)
      
             Map<String, List<MenuLink>> sidebarSections = MenuService.getSidebarSections(
            this.getClass().getSimpleName(), 
            username, 
            userRol
        );
        // --- 1. Crear el NotificationModal (Error para el ejemplo) ---
        WebComponent errorModal = new NotificationModal(
                "errorModal", 
                "Error de Prueba de Modal",
                "Este modal se activó mediante un botón dentro del contenido principal del dashboard.",
                "Error" // Nivel que genera icono y color rojo
        );

        // --- 2. Crear el Contenido Principal (Botón) ---
        Tag activationButton = new Tag("button")
                .withAttribute("onclick", "openModal('errorModal')") // Llama a la función JS con el ID
                .withClass("px-6 py-3 bg-red-600 text-white rounded-lg shadow-md hover:bg-red-700 transition duration-150")
                .withText("Abrir Modal de Error");

        Tag mainContentContainer = new Tag("div").withClass("bg-white dark:bg-gray-800 p-8 rounded-lg shadow-xl min-h-[400px]")
                .withChild(new Tag("h2").withClass("text-2xl font-semibold mb-6 text-gray-800 dark:text-white").withText("Contenido de Prueba del Modal"))
                .withChild(activationButton);

//        // --- 3. Invocar DashboardLayout ---
//        String htmlCompleto = DashboardLayout.buildPage(
//            request, 
//            username, 
//            mainContentContainer, // Contenido principal con el botón
//            sidebarSections, 
//            title, 
//            errorModal // ⭐ El modal se pasa como componente y se inyecta en el <body>
//        );
  // Contenido de ejemplo para el panel de perfil
        WebComponent mainPanel = new Tag("div")
            .withChild(header)
            .withChild(new Tag("p").withText("Email: " + username.toLowerCase() + "@example.com"))
            .withChild(new Tag("p").withText("Role: Administrator"))
            .withChild(new Tag("hr"))
            .withChild(new Tag("button").withClass("btn btn-primary").withText("Edit Profile"))
            .withChild(new Tag("button").withClass("btn btn-secondary ms-2").withText("Change Password"));
           
        String htmlCompleto = DashboardLayout.buildPage(
            request, 
            username, 
            mainPanel, 
            sidebarSections, 
            "Modal" 
        );

        response.getWriter().write(htmlCompleto);
    }
    
    // Función de utilidad para simular los enlaces del menú
    private Map<String, List<MenuLink>> createDemoSidebarSections(String contextPath) {
        Map<String, List<MenuLink>> sections = new HashMap<>();
        List<MenuLink> mainLinks = new ArrayList<>();
        mainLinks.add(new MenuLink("Clientes", contextPath + "/clientes", "fas fa-users"));
        mainLinks.add(new MenuLink("Modal Test", contextPath + "/modal-test", "fas fa-bell"));
        sections.put("Principal", mainLinks);
        return sections;
    }
}