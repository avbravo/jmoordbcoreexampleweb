/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.icon;

/**
 *
 * @author avbravo
 */
public class FontAwesomenIconUtil {

    public static String toIcon(FontAwesomeIcon icon) {
        if (icon == null) {
            return ""; // O maneja el valor nulo de otra manera
        }

        // Combina el estilo (fa-solid, fa-regular, etc.) y el nombre del icono (fa-house, fa-image, etc.)
        return String.format("%s %s", icon.getStyle(), icon.getIconName());
    }

    /**
     * Opcional: genera la etiqueta HTML completa del <i>.
     *
     * * @param icon El icono del enum a convertir.
     * @return La etiqueta HTML completa.
     */
    public static String toHtmlTag(FontAwesomeIcon icon) {
        String classes = toIcon(icon);
        return String.format("<i class=\"%s\"></i>", classes);
    }
}
