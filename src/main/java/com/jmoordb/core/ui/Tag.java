/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui;

/**
 *
 * @author avbravo
 */
import com.jmoordb.core.ui.css.type.CssTypeH3Services;
import com.jmoordb.core.ui.css.type.CssTypeInputServices;
import com.jmoordb.core.ui.css.type.CssTypeLabelServices;
import java.util.*;

public class Tag implements WebComponent {
    // <editor-fold defaultstate="collapsed" desc="css">

    CssTypeH3Services cssTypeH3Services = new CssTypeH3Services();
    CssTypeInputServices cssTypeInputServices = new CssTypeInputServices();
    CssTypeLabelServices cssTypeLabelServices = new CssTypeLabelServices();
    // </editor-fold>
    private final String tagName;
    private final List<WebComponent> childs = new ArrayList<>();
    private final Map<String, String> attributes = new HashMap<>();

    // <editor-fold defaultstate="collapsed" desc="set/get">

    public CssTypeH3Services getCssTypeH3Services() {
        return cssTypeH3Services;
    }

    public void setCssTypeH3Services(CssTypeH3Services cssTypeH3Services) {
        this.cssTypeH3Services = cssTypeH3Services;
    }

    public CssTypeInputServices getCssTypeInputServices() {
        return cssTypeInputServices;
    }

    public void setCssTypeInputServices(CssTypeInputServices cssTypeInputServices) {
        this.cssTypeInputServices = cssTypeInputServices;
    }

    public CssTypeLabelServices getCssTypeLabelServices() {
        return cssTypeLabelServices;
    }

    public void setCssTypeLabelServices(CssTypeLabelServices cssTypeLabelServices) {
        this.cssTypeLabelServices = cssTypeLabelServices;
    }
    
    // </editor-fold>
    

    /**
     * Constructor.
     *
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
    public Tag add(WebComponent child) {
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


     public Tag modalTailwindConfig(){
         withText("<script>tailwind.config = { darkMode: 'class', theme: { extend: {}, }, }</script>");

        return this;
    }
    /**
     * Permite acceder a la lista de hijos (útil para modificar el último hijo o
     * anidación compleja).
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
        attributes.forEach((name, value)
                -> sb.append(" ").append(name).append("=\"").append(value).append("\"")
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
    public static class TextComponent implements WebComponent {

        private final String content;

        public TextComponent(String content) {
            this.content = content;
        }

        @Override
        public String render() {
            return content;
        }
    }
    
    
    
}
