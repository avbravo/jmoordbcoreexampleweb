/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.util.encode;

import com.jmoordb.core.ui.util.document.DocumentUtil;
import org.bson.conversions.Bson;
import org.glassfish.jersey.uri.UriComponent;

/**
 *
 * @author avbravo
 */
public class EncodeUtil {
    
    // <editor-fold defaultstate="collapsed" desc="code">
// </editor-fold>
    public static String encodeJson(String query){
        try {
            
 return  UriComponent.encode(query, UriComponent.Type.QUERY_PARAM_SPACE_ENCODED);
            } catch (Exception e) {
            System.out.println("encodeJson() "+e.getLocalizedMessage());
           
        }
    return query;
     
    }
    
    // <editor-fold defaultstate="collapsed" desc="code">
    
    
      public static String encodeBson(Bson filter){
        try {
        return encodeJson(DocumentUtil.bsonToJson(filter));
       } catch (Exception e) {
            System.out.println("encodeJson() "+e.getLocalizedMessage());
           
        }
    return "";
     
    }
// </editor-fold>
}
