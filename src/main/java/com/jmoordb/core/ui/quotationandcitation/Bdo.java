/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.quotationandcitation;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;

/**
 *
 * @author avbravo
 */
public class Bdo extends Tag {

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Bdo() {

        super("bdo");

    }

    public Bdo dir(BdoType bdoType) {
        switch(bdoType){
            case LTR:
                withAttribute("dir", "ltr");
                break;
            case RTL:
                withAttribute("dir", "rtl");
                 break;
        }
        
        return this;
    }

    public Bdo add(WebComponent webComponent) {
        if (webComponent != null) {
            withChild(webComponent);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
