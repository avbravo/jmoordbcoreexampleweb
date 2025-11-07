/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.icon;

/**
 *
 * @author avbravo
 */


/**
 * Enum de Flowbite Icons almacenando el código SVG completo para inyección directa en HTML.
 * El SVG tiene los atributos base de Flowbite (w-6 h-6 text-gray-800 dark:text-white).
 */
public enum FlowbiteIconSvg {

    // -------------------------------------------------------------------------
    // I. ACCIÓN Y EDICIÓN (Ejemplo: Su icono de add-column-after-outline)
    // -------------------------------------------------------------------------

    // Nombre: ADD_COLUMN_AFTER_OUTLINE
    ADD_COLUMN_AFTER_OUTLINE(
        "add-column-after", "outline",
        // SVG CODE INLINE
        "<svg class=\"w-6 h-6 text-gray-800 dark:text-white\" aria-hidden=\"true\" xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" fill=\"none\" viewBox=\"0 0 24 24\">" +
        "  <path stroke=\"currentColor\" stroke-linecap=\"round\" stroke-linejoin=\"round\" stroke-width=\"2\" d=\"M15 5v14m-8-7h2m0 0h2m-2 0v2m0-2v-2m12 1h-6m6 4h-6M4 19h16c.5523 0 1-.4477 1-1V6c0-.55228-.4477-1-1-1H4c-.55228 0-1 .44772-1 1v12c0 .5523.44772 1 1 1Z\"/>" +
        "</svg>"
    ),

    // Nombre: TRASH_CAN_OUTLINE (Papelera)
    TRASH_CAN_OUTLINE(
        "trash", "outline",
        // SVG CODE INLINE
        "<svg class=\"w-6 h-6 text-gray-800 dark:text-white\" aria-hidden=\"true\" xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" fill=\"none\" viewBox=\"0 0 24 24\">" +
        "  <path stroke=\"currentColor\" stroke-linecap=\"round\" stroke-linejoin=\"round\" stroke-width=\"2\" d=\"M5 7h14m-9 3v8m4-8v8M10 7V4a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3M4 7h16a1 1 0 0 1 1 1v11a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V8a1 1 0 0 1 1-1Z\"/>" +
        "</svg>"
    ),

    // -------------------------------------------------------------------------
    // II. INTERFAZ COMÚN (Ejemplo: HOME_SOLID)
    // -------------------------------------------------------------------------

    // Nombre: HOME_SOLID
    HOME_SOLID(
        "home", "solid",
        // SVG CODE INLINE
        "<svg class=\"w-6 h-6 text-gray-800 dark:text-white\" aria-hidden=\"true\" xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" fill=\"currentColor\" viewBox=\"0 0 24 24\">" +
        "  <path fill-rule=\"evenodd\" d=\"M11.293 3.293a1 1 0 0 1 1.414 0l6 6h-6.414a1 1 0 0 0-.707.293l-6 6a1 1 0 0 1-1.414-1.414L11.293 3.293Z\" clip-rule=\"evenodd\"/>" +
        "  <path fill-rule=\"evenodd\" d=\"M11 7.151V3.21a1 1 0 0 0-.258-.696l-.004-.007a1 1 0 0 0-.6-.247c-.201.002-.397.086-.541.226L4.793 8.293A1 1 0 0 0 4 9v9a2 2 0 0 0 2 2h4.5a1 1 0 1 0 0-2H6v-9l7.293-7.293A1 1 0 0 1 14 3.793V7.151a1 1 0 0 0 2 0V3.793l3.293 3.293A1 1 0 0 0 20 8v10a2 2 0 0 1-2 2h-4.5a1 1 0 1 0 0 2H18a4 4 0 0 0 4-4V9a3 3 0 0 0-.879-2.121l-7.707-7.707a3 3 0 0 0-4.242 0L2.879 5.879A3 3 0 0 0 2 8v10a4 4 0 0 0 4 4h12a4 4 0 0 0 4-4V8a1 1 0 0 0-1.707-.707L12 1.293A1 1 0 0 0 10.586 1.293l-6-6Z\" clip-rule=\"evenodd\"/>" +
        "</svg>"
    );
    
    // -------------------------------------------------------------------------
    
    private final String name;
    private final String style;
    private final String svgCode;

    private FlowbiteIconSvg(String name, String style, String svgCode) {
        this.name = name;
        this.style = style;
        this.svgCode = svgCode;
    }

    /**
     * Obtiene el código SVG completo para inyectar directamente en el HTML.
     */
    public String getSvgCode() {
        return svgCode;
    }

    /**
     * Obtiene la referencia del icono (ej. "home-solid").
     */
    public String getReference() {
        return name + "-" + style;
    }
}