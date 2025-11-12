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
public class H2 extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public H2() {

        super("h2");

    }
   public H2(String text) {

        super("h2");
    withText(text);
    }
        public H2 addClass(String styleClass) {

        withClass(styleClass);
        return this;
    }
    public H2 text(String text) {
        withText(text);
        return this;
    }
  public H2 style(String style) {
         withAttribute("style", style);

        return this;
    }

    public Tag build() {
        return this;
    }

}
