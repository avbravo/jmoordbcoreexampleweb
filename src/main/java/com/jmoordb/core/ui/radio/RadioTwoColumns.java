/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.radio;

import com.jmoordb.core.ui.radio.item.RadioItem;
import com.jmoordb.core.ui.FieldSet;
import com.jmoordb.core.ui.radio.element.RadioElement;
import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Li;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.div.Div;
import com.jmoordb.core.ui.css.RadioInlineCss;
import com.jmoordb.core.ui.css.RadioTwoColumnsCss;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class RadioTwoColumns extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public RadioTwoColumns(String id, String title, List<RadioElement> radioElements) {
        super("div");
        //withClass(RadioTwoColumnsCss.Div.css);
        withAttribute("id", id);
        add(new FieldSet().text(title));
        add(new RadioTwoColumnsItem(id, title, radioElements));

    }

    public RadioTwoColumns id(String id) {
        withAttribute("id", id);
        return this;
    }

    public RadioTwoColumns addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public RadioTwoColumns add(Label label) {
        if (label != null) {
            withChild(label);
        }

        return this;
    }

    public RadioTwoColumns add(RadioItem radioItem) {

        if (radioItem != null) {
            withChild(radioItem);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
