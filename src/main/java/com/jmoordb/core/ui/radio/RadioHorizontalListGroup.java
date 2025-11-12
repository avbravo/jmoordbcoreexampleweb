/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.radio;

import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Li;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.css.RadioHorizontalListGroupCss;
import com.jmoordb.core.ui.div.Div;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class RadioHorizontalListGroup extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public RadioHorizontalListGroup(List<RadioListGroupElement> radioListGroupElements) {
        super("ul");
        withClass(RadioHorizontalListGroupCss.UL.css);

        if (radioListGroupElements == null || radioListGroupElements.isEmpty()) {
        } else {
            for (RadioListGroupElement rge : radioListGroupElements) {
                add(
                        new Li(RadioHorizontalListGroupCss.LI.css)
                                .add(
                                        new Div(RadioHorizontalListGroupCss.DIV.css)
                                                .add(rge.radioItem())
                                                .add(rge.label())
                                )
                );
            }
        }
    }

    public RadioHorizontalListGroup addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public RadioHorizontalListGroup add(Label label) {
        if (label != null) {
            withChild(label);
        }

        return this;
    }

    public RadioHorizontalListGroup add(RadioItem radioItem) {

        if (radioItem != null) {
            withChild(radioItem);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
