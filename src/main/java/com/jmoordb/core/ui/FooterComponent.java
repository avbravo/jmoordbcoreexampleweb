/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui;

public class FooterComponent implements WebComponent {
    private final String text;

    public FooterComponent(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return new Tag("footer").withClass("footer mt-auto py-3 bg-light-custom")
            .withChild(new Tag("div").withClass("container text-center")
                .withChild(new Tag("span").withClass("text-muted")
                    .withText(text)))
            .render();
    }
}