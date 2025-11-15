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
    private final Integer notificationCount; 
    private final Boolean showNotification;

    public NavBar(String username, HttpServletRequest request, Boolean showNotification, Integer notificationCount) {
        this.username = username;
        this.request = request;
        this.notificationCount = notificationCount;
        this.showNotification = showNotification;
    }

    @Override
    public String render() {
        String contextPath = request.getContextPath();
        String framework = (String) request.getSession().getAttribute("cssFramework");
        boolean isTailwind = "tailwind".equals(framework);

       
        
        // Clases Condicionales
        String navClass = isTailwind ? "navbar-custom fixed top-0 left-0 right-0 z-50 flex items-center justify-between" : "navbar navbar-expand-lg navbar-custom fixed-top";
        String containerClass = isTailwind ? "w-full mx-auto px-4 flex items-center justify-between" : "container-fluid";
        String rightContainerClass = isTailwind ? "flex items-center space-x-3 ml-auto mr-3" : "d-flex align-items-center ms-auto me-3";
        String btnClass = isTailwind ? "p-2 text-sm rounded-md" : "btn btn-sm";
        String dropdownBtnClass = isTailwind ? "bg-transparent hover:bg-gray-700 p-2 text-sm rounded-md text-white dropdown-toggle" : "btn btn-sm dropdown-toggle text-light";
        String textLightClass = isTailwind ? "text-white" : "text-light";
//        String badgeClass = isTailwind ? "absolute top-0 right-0 translate-x-1/2 -translate-y-1/2 bg-red-600 text-white text-xs font-bold rounded-full px-2 py-0.5" : "position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger";
  // ⭐ CORRECCIÓN: Se añadió 'text-white' para Bootstrap para asegurar la visibilidad del número.
        String badgeClass = isTailwind
                ? "absolute top-0 right-0 translate-x-1/2 -translate-y-1/2 bg-red-600 text-white text-xs font-bold rounded-full px-2 py-0.5"
                : "position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger text-white";

        // Estructura principal: <nav>
        Tag navBar = new Tag("nav").withClass(navClass);
        Tag containerFluid = new Tag("div").withClass(containerClass);

        // 1. Botón Hamburguesa (Sidebar Toggle)
        Tag sidebarToggle = new Tag("button").withClass(btnClass + " me-3 sidebar-toggle-btn " + textLightClass)
                .withAttribute("onclick", "toggleSidebar()")
                .withChild(new Tag("i").withClass("fas fa-bars"));

        containerFluid.withChild(sidebarToggle);

        // Marca/Logo
        String brandClass = isTailwind ? "text-xl font-bold hover:text-white" : "navbar-brand";
        containerFluid.withChild(new Tag("a").withClass(brandClass)
                .withAttribute("href", contextPath + "/api/dashboard")
                .withText("Dashboard Central"));

        // Contenedor de la derecha
        Tag rightContainer = new Tag("div").withClass(rightContainerClass);

        if (showNotification) {
            
            // 2. Icono de Notificaciones
            Tag notifIcon = new Tag("i").withClass("fas fa-bell");
            Tag badgeSpan = new Tag("span").withClass(badgeClass).withText(notificationCount.toString());
            
            // ⭐ Asegúrate de que el enlace sea 'relative' (tanto para Tailwind como para Bootstrap)
            String linkClasses = isTailwind 
                ? btnClass + " me-3 " + textLightClass + " relative" 
                : btnClass + " me-3 " + textLightClass + " position-relative";
                
            Tag notifLink = new Tag("a").withClass(linkClasses) 
                    .withAttribute("href", "#notifications")
                    .withChild(notifIcon)
                    .withChild(badgeSpan);
            
            rightContainer.withChild(notifLink);
        }

        // 3. Selector de Tema (Dark/White Mode)
        rightContainer.withChild(new Tag("button").withClass(btnClass + " me-3 theme-toggle " + textLightClass)
                .withAttribute("onclick", "toggleTheme()")
                .withChild(new Tag("i").withClass("fas fa-sun")));

          // ⭐ 4. Selector de Framework CSS (Desplegable)
//        String dropdownMenuClass = isTailwind ? "absolute right-0 mt-2 w-48 rounded-md shadow-lg bg-content z-50" : "dropdown-menu dropdown-menu-end";
//        String dropdownItemClass = isTailwind ? "block px-4 py-2 text-sm text-text-color hover:bg-gray-700" : "dropdown-item";

//        Tag frameworkDropdown = new Tag("div").withClass("dropdown me-3");
//
//        Tag dropdownButton = new Tag("button").withClass(dropdownBtnClass)
//                .withAttribute("type", "button")
//                .withAttribute("data-bs-toggle", "dropdown")
//                .withAttribute("aria-expanded", "false")
//                .withText(framework.toUpperCase());
//
//        Tag dropdownMenu = new Tag("ul").withClass(dropdownMenuClass);

      
    //         // Opción Bootstrap
    //        dropdownMenu.withChild(new Tag("li").withChild(new Tag("a").withClass(dropdownItemClass)
    //                .withAttribute("href", "javascript:void(0)")
    //                .withAttribute("onclick", "document.querySelector('.dropdown-toggle').click(); setCssFramework('bootstrap');") // ⭐ Prioriza el click de cierre
    //                .withText("Bootstrap")));
    //
    //        // Opción Tailwind CSS
    //        dropdownMenu.withChild(new Tag("li").withChild(new Tag("a").withClass(dropdownItemClass)
    //                .withAttribute("href", "javascript:void(0)")
    //                .withAttribute("onclick", "document.querySelector('.dropdown-toggle').click(); setCssFramework('tailwind');") // ⭐ Prioriza el click de cierre
    //                .withText("Tailwind CSS")));
    //        
        
        
        
        
//// Opción Bootstrap
//dropdownMenu.withChild(new Tag("li").withChild(new Tag("a").withClass(dropdownItemClass)
//    .withAttribute("href", "javascript:void(0)")
//    // ⭐ SIMPLIFIED LOGIC: Call setCssFramework only. The reload handles the close.
//    .withAttribute("onclick", "setCssFramework('bootstrap');") 
//    .withText("Bootstrap")));
//
//// Opción Tailwind CSS
//dropdownMenu.withChild(new Tag("li").withChild(new Tag("a").withClass(dropdownItemClass)
//    .withAttribute("href", "javascript:void(0)")
//    // ⭐ SIMPLIFIED LOGIC: Call setCssFramework only. The reload handles the close.
//    .withAttribute("onclick", "setCssFramework('tailwind');") 
//    .withText("Tailwind CSS")));




//-----------------------------------


//// 4. Selector de Framework CSS (Desplegable)
//// ...
//
//// Opción Bootstrap
//dropdownMenu.withChild(new Tag("li").withChild(new Tag("a").withClass(dropdownItemClass)
//    .withAttribute("href", "javascript:void(0)")
//    // ⭐ CORRECCIÓN: Usar setTimeout para garantizar que el cierre de Bootstrap ocurra ANTES de la recarga.
//    .withAttribute("onclick", "document.querySelector('.dropdown-toggle').click(); setTimeout(() => setCssFramework('bootstrap'), 10);") 
//    .withText("Bootstrap")));
//
//// Opción Tailwind CSS
//dropdownMenu.withChild(new Tag("li").withChild(new Tag("a").withClass(dropdownItemClass)
//    .withAttribute("href", "javascript:void(0)")
//    // ⭐ CORRECCIÓN: Usar setTimeout para garantizar que el cierre de Bootstrap ocurra ANTES de la recarga.
//    .withAttribute("onclick", "document.querySelector('.dropdown-toggle').click(); setTimeout(() => setCssFramework('tailwind'), 10);") 
//    .withText("Tailwind CSS")));
//// ...

// ...

        
//        frameworkDropdown.withChild(dropdownButton).withChild(dropdownMenu);
//        rightContainer.withChild(frameworkDropdown);

        // 5. Información de Usuario Logeado
        String usernameClass = isTailwind ? "hidden md:block text-sm text-gray-400 mr-3" : "navbar-text me-3 text-white-50";
        rightContainer.withChild(new Tag("span").withClass(usernameClass)
                .withText(username));

        // 6. Botón de Logout
        String logoutBtnClass = isTailwind ? "border border-red-500 text-red-500 hover:bg-red-500 hover:text-white p-2 text-sm rounded-md" : "btn btn-outline-danger btn-sm";
        rightContainer.withChild(new Tag("a").withClass(logoutBtnClass)
                .withAttribute("href", contextPath + "/logout")
                .withText("Logout"));

        containerFluid.withChild(rightContainer);
        navBar.withChild(containerFluid);

        return navBar.render();
    }
}
