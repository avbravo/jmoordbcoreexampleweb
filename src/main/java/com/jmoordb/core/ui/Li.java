/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui;

import com.jmoordb.core.ui.Tag;

/**
 * Clase para generar una etiqueta HTML <li> (List Item).
 * * @author avbravo
 */
public class Li extends Tag {

    /**
     * Constructor. Inicializa la etiqueta con el nombre "li".
     */
    public Li() {
        super("li");
    }
    public Li(String cssClass) {
        super("li");
           withClass(cssClass);
    }

    // --- Métodos de Chaining Fluido específicos para LI ---

    /**
     * Añade un Componente (otro Tag o componente custom) como hijo.
     * Sobrescribe para mantener el tipo de retorno Li.
     */
    @Override
    public Li add(WebComponent child) {
        super.add(child);
        return this;
    }
    
    /**
     * Añade contenido de texto simple.
     */
    public Li text(String text) {
        super.withText(text);
        return this;
    }
    
    /**
     * Establece el atributo 'id'.
     */
    public Li id(String id) {
        withAttribute("id", id);
        return this;
    }

    /**
     * Establece o concatena clases CSS específicas para el ítem.
     */
    public Li addClass(String cssClass) {
        withClass(cssClass);
        return this;
    }
    
    /**
     * Método final para completar la construcción de la etiqueta.
     */
    public Tag build() {
        return this;
    }
}