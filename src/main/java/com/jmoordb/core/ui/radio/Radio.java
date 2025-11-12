/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.radio;

import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.css.RadioSimpleCss;

/**
 *
 * @author avbravo
 */
public class Radio extends Tag {

    String labelClass = RadioSimpleCss.Label.css;
    String inputClass = RadioSimpleCss.Input.css;
//    String labelClass = "block mb-2 text-sm font-medium text-gray-900 dark:text-white";
//    String inputClass = "bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500";

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Radio() {
        super("div");
        withClass("flex items-center mb-4");

    }

    public Radio addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public Radio add(Label label) {
        if (label != null) {
            withChild(label);
        }

        return this;
    }

    public Radio add(RadioItem radioItem) {

        if (radioItem != null) {
            withChild(radioItem);
        }

        return this;
    }

   
    

    public Tag build() {
        return this;
    }

   

}
