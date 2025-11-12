/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.jmoordb.core.ui.css;

/**
 *
 * @author avbravo
 */
public enum RadioDropdownCss {
    Label("font-medium text-gray-900 dark:text-gray-300"),
    Input("w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"),   
    DivInput("flex p-2 rounded-sm hover:bg-gray-100 dark:hover:bg-gray-600"),
    DivInputElement("flex items-center h-5"),
    DivLabel("ms-2 text-sm"),
    UL("p-3 space-y-1 text-sm text-gray-700 dark:text-gray-200"),
    P("text-xs font-normal text-gray-500 dark:text-gray-300"),
    Link("text-blue-600 dark:text-blue-500 hover:underline");

    public final String css;

    private RadioDropdownCss(String css) {
        this.css = css;
    }
}
