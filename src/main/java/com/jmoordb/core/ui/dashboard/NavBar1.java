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

public class NavBar1 implements WebComponent {

    private final String username;
    private final HttpServletRequest request;
    private final Integer notificationCount;
    private final Boolean showNotification;

    public NavBar1(String username, HttpServletRequest request, Boolean showNotification,Integer notificationCount ) {
        this.username = username;
        this.request = request;
        this.notificationCount = notificationCount;
        this.showNotification = showNotification;
    }

    @Override
    public String render() {
        String contextPath = request.getContextPath();
        String currentFramework = (String) request.getSession().getAttribute("cssFramework");
        
        // Estructura principal: <nav>
        Tag navBar = new Tag("nav").withClass("navbar navbar-expand-lg navbar-custom fixed-top");
        Tag containerFluid = new Tag("div").withClass("container-fluid");

        // 1. Botón Hamburguesa (Sidebar Toggle)
        Tag sidebarToggle = new Tag("button").withClass("btn btn-sm me-3 sidebar-toggle-btn")
            .withAttribute("onclick", "toggleSidebar()")
            .withChild(new Tag("i").withClass("fas fa-bars"));
        
        containerFluid.withChild(sidebarToggle);
        
        // Marca/Logo
        containerFluid.withChild(new Tag("a").withClass("navbar-brand")
            .withAttribute("href", contextPath + "/dashboard")
            .withText("Dashboard Central"));
            
        // Contenedor de la derecha
        Tag rightContainer = new Tag("div").withClass("d-flex align-items-center ms-auto me-3");
        
        // 2. Icono de Notificaciones
        if(showNotification){
             rightContainer.withChild(new Tag("a").withClass("btn btn-sm me-3 text-white position-relative")
            .withAttribute("href", "#notifications")
            .withChild(new Tag("i").withClass("fas fa-bell"))
            .withChild(new Tag("span").withClass("position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger").withText(notificationCount.toString()))); 
        
        }
       
        // 3. Selector de Tema (Dark/White Mode)
        rightContainer.withChild(new Tag("button").withClass("btn btn-sm me-3 theme-toggle text-light")
            .withAttribute("onclick", "toggleTheme()")
            .withChild(new Tag("i").withClass("fas fa-sun"))); // El ícono se actualiza por JS
        
        // ⭐ 4. Selector de Framework CSS (Desplegable)
        Tag frameworkDropdown = new Tag("div").withClass("dropdown me-3");

        Tag dropdownButton = new Tag("button").withClass("btn btn-sm dropdown-toggle text-light")
            .withAttribute("type", "button")
            .withAttribute("data-bs-toggle", "dropdown") 
            .withAttribute("aria-expanded", "false")
            .withText(currentFramework.toUpperCase());

        Tag dropdownMenu = new Tag("ul").withClass("dropdown-menu dropdown-menu-end");

        // Opción Bootstrap
        dropdownMenu.withChild(new Tag("li").withChild(new Tag("a").withClass("dropdown-item")
            .withAttribute("href", "javascript:void(0)")
            .withAttribute("onclick", "setCssFramework('bootstrap')")
            .withText("Bootstrap")));

        // Opción Tailwind CSS
        dropdownMenu.withChild(new Tag("li").withChild(new Tag("a").withClass("dropdown-item")
            .withAttribute("href", "javascript:void(0)")
            .withAttribute("onclick", "setCssFramework('tailwind')")
            .withText("Tailwind CSS")));

        frameworkDropdown.withChild(dropdownButton).withChild(dropdownMenu);
        rightContainer.withChild(frameworkDropdown);
        
        // 5. Información de Usuario Logeado
        rightContainer.withChild(new Tag("span").withClass("navbar-text me-3 text-white-50") 
            .withText("Hello, " + username));
        
        // 6. Botón de Logout
        rightContainer.withChild(new Tag("a").withClass("btn btn-outline-danger btn-sm")
            .withAttribute("href", contextPath + "/logout")
            .withText("Logout"));
            
        containerFluid.withChild(rightContainer);
        navBar.withChild(containerFluid);
        
        return navBar.render();
    }
}