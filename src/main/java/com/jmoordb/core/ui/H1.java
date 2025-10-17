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
public class H1 extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public H1() {

        super("h1");

    }

    public H1 text(String text) {
        withText(text);
        return this;
    }
 

    public Tag build() {
        return this;
    }

}
