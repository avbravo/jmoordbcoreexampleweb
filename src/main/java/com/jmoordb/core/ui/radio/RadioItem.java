/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.radio;

import com.jmoordb.core.ui.A;
import com.jmoordb.core.ui.Tag;

/**
 *
 * @author avbravo
 */
public class RadioItem extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public RadioItem() {

        super("input");
        withAttribute("type", "radio");

    }

    public RadioItem(String id, String name, String styleClass) {

        super("input");
        withAttribute("type", "radio");
        withAttribute("id", id);
        withAttribute("name", name);
        if (styleClass == null || styleClass.equals("")) {

        } else {
            withClass(styleClass);
        }

    }
   

   
 
    public RadioItem text(String text) {
        withText(text);
        return this;
    }

    public RadioItem id(String id) {
        withAttribute("id", id);
        return this;
    }

    public RadioItem name(String name) {
        withAttribute("name", name);
        return this;
    }

    public RadioItem addClass(String withClass) {
        withClass(withClass);
        return this;
    }

     public RadioItem disabled(Boolean disabled) {
        if (disabled) {
            withAttribute("disabled", "");
        }
        return this;
    }

    public RadioItem checked(Boolean checked) {
        if (checked) {
            withAttribute("checked", "");
        }
        return this;
    
    }

    public RadioItem value(String value) {
        withAttribute("value", value);
        return this;
    }

   

    public RadioItem required(Boolean required) {
        if (required) {
            withAttribute("required", "true");
        }

        return this;
    }

    public Tag build() {
        return this;
    }
}
