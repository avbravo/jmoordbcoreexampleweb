/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.panel;

/**
 *
 * @author avbravo
 */
import com.jmoordb.core.ui.Tag;
import java.util.List;
import com.jmoordb.core.ui.WebComponent;

public class Panel implements WebComponent {

    // El componente de tabla es fijo en el layout de inventario.
    private final WebComponent tabla;

    // Una lista de componentes que se inyectarán en la parte superior (e.g., Mensajes).
    private final List<WebComponent> componentesSuperiores;

    /**
     * Constructor para el panel principal del dashboard.
     *
     * @param tabla El componente de tabla editable a incrustar.
     * @param componentesSuperiores Componentes a colocar antes del layout de
     * filas (e.g., alertas).
     */
    public Panel(WebComponent tabla, List<WebComponent> componentesSuperiores) {
        this.tabla = tabla;
        this.componentesSuperiores = componentesSuperiores;
    }

    @Override
    public String render() {

        Tag contenedor = new Tag("div").withClass("container mt-4");

        // 1. Inyectar Componentes Superiores (e.g., Mensajes/Alertas)
        if (componentesSuperiores != null) {
            componentesSuperiores.forEach(contenedor::withChild);
        }

        // 2. Título Principal
        contenedor.withChild(new Tag("h1").withClass("mb-4").withText("Dashboard de Gestión"));

        // 3. Layout de Filas
        Tag row = new Tag("div").withClass("row")
                // Tarjeta 1: Ventas Totales (Fija en este diseño)
                .withChild(new Tag("div").withClass("col-md-4 mb-4")
                        .withChild(new Tag("div").withClass("card bg-info text-white")
                                .withChild(new Tag("div").withClass("card-body")
                                        .withChild(new Tag("h5").withClass("card-title").withText("Ventas Totales"))
                                        .withChild(new Tag("p").withClass("card-text fs-3").withText("$5,400.00")))))
                // Tarjeta 2: Inventario Editable (Inyecta el componente de tabla)
                .withChild(new Tag("div").withClass("col-md-8")
                        .withChild(new Tag("div").withClass("card shadow-sm")
                                .withChild(new Tag("div").withClass("card-header").withText("Inventario Editable"))
                                .withChild(new Tag("div").withClass("card-body")
                                        .withChild(this.tabla)))); // ⬅️ Tabla inyectada

        contenedor.withChild(row);

        return contenedor.render();
    }
}
