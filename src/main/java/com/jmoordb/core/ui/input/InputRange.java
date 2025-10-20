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
public class InputRange extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public InputRange() {

        super("input");
        withAttribute("type", "range");

    }

    public InputRange text(String text) {
        withText(text);
        return this;
    }
    public InputRange min(String min) {
   withAttribute("min", min);
        return this;
    }
    public InputRange max(String max) {
   withAttribute("max", max);
        return this;
    }

    public InputRange id(String id) {
        withAttribute("id", id);
        return this;
    }
    
        public InputRange name(String name) {
        withAttribute("name", name);
        return this;
    }
    public InputRange placeholder(String placeholder) {
        withAttribute("placeholder", placeholder);
        return this;
    }
     public InputRange addClass(String withClass) {
      withClass(withClass);
        return this;
    }



    public InputRange value(String value) {
        withAttribute("value", value);
        return this;
    }

    public InputRange step(String step) {
        withAttribute("step", step);
        return this;
    }

    public InputRange readonly(Boolean readonly) {
        if (readonly) {
            withAttribute("readonly", "");
        }

        return this;
    }

    public InputRange required(Boolean required) {
        if (required) {
            withAttribute("required", "true");
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
