/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.properties;

/**
 *
 * @author avbravo
 */

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.inject.Inject; // Para inyectar la clase CDI
import java.io.IOException;

@WebFilter("/*") // Aplica el filtro a todas las URLs
public class I18nLocaleFilter implements Filter {

    @Inject
    private JettraResourcesFiles resourcesFiles; // Inyecta la clase SessionScoped/ApplicationScoped

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            
            // 1. Carga el Locale y los ResourceBundles en la instancia inyectada
            resourcesFiles.loadLocale(httpRequest);
        }

        // 2. Continúa con la cadena de filtros/servlets
        chain.doFilter(request, response);
    }

    // Métodos init y destroy omitidos por simplicidad
}