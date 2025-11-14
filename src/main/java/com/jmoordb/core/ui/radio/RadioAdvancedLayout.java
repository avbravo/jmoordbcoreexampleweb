/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.radio;

import com.jmoordb.core.ui.radio.item.RadioItem;
import com.jmoordb.core.ui.radio.element.RadioElement;
import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Li;
import com.jmoordb.core.ui.Svg;
import com.jmoordb.core.ui.SvgPath;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.css.RadioAdvancedLayoutCss;
import com.jmoordb.core.ui.div.Div;
import com.jmoordb.core.ui.css.type.CssType;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class RadioAdvancedLayout extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public RadioAdvancedLayout(String id, List<RadioElement> radioElements) {
        super("ul");
        withClass(RadioAdvancedLayoutCss.UL.css);

        if (radioElements == null || radioElements.isEmpty()) {

        } else {

            for (RadioElement rge : radioElements) {
                String valueText = rge.label().getText();
                String forField = rge.label().getForField();
                String subText = rge.label().getSubText();
                add(new Li()
                        .add(rge.radioItem())
                        .add(new Label("", CssType.RadioAdvancedLayout, forField)
                                .add(
                                        new Div().addClass(RadioAdvancedLayoutCss.Div.css)
                                                .add(new Div().addClass(RadioAdvancedLayoutCss.Div1.css).text(valueText))
                                                .add(new Div().addClass(RadioAdvancedLayoutCss.Div2.css).text(subText))
                                )
                                .add(
                                        new Svg().addClass(RadioAdvancedLayoutCss.SVG.css)
                                                .aria_hidden("true")
                                                .xmlns("http://www.w3.org/2000/svg")
                                                .withAttribute("width", "24")
                                                .withAttribute("fill", "none")
                                                .withAttribute("viewBox", "0 0 24 24")
                                                .withAttribute(id, valueText)
                                                .add(new SvgPath()
                                                        .stroke("currentColor")
                                                        .stroke_linecap("round")
                                                        .stroke_linejoin("round")
                                                        .stroke_width("2")
                                                        .d("M19 12H5m14 0-4 4m4-4-4-4")
                                                )
                                )
                        )
                );
            }
        }
    }

    public RadioAdvancedLayout addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public RadioAdvancedLayout add(Label label) {
        if (label != null) {
            withChild(label);
        }

        return this;
    }

    public RadioAdvancedLayout add(RadioItem radioItem) {

        if (radioItem != null) {
            withChild(radioItem);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
