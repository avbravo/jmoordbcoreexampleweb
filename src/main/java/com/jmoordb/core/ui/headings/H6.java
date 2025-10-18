/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.headings;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.Tag;

/**
 *
 * @author avbravo
 */
public class H6 extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public H6() {

        super("h6");

    }

    public H6 text(String text) {
        withText(text);
        return this;
    }
 
 public H6 style(String style) {
         withAttribute("style", style);

        return this;
    }
    public Tag build() {
        return this;
    }

}
