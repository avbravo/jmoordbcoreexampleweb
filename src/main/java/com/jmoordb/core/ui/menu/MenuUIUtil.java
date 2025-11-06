/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.jmoordb.core.ui.menu;

/**
 *
 * @author avbravo
 */
public abstract class MenuUIUtil {
 public static  Boolean isActive(String source, Class dest){
      return  source.equals(dest.getSimpleName());
    }
}
