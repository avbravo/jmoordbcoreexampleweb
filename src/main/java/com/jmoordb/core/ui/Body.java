/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui;

import static com.jmoordb.core.ui.ButtonType.BUTTON;
import static com.jmoordb.core.ui.ButtonType.RESET;
import static com.jmoordb.core.ui.ButtonType.SUBMIT;
import com.jmoordb.core.ui.Tag;

/**
 *
 * @author avbravo
 */
public class Body extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Body() {

        super("body");

    }

    public Body add(WebComponent webComponent) {
        if (webComponent != null) {
            withChild(webComponent);
        }

        return this;
    }



    public Body styleClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public Tag build() {
        return this;
    }

}
