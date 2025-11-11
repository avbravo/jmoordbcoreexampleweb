
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.form;

import com.jmoordb.core.ui.Tag;

/**
 *
 * @author avbravo
 */
public class Grid extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Grid() {

        super("div");
        withClass("grid gap-6 mb-6 md:grid-cols-2");

    }

    public Grid addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public Grid add(GridCol inputCol) {
        if (inputCol != null) {
            withChild(inputCol);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
