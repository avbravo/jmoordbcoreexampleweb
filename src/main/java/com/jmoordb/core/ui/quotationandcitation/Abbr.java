/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.quotationandcitation;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;

/**
 *
 * @author avbravo
 */
public class Abbr extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Abbr() {

        super("abbr");

    }

    public Abbr title(String title) {
        withAttribute("title", title);
        return this;
    }

    public Abbr add(WebComponent webComponent) {
        if (webComponent != null) {
            withChild(webComponent);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
