/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.radio;

import com.jmoordb.core.ui.radio.element.RadioElement;
import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Li;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.div.Div;
import com.jmoordb.core.ui.radio.css.RadioInlineCss;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class RadioInline extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public RadioInline(List<RadioElement> radioListGroupElements) {
        super("div");
        withClass(RadioInlineCss.Div.css);

        if (radioListGroupElements == null || radioListGroupElements.isEmpty()) {
        } else {
            for (RadioElement rge : radioListGroupElements) {
                add(
                        new Div(RadioInlineCss.DivRow.css)
                                .add(
                                        rge.radioItem()
                                )
                                .add(rge.label())
                );

            }
        }
    }

    public RadioInline addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public RadioInline add(Label label) {
        if (label != null) {
            withChild(label);
        }

        return this;
    }

    public RadioInline add(RadioItem radioItem) {

        if (radioItem != null) {
            withChild(radioItem);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
