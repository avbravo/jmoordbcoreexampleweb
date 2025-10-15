/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.controller;

/**
 *
 * @author avbravo
 */
import fish.payara.dashboard.MenuService;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.dashboard.DashboardLayout;
import com.jmoordb.core.ui.menu.MenuLink;
import com.jmoordb.core.ui.panel.Panel;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/profile")
public class ProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        HttpSession session = request.getSession(false);
        // 1. Validar Sesión
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String username = (String) session.getAttribute("usuario");
             // ⭐ SIMULACIÓN DE ROL (DEBE VENIR DE LA SESIÓN O DB)
        String userRol = (String) session.getAttribute("rol"); // Asumimos que el rol está en sesión
        if (userRol == null) userRol = "ADMIN"; // Valor por defecto para prueba
        
        // ⭐ 2. DEFINICIÓN DE MENÚS SIMPLIFICADA
        // Obtener la lista de menús desde el nuevo servicio.
        Map<String, List<MenuLink>> sidebarSections = MenuService.getSidebarSections(
            this.getClass().getSimpleName(), // Pasa "DashboardController"
            username, 
            userRol
        );
        
        response.setContentType("text/html;charset=UTF-8");
        
        // 3. CONSTRUCCIÓN DEL CONTENIDO PRINCIPAL (ESPECÍFICO DEL PERFIL)
        
        // Contenido de ejemplo para el panel de perfil
        WebComponent profileContent = new Tag("div")
            .withChild(new Tag("p").withText("Username: " + username))
            .withChild(new Tag("p").withText("Email: " + username.toLowerCase() + "@example.com"))
            .withChild(new Tag("p").withText("Role: Administrator"))
            .withChild(new Tag("hr"))
            .withChild(new Tag("button").withClass("btn btn-primary").withText("Edit Profile"))
            .withChild(new Tag("button").withClass("btn btn-secondary ms-2").withText("Change Password"));

        // Panel que contiene la información del perfil
        WebComponent mainPanel = new Panel("User Profile", profileContent, request);
        
        
        // 4. RENDERIZADO FINAL: Se utiliza la clase DashboardLayout
        String htmlCompleto = DashboardLayout.buildPage(
                request, 
                username, 
                mainPanel, // Contenido específico que se inyecta
                sidebarSections, // Estructura del menú
                "User Profile" // Título de la página
        );
            
        response.getWriter().write(htmlCompleto);
    }
}