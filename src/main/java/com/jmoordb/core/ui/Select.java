/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui;

/**
 *
 * @author avbravo
 */
public class Select extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Select() {

        super("select");

    }

   
    public Select id(String id) {
        withAttribute("id", id);
        return this;
    }
    
        public Select name(String name) {
        withAttribute("name", name);
        return this;
    }

    
  public Select addClass(String styleClass) {
      withClass(styleClass);
        return this;
    }




    public Select required(Boolean required) {
        if (required) {
            withAttribute("required", "true");
        }

        return this;
    }

    
      public Select add(WebComponent webComponent) {
     if (webComponent!= null) {
         withChild(webComponent);
        }

        return this;
    }
    public Tag build() {
        return this;
    }

}
