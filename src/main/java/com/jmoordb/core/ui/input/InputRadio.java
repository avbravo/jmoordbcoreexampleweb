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
public class InputRadio extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public InputRadio() {

        super("input");
        withAttribute("type", "radio");

    }

    public InputRadio text(String text) {
        withText(text);
        return this;
    }

    public InputRadio id(String id) {
        withAttribute("id", id);
        return this;
    }
    
        public InputRadio name(String name) {
        withAttribute("name", name);
        return this;
    }

     public InputRadio addClass(String withClass) {
      withClass(withClass);
        return this;
    }
    public InputRadio placeholder(String placeholder) {
        withAttribute("placeholder", placeholder);
        return this;
    }


    public InputRadio value(String value) {
        withAttribute("value", value);
        return this;
    }

    public InputRadio step(String step) {
        withAttribute("step", step);
        return this;
    }

    public InputRadio readonly(Boolean readonly) {
        if (readonly) {
            withAttribute("readonly", "");
        }

        return this;
    }

    public InputRadio required(Boolean required) {
        if (required) {
            withAttribute("required", "true");
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
