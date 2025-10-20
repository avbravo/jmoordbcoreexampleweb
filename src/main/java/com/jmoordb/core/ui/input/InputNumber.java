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
public class InputNumber extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public InputNumber() {

        super("input");
        withAttribute("type", "number");

    }

    public InputNumber text(String text) {
        withText(text);
        return this;
    }
    public InputNumber min(String min) {
   withAttribute("min", min);
        return this;
    }
    public InputNumber max(String max) {
   withAttribute("max", max);
        return this;
    }

    public InputNumber id(String id) {
        withAttribute("id", id);
        return this;
    }
    
        public InputNumber name(String name) {
        withAttribute("name", name);
        return this;
    }

     public InputNumber addClass(String withClass) {
      withClass(withClass);
        return this;
    }

    public InputNumber placeholder(String placeholder) {
        withAttribute("placeholder", placeholder);
        return this;
    }
    public InputNumber value(String value) {
        withAttribute("value", value);
        return this;
    }

    public InputNumber step(String step) {
        withAttribute("step", step);
        return this;
    }

    public InputNumber readonly(Boolean readonly) {
        if (readonly) {
            withAttribute("readonly", "");
        }

        return this;
    }

    public InputNumber required(Boolean required) {
        if (required) {
            withAttribute("required", "true");
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
