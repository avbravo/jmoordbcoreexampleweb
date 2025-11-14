/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.jmoordb.core.ui.css;

/**
 *
 * @author avbravo
 */
public enum RadioAdvancedLayoutCss {
    H3("mb-5 text-lg font-medium text-gray-900 dark:text-white"),
    UL("grid w-full gap-6 md:grid-cols-2"),
    Input("hidden peer"),     
    Label("inline-flex items-center justify-between w-full p-5 text-body bg-neutral-primary-soft border-1 border-default rounded-base cursor-pointer peer-checked:hover:bg-brand-softer peer-checked:border-brand-subtle peer-checked:bg-brand-softer hover:bg-neutral-secondary-medium peer-checked:text-fg-brand-strong"),
    Div("block"),
    Div1("w-full font-semibold"),
    Div2("w-full"),
    SVG("w-5 h-5 ms-3 rtl:rotate-180");
    
    public final String css;

    private RadioAdvancedLayoutCss(String css) {
        this.css = css;
    }
}
