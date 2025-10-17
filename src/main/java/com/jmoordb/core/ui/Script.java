/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.script.ScriptUtil;

/**
 *
 * @author avbravo
 */
public class Script extends Tag{
    
    /**
     * Color red, blue
     * @param tagName
     * @param color 
     */
    public Script() {
        
        super("script");
        
        
    }
    
     public Script closeModal(String modal, String function){
         withText(ScriptUtil.closeModal(modal, function));

        return this;
    }
     public Script src(String src){
     withAttribute("src", src);

        return this;
    }
  
     public Tag build(){
      return this;
   }
    
}
