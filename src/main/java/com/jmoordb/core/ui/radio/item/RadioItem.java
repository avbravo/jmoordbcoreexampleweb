/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.radio.item;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.css.RadioBorderCss;
import com.jmoordb.core.ui.css.RadioCss;
import com.jmoordb.core.ui.css.type.CssType;
import static com.jmoordb.core.ui.css.type.CssType.Radio;
import static com.jmoordb.core.ui.css.type.CssType.RadioDropdown;
import static com.jmoordb.core.ui.css.type.CssType.RadioHorizontalListGroup;
import static com.jmoordb.core.ui.css.type.CssType.RadioInline;
import static com.jmoordb.core.ui.css.type.CssType.RadioListGroup;
import static com.jmoordb.core.ui.css.type.CssType.RadioTwoColumns;
import com.jmoordb.core.ui.css.RadioDropdownCss;
import com.jmoordb.core.ui.css.RadioHorizontalListGroupCss;
import com.jmoordb.core.ui.css.RadioInlineCss;
import com.jmoordb.core.ui.css.RadioListGroupCss;
import com.jmoordb.core.ui.css.RadioTwoColumnsCss;

/**
 *
 * @author avbravo
 */
public class RadioItem extends Tag {

    String id;

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public RadioItem() {

        super("input");
        withAttribute("type", "radio");

    }

    public RadioItem(String id, String name, String styleClass) {

        super("input");
        withAttribute("type", "radio");
        withAttribute("id", id);
        this.id = id;
        withAttribute("name", name);
        if (styleClass == null || styleClass.equals("")) {

        } else {
            withClass(styleClass);
        }

    }

    public RadioItem(String id, String name, CssType cssType) {

        super("input");
        withAttribute("type", "radio");
        withAttribute("id", id);
        this.id = id;
        withAttribute("name", name);
        withClass(getCssTypeInputServices().toCss(cssType));

        

    }

    public RadioItem text(String text) {
        withText(text);
        return this;
    }

    public RadioItem id(String id) {
        withAttribute("id", id);
        this.id = id;
        return this;
    }

    public RadioItem name(String name) {
        withAttribute("name", name);
        return this;
    }

    public RadioItem addClass(String withClass) {
        withClass(withClass);
        return this;
    }

    public RadioItem disabled(Boolean disabled) {
        if (disabled) {
            withAttribute("disabled", "");
        }
        return this;
    }

    public RadioItem checked(Boolean checked) {
        if (checked) {
            withAttribute("checked", "");
        }
        return this;

    }

    public RadioItem value(String value) {
        withAttribute("value", value);
        return this;
    }

    public RadioItem required(Boolean required) {
        if (required) {
            withAttribute("required", "true");
        }

        return this;
    }

    public String getId() {
        return id;
    }

    public Tag build() {
        return this;
    }
}
