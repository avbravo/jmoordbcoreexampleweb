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

public class FooterOld_1 implements WebComponent {
    private final String copyrightText;

    public FooterOld_1(String copyrightText) {
        this.copyrightText = copyrightText;
    }

    @Override
    public String render() {
        // Estructura principal: <footer> con clase para auto-margin (mt-auto)
        Tag footer = new Tag("footer").withClass("footer mt-auto py-3 bg-light-custom");
        
        // Contenedor interno para centrar el contenido
        Tag container = new Tag("div").withClass("container text-center");
        
        // 1. Texto de Copyright
        container.withChild(new Tag("span").withClass("text-muted d-block")
            .withText(copyrightText));
            
        // 2. Enlaces Legales o de Información
        container.withChild(new Tag("div").withClass("small mt-1")
            .withChild(new Tag("a").withAttribute("href", "/privacy").withText("Privacy Policy"))
            .withText(" | ")
            .withChild(new Tag("a").withAttribute("href", "/terms").withText("Terms of Use")));
        
        // 3. Iconos de Redes Sociales (Ejemplo con Font Awesome)
        container.withChild(new Tag("div").withClass("social-icons mt-2")
            .withChild(new Tag("a").withAttribute("href", "#").withClass("mx-2 text-secondary").withChild(new Tag("i").withClass("fab fa-twitter")))
            .withChild(new Tag("a").withAttribute("href", "#").withClass("mx-2 text-secondary").withChild(new Tag("i").withClass("fab fa-facebook")))
            .withChild(new Tag("a").withAttribute("href", "#").withClass("mx-2 text-secondary").withChild(new Tag("i").withClass("fab fa-linkedin")))
        );

        footer.withChild(container);
        return footer.render();
    }
}
