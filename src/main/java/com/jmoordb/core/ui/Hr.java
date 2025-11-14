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
public class Hr extends Tag {

    /**
     * Constructor. Inicializa la etiqueta con el nombre "li".
     */
    public Hr() {
        super("hr");
    }
   
    // --- Métodos de Chaining Fluido específicos para LI ---

    /**
     * Añade un Componente (otro Tag o componente custom) como hijo.
     * Sobrescribe para mantener el tipo de retorno Li.
     */
   
    /**
     * Método final para completar la construcción de la etiqueta.
     */
    public Tag build() {
        return this;
    }
}