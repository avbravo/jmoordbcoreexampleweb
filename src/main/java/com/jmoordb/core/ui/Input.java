/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui;

import com.jmoordb.core.ui.Tag;

/**
 *
 * @author avbravo
 */
public class Input extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Input() {

        super("label");

    }

    public Input text(String text) {
        withText(text);
        return this;
    }

    public Input id(String id) {
        withAttribute("id", id);
        return this;
    }

    public Input typeInput(TypeInput typeInput) {
        switch (typeInput) {
            case NUMBER:
                withAttribute("type", "number");
                break;
            case TEXT:
                withAttribute("type", "text");
                break;
            default:
                withAttribute("type", "text");

        }

        return this;
    }
    public Input classStyle(String labelClass) {
      withClass(labelClass);
        return this;
    }

    public Input name(String name) {
        withAttribute("name", name);
        return this;
    }

    public Input value(String value) {
        withAttribute("value", value);
        return this;
    }

    public Input step(String step) {
        withAttribute("step", step);
        return this;
    }

    public Input readonly(Boolean readonly) {
        if (readonly) {
            withAttribute("readonly", "");
        }

        return this;
    }

    public Input required(Boolean required) {
        if (required) {
            withAttribute("required", "true");
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
