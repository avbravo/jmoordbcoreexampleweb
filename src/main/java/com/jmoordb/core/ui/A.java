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
public class A extends Tag{
    
    /**
     * Color red, blue
     * @param tagName
     * @param color 
     */
    public A() {
        
        super("a");
           
    }
    
    
     public A href(String href) {
        withAttribute("href",href);
        return this;
    }
     public A text(String text) {
         withText(text);
        return this;
    }
    
        
    public A addClass(String styleClass) {
      withClass(styleClass);
        return this;
    }
    
      public A add(WebComponent webComponent) {
        if (webComponent != null) {
            withChild(webComponent);
        }

        return this;
    }

   public Tag build(){
      return this;
   }
    
}
