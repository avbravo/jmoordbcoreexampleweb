/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.radio;

import com.jmoordb.core.ui.radio.item.RadioItem;
import com.jmoordb.core.ui.radio.element.RadioElement;
import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Li;
import com.jmoordb.core.ui.P;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.css.RadioDropdownCss;
import com.jmoordb.core.ui.div.Div;
import com.jmoordb.core.ui.css.type.CssType;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class RadioDropdownItem extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public RadioDropdownItem(String id, String aria_labelledby, List<RadioElement> radioListGroupElements) {
        super("ul");
        withClass(RadioDropdownCss.UL.css);
        withAttribute("aria-labelledby", aria_labelledby);

        if (radioListGroupElements == null || radioListGroupElements.isEmpty()) {

        } else {

            for (RadioElement rge : radioListGroupElements) {
                String valueText = rge.label().getText();
                String forField = rge.label().getForField();
                String subText = rge.label().getSubText();
                add(new Li()
                                .add(new Div(RadioDropdownCss.DivLI.css)
                                                .add(new Div(RadioDropdownCss.DivInput.css)
                                                        .add(rge.radioItem())
                                                )
                                                .add(new Div(RadioDropdownCss.DivLabel.css)
                                                                .add(new Label("", CssType.RadioDropdown, forField)
                                                                                .add(new Div().text(valueText))
                                                                                .add(new P(subText, RadioDropdownCss.P.css).id(id + "-text"))
                                                                )
                                                )
                                )
                );
            }
        }
    }

    public RadioDropdownItem addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public RadioDropdownItem add(Label label) {
        if (label != null) {
            withChild(label);
        }

        return this;
    }

    public RadioDropdownItem add(RadioItem radioItem) {

        if (radioItem != null) {
            withChild(radioItem);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
