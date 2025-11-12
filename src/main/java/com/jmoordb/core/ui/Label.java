/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui;

import com.jmoordb.core.ui.radio.RadioItemLink;

/**
 *
 * @author avbravo
 */
public class Label extends Tag {
String text;
String subText;
String id;
    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Label() {

        super("label");

    }

    public Label(String text) {

        super("label");
        withText(text);
    }

    public Label(String text, String styleClass) {

        super("label");
        withText(text);
        this.text=text;
        withClass(styleClass);

    }

    public Label(String text, String styleClass, String forField) {

        super("label");
        this.text = text;
        withText(text);
        if (styleClass == null || styleClass.equals("")) {

        } else {
            withClass(styleClass);
        }
        if (forField == null || forField.equals("")) {

        } else {
            withAttribute("for", forField);
        }

    }
    
     public Label setSubText(String subText) {
                this.subText = text;
                return this;

    }
     

    public Label(String text, String styleClass, String forField, RadioItemLink radioItemLink) {

        super("label");
                this.text = text;
        withText(text);
        if (styleClass == null || styleClass.equals("")) {

        } else {
            withClass(styleClass);
        }
        if (forField == null || forField.equals("")) {

        } else {
            withAttribute("for", forField);
        }

        add(radioItemLink);
    }

    public Label text(String text) {
                this.text = text;
        withText(text);
        return this;
    }

    public Label addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public Label forField(String field) {
        withAttribute("for", field);
        return this;
    }

    public Label id(String id) {
        withAttribute("id", id);
        this.id=id;
        return this;
    }
    
    public String getText(){
        return this.text;
    }

    public String getId() {
        return id;
    }

    public String getSubText() {
        return subText;
    }
    
    

    public Tag build() {
        return this;
    }

}
