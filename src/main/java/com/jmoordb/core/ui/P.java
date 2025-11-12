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
public class P extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public P() {
        super("p");
    }
    public P(String text, String styleClass) {
        super("p");
         withText(text);
         withClass(styleClass);
    }

    public P text(String text) {
        withText(text);
        return this;
    }
    public P id(String id) {
    withAttribute("id", id);
        return this;
    }

    public P addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public Tag build() {
        return this;
    }

}
