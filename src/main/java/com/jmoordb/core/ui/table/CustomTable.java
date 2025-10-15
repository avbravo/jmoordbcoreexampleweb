/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.table;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;

/**
 *
 * @author avbravo
 */


public class CustomTable implements WebComponent {
    private final String title;
    
    public CustomTable(String title) { this.title = title; }
    
    @Override
    public String render() {
        
        // ⭐ CAMBIO CLAVE: Cambiar 'dark:text-gray-400' a 'dark:text-white'
        // Además, se recomienda cambiar 'text-gray-500' a 'text-gray-900' para mejor contraste en modo claro.
        String tableClasses = "w-full text-sm text-left text-gray-900 dark:text-white";
        
        return new Tag("table").withClass(tableClasses)
            .withChild(new Tag("thead")
                .withChild(new Tag("tr").withChild(new Tag("th").withText(title))))
            .withChild(new Tag("tbody").withChild(new Tag("tr").withChild(new Tag("td").withText("Dato 1")))).render();
    }
}

//public class CustomTable implements WebComponent {
//    private final String title;
//    public CustomTable(String title) { this.title = title; }
//    @Override
//    public String render() {
//        return new Tag("table").withClass("w-full text-sm text-left text-gray-500 dark:text-gray-400")
//            .withChild(new Tag("thead").withChild(new Tag("tr").withChild(new Tag("th").withText(title))))
//            .withChild(new Tag("tbody").withChild(new Tag("tr").withChild(new Tag("td").withText("Dato 1")))).render();
//    }
//}

