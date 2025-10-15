/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.panel;

/**
 *
 * @author avbravo
 */


import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;

public class Panel1 implements WebComponent {
    private final String title;
    private final WebComponent content;

    public Panel1(String title, WebComponent content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String render() {
        // Panel principal usando card de Bootstrap
        Tag card = new Tag("div").withClass("card shadow-sm mb-4");
        
        // Encabezado
        card.withChild(new Tag("div").withClass("card-header")
            .withChild(new Tag("h5").withClass("mb-0").withText(title)));
        
        // Cuerpo
        card.withChild(new Tag("div").withClass("card-body")
            .withChild(content));
        
        return card.render();
    }
}