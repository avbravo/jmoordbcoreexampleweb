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
public class Meta extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Meta() {

        super("meta");

    }

    public Meta add(WebComponent webComponent) {
     if (webComponent!= null) {
         withChild(webComponent);
        }

        return this;
    }
  
        public Meta text(String text) {
        withText(text);
        return this;
    }

     public Meta charset(String charset) {
        withAttribute("charset", charset);
        return this;
    }
     public Meta name(String name) {
        withAttribute("name", name);
        return this;
    }
     public Meta content(String content) {
        withAttribute("content", content);
        return this;
    }

     
     
    public Tag build() {
        return this;
    }

}
