/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.alert;

import com.jmoordb.core.ui.A;
import com.jmoordb.core.ui.Button;
import com.jmoordb.core.ui.ButtonType;
import com.jmoordb.core.ui.Div;
import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Span;
import com.jmoordb.core.ui.Svg;
import com.jmoordb.core.ui.SvgPath;
import com.jmoordb.core.ui.WebComponent;

/**
 *
 * @author avbravo
 */
public class Alert implements WebComponent {

    private final String mensaje;
    private final String tipo; // success, danger, warning, info
    private final boolean autoCerrar;

    /**
     * Constructor para generar un mensaje de alerta de Bootstrap.
     *
     * @param mensaje El texto del mensaje.
     * @param tipo El tipo de alerta (ej. "success", "danger").
     * @param autoCerrar Si debe incluir el botón de cierre (x).
     */
    public Alert(String mensaje, String tipo, boolean autoCerrar) {
        this.mensaje = mensaje;
        this.tipo = tipo.toLowerCase().trim();
        this.autoCerrar = autoCerrar;
    }

    @Override
    public String render() {
        // Clases de Bootstrap: alert y alert-[tipo]
        Div alerta = new Div();

        switch (tipo) {
            case "danger":
                alerta = new Div()
                        .id("alert-border-2")
                        .addClass("flex items-center p-4 mb-4 text-red-800 border-t-4 border-red-300 bg-red-50 dark:text-red-400 dark:bg-gray-800 dark:border-red-800")
                        .attribute("role", "alert")
                        .add(
                                new Svg()
                                        .addClass("shrink-0 w-4 h-4").aria_hidden("true")
                                        .xmlns("http://www.w3.org/2000/svg")
                                        .fill("currentColor")
                                        .viewBox("0 0 20 20")
                                        .add(new SvgPath()
                                                .d("M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z")
                                        )
                        )
                        .add(
                                new Div().addClass("ms-3 text-sm font-medium")
                                        .add(new Label().text(mensaje))
                                        .add(
                                                new A()
                                                        .href("#")
                                                        .addClass("font-semibold underline hover:no-underline")
                                                        .text("")
                                        )
                        )
                        .add(
                                new Button().type(ButtonType.BUTTON)
                                        .addClass("ms-auto -mx-1.5 -my-1.5 bg-red-50 text-red-500 rounded-lg focus:ring-2 focus:ring-red-400 p-1.5 hover:bg-red-200 inline-flex items-center justify-center h-8 w-8 dark:bg-gray-800 dark:text-red-400 dark:hover:bg-gray-700")
                                        .data_dismiss_target("#alert-border-2")
                                        .aria_label("Close")
                                        .add(new Span()
                                                .addClass("sr-only")
                                                .text("Dismiss")
                                        )
                                        .add(new Svg()
                                                .addClass("w-3 h-3")
                                                .aria_hidden("true")
                                                .xmlns("http://www.w3.org/2000/svg")
                                                .fill("none")
                                                .viewBox("0 0 14 14")
                                                .add(
                                                        new SvgPath()
                                                        .stroke("currentColor")
                                                        .stroke_linecap("round")
                                                        .stroke_linejoin("round")
                                                        .stroke_width("2")
                                                        .d("m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6")
                                                )
                                        )
                        );
                break;
        }


        return alerta.render();
    }

    public WebComponent build() {
        return this;
    }
}
