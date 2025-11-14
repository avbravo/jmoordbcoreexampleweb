/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.input;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.css.FormRowCss;
import com.jmoordb.core.ui.css.GridColCss;
import com.jmoordb.core.ui.css.InputFileCss;
import com.jmoordb.core.ui.css.type.CssType;
import static com.jmoordb.core.ui.css.type.CssType.FormRow;
import static com.jmoordb.core.ui.css.type.CssType.GridCol;
import static com.jmoordb.core.ui.css.type.CssType.Radio;
import static com.jmoordb.core.ui.css.type.CssType.RadioBorder;
import static com.jmoordb.core.ui.css.type.CssType.RadioDropdown;
import static com.jmoordb.core.ui.css.type.CssType.RadioHorizontalListGroup;
import static com.jmoordb.core.ui.css.type.CssType.RadioInline;
import static com.jmoordb.core.ui.css.type.CssType.RadioListGroup;
import static com.jmoordb.core.ui.css.type.CssType.RadioTwoColumns;
import com.jmoordb.core.ui.css.RadioBorderCss;
import com.jmoordb.core.ui.css.RadioCss;
import com.jmoordb.core.ui.css.RadioDropdownCss;
import com.jmoordb.core.ui.css.RadioHorizontalListGroupCss;
import com.jmoordb.core.ui.css.RadioInlineCss;
import com.jmoordb.core.ui.css.RadioListGroupCss;
import com.jmoordb.core.ui.css.RadioTwoColumnsCss;
import static com.jmoordb.core.ui.css.type.CssType.InputFile;

/**
 *
 * @author avbravo
 */
public class InputColor extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public InputColor() {

        super("input");
        withAttribute("type", "color");

    }

    public InputColor(String id, String name, String styleClass) {

        super("input");
        withAttribute("type", "color");
        withAttribute("id", id);
        withAttribute("name", name);
        if (styleClass == null || styleClass.equals("")) {

        } else {
            withClass(styleClass);
        }

    }

    public InputColor(String id, String name, CssType cssType) {

        super("input");
        withAttribute("type", "color");
        withAttribute("id", id);
        withAttribute("name", name);

        withClass(getCssTypeInputServices().toCss(cssType));

    }



    public InputColor text(String text) {
        withText(text);
        return this;
    }

    public InputColor id(String id) {
        withAttribute("id", id);
        return this;
    }

    public InputColor name(String name) {
        withAttribute("name", name);
        return this;
    }

    public InputColor addClass(String withClass) {
        withClass(withClass);
        return this;
    }

    public InputColor placeholder(String placeholder) {
        withAttribute("placeholder", placeholder);
        return this;
    }

    public InputColor value(String value) {
        withAttribute("value", value);
        return this;
    }

    public InputColor step(String step) {
        withAttribute("step", step);
        return this;
    }

    public InputColor readonly(Boolean readonly) {
        if (readonly) {
            withAttribute("readonly", "");
        }

        return this;
    }

    public InputColor required(Boolean required) {
        if (required) {
            withAttribute("required", "true");
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
