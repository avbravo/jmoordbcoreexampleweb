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
public class InputCheckBox extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public InputCheckBox() {

        super("input");
        withAttribute("type", "checkbox");

    }

    public InputCheckBox text(String text) {
        withText(text);
        return this;
    }

    public InputCheckBox id(String id) {
        withAttribute("id", id);
        return this;
    }
    
        public InputCheckBox name(String name) {
        withAttribute("name", name);
        return this;
    }

     public InputCheckBox styleClass(String styleClass) {
      withClass(styleClass);
        return this;
    }



    public InputCheckBox value(String value) {
        withAttribute("value", value);
        return this;
    }

    public InputCheckBox step(String step) {
        withAttribute("step", step);
        return this;
    }

    public InputCheckBox readonly(Boolean readonly) {
        if (readonly) {
            withAttribute("readonly", "");
        }

        return this;
    }

    public InputCheckBox required(Boolean required) {
        if (required) {
            withAttribute("required", "true");
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
