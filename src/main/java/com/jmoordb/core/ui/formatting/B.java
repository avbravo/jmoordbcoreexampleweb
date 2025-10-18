/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.formatting;

import com.jmoordb.core.ui.Button;
import com.jmoordb.core.ui.Html;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;

/**
 *
 * @author avbravo
 */
public class B extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public B() {

        super("b");

    }

    public B text(String text) {
        withText(text);
        return this;
    }

    public B add(WebComponent webComponent) {
        if (webComponent != null) {
            withChild(webComponent);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
