/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.radio;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.css.RadioCss;

/**
 *
 * @author avbravo
 */
public class RadioItemLink extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public RadioItemLink() {

        super("a");

    }

    public RadioItemLink(String href, String text) {

        super("a");
        withAttribute("href", href);
        addClass(RadioCss.Link.css);
    withText(text);
    }

    public RadioItemLink href(String href) {
        withAttribute("href", href);
        return this;
    }

    public RadioItemLink text(String text) {
        withText(text);
        return this;
    }

    public RadioItemLink addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public RadioItemLink add(WebComponent webComponent) {
        if (webComponent != null) {
            withChild(webComponent);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
