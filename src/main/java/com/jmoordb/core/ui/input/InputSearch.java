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
public class InputSearch extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public InputSearch() {

        super("input");
        withAttribute("type", "search");

    }
 public InputSearch(String id, String name, String styleClass) {

        super("input");
 withAttribute("type", "search");
        withAttribute("id", id);
        withAttribute("name", name);
        if (styleClass == null || styleClass.equals("")) {

        } else {
            withClass(styleClass);
        }

    }
    public InputSearch text(String text) {
        withText(text);
        return this;
    }

    public InputSearch id(String id) {
        withAttribute("id", id);
        return this;
    }
    
        public InputSearch name(String name) {
        withAttribute("name", name);
        return this;
    }
        public InputSearch onkeyup(String onkeyup) {
        withAttribute("onkeyup", onkeyup);
        return this;
    }

     public InputSearch addClass(String withClass) {
      withClass(withClass);
        return this;
    }



    public InputSearch value(String value) {
        withAttribute("value", value);
        return this;
    }
    public InputSearch placeholder(String placeholder) {
        withAttribute("placeholder", placeholder);
        return this;
    }

    public InputSearch step(String step) {
        withAttribute("step", step);
        return this;
    }

    public InputSearch readonly(Boolean readonly) {
        if (readonly) {
            withAttribute("readonly", "");
        }

        return this;
    }

    public InputSearch required(Boolean required) {
        if (required) {
            withAttribute("required", "true");
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
