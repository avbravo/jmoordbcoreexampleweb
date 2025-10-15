/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.grid;

import com.jmoordb.core.ui.WebComponent;



/**
 *
 * @author avbravo
 */
public class GridItemOld_1 {

    private final WebComponent component;
    private final int colSm; // Ancho en pantallas pequeñas (Small, por defecto)
    private final int colMd; // Ancho en pantallas medianas (Medium)
    private final int colLg; // Ancho en pantallas grandes (Large)

    /**
     * Define un elemento a ser colocado en la cuadrícula, con control
     * responsivo.
     *
     * @param componente El Componente a renderizar dentro de la columna.
     * @param colSm El ancho de la columna en Móviles (col-sm-*).
     * @param colMd El ancho de la columna en Tablets (col-md-*).
     * @param colLg El ancho de la columna en Desktops (col-lg-*).
     */
    public GridItemOld_1(WebComponent component, int colSm, int colMd, int colLg) {
        // Validación simple para asegurar que los valores son válidos
        if (colSm < 1 || colSm > 12 || colMd < 1 || colMd > 12 || colLg < 1 || colLg > 12) {
            throw new IllegalArgumentException("Todos los anchos de columna deben estar entre 1 y 12.");
        }
        this.component = component;
        this.colSm = colSm;
        this.colMd = colMd;
        this.colLg = colLg;
    }

    public WebComponent getComponent() {
        return component;
    }

    public int getColSm() {
        return colSm;
    }

    public int getColMd() {
        return colMd;
    }

    public int getColLg() {
        return colLg;
    }

    /**
     * Genera la cadena de clases CSS de Bootstrap para la columna.
     */
    public String getCssClasses() {
        // Genera una cadena como: "col-sm-12 col-md-6 col-lg-4"
        return "col-sm-" + colSm + " col-md-" + colMd + " col-lg-" + colLg;
    }

}
