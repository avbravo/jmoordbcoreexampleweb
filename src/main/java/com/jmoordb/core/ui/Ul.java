/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui;

/**
 *
 * @author avbravo
 */


import com.jmoordb.core.ui.Tag;

/**
 * Clase para generar una etiqueta HTML <ul> (Unordered List).
 * Extiende Tag para heredar el mecanismo de renderizado y el Fluent Interface.
 * * @author avbravo
 */
public class Ul extends Tag {

    /**
     * Constructor. Inicializa la etiqueta con el nombre "ul".
     */
    public Ul() {
        super("ul");
    }

    // --- Métodos de Chaining Fluido específicos para UL ---
    
    /**
     * Añade un Componente (otro Tag o componente custom) como hijo.
     * Sobrescribe para mantener el tipo de retorno Ul.
     */
    @Override
    public Ul add(WebComponent child) {
        super.add(child);
        return this;
    }
    
    /**
     * Establece el atributo 'id'.
     */
    public Ul id(String id) {
        withAttribute("id", id);
        return this;
    }
    
    /**
     * Establece o concatena clases CSS específicas para la UL.
     */
   public Ul addClass(String styleClass) {
      withClass(styleClass);
        return this;
    }

    
    /**
     * Método final para completar la construcción de la etiqueta.
     */
    public Tag build() {
        return this;
    }
}