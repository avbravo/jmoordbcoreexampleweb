/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.radio;

import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.P;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.css.RadioCss;
import com.jmoordb.core.ui.div.Div;

/**
 *
 * @author avbravo
 */
public class RadioHelper extends Tag {

    String labelClass = RadioCss.Label.css;
    String inputClass = RadioCss.Input.css;
//    String labelClass = "block mb-2 text-sm font-medium text-gray-900 dark:text-white";
//    String inputClass = "bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500";

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public RadioHelper() {
        super("div");
        withClass("flex");

    }

    public RadioHelper(RadioItem radioItem, Label label, String help) {
        super("div");
        withClass("flex");
        add(new Div("flex items-center h-5")
                .add(radioItem));
        add(new Div("ms-2 text-sm")
                .add(label)
                .add(new P(help,"text-xs font-normal text-gray-500 dark:text-gray-300"))
        );

    }

    public RadioHelper addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public RadioHelper label(Label label) {
        if (label != null) {
            withChild(label);
        }

        return this;
    }

    public RadioHelper radioItem(RadioItem radioItem) {

        if (radioItem != null) {
            withChild(radioItem);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
