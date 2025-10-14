/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui;

/**
 *
 * @author avbravo
 */


import java.util.*;

public class Tag implements WebComponent {
    private final String tagName;
    private final List<WebComponent> childs = new ArrayList<>();
    private final Map<String, String> attributes = new HashMap<>();

    /**
     * Constructor.
     * @param tagName El nombre de la etiqueta HTML (e.g., "div", "a", "input").
     */
    public Tag(String tagName) {
        this.tagName = tagName.toLowerCase();
    }

    // --- Métodos de Chaining Fluido (Fluent Interface) ---
    
    /**
     * Añade un Componente (otro Tag o componente custom) como hijo.
     */
    public Tag withChild(WebComponent child) {
        if (child != null) {
            childs.add(child);
        }
        return this;
    }

    /**
     * Añade contenido de texto simple.
     */
    public Tag withText(String text) {
        if (text != null) {
            // Usa una clase interna simple para manejar el texto como un Componente
            return withChild(new TextComponent(text));
        }
        return this;
    }
    
    /**
     * Añade o concatena clases CSS.
     */
    public Tag withClass(String cssClass) {
        attributes.merge("class", cssClass, (current, newClass) -> current + " " + newClass);
        return this;
    }
    
    /**
     * Añade un atributo y su valor (e.g., "id", "myId").
     */
    public Tag withAttribute(String name, String value) {
        attributes.put(name, value);
        return this;
    }

    /**
     * Permite acceder a la lista de hijos (útil para modificar el último hijo o anidación compleja).
     */
    public List<WebComponent> getChilds() {
        return childs;
    }

    // --- Implementación del Renderizado ---
    
    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        
        // 1. Apertura de la etiqueta: <div class="...">
        sb.append("<").append(tagName);
        attributes.forEach((name, value) -> 
            sb.append(" ").append(name).append("=\"").append(value).append("\"")
        );
        
        // Determinar si la etiqueta es de cierre automático (self-closing)
        boolean isSelfClosing = tagName.equals("input") || tagName.equals("img") || tagName.equals("link") || tagName.equals("meta");

        if (isSelfClosing) {
             sb.append(">"); // <input type="text">
        } else {
            sb.append(">");
            // 2. Contenido recursivo (Hijos)
            childs.forEach(child -> sb.append(child.render()));
            // 3. Cierre de la etiqueta: </div>
            sb.append("</").append(tagName).append(">");
        }
        
        return sb.toString();
    }

    // Clase interna simple para manejar texto plano (un componente que no es una etiqueta)
    private static class TextComponent implements WebComponent {
        private final String content;
        public TextComponent(String content) { this.content = content; }
        @Override public String render() { return content; }
    }
}