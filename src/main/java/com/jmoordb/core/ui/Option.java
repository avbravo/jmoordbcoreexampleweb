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
public class Option extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Option() {

        super("option");

    }

   
    public Option value(String value) {
        withAttribute("value", value);
        return this;
    }
    
      public Option text(String text) {
        withText(text);
        return this;
    }


    public Option disabled(Boolean disabled) {
        if (disabled) {
            withAttribute("disabled", "true");
        }

        return this;
    }
    public Option selected(Boolean selected) {
        if (selected) {
            withAttribute("selected", "true");
        }

        return this;
    }

    
      public Option add(WebComponent webComponent) {
     if (webComponent!= null) {
         withChild(webComponent);
        }

        return this;
    }
    public Tag build() {
        return this;
    }

}
