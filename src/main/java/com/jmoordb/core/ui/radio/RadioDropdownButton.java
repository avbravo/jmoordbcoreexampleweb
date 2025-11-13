/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.radio;

import com.jmoordb.core.ui.*;

/**
 *
 * @author avbravo
 */
public class RadioDropdownButton extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public RadioDropdownButton(String id, String data_dropdown_toggle, String text) {

        super("button");
        withAttribute("id", id);
        withAttribute("data-dropdown-toggle", data_dropdown_toggle);
        withClass("text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2.5 text-center inline-flex items-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800");
        withText(text);
        add(
                new Svg("w-2.5 h-2.5 ms-2.5").aria_hidden("true").xmlns("http://www.w3.org/2000/svg").fill("none").viewBox("0 0 10 6")
                        .add(new SvgPath().stroke("currentColor").stroke_linecap("round").stroke_linejoin("round").stroke_width("2").d("m1 1 4 4 4-4")
                        )
        );
    }

    public RadioDropdownButton add(WebComponent webComponent) {
        if (webComponent != null) {
            withChild(webComponent);
        }

        return this;
    }

    public Tag build() {
        return this;
    }
}
