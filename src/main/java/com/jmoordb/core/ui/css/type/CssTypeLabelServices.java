/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.css.type;

import com.jmoordb.core.ui.css.FormRowCss;
import com.jmoordb.core.ui.css.GridColCss;
import com.jmoordb.core.ui.css.InputFileCss;
import com.jmoordb.core.ui.css.RadioAdvancedLayoutCss;
import com.jmoordb.core.ui.css.RadioBorderCss;
import com.jmoordb.core.ui.css.RadioCss;
import com.jmoordb.core.ui.css.RadioDropdownCss;
import com.jmoordb.core.ui.css.RadioHorizontalListGroupCss;
import com.jmoordb.core.ui.css.RadioInlineCss;
import com.jmoordb.core.ui.css.RadioListGroupCss;
import com.jmoordb.core.ui.css.RadioTwoColumnsCss;
import static com.jmoordb.core.ui.css.type.CssType.FormRow;
import static com.jmoordb.core.ui.css.type.CssType.GridCol;
import static com.jmoordb.core.ui.css.type.CssType.InputFile;
import static com.jmoordb.core.ui.css.type.CssType.Radio;
import static com.jmoordb.core.ui.css.type.CssType.RadioAdvancedLayout;
import static com.jmoordb.core.ui.css.type.CssType.RadioBorder;
import static com.jmoordb.core.ui.css.type.CssType.RadioDropdown;
import static com.jmoordb.core.ui.css.type.CssType.RadioHorizontalListGroup;
import static com.jmoordb.core.ui.css.type.CssType.RadioInline;
import static com.jmoordb.core.ui.css.type.CssType.RadioListGroup;
import static com.jmoordb.core.ui.css.type.CssType.RadioTwoColumns;

/**
 *
 * @author avbravo
 */
public class CssTypeLabelServices {

    public String toCss(CssType cssType) {
        String result = "";
        switch (cssType) {
            case FormRow:
                result = (FormRowCss.Label.css);
                break;
            case GridCol:
                result = (GridColCss.Label.css);
                break;
            case InputFile:
                //  result = (InputFileCss.Label.css);
                break;
            case RadioAdvancedLayout:
                result = (RadioAdvancedLayoutCss.Label.css);
                break;
            case RadioBorder:
                result = (RadioBorderCss.Label.css);
                break;
            case Radio:
                result = (RadioCss.Label.css);
                break;

            case RadioDropdown:
                result = (RadioDropdownCss.Label.css);
                break;
            case RadioHorizontalListGroup:
                result = (RadioHorizontalListGroupCss.Label.css);
                break;
            case RadioInline:
                result = (RadioInlineCss.Label.css);
                break;
            case RadioListGroup:
                result = (RadioListGroupCss.Label.css);
                break;
            case RadioTwoColumns:
                result = (RadioTwoColumnsCss.Label.css);
                break;

            default:
                break;

        }
        return result;
    }

    

}
