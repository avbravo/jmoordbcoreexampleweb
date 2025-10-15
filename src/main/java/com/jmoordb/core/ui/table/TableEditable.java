/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.table;

/**
 *
 * @author avbravo
 */



// La clase debe implementar la interfaz Component para ser aceptada en GridItem.

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import java.util.List;
import java.util.Map;

public class TableEditable implements WebComponent { 

    private final List<String> headers;
    private final Map<String, List<String>> data;

    public TableEditable(List<String> headers, Map<String, List<String>> data) {
        this.headers = headers;
        this.data = data;
    }

    // El método render() es obligatorio para la interfaz Component.
    @Override
    public String render() {
        Tag table = new Tag("table").withClass("table table-striped table-hover");

        // Generar encabezados (<thead>)
        Tag thead = new Tag("thead");
        Tag trHead = new Tag("tr");
        
        headers.forEach(header -> 
            trHead.withChild(new Tag("th").withText(header))
        );
        thead.withChild(trHead);
        table.withChild(thead);

        // Generar cuerpo (<tbody>)
        Tag tbody = new Tag("tbody");
        
        // Simulación de la generación de filas de datos (adaptar a tu lógica real)
        data.forEach((id, values) -> {
            Tag trBody = new Tag("tr");
            trBody.withChild(new Tag("td").withText(id)); // ID de la fila
            values.forEach(value -> 
                trBody.withChild(new Tag("td").withText(value))
            );
            tbody.withChild(trBody);
        });
        
        table.withChild(tbody);
        
        return table.render();
    }
}