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
public class InputTime extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public InputTime() {

        super("input");
        withAttribute("type", "time");

    }
    
     public InputTime(String id, String name, String styleClass) {

        super("input");
 withAttribute("type", "time");
        withAttribute("id", id);
        withAttribute("name", name);
        if (styleClass == null || styleClass.equals("")) {

        } else {
            withClass(styleClass);
        }

    }

    public InputTime text(String text) {
        withText(text);
        return this;
    }

    public InputTime id(String id) {
        withAttribute("id", id);
        return this;
    }
    
        public InputTime name(String name) {
        withAttribute("name", name);
        return this;
    }

     public InputTime addClass(String withClass) {
      withClass(withClass);
        return this;
    }

    public InputTime placeholder(String placeholder) {
        withAttribute("placeholder", placeholder);
        return this;
    }

    public InputTime value(String value) {
        withAttribute("value", value);
        return this;
    }

    public InputTime step(String step) {
        withAttribute("step", step);
        return this;
    }

    public InputTime readonly(Boolean readonly) {
        if (readonly) {
            withAttribute("readonly", "");
        }

        return this;
    }

    public InputTime required(Boolean required) {
        if (required) {
            withAttribute("required", "true");
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
