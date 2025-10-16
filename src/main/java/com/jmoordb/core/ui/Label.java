/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui;

import com.jmoordb.core.ui.Tag;

/**
 *
 * @author avbravo
 */
public class Label extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Label() {

        super("input");

    }

    public Label text(String text) {
        withText(text);
        return this;
    }
    public Label classStyle(String labelClass) {
      withClass(labelClass);
        return this;
    }

    public Label forField(String field) {
        withAttribute("for", field);
        return this;
    }
    public Label id(String id) {
        withAttribute("id", id);
        return this;
    }
   

    public Tag build() {
        return this;
    }

}
