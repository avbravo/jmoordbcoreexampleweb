/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.properties;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author avbravo
 */
@Named
@ApplicationScoped
public class JettraResourcesFiles implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // Paths de los ResourceBundle
    private final String pathMessagesProperties = "com.properties.messages";
    private final String pathConfigurationProperties = "com.properties.configuration";
    private final String pathCoreProperties = "com.jettraui.properties.core";
    
    // ResourceBundles cargados
    private ResourceBundle mrb; 
    private ResourceBundle crb; 
    private ResourceBundle corerb; 

    // Opcional: Para mantener el Locale actual si se usa @SessionScoped
    private Locale currentLocale; 

    public JettraResourcesFiles() {
        // En Servlet, la inicialización debe hacerse bajo demanda o en un filtro/listener 
        // si se requiere el Locale de la Request
    }

    // Nota: El @PostConstruct aquí solo puede cargar el Locale por defecto del servidor 
    // ya que no hay acceso a la Request/Session en ese momento.
    // La carga del Locale basado en el usuario DEBE hacerse usando `loadLocale(HttpServletRequest request)`.
    @PostConstruct
    public void init() {
        // Por defecto, carga el Locale del sistema o uno predeterminado (e.g., Español)
        currentLocale = Locale.getDefault();
        loadBundles(currentLocale);
    }
    
    /**
     * Carga los ResourceBundles basados en el Locale obtenido de la solicitud.
     * DEBE llamarse al inicio de cada solicitud (e.g., en un Servlet o Filter).
     * @param request La solicitud HTTP actual.
     */
    public void loadLocale(HttpServletRequest request) {
        // En Servlet, obtenemos el Locale preferido del cliente de la Request
        Locale requestLocale = request.getLocale(); 
        
        // Solo recarga si el Locale ha cambiado (importante si la clase es SessionScoped)
        if (this.currentLocale == null || !this.currentLocale.equals(requestLocale)) {
            this.currentLocale = requestLocale;
            loadBundles(this.currentLocale);
        }
    }
    
    private void loadBundles(Locale locale) {
        // Asegúrate de que los ResourceBundles están disponibles en el classpath
        mrb = ResourceBundle.getBundle(pathMessagesProperties, locale);
        crb = ResourceBundle.getBundle(pathConfigurationProperties, locale);
        corerb = ResourceBundle.getBundle(pathCoreProperties, locale);
    }

    // --- Métodos de Acceso (Getters) ---
    
    public ResourceBundle getCrb() {
        return crb;
    }

    public ResourceBundle getCorerb() {
        return corerb;
    }

    public ResourceBundle getMrb() {
        return mrb;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    /**
     * Devuelve el mensaje del ResourceBundle de mensajes (mrb).
     */
    public String fromMessage(String key) {
        return mrb.getString(key);
    }

    /**
     * Devuelve el valor del ResourceBundle de configuración (crb).
     */
    public String fromConfiguration(String key) {
        return crb.getString(key);
    }

    /**
     * Devuelve el valor del ResourceBundle core (corerb).
     */
    public String fromCore(String key) {
        return corerb.getString(key);
    }
}