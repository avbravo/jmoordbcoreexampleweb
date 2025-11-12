/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.radio;

import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.css.RadioHorizontalListGroupCss;
import com.jmoordb.core.ui.headings.H3;

/**
 *
 * @author avbravo
 */
public class RadioListGroupHeader extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public RadioListGroupHeader(String title) {
        super("h3");
        add(new H3(title).addClass(RadioHorizontalListGroupCss.H3.css));

    }

    public RadioListGroupHeader addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public RadioListGroupHeader add(Label label) {
        if (label != null) {
            withChild(label);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
