/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.jmoordb.core.ui.css;

/**
 *
 * @author avbravo
 */
public enum GridColCss {
    Label("block mb-2 text-sm font-medium text-gray-900 dark:text-white"),
    Input("bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500");
    
    
    public final String css;

    private GridColCss(String css) {
        this.css = css;
    }
}
