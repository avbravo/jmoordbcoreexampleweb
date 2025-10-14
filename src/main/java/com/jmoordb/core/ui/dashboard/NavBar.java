/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.dashboard;

/**
 *
 * @author avbravo
 */


import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import jakarta.servlet.http.HttpServletRequest;

public class NavBar implements WebComponent {

    private final String username;
    private final HttpServletRequest request;

    public NavBar(String username, HttpServletRequest request) {
        this.username = username;
        this.request = request;
    }

    @Override
    public String render() {
        String contextPath = request.getContextPath();
        
        // Estructura principal: <nav>
        Tag navBar = new Tag("nav").withClass("navbar navbar-expand-lg navbar-custom fixed-top");
        Tag containerFluid = new Tag("div").withClass("container-fluid");

        // 1. Botón Hamburguesa (Sidebar Toggle)
        // Este botón ejecuta la función JS 'toggleSidebar()' y es clave para la responsividad.
        Tag sidebarToggle = new Tag("button").withClass("btn btn-sm me-3 sidebar-toggle-btn")
            .withAttribute("onclick", "toggleSidebar()")
            .withAttribute("aria-label", "Toggle Sidebar")
            .withChild(new Tag("i").withClass("fas fa-bars"));
        
        containerFluid.withChild(sidebarToggle);
        
        // Marca/Logo del Dashboard
        containerFluid.withChild(new Tag("a").withClass("navbar-brand")
            .withAttribute("href", contextPath + "/dashboard")
            .withText("Dashboard Central"));
            
        // Contenedor de la derecha (alineado a la derecha con ms-auto)
        Tag rightContainer = new Tag("div").withClass("d-flex align-items-center ms-auto me-3");
        
        // 2. Icono de Notificaciones (Con contador de ejemplo)
        rightContainer.withChild(new Tag("a").withClass("btn btn-sm me-3 text-white position-relative")
            .withAttribute("href", "#notifications")
            .withAttribute("aria-label", "Notifications")
            .withChild(new Tag("i").withClass("fas fa-bell"))
            .withChild(new Tag("span").withClass("position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger").withText("3"))); 
        
        // 3. Selector de Tema (Dark/White Mode)
        // Ejecuta la función JS 'toggleTheme()'
//        rightContainer.withChild(new Tag("button").withClass("btn btn-sm me-3 theme-toggle")
//            .withAttribute("onclick", "toggleTheme()")
//            .withAttribute("aria-label", "Toggle Theme")
//            .withChild(new Tag("i").withClass("fas fa-sun"))); 
        
        // 3. Selector de Tema (Dark/White Mode) - ¡Aseguramos el color y la visibilidad!
rightContainer.withChild(new Tag("button").withClass("btn btn-sm me-3 theme-toggle text-light") // << Usamos text-light para forzar el blanco/gris claro
    .withAttribute("onclick", "toggleTheme()")
    .withAttribute("aria-label", "Toggle Theme")
    // El ícono visible por defecto (sol) es fas fa-sun
    .withChild(new Tag("i").withClass("fas fa-sun")));


        // 4. Información de Usuario Logeado
        rightContainer.withChild(new Tag("span").withClass("navbar-text me-3 text-white-50") 
            .withText("Hello, " + username));
        
        // 5. Botón de Logout
        rightContainer.withChild(new Tag("a").withClass("btn btn-outline-light btn-sm")
            .withAttribute("href", contextPath + "/logout")
            .withText("Logout"));
            
        // Ensamblaje final
        containerFluid.withChild(rightContainer);
        navBar.withChild(containerFluid);
        
        return navBar.render();
    }
}