/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.div;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;

/**
 *
 * @author avbravo
 */
public class Div extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Div() {

        super("div");

    }
    public Div(String styleClass) {

        super("div");
        withClass(styleClass);

    }

    public Div addClass(String styleClass) {

        withClass(styleClass);
        return this;
    }

    public Div text(String text) {
        withText(text);
        return this;
    }

    public Div id(String id) {
        withAttribute("id", id);
        return this;
    }

    public Div name(String name) {
        withAttribute("name", name);
        return this;
    }
    public Div attribute(String name, String value) {
        withAttribute(name, value);
        return this;
    }

    public Div add(WebComponent webComponent) {
        if (webComponent != null) {
            withChild(webComponent);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
