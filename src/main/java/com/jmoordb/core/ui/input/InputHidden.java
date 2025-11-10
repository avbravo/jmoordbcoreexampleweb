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
public class InputHidden extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public InputHidden() {

        super("input");
        withAttribute("type", "hidden");

    }

    public InputHidden(String id, String name, String styleClass) {

        super("input");
        withAttribute("type", "hidden");
        withAttribute("id", id);
        withAttribute("name", name);
        if (styleClass == null || styleClass.equals("")) {

        } else {
            withClass(styleClass);
        }

    }

    public InputHidden text(String text) {
        withText(text);
        return this;
    }

    public InputHidden id(String id) {
        withAttribute("id", id);
        return this;
    }

    public InputHidden name(String name) {
        withAttribute("name", name);
        return this;
    }

    public InputHidden addClass(String withClass) {
        withClass(withClass);
        return this;
    }

    public InputHidden value(String value) {
        withAttribute("value", value);
        return this;
    }

    public InputHidden step(String step) {
        withAttribute("step", step);
        return this;
    }

    public InputHidden readonly(Boolean readonly) {
        if (readonly) {
            withAttribute("readonly", "");
        }

        return this;
    }

    public InputHidden required(Boolean required) {
        if (required) {
            withAttribute("required", "true");
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
