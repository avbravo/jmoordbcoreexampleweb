/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.jmoordb.core.ui.css;

/**
 *
 * @author avbravo
 */
public enum RadioListGroupCss {
    Label("w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"),
    Input("w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"),   
    UL("w-48 text-sm font-medium text-gray-900 bg-white border border-gray-200 rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-white"),
    LI("w-full border-b border-gray-200 rounded-t-lg dark:border-gray-600"),
    H3("mb-4 font-semibold text-gray-900 dark:text-white"),
    DIV("flex items-center ps-3"),
    Link("text-blue-600 dark:text-blue-500 hover:underline");

    public final String css;

    private RadioListGroupCss(String css) {
        this.css = css;
    }
}
