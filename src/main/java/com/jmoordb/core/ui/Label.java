/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui;

import com.jmoordb.core.ui.css.FormRowCss;
import com.jmoordb.core.ui.css.GridColCss;
import com.jmoordb.core.ui.radio.RadioItemLink;
import com.jmoordb.core.ui.css.RadioBorderCss;
import com.jmoordb.core.ui.css.RadioCss;
import com.jmoordb.core.ui.css.type.CssType;
import static com.jmoordb.core.ui.css.type.CssType.FormRow;
import static com.jmoordb.core.ui.css.type.CssType.GridCol;
import static com.jmoordb.core.ui.css.type.CssType.Radio;
import static com.jmoordb.core.ui.css.type.CssType.RadioBorder;
import static com.jmoordb.core.ui.css.type.CssType.RadioDropdown;
import static com.jmoordb.core.ui.css.type.CssType.RadioHorizontalListGroup;
import static com.jmoordb.core.ui.css.type.CssType.RadioInline;
import static com.jmoordb.core.ui.css.type.CssType.RadioListGroup;
import static com.jmoordb.core.ui.css.type.CssType.RadioTwoColumns;
import com.jmoordb.core.ui.css.RadioDropdownCss;
import com.jmoordb.core.ui.css.RadioHorizontalListGroupCss;
import com.jmoordb.core.ui.css.RadioInlineCss;
import com.jmoordb.core.ui.css.RadioListGroupCss;
import com.jmoordb.core.ui.css.RadioTwoColumnsCss;

/**
 *
 * @author avbravo
 */
public class Label extends Tag {
String text;
String subText;
String id;
String forField;
    /**
     * Color red, blue
     *
     * @param tagName
     * @param color
     */
    public Label() {

        super("label");

    }

    public Label(String text) {

        super("label");
        withText(text);
    }

    public Label(String text, String styleClass) {

        super("label");
        withText(text);
        this.text=text;        
        withClass(styleClass);

    }
    
     public Label(String text, CssType cssType) {

        super("label");
        this.text = text;
        withText(text);
         withClass(cssType);
        
                
       
    }
    

    public Label(String text, String styleClass, String forField) {

        super("label");
        this.text = text;
        withText(text);
        if (styleClass == null || styleClass.equals("")) {

        } else {
            withClass(styleClass);
        }
        if (forField == null || forField.equals("")) {

        } else {
            withAttribute("for", forField);
            this.forField =forField;
        }

    }
    public Label(String text, CssType cssType, String forField) {

        super("label");
        this.text = text;
        withText(text);
        withClass(cssType);
         
                
        if (forField == null || forField.equals("")) {

        } else {
            withAttribute("for", forField);
            this.forField =forField;
        }

    }
    
    public Label withClass(CssType cssType){
        switch (cssType) {
              case FormRow:
               withClass(FormRowCss.Label.css);
               break;
            case GridCol:
               withClass(GridColCss.Label.css);
               break;
            case Radio:
                withClass(RadioCss.Label.css);
                break;
            case RadioBorder:
                withClass(RadioBorderCss.Label.css);
                break;
            case RadioDropdown:
                withClass(RadioDropdownCss.Label.css);
                break;
            case RadioHorizontalListGroup:
                 withClass(RadioHorizontalListGroupCss.Label.css);
                break;
            case RadioInline:
                withClass(RadioInlineCss.Label.css);
                break;
            case RadioListGroup:
                withClass(RadioListGroupCss.Label.css);
                break;
            case RadioTwoColumns:
               withClass(RadioTwoColumnsCss.Label.css);
                break;

            default:

        }
        return this;
    }
     public Label setSubText(String subText) {
                this.subText = subText;
                return this;

    }
     

    public Label(String text, String styleClass, String forField, RadioItemLink radioItemLink) {

        super("label");
        this.text = text;
        withText(text);
        if (styleClass == null || styleClass.equals("")) {

        } else {
            withClass(styleClass);
        }
        if (forField == null || forField.equals("")) {

        } else {
            withAttribute("for", forField);
            this.forField = forField;
        }

        add(radioItemLink);
    }
    public Label(String text, CssType cssType, String forField, RadioItemLink radioItemLink) {

        super("label");
                this.text = text;
        withText(text);
        withClass(cssType);
        
        if (forField == null || forField.equals("")) {

        } else {
            withAttribute("for", forField);
             this.forField =forField;
        }

        add(radioItemLink);
    }
    
    
    
    
    
    
    
    
    
    

    public Label text(String text) {
                this.text = text;
        withText(text);
        return this;
    }

    public Label addClass(String styleClass) {
        withClass(styleClass);
        return this;
    }

    public Label forField(String field) {
        withAttribute("for", field);
         this.forField =field;
        return this;
    }

    public Label id(String id) {
        withAttribute("id", id);
        this.id=id;
        return this;
    }
    
    public String getText(){
        return this.text;
    }

    public String getId() {
        return id;
    }

    public String getSubText() {
        return subText;
    }

    public String getForField() {
        return forField;
    }
    
    

    public Tag build() {
        return this;
    }

}
