/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.radio;

import com.jmoordb.core.ui.radio.element.RadioElement;
import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Tag;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class RadioDropdown extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public RadioDropdown(String id, String aria_labelledby, List<RadioElement> radioListGroupElements) {
        super("div");
        withClass("z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow-sm w-60 dark:bg-gray-700 dark:divide-gray-600");
        withAttribute("id", id);
        withAttribute("data-popper-reference-hidden", "");
        withAttribute("data-popper-escaped", "");
        withAttribute("data-popper-placement", "top");


        withAttribute("style", "position: absolute; inset: auto auto 0px 0px; margin: 0px; transform: translate3d(522.5px, 6119.5px, 0px);");
        add(new RadioDropdownItem(id, aria_labelledby, radioListGroupElements));
    }

    public RadioDropdown addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public RadioDropdown add(Label label) {
        if (label != null) {
            withChild(label);
        }

        return this;
    }

    public RadioDropdown add(RadioItem radioItem) {

        if (radioItem != null) {
            withChild(radioItem);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
