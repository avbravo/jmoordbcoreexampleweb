
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.form;

import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.input.InputColor;
import com.jmoordb.core.ui.input.InputDate;
import com.jmoordb.core.ui.input.InputEmail;
import com.jmoordb.core.ui.input.InputFile;
import com.jmoordb.core.ui.input.InputHidden;
import com.jmoordb.core.ui.input.InputNumber;
import com.jmoordb.core.ui.input.InputPassword;
import com.jmoordb.core.ui.input.InputRadio;
import com.jmoordb.core.ui.input.InputRange;
import com.jmoordb.core.ui.input.InputSearch;
import com.jmoordb.core.ui.input.InputText;
import com.jmoordb.core.ui.input.InputTime;
import com.jmoordb.core.ui.input.TypeInput;

/**
 *
 * @author avbravo
 */
public class Grid extends Tag {

    String labelClass = "block mb-2 text-sm font-medium text-gray-900 dark:text-white";
    String inputClass = "bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500";

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Grid() {

        super("div");
        withClass("grid gap-6 mb-6 md:grid-cols-2");
      

    }
   
    public Grid addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public Grid add(InputCol inputCol) {
        if (inputCol != null) {
            withChild(inputCol);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

}
