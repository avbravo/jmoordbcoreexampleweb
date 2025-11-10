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
public class InputGroup extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public InputGroup(String label, String Text, String id, String name,TypeInput typeInput) {

        super("div");

    }

    public InputGroup addClass(String styleClass) {

        withClass(styleClass);
        return this;
    }

    public InputGroup text(String text) {
        withText(text);
        return this;
    }

    public InputGroup id(String id) {
        withAttribute("id", id);
        return this;
    }

    public InputGroup name(String name) {
        withAttribute("name", name);
        return this;
    }
    public InputGroup attribute(String name, String value) {
        withAttribute(name, value);
        return this;
    }

    public InputGroup add(WebComponent webComponent) {
        if (webComponent != null) {
            withChild(webComponent);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
