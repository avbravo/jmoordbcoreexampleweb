/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.buttons;

import com.jmoordb.core.ui.Tag;

/**
 *
 * @author avbravo
 */
public class Button extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Button(String tagName) {

        super("button");
        withText(tagName);
        

    }

    public Button id(String id) {
        withAttribute("id", id);
        return this;
    }
    public Button color(String color) {
       withClass("px-4 py-2 bg-" + color + "-500 text-white rounded hover:bg-" + color + "-600 dark:bg-" + color + "-700 dark:hover:bg-" + color + "-800");
        return this;
    }
    public Button name(String name) {
        withAttribute("name", name);
        return this;
    }

    public Button onClick(String function) {
        withAttribute("onclick", function);
        return this;
    }

    public Tag build() {
        return this;
    }
}
