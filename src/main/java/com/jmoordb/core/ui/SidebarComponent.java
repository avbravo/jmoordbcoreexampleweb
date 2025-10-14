/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui;

/**
 *
 * @author avbravo
 */




import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

public class SidebarComponent implements WebComponent {
    private final HttpServletRequest request;
    private final List<MenuLink> sidebarLinks;

    public SidebarComponent(HttpServletRequest request, List<MenuLink> sidebarLinks) {
        this.request = request;
        this.sidebarLinks = sidebarLinks;
    }

    @Override
    public String render() {
        String contextPath = request.getContextPath();
        
        // Creamos la etiqueta <ul> que contendrá los enlaces.
        Tag menuUl = new Tag("ul").withClass("nav flex-column sidebar-nav");
        
        // 1. Generamos los enlaces (<li><a>) y los añadimos al <ul>
        sidebarLinks.forEach(link -> {
            String activeClass = link.isActive() ? "active" : "";
            Tag anchor = new Tag("a").withClass("nav-link " + activeClass)
                .withAttribute("href", contextPath + link.getUrl())
                .withChild(new Tag("i").withClass(link.getIconClass() + " me-2")) 
                .withText(link.getText());

            // Añadimos el <li> con el <a> al <ul>
            menuUl.withChild(new Tag("li").withClass("nav-item").withChild(anchor));
        });
        
        // 2. Creamos la estructura principal del Sidebar (<div>) y le añadimos el <ul>
        Tag sidebar = new Tag("div").withClass("sidebar")
            .withAttribute("id", "mySidebar")
            .withChild(menuUl); // Añadimos el <ul> aquí
        
        // 3. Botón de Toggle
        Tag toggleBtn = new Tag("button").withClass("sidebar-toggle btn btn-sm btn-custom")
            .withAttribute("onclick", "toggleSidebar()")
            .withChild(new Tag("i").withClass("fas fa-bars"));

        // Retornamos el botón de toggle y el sidebar
        return toggleBtn.render() + sidebar.render();
    }
}