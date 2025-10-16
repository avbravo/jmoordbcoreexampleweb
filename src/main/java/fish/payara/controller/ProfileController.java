/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.controller;


import fish.payara.dashboard.MenuSideBar;
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
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String username = (String) session.getAttribute("username");
        String userRol = (String) session.getAttribute("userRol"); 
        if (userRol == null) userRol = "ADMIN";
        
        // ⭐ 1. Determinar el Framework Actual
        String cssFramework = (String) request.getSession().getAttribute("cssFramework");
        boolean isTailwind = "tailwind".equals(cssFramework);

        // ⭐ 2. Definir Clases CSS Condicionales
        String primaryBtnClass = isTailwind 
                ? "bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded shadow-md transition duration-150" 
                : "btn btn-primary";
        
        String secondaryBtnClass = isTailwind 
                ? "bg-gray-400 hover:bg-gray-500 text-gray-800 font-bold py-2 px-4 rounded shadow-md ml-2 transition duration-150" 
                : "btn btn-secondary ms-2";


        // 2. DEFINICIÓN DE MENÚS SIMPLIFICADA
        Map<String, List<MenuLink>> sidebarSections = MenuSideBar.getSidebarSections(
            this.getClass().getSimpleName(), 
            username, 
            userRol
        );
        
        response.setContentType("text/html;charset=UTF-8");
        
        // 3. CONSTRUCCIÓN DEL CONTENIDO PRINCIPAL (ESPECÍFICO DEL PERFIL)
        
        // Contenido de ejemplo para el panel de perfil. Usamos las clases condicionales.
        WebComponent profileContent = new Tag("div")
            .withClass(isTailwind ? "space-y-4" : "") // Añadir espaciado de Tailwind
            .withChild(new Tag("p").withText("Username: " + username))
            .withChild(new Tag("p").withText("Email: " + username.toLowerCase() + "@example.com"))
            .withChild(new Tag("p").withText("Role: Administrator"))
            .withChild(new Tag("hr").withClass(isTailwind ? "my-4 border-gray-300 dark:border-gray-600" : ""))
            .withChild(new Tag("button").withClass(primaryBtnClass).withText("Edit Profile"))
            .withChild(new Tag("button").withClass(secondaryBtnClass).withText("Change Password"));

        // Panel que contiene la información del perfil
        // NOTA: La clase Panel debe manejar también su estilo interiormente usando el framework de la request.
        WebComponent mainPanel = new Panel("User Profile", profileContent, request);
        
        
        // 4. RENDERIZADO FINAL: Se utiliza la clase DashboardLayout
        // Se asume que DashboardLayout ya maneja el parámetro WebComponent modal (el 5to argumento), 
        // por lo que pasamos 'null' ya que no estamos mostrando un modal aquí.
        String htmlCompleto = DashboardLayout.buildPage(
                request, 
                username, 
                mainPanel, // Contenido específico que se inyecta
                sidebarSections, // Estructura del menú
                "User Profile",
                "© 2024 Modern Dashboard Framework."
        );
            
        response.getWriter().write(htmlCompleto);
    }
}
