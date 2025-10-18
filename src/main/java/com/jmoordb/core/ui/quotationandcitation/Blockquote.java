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
public class Blockquote extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Blockquote() {

        super("blockquote");

    }

    public Blockquote cite(String cite) {
       withAttribute("cite", cite);
        return this;
    }

    public Blockquote add(WebComponent webComponent) {
        if (webComponent != null) {
            withChild(webComponent);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
