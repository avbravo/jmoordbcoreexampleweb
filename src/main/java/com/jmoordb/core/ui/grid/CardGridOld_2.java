/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.grid;

import com.jmoordb.core.ui.Tag;
import java.util.List;
import com.jmoordb.core.ui.WebComponent;

public class CardGridOld_2 implements WebComponent {

    private final List<WebComponent> items;
    private final int columns;

    public CardGridOld_2(List<WebComponent> items, int columns) {
        this.items = items;
        this.columns = columns;
    }

    @Override
    public String render() {
        // Usa las clases de Bootstrap "row" y "g-4" (gap)
        Tag row = new Tag("div").withClass("row g-4");
        
        // Clases de Bootstrap para responsividad
        String colClass = String.format("col-12 col-sm-6 col-lg-%d", 12 / columns); 
        
        for (WebComponent item : items) {
            row.withChild(new Tag("div").withClass(colClass).withChild(item));
        }
        
        return row.render();
    }
}
