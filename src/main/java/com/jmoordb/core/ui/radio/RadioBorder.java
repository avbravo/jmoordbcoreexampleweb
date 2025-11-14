/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.radio;

import com.jmoordb.core.ui.radio.item.RadioItem;
import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.css.RadioBorderCss;

/**
 *
 * @author avbravo
 */
public class RadioBorder extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public RadioBorder() {
        super("div");
        withClass(RadioBorderCss.Div.css);

    }

    public RadioBorder addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public RadioBorder add(Label label) {
        if (label != null) {
            withChild(label);
        }
        return this;
    }

    public RadioBorder add(RadioItem radioItem) {
        if (radioItem != null) {
            withChild(radioItem);
        }
        return this;
    }

    public Tag build() {
        return this;
    }

}
