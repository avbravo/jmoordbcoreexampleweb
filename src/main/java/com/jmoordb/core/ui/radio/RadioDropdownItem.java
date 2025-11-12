/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.radio;

import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Li;
import com.jmoordb.core.ui.P;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.css.RadioDropdownCss;
import com.jmoordb.core.ui.div.Div;
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
    public RadioDropdownItem(String id, List<RadioListGroupElement> radioListGroupElements) {
        super("ul");
        withClass(RadioDropdownCss.UL.css);
        withAttribute("aria-labelledby", id);

        if (radioListGroupElements == null || radioListGroupElements.isEmpty()) {
            System.out.println("\t test ---> lista vacia");
        } else {

            for (RadioListGroupElement rge : radioListGroupElements) {
                System.out.println("\t añadiendo elemento " + rge.radioItem().getId());
                System.out.println("\t....................rge.radioItem().build():" + rge.radioItem().build());

                add(
                        new Li()
                                .add(
                                        new Div(RadioDropdownCss.DivInput.css)
                                                .add(new Div(RadioDropdownCss.DivInputElement.css)
                                                        .add(rge.radioItem())
                                                )
                                )
                                .add(
                                        new Div(RadioDropdownCss.DivLabel.css)
                                                .add(rge.label().add(new Div().text(rge.label().getText())))
                                                .add(new P(rge.label().getSubText(), RadioDropdownCss.P.css).id(rge.radioItem().getId() + "text"))
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
