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
import java.util.function.Function;

public class PaginatedTable implements WebComponent {
    // ... (constructor y renderTable omitidos por brevedad, son los correctos) ...
    private final List<String> headers;
    private final Map<String, List<String>> allData;
    private final int pageSize;
    private final int currentPage;
    private final long totalRecords;
    private final String paginationBaseUrl;
    int rowIndex = 0;
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
        Tag container = new Tag("div").withClass("table-responsive");
        container.withChild(renderTable());
        container.withChild(renderPagination());
        return container.render();
    }
    
    private Tag renderTable() {
         Tag table = new Tag("table").withClass("w-full text-sm text-left dark:text-white"); 
        Tag thead = new Tag("thead").withClass("text-xs uppercase bg-gray-50 dark:bg-gray-800 dark:text-gray-300 border-b dark:border-gray-700");
        Tag trHead = new Tag("tr");
        trHead.withChild(new Tag("th").withClass("px-6 py-3 font-medium").withText("ID"));

        headers.forEach(header -> trHead.withChild(new Tag("th").withClass("px-6 py-3 font-medium").withText(header)));
        thead.withChild(trHead);
        table.withChild(thead);

        Tag tbody = new Tag("tbody");
        String rowClassEven = "bg-white border-b dark:bg-transparent dark:border-gray-700";
        String rowClassOdd = "bg-gray-50 border-b dark:bg-transparent dark:border-gray-700";
       rowIndex = 0;

        allData.forEach((id, values) -> {
            String currentRowClass = ((rowIndex % 2 == 0) ? rowClassEven : rowClassOdd) 
                                   + " hover:bg-gray-100 dark:hover:bg-gray-700";
            
            Tag trBody = new Tag("tr").withClass(currentRowClass);

            trBody.withChild(new Tag("td").withClass("px-6 py-4 font-medium whitespace-nowrap").withText(id));

            values.forEach(value -> trBody.withChild(new Tag("td").withClass("px-6 py-4").withText(value)));
            
            tbody.withChild(trBody);
            rowIndex++;
        });

        table.withChild(tbody);
        return table;
    }

    // -------------------------------------------------------------------------
    // --- Método para renderizar la paginación (CORREGIDO PARA DARK MODE)
    // -------------------------------------------------------------------------
    private Tag renderPagination() {
        if (totalRecords <= pageSize) {
            return new Tag("div"); 
        }
        
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        
        Tag nav = new Tag("nav").withClass("flex justify-center mt-4");
        Tag ul = new Tag("ul").withClass("flex items-center space-x-2"); 

        Function<Integer, Tag> createPageItem = (pageNumber) -> {
            boolean isPreviousOrNext = pageNumber == 0 || pageNumber == totalPages + 1;
            int targetPage;
            String linkText;
            
            if (pageNumber == 0) { 
                linkText = "Previous";
                targetPage = Math.max(1, currentPage - 1);
            } else if (pageNumber == totalPages + 1) { 
                linkText = "Next";
                targetPage = Math.min(totalPages, currentPage + 1);
            } else { 
                linkText = String.valueOf(pageNumber);
                targetPage = pageNumber;
            }
            
            boolean isActive = targetPage == currentPage && !isPreviousOrNext;
            boolean isDisabled = (pageNumber == 0 && currentPage == 1) || (pageNumber == totalPages + 1 && currentPage == totalPages);
            
            String linkClass = "px-4 py-2 border rounded transition duration-150 ease-in-out text-sm"; // Añadido text-sm
            
            if (isActive) {
                linkClass += " bg-blue-600 border-blue-600 text-white dark:bg-blue-600 dark:border-blue-600 dark:text-white";
            } else if (isDisabled) {
                linkClass += " bg-gray-100 text-gray-400 border-gray-300 cursor-not-allowed dark:bg-gray-900 dark:border-gray-700 dark:text-gray-600";
            } else {
                linkClass += " bg-white text-gray-700 border-gray-300 hover:bg-gray-50 dark:bg-gray-800 dark:border-gray-700 dark:text-white dark:hover:bg-gray-700";
            }
            
            // ⭐ CLAVE: URL generada para la recarga del Servlet
            String url = paginationBaseUrl + targetPage; 
            Tag a = new Tag("a").withClass(linkClass)
                                .withAttribute("href", isDisabled ? "#" : url)
                                .withText(linkText);
            
            if (isPreviousOrNext) { a.withAttribute("aria-label", linkText); }
            if (isActive) { a.withAttribute("aria-current", "page"); }
            
            return new Tag("li").withChild(a);
        };

        ul.withChild(createPageItem.apply(0));
        
        int startPage = Math.max(1, currentPage - 2);
        int endPage = Math.min(totalPages, currentPage + 2);

        for (int i = startPage; i <= endPage; i++) {
            ul.withChild(createPageItem.apply(i));
        }

        ul.withChild(createPageItem.apply(totalPages + 1));
        
        nav.withChild(ul);
        return nav;
    }
}