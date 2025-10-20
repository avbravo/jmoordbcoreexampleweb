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
public class InputEmail extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public InputEmail() {

        super("input");
        withAttribute("type", "email");

    }

    public InputEmail text(String text) {
        withText(text);
        return this;
    }

    public InputEmail id(String id) {
        withAttribute("id", id);
        return this;
    }
    
        public InputEmail placeholder(String placeholder) {
        withAttribute("placeholder", placeholder);
        return this;
    }
        public InputEmail name(String name) {
        withAttribute("name", name);
        return this;
    }

     public InputEmail addClass(String withClass) {
      withClass(withClass);
        return this;
    }



    public InputEmail value(String value) {
        withAttribute("value", value);
        return this;
    }

    public InputEmail step(String step) {
        withAttribute("step", step);
        return this;
    }

    public InputEmail readonly(Boolean readonly) {
        if (readonly) {
            withAttribute("readonly", "");
        }

        return this;
    }

    public InputEmail required(Boolean required) {
        if (required) {
            withAttribute("required", "true");
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
