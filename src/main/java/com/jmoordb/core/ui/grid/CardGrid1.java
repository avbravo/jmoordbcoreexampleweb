/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.grid;



import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import jakarta.servlet.http.HttpServletRequest; // Necesario para obtener la solicitud
import java.util.List;

public class CardGrid1 implements WebComponent {
    private final List<WebComponent> items;
    private final int columns;
    private final HttpServletRequest request; // Añadir la solicitud

    public CardGrid1(List<WebComponent> items, int columns, HttpServletRequest request) {
        this.items = items;
        this.columns = columns;
        this.request = request;
    }

    @Override
    public String render() {
        // Obtener la preferencia del framework
        String framework = (String) request.getSession().getAttribute("cssFramework");
        boolean isTailwind = "tailwind".equals(framework);
        
        Tag row;
        String colClass;
        
        if (isTailwind) {
            // ⭐ CLASES DE TAILWIND
            row = new Tag("div").withClass("grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-" + columns + " gap-4");
            colClass = ""; // Tailwind no necesita una clase de columna específica aquí, el 'grid' lo maneja
        } else {
            // CLASES DE BOOTSTRAP
            row = new Tag("div").withClass("row g-4");
            colClass = String.format("col-12 col-sm-6 col-lg-%d", 12 / columns); 
        }
        
        for (WebComponent item : items) {
            Tag itemWrapper = new Tag("div").withChild(item);
            if (!colClass.isEmpty()) {
                itemWrapper.withClass(colClass);
            }
            row.withChild(itemWrapper);
        }
        
        return row.render();
    }
}