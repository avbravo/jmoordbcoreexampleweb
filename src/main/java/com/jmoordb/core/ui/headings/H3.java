/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.headings;

import com.jmoordb.core.ui.Tag;

/**
 *
 * @author avbravo
 */
public class H3 extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public H3() {

        super("h3");

    }
    public H3(String text) {

        super("h3");
     withText(text);

    }

        public H3 addClass(String styleClass) {

        withClass(styleClass);
        return this;
    }
    public H3 text(String text) {
        
        return this;
    }
 
 public H3 style(String style) {
         withAttribute("style", style);

        return this;
    }
    public Tag build() {
        return this;
    }

}
