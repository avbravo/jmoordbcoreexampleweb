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
public class Title extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Title() {

        super("title");

    }

    public Title text(String text) {
        withText(text);
        return this;
    }

  

    public Tag build() {
        return this;
    }

}
