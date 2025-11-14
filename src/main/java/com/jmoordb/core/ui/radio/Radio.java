/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.radio;

import com.jmoordb.core.ui.radio.item.RadioItem;
import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.css.RadioCss;

/**
 *
 * @author avbravo
 */
public class Radio extends Tag {


    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Radio() {
        super("div");
        withClass(RadioCss.Div.css);

    }
  

    public Radio addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public Radio add(Label label) {
        if (label != null) {
            withChild(label);
        }

        return this;
    }

    public Radio add(RadioItem radioItem) {

        if (radioItem != null) {
            withChild(radioItem);
        }

        return this;
    }

   
    

    public Tag build() {
        return this;
    }

   

}
