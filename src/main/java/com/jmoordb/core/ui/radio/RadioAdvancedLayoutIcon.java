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
import com.jmoordb.core.ui.css.RadioAdvancedLayoutIconCss;
import com.jmoordb.core.ui.div.Div;
import com.jmoordb.core.ui.css.type.CssType;
import com.jmoordb.core.ui.radio.element.RadioElementIcon;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class RadioAdvancedLayoutIcon extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public RadioAdvancedLayoutIcon(List<RadioElementIcon> radioElementIcons) {
        super("ul");
        withClass(RadioAdvancedLayoutIconCss.UL.css);

        if (radioElementIcons == null || radioElementIcons.isEmpty()) {

        } else {

            for (RadioElementIcon rge : radioElementIcons) {
                String valueText = rge.label().getText();
                String forField = rge.label().getForField();
                String subText = rge.label().getSubText();
                add(new Li()
                          .add(
                                        rge.svg()
                                )
                        .add(rge.radioItem())
                        .add(new Label("", CssType.RadioAdvancedLayoutIcon, forField)
                                .add(
                                        new Div().addClass(RadioAdvancedLayoutIconCss.Div.css)
                                                .add(new Div().addClass(RadioAdvancedLayoutIconCss.Div1.css).text(valueText))
                                                .add(new Div().addClass(RadioAdvancedLayoutIconCss.Div2.css).text(subText))
                                )
                              
                        )
                );
            }
        }
    }

    public RadioAdvancedLayoutIcon addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public RadioAdvancedLayoutIcon add(Label label) {
        if (label != null) {
            withChild(label);
        }

        return this;
    }

    public RadioAdvancedLayoutIcon add(RadioItem radioItem) {

        if (radioItem != null) {
            withChild(radioItem);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
