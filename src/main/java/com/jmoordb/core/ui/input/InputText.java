/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.input;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.Tag;

/**
 *
 * @author avbravo
 */
public class InputText extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public InputText() {

        super("input");
        withAttribute("type", "text");

    }

    public InputText text(String text) {
        withText(text);
        return this;
    }

    public InputText id(String id) {
        withAttribute("id", id);
        return this;
    }
    
        public InputText name(String name) {
        withAttribute("name", name);
        return this;
    }

     public InputText styleClass(String styleClass) {
      withClass(styleClass);
        return this;
    }



    public InputText value(String value) {
        withAttribute("value", value);
        return this;
    }

    public InputText step(String step) {
        withAttribute("step", step);
        return this;
    }

    public InputText readonly(Boolean readonly) {
        if (readonly) {
            withAttribute("readonly", "");
        }

        return this;
    }

    public InputText required(Boolean required) {
        if (required) {
            withAttribute("required", "true");
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
