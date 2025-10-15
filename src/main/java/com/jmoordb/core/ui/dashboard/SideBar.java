/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.dashboard;

/**
 *
 * @author avbravo
 */




import com.jmoordb.core.ui.menu.MenuLink;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class SideBar implements WebComponent {
    private final HttpServletRequest request;
    private final Map<String, List<MenuLink>> menuSections; 

    public SideBar(HttpServletRequest request, Map<String, List<MenuLink>> menuSections) {
        this.request = request;
        this.menuSections = menuSections;
    }

    @Override
    public String render() {
        String contextPath = request.getContextPath();
        
        // 1. Estructura principal del Sidebar (con las clases CSS para el layout fijo)
        Tag sidebar = new Tag("div").withClass("sidebar shadow-lg")
            .withAttribute("id", "mySidebar");
            
        // 2. Inicializar el <ul> donde irán todos los menús
        Tag menuUl = new Tag("ul").withClass("nav flex-column sidebar-nav mt-4");
        
        // 3. Generar secciones de menú, títulos y separadores
        menuSections.forEach((sectionTitle, links) -> {
            
            // Si el título no está vacío, añade el título de la sección
            if (sectionTitle != null && !sectionTitle.isEmpty()) {
                menuUl.withChild(new Tag("li").withClass("sidebar-header").withText(sectionTitle));
            } else {
                // Si el título es vacío, añade un separador
                menuUl.withChild(new Tag("li").withChild(new Tag("hr").withClass("sidebar-divider")));
            }

            // 4. Añadir Enlaces de la Sección
            if (links != null) { // CRÍTICO: Asegurarse de que la lista no es nula
                links.forEach(link -> {
                    String activeClass = link.isActive() ? "active" : "";
                    
                    Tag icon = new Tag("i").withClass(link.getIconClass() + " me-2");
                    
                    Tag anchor = new Tag("a").withClass("nav-link " + activeClass)
                        .withAttribute("href", contextPath + link.getUrl())
                        // Añade el ícono como un hijo
                        .withChild(icon) 
                        // Añade el texto como contenido (usando la versión corregida de Tag.withText)
                        .withText(link.getText()); 
                    
                    menuUl.withChild(new Tag("li").withClass("nav-item").withChild(anchor));
                });
            }
        });

        sidebar.withChild(menuUl);
        return sidebar.render();
    }
}