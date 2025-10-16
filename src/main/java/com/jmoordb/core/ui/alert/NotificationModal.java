package com.jmoordb.core.ui.alert; // ⭐ Paquete reutilizable

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;

public class NotificationModal implements WebComponent {

    private final String id;
    private final String title;
    private final String detail;
    private final String level; // Valores: "Informacion", "Advertencia", "Error", "Critico"

    public NotificationModal(String id, String title, String detail, String level) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.level = level;
    }

    @Override
    public String render() {
        // Lógica de asignación de clases y SVG (omito el detalle por brevedad, es el código anterior corregido)
        String buttonClass;
        String iconContainerClass;
        String iconHtml;
        
        // ... (Tu bloque switch para asignar buttonClass, iconContainerClass, iconHtml basado en el level) ...
        
        switch (level.toLowerCase()) {
            case "error":
                buttonClass = "bg-red-600 hover:bg-red-700 dark:bg-red-700 dark:hover:bg-red-600";
                iconContainerClass = "bg-red-100 dark:bg-red-700/50";
                iconHtml = "<svg class='h-6 w-6 text-red-600 dark:text-red-400' fill='none' viewBox='0 0 24 24' stroke='currentColor' stroke-width='2'><path stroke-linecap='round' stroke-linejoin='round' d='M6 18L18 6M6 6l12 12' /></svg>";
                break;
            case "advertencia":
                buttonClass = "bg-yellow-500 hover:bg-yellow-600 dark:bg-yellow-600 dark:hover:bg-yellow-500";
                iconContainerClass = "bg-yellow-100 dark:bg-yellow-700/50";
                iconHtml = "<svg class='h-6 w-6 text-yellow-600 dark:text-yellow-400' fill='none' viewBox='0 0 24 24' stroke='currentColor' stroke-width='2'><path stroke-linecap='round' stroke-linejoin='round' d='M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.3 16c-.77 1.333.192 3 1.732 3z' /></svg>";
                break;
            case "critico":
                 buttonClass = "bg-gray-800 hover:bg-gray-900 dark:bg-gray-700 dark:hover:bg-gray-900";
                 iconContainerClass = "bg-gray-300 dark:bg-gray-700/50";
                 iconHtml = "<svg class='h-6 w-6 text-gray-800 dark:text-gray-300' fill='none' viewBox='0 0 24 24' stroke='currentColor' stroke-width='2'><path stroke-linecap='round' stroke-linejoin='round' d='M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z' /></svg>";
                 break;
            case "informacion":
            default:
                buttonClass = "bg-blue-600 hover:bg-blue-700 dark:bg-blue-700 dark:hover:bg-blue-600";
                iconContainerClass = "bg-blue-100 dark:bg-blue-700/50";
                iconHtml = "<svg class='h-6 w-6 text-blue-600 dark:text-blue-400' xmlns='http://www.w3.org/2000/svg' fill='none' viewBox='0 0 24 24' stroke='currentColor'><path stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M5 13l4 4L19 7' /></svg>";
                break;
        }

        // Estructura HTML del Modal
        return new Tag("div").withAttribute("id", id).withClass("hidden fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full z-50")
             .withChild(new Tag("div").withClass("relative top-20 mx-auto p-5 border w-96 shadow-lg rounded-md bg-white dark:bg-gray-800")
             .withChild(new Tag("div").withClass("mt-3 text-center")
             
             // Icono Container
             .withChild(
                 new Tag("div").withClass("mx-auto flex items-center justify-center h-12 w-12 rounded-full " + iconContainerClass)
                     .withText(iconHtml) 
             )
             
             // Título
             .withChild(new Tag("h3").withClass("text-lg leading-6 font-medium text-gray-900 mt-2 dark:text-white").withText(title))
             
             // Detalle
             .withChild(new Tag("div").withClass("mt-2 px-7 py-3")
             .withChild(new Tag("p").withClass("text-sm text-gray-500 dark:text-gray-400").withText(detail)))
             
             // Botón Cerrar
             .withChild(new Tag("div").withClass("items-center px-4 py-3")
             .withChild(new Tag("button").withAttribute("id", id + "-close")
             .withClass("px-4 py-2 text-white rounded-md w-full shadow-sm " + buttonClass) 
             .withText("Cerrar")))))
             
             .render(); 
    }
    

}