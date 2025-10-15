/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.table;

/**
 *
 * @author avbravo
 */
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import java.util.List;
import java.util.Map;

public class PaginatedTable implements WebComponent {

    private final List<String> headers;
    private final Map<String, List<String>> allData; // Ahora contiene TODOS los datos
    private final int pageSize;
    private final int currentPage;
    private final long totalRecords;
    private final String paginationBaseUrl; // URL base para la paginación (ej: /reports/sales?page=)

    public PaginatedTable(List<String> headers, Map<String, List<String>> allData,
            int pageSize, int currentPage, long totalRecords, String paginationBaseUrl) {
        this.headers = headers;
        this.allData = allData;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalRecords = totalRecords;
        this.paginationBaseUrl = paginationBaseUrl;
    }

    @Override
    public String render() {
        // Contenedor principal para la tabla y la paginación
        Tag container = new Tag("div").withClass("table-responsive");

        // 1. Renderizar la tabla con las filas LIMITADAS
        container.withChild(renderTable());

        // 2. Renderizar el componente de paginación
        container.withChild(renderPagination());

        return container.render();
    }

    // --- Método para renderizar solo la tabla ---
    private Tag renderTable() {
        // Clases de tabla mejoradas para Bootstrap/Tailwind Dark Mode
        Tag table = new Tag("table").withClass("table table-striped table-hover w-full text-sm dark:text-white");

        // Generar encabezados (<thead>)
        Tag thead = new Tag("thead").withClass("text-xs uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-300");
        Tag trHead = new Tag("tr");

        // Columna para el ID/Clave
        trHead.withChild(new Tag("th").withClass("px-6 py-3").withText("ID"));

        headers.forEach(header
                -> trHead.withChild(new Tag("th").withClass("px-6 py-3").withText(header))
        );
        thead.withChild(trHead);
        table.withChild(thead);

        // Generar cuerpo (<tbody>) con LIMIT y OFFSET
        Tag tbody = new Tag("tbody");

        // La clave de la paginación en el servidor es aquí.
        // Dado que el Map 'allData' ya fue filtrado en el Controller/Service, 
        // simplemente iteramos sobre él. Si 'allData' contiene TODOS los registros, 
        // ¡la lógica de limitación debe estar ANTES de llamar a este constructor!
        // Asumiendo que 'allData' solo contiene los datos de la página actual:
        String rowClass = "bg-white border-b dark:bg-transparent dark:border-gray-700";

        allData.forEach((id, values) -> {
            Tag trBody = new Tag("tr").withClass(rowClass);

            // Columna ID
            trBody.withChild(new Tag("td").withClass("px-6 py-4 font-medium whitespace-nowrap").withText(id));

            values.forEach(value
                    -> trBody.withChild(new Tag("td").withClass("px-6 py-4").withText(value))
            );
            tbody.withChild(trBody);
        });

        table.withChild(tbody);

        return table;
    }
// Dentro de la clase PaginatedTable.java

    // Dentro de la clase PaginatedTable.java

    // --- Método para renderizar la paginación ---
    private Tag renderPagination() {
        if (totalRecords <= pageSize) {
            return new Tag("div"); // No mostrar paginación si cabe en una página
        }
        
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        
        Tag nav = new Tag("nav").withClass("d-flex justify-content-center mt-3");
        Tag ul = new Tag("ul").withClass("pagination");

        // Función para crear un elemento <li>
        java.util.function.Function<Integer, Tag> createPageItem = (pageNumber) -> {
            boolean isPreviousOrNext = pageNumber == 0 || pageNumber == totalPages + 1;
            int targetPage;
            String linkText;
            String liClass = "page-item";
            
            // 1. Determinar el texto y la página destino
            if (pageNumber == 0) { // Botón 'Previous'
                linkText = "Previous";
                targetPage = Math.max(1, currentPage - 1);
            } else if (pageNumber == totalPages + 1) { // Botón 'Next'
                linkText = "Next";
                targetPage = Math.min(totalPages, currentPage + 1);
            } else { // Botones numéricos
                linkText = String.valueOf(pageNumber);
                targetPage = pageNumber;
            }
            
            boolean isActive = targetPage == currentPage && !isPreviousOrNext;
            boolean isDisabled = (pageNumber == 0 && currentPage == 1) || (pageNumber == totalPages + 1 && currentPage == totalPages);
            
            // 2. Construir Clases de Tema
            String linkClass = "page-link"; // Clases base de Bootstrap
            
            // Estilos de Modo Oscuro para el estado NORMAL/HOVER
            linkClass += " dark:bg-gray-800 dark:border-gray-700 dark:text-white dark:hover:bg-gray-700";

            // 3. Aplicar Clases de Estado (Active / Disabled)
            if (isActive) {
                liClass += " active";
                // Enlace activo: Color azul para destacar (anula el hover)
                linkClass = "page-link dark:bg-blue-600 dark:border-blue-700 dark:text-white"; 
            } else if (isDisabled) {
                liClass += " disabled";
                // ⭐ CORRECCIÓN CLAVE: Estilos para el estado disabled en Dark Mode
                // Bootstrap hace que el linkDisabled sea gris y sin interacción.
                linkClass += " disabled dark:bg-gray-900 dark:border-gray-700 dark:text-gray-500 cursor-not-allowed";
            }
            
            // 4. Construir el <a>
            String url = paginationBaseUrl + targetPage;
            Tag a = new Tag("a").withClass(linkClass)
                                .withAttribute("href", isDisabled ? "#" : url) // '#' si está deshabilitado
                                .withText(linkText);
            
            if (isPreviousOrNext) {
                 a.withAttribute("aria-label", linkText);
            }
            if (isActive) {
                a.withAttribute("aria-current", "page");
            }
            
            // 5. Crea el <li>
            Tag li = new Tag("li").withClass(liClass).withChild(a);
            return li;
        };

        // 1. Botón "Previous" (0)
        ul.withChild(createPageItem.apply(0));
        
        // 2. Números de página
        int startPage = Math.max(1, currentPage - 2);
        int endPage = Math.min(totalPages, currentPage + 2);

        for (int i = startPage; i <= endPage; i++) {
            ul.withChild(createPageItem.apply(i));
        }

        // 3. Botón "Next" (totalPages + 1)
        ul.withChild(createPageItem.apply(totalPages + 1));
        
        nav.withChild(ul);
        return nav;
    }
    

}
