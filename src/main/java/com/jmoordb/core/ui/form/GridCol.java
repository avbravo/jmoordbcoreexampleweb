
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.form;

import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.css.GridColCss;
import com.jmoordb.core.ui.input.InputColor;
import com.jmoordb.core.ui.input.InputDate;
import com.jmoordb.core.ui.input.InputEmail;
import com.jmoordb.core.ui.input.InputFile;
import com.jmoordb.core.ui.input.InputHidden;
import com.jmoordb.core.ui.input.InputNumber;
import com.jmoordb.core.ui.input.InputPassword;
import com.jmoordb.core.ui.input.InputRange;
import com.jmoordb.core.ui.input.InputSearch;
import com.jmoordb.core.ui.input.InputText;
import com.jmoordb.core.ui.input.InputTime;
import com.jmoordb.core.ui.input.TypeInput;
import com.jmoordb.core.ui.radio.RadioItem;

/**
 *
 * @author avbravo
 */
public class GridCol extends Tag {

    String labelClass = GridColCss.Label.css;
    String inputClass = GridColCss.Input.css;

    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */

    // <editor-fold defaultstate="collapsed" desc="GridCol(String label, String idAndName)">
    public GridCol(String label, String idAndName) {
        super("div");
        add(
                new Label(label, labelClass, idAndName)
        );
        add(
                new InputText(idAndName, idAndName, inputClass)
        );
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GridCol(String label, String id, String name)">
    public GridCol(String label, String id, String name) {

        super("div");
        add(
                new Label(label, labelClass, id)
        );
        add(
                new InputText(id, name, inputClass)
        );

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GridCol(Label label, Tag input)">
    public GridCol(Label label, Tag input) {
        super("div");
        add(label);
        add(input);

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public GridCol(String label, String id, String name, TypeInput typeInput)">
    public GridCol(String label, String id, String name, TypeInput typeInput) {

        super("div");
        add(
                new Label(label, labelClass, id)
        );
        switch (typeInput) {
            case TypeInput.COLOR:
                add(
                        new InputColor(id, name, inputClass)
                );
                break;
            case TypeInput.DATE:
                add(
                        new InputDate(id, name, inputClass)
                );
                break;
            case TypeInput.EMAIL:
                add(
                        new InputEmail(id, name, inputClass)
                );
                break;
            case TypeInput.FILE:
                add(
                        new InputFile(id, name, inputClass)
                );
                break;
            case TypeInput.HIDDEN:
                add(
                        new InputHidden(id, name, inputClass)
                );
                break;
            case TypeInput.NUMBER:
                add(
                        new InputNumber(id, name, inputClass)
                );
                break;
            case TypeInput.PASSWORD:
                add(
                        new InputPassword(id, name, inputClass)
                );
                break;
            case TypeInput.RADIO:
                add(
                        new RadioItem(id, name, inputClass)
                );
                break;
            case TypeInput.RANGE:
                add(
                        new InputRange(id, name, inputClass)
                );
                break;
            case TypeInput.SEARCH:
                add(
                        new InputSearch(id, name, inputClass)
                );
                break;
            case TypeInput.TEXT:
                add(
                        new InputText(id, name, inputClass)
                );

                break;
            case TypeInput.TIME:
                add(
                        new InputTime(id, name, inputClass)
                );
                break;

            default:
                throw new AssertionError();
        }

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" public GridCol(String label, String id, String name, TypeInput typeInput, Boolean required, Boolean readonly) ">
    public GridCol(String label, String id, String name, TypeInput typeInput, Boolean required, Boolean readonly) {

        super("div");
        add(
                new Label(label, labelClass, id)
        );

        switch (typeInput) {
            case TypeInput.COLOR:
                add(
                        new InputColor(id, name, inputClass).required(required).readonly(readonly)
                );
                break;
            case TypeInput.DATE:
                add(
                        new InputDate(id, name, inputClass).required(required).readonly(readonly)
                );
                break;
            case TypeInput.EMAIL:
                add(
                        new InputEmail(id, name, inputClass).required(required).readonly(readonly)
                );
                break;
            case TypeInput.FILE:
                add(
                        new InputFile(id, name, inputClass).required(required).readonly(readonly)
                );
                break;
            case TypeInput.HIDDEN:
                add(
                        new InputHidden(id, name, inputClass).required(required).readonly(readonly)
                );
                break;
            case TypeInput.NUMBER:
                add(
                        new InputNumber(id, name, inputClass).required(required).readonly(readonly)
                );
                break;
            case TypeInput.PASSWORD:
                add(
                        new InputPassword(id, name, inputClass).required(required).readonly(readonly)
                );
                break;
            case TypeInput.RADIO:
                add(
                        new RadioItem(id, name, inputClass).required(required).disabled(readonly)
                );
                break;
            case TypeInput.RANGE:
                add(
                        new InputRange(id, name, inputClass).required(required).readonly(readonly)
                );
                break;
            case TypeInput.SEARCH:
                add(
                        new InputSearch(id, name, inputClass).required(required).readonly(readonly)
                );
                break;
            case TypeInput.TEXT:
                add(
                        new InputText(id, name, inputClass).required(required).readonly(readonly)
                );

                break;
            case TypeInput.TIME:
                add(
                        new InputTime(id, name, inputClass).required(required).readonly(readonly)
                );
                break;

            default:
                throw new AssertionError();
        }

    }
// </editor-fold>

    public GridCol addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public GridCol add(WebComponent webComponent) {
        if (webComponent != null) {
            withChild(webComponent);
        }

        return this;
    }

    public Tag build() {
        return this;
    }

    public String getLabelClass() {
        return labelClass;
    }

    public void setLabelClass(String labelClass) {
        this.labelClass = labelClass;
    }

    public String getInputClass() {
        return inputClass;
    }

    public void setInputClass(String inputClass) {
        this.inputClass = inputClass;
    }

}
