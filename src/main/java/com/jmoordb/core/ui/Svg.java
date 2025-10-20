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
public class Svg extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Svg() {

        super("svg");

    }

    public Svg addClass(String styleClass) {

        withClass(styleClass);
        return this;
    }


    public Svg aria_hidden(String aria_hidden ){
        withAttribute("aria-hidden", aria_hidden);
        return this;
    }
    
    public Svg xmlns(String xmlns ){
        withAttribute("xmlns", xmlns);
        return this;
    }
    
    public Svg fill(String fill ){
        withAttribute("fill", fill);
        return this;
    }
    public Svg viewBox(String viewBox ){
        withAttribute("viewBox", viewBox);
        return this;
    }
    
    

    public Svg attribute(String name, String value) {
        withAttribute(name, value);
        return this;
    }

    public Svg add(WebComponent webComponent) {
        if (webComponent != null) {
            withChild(webComponent);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
