/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui;

import static com.jmoordb.core.ui.ButtonType.BUTTON;
import static com.jmoordb.core.ui.ButtonType.RESET;
import static com.jmoordb.core.ui.ButtonType.SUBMIT;

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
    public Button() {

        super("button");

    }

    public Button addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public Button type(ButtonType buttonType) {
        switch (buttonType) {
            case BUTTON:
                withAttribute("type", "button");
                break;
            case RESET:
                withAttribute("type", "reset");
                break;
            case SUBMIT:
                withAttribute("type", "submit");
                break;
            default:

        }
        return this;
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

    public Button text(String text) {
        withText(text);
        return this;
    }

    public Button onClick(String function) {
        withAttribute("onclick", function);
        return this;
    }

    public Button attribute(String name, String value) {
        withAttribute(name, value);
        return this;
    }
    public Button data_dismiss_target(String data_dismiss_target) {
        withAttribute("data-dismiss-target", data_dismiss_target);
        return this;
    }
    public Button aria_label(String aria_label) {
        withAttribute("aria-label",aria_label);
        return this;
    }
    public Button hx_post(String hx_post) {
        withAttribute("hx-post",hx_post);
        return this;
    }
    public Button hx_target(String hx_target) {
        withAttribute("hx-target",hx_target);
        return this;
    }
      
    public Button hx_swap(String hx_swap) {
        withAttribute("hx-swap",hx_swap);
        return this;
    }
    public Button hx_include(String hx_include) {
        withAttribute("hx-include",hx_include);
        return this;
    }
    public Button hx_indicator(String hx_indicator) {
        withAttribute("hx-indicator",hx_indicator);
        return this;
    }
    
  public Button add(WebComponent webComponent) {
     if (webComponent!= null) {
         withChild(webComponent);
        }

        return this;
    }
    public Tag build() {
        return this;
    }
}
