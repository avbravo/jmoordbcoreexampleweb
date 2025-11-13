/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.jmoordb.core.ui.radio.css;

/**
 *
 * @author avbravo
 */
public enum RadioInlineCss {
    Label("select-none ms-2 text-sm font-medium text-heading"),
    Input("w-4 h-4 text-neutral-primary border-default-medium bg-neutral-secondary-medium rounded-full checked:border-brand focus:ring-2 focus:outline-none focus:ring-brand-subtle border border-default appearance-none"),   
    Div("flex"),
    DivRow("flex items-center me-4"),
    H3("mb-4 font-semibold text-gray-900 dark:text-white"),
    Link("text-blue-600 dark:text-blue-500 hover:underline");

    public final String css;

    private RadioInlineCss(String css) {
        this.css = css;
    }
}
