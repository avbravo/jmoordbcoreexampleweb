/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.buttons;

import com.jmoordb.core.ui.Tag;

/**
 *
 * @author avbravo
 */
public class Button extends Tag{
    
    /**
     * Color red, blue
     * @param tagName
     * @param color 
     */
    public Button(String tagName,String color) {
        
        super("button");
        withText(tagName);
        withClass("px-4 py-2 bg-"+color+"-500 text-white rounded hover:bg-"+color+"-600 dark:bg-"+color+"-700 dark:hover:bg-"+color+"-800");
        
    }
    
   
    
}
