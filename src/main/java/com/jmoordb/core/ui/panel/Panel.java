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
import jakarta.servlet.http.HttpServletRequest;

public class Panel implements WebComponent {
    private final String title;
    private final WebComponent content;
    private final HttpServletRequest request;

    public Panel(String title, WebComponent content, HttpServletRequest request) {
        this.title = title;
        this.content = content;
        this.request = request;
    }

    @Override
    public String render() {
        String framework = (String) request.getSession().getAttribute("cssFramework");
        boolean isTailwind = "tailwind".equals(framework);

        // Clases Condicionales
        String cardClass = isTailwind ? "card rounded-lg shadow-md mb-4" : "card shadow-sm mb-4";
        String headerClass = isTailwind ? "p-3 border-b border-border-color card-header" : "card-header";
        String titleClass = isTailwind ? "text-lg font-semibold mb-0" : "mb-0";
        String bodyClass = isTailwind ? "p-4 card-body" : "card-body";
        
        // Panel principal usando card
        Tag card = new Tag("div").withClass(cardClass);
        
        // Encabezado
        card.withChild(new Tag("div").withClass(headerClass)
            .withChild(new Tag("h5").withClass(titleClass).withText(title)));
        
        // Cuerpo
        card.withChild(new Tag("div").withClass(bodyClass)
            .withChild(content));
        
        return card.render();
    }
}