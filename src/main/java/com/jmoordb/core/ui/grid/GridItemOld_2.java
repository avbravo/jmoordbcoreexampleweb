/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.grid;


import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;

public class GridItemOld_2 implements WebComponent {
    private final String title;
    private final String content;
    private final String iconClass;

    public GridItemOld_2(String title, String content, String iconClass) {
        this.title = title;
        this.content = content;
        this.iconClass = iconClass;
    }

    @Override
    public String render() {
        // Estructura de Card de Bootstrap
        Tag card = new Tag("div").withClass("card shadow-sm");
        Tag cardBody = new Tag("div").withClass("card-body d-flex align-items-center");

        // Icono (lado izquierdo)
        Tag iconContainer = new Tag("div").withClass("p-3 me-3 rounded-circle bg-primary text-white");
        iconContainer.withChild(new Tag("i").withClass(iconClass + " fa-lg"));
        
        // Contenido (lado derecho)
        Tag textContainer = new Tag("div");
        textContainer.withChild(new Tag("h5").withClass("card-title mb-0").withText(title));
        textContainer.withChild(new Tag("p").withClass("card-text mb-0").withText(content));
        
        cardBody.withChild(iconContainer).withChild(textContainer);
        card.withChild(cardBody);
        
        return card.render();
    }
}