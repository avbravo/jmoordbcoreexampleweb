/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.dashboard;

/**
 *
 * @author avbravo
 */


import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;

public class Footer implements WebComponent {
     private final String text;

    public Footer(String text) {
        this.text = text;
    }

     @Override
    public String render() {
        return new Tag("footer").withClass("footer py-3 mt-4")
            .withChild(new Tag("div").withClass("container-fluid")
                .withChild(new Tag("p").withClass("text-center mb-0")
                    .withText(text)))
            .render();
    }
}
