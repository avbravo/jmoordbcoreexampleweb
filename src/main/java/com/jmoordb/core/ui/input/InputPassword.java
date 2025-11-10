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
public class InputPassword extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public InputPassword() {

        super("input");
        withAttribute("type", "password");

    }

      public InputPassword(String id, String name, String styleClass) {

        super("input");
         withAttribute("type", "password");
        withAttribute("id", id);
        withAttribute("name", name);
        if (styleClass == null || styleClass.equals("")) {

        } else {
            withClass(styleClass);
        }

    }
      
    public InputPassword text(String text) {
        withText(text);
        return this;
    }

    public InputPassword id(String id) {
        withAttribute("id", id);
        return this;
    }
    
        public InputPassword name(String name) {
        withAttribute("name", name);
        return this;
    }

     public InputPassword addClass(String withClass) {
      withClass(withClass);
        return this;
    }
    public InputPassword placeholder(String placeholder) {
        withAttribute("placeholder", placeholder);
        return this;
    }

    public InputPassword value(String value) {
        withAttribute("value", value);
        return this;
    }

    public InputPassword step(String step) {
        withAttribute("step", step);
        return this;
    }

    public InputPassword readonly(Boolean readonly) {
        if (readonly) {
            withAttribute("readonly", "");
        }

        return this;
    }

    public InputPassword required(Boolean required) {
        if (required) {
            withAttribute("required", "true");
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
