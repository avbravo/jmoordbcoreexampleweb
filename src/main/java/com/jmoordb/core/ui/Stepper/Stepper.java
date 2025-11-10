
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.Stepper;

import com.jmoordb.core.ui.Li;
import com.jmoordb.core.ui.P;
import com.jmoordb.core.ui.Span;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.headings.H3;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class Stepper extends Tag{
    
    /**
     * Color red, blue
     * @param tagName
     * @param color 
     */
    public Stepper(List<StepperData> stepperDatas) {
        
        super("o");
        withClass("items-center w-full space-y-4 sm:flex sm:space-x-8 sm:space-y-0 rtl:space-x-reverse");
        if(stepperDatas == null || stepperDatas.isEmpty()){
            
        }else{
            String liClass="";
            String spanClass="";
            for(StepperData s:stepperDatas){
                if(s.active()){
                  
                    liClass="fàlex items-center text-blue-600 dark:text-blue-500 space-x-2.5 rtl:space-x-reverse";
                    spanClass="flex items-center justify-center w-8 h-8 border border-blue-600 rounded-full shrink-0 dark:border-blue-500";
                }else{
                  
                    liClass="flex items-center text-gray-500 dark:text-gray-400 space-x-2.5 rtl:space-x-reverse";
                    spanClass="flex items-center justify-center w-8 h-8 border border-gray-500 rounded-full shrink-0 dark:border-gray-400";
                }
                
                  add(new Li(liClass)
                                    .add(new Span(spanClass)
                                            .text(s.leftValue()))
                                    .add(new Span()
                                            .add(new H3().text(s.title()).style("font-medium leading-tight"))
                                            .add(new P().addClass("text-sm").text(s.subTitle()))
                                    )
                  );
            }
        }
           
    }
    public Stepper(String styleClass) {
        
        super("o");
        
          withClass(styleClass);
           
    }
    
    
  
        
    public Stepper addClass(String styleClass) {
      withClass(styleClass);
        return this;
    }
    
        public Stepper add(WebComponent webComponent) {
     if (webComponent!= null) {
         withChild(webComponent);
        }

        return this;
    }
   public Tag build(){
      return this;
   }
    
}
