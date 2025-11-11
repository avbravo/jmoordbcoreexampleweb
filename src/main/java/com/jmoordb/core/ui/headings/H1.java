/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.headings;

import com.jmoordb.core.ui.div.Div;
import com.jmoordb.core.ui.form.Form;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;

/**
 *
 * @author avbravo
 */
public class H1 extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public H1() {

        super("h1");

    }

    
    public H1 addClass(String styleClass) {

        withClass(styleClass);
        return this;
    }
    public H1 text(String text) {
        withText(text);
        return this;
    }
    public H1 style(String style) {
         withAttribute("style", style);

        return this;
    }
   
    public Tag build() {
        return this;
    }

}
