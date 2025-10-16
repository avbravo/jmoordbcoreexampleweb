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
public class Form extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Form() {

        super("form");

    }

    public Form id(String id) {
        withAttribute("id", id);
        return this;
    }

    public Tag build() {
        return this;
    }

}
