/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.script;

/**
 *
 * @author avbravo
 */
public class ScriptUtil {
        public static String closeModal(String nameOfModal,String functionName ){
            String result ="";
            try {
                 result = 
                  "function "+functionName +"() {"
                + "  document.getElementById('"+nameOfModal+"').classList.remove('hidden');"
                + "}"
                + "document.addEventListener('DOMContentLoaded', function() {"
                + "  const modal = document.getElementById('"+nameOfModal+"');"
                + "  const closeButton = document.getElementById('"+nameOfModal+"-close');"
                + "  if (closeButton) {"
                + "    closeButton.addEventListener('click', function() {"
                + "      modal.classList.add('hidden');"
                + "    });"
                + "  }"
                + "});";
            } catch (Exception e) {
                System.out.println("closeModal() "+e.getLocalizedMessage());
            }
            return result;
        }
}
