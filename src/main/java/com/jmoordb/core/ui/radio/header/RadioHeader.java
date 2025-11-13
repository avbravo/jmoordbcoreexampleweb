/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.radio.header;

import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.headings.H3;
import com.jmoordb.core.ui.radio.css.RadioCssType;
import static com.jmoordb.core.ui.radio.css.RadioCssType.RadioHorizontalListGroup;
import com.jmoordb.core.ui.radio.css.RadioHorizontalListGroupCss;
import com.jmoordb.core.ui.radio.css.RadioInlineCss;
import com.jmoordb.core.ui.radio.css.RadioListGroupCss;

/**
 *
 * @author avbravo
 */
public class RadioHeader extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public RadioHeader(String title, RadioCssType radioCssType) {
        super("h3");
        switch(radioCssType){
            case RadioInline:
                  add(new H3(title).addClass(RadioInlineCss.H3.css)
                  );
                break;
            case RadioListGroup:
                 add(new H3(title).addClass(RadioListGroupCss.H3.css)
                  );
                break;
            case RadioHorizontalListGroup:
                 add(new H3(title).addClass(RadioHorizontalListGroupCss.H3.css)
                  );
                break;
        }
      

    }

   
    public RadioHeader addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public RadioHeader add(Label label) {
        if (label != null) {
            withChild(label);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
