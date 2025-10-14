/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.panel;

import com.jmoordb.core.ui.Tag;
import java.util.List;
import com.jmoordb.core.ui.WebComponent;

public class CardGrid implements WebComponent {

    private final String titulo;
    private final List<GridItem> items;

    /**
     * Componente que genera una cuadrícula (Grid) responsiva dentro de un Card
     * de Bootstrap.
     *
     * @param titulo El título del Card Header.
     * @param items La lista de elementos con sus especificaciones de columna
     * responsivas.
     */
    public CardGrid(String titulo, List<GridItem> items) {
        this.titulo = titulo;
        this.items = items;
    }

    @Override
    public String render() {
        // 1. Estructura base: Card de Bootstrap
        Tag card = new Tag("div").withClass("card shadow-sm");

        // Header
        card.withChild(new Tag("div").withClass("card-header").withText(titulo));

        // Body del Card
        Tag cardBody = new Tag("div").withClass("card-body");

        // 2. Contenedor de Fila Responsiva
        // Usamos la clase 'row' de Bootstrap que maneja las filas y el 'g-3' para el espaciado (gap)
        Tag gridRow = new Tag("div").withClass("row g-3");

        // 3. Generar las Columnas Responsivas
        items.forEach(item -> {
            // Obtiene la cadena de clases responsivas: "col-sm-X col-md-Y col-lg-Z"
            String colClasses = item.getCssClasses();

            // Crea la etiqueta de columna e inyecta el componente
            Tag columna = new Tag("div")
                    .withClass(colClasses)
                    .withChild(item.getComponent());

            gridRow.withChild(columna);
        });

        cardBody.withChild(gridRow);
        card.withChild(cardBody);

        return card.render();
    }
}
