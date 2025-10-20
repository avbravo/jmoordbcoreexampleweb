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
public class Span extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Span() {

        super("span");

    }

   
    

    public Span text(String text) {
        withText(text);
        return this;
    }
  public Span addClass(String styleClass) {
      withClass(styleClass);
        return this;
    }


    public Span forField(String field) {
        withAttribute("for", field);
        return this;
    }
    public Span id(String id) {
        withAttribute("id", id);
        return this;
    }
   

    public Tag build() {
        return this;
    }

}
