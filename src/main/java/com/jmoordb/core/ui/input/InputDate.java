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
public class InputDate extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public InputDate() {

        super("input");
        withAttribute("type", "date");

    }

    public InputDate text(String text) {
        withText(text);
        return this;
    }

    public InputDate id(String id) {
        withAttribute("id", id);
        return this;
    }
    
        public InputDate name(String name) {
        withAttribute("name", name);
        return this;
    }

     public InputDate styleClass(String styleClass) {
      withClass(styleClass);
        return this;
    }



    public InputDate value(String value) {
        withAttribute("value", value);
        return this;
    }

    public InputDate step(String step) {
        withAttribute("step", step);
        return this;
    }

    public InputDate readonly(Boolean readonly) {
        if (readonly) {
            withAttribute("readonly", "");
        }

        return this;
    }

    public InputDate required(Boolean required) {
        if (required) {
            withAttribute("required", "true");
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
