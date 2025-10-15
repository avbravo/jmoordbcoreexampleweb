/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui;

/**
 *
 * @author avbravo
 */



import com.jmoordb.core.ui.menu.MenuLink;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

public class NavbarComponentOld implements WebComponent {
    private final String username;
    private final HttpServletRequest request;
    private final List<MenuLink> topMenu;

    public NavbarComponentOld(String username, HttpServletRequest request, List<MenuLink> topMenu) {
        this.username = username;
        this.request = request;
        this.topMenu = topMenu;
    }

    @Override
    public String render() {
        String contextPath = request.getContextPath();
        
        // 1. Creamos el contenedor principal del navbar: <div class="container-fluid">
        Tag containerFluid = new Tag("div").withClass("container-fluid")
            // Marca principal y logo
            .withChild(new Tag("a").withClass("navbar-brand ms-3")
                .withAttribute("href", contextPath + "/dashboard")
                .withText("Java Pure Framework"));

        // 2. Contenedor para el menú de navegación (<ul>)
        Tag menuUl = new Tag("ul").withClass("navbar-nav me-auto mb-2 mb-lg-0");
        topMenu.forEach(link -> {
            String activeClass = link.isActive() ? "active" : "";
            Tag anchor = new Tag("a").withClass("nav-link " + activeClass)
                .withAttribute("href", contextPath + link.getUrl())
                .withChild(new Tag("i").withClass(link.getIconClass() + " me-1"))
                .withText(link.getText());
            menuUl.withChild(new Tag("li").withClass("nav-item").withChild(anchor));
        });
        
        // 3. Contenedor de la derecha: Tema, Usuario, Salir
        Tag rightContainer = new Tag("div").withClass("d-flex align-items-center ms-auto");
        
        // Selector de Tema (Botón de Sol/Luna)
        rightContainer.withChild(new Tag("button").withClass("btn btn-sm me-3 theme-toggle")
            .withAttribute("onclick", "toggleTheme()")
            .withAttribute("aria-label", "Toggle Theme")
            .withChild(new Tag("i").withClass("fas fa-sun")));

        // Información de Sesión
        rightContainer.withChild(new Tag("span").withClass("navbar-text me-3") 
            .withText("User: " + username));
        
        // Botón de Salir (Logout)
        rightContainer.withChild(new Tag("a").withClass("btn btn-outline-danger btn-sm")
            .withAttribute("href", contextPath + "/logout")
            .withText("Logout"));
            
        // 4. ENSAMBLAJE DE CONTENEDORES EN EL containerFluid
        containerFluid.withChild(menuUl)      // Añadir menú central
                      .withChild(rightContainer); // Añadir elementos de la derecha

        // 5. Estructura principal: <nav>
        Tag navBar = new Tag("nav").withClass("navbar navbar-expand-lg navbar-custom fixed-top")
            .withChild(containerFluid); // Añadir el contenedor ensamblado
        
        return navBar.render();
    }
}