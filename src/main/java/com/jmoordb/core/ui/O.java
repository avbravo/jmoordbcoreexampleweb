/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui;

/**
 *
 * @author avbravo
 */
public class O extends Tag{
    
    /**
     * Color red, blue
     * @param tagName
     * @param color 
     */
    public O() {
        
        super("o");
           
    }
    public O(String styleClass) {
        
        super("o");
        
          withClass(styleClass);
           
    }
    
    
  
        
    public O addClass(String styleClass) {
      withClass(styleClass);
        return this;
    }
    
        public O add(WebComponent webComponent) {
     if (webComponent!= null) {
         withChild(webComponent);
        }

        return this;
    }
   public Tag build(){
      return this;
   }
    
}
