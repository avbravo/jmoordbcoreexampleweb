/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package fish.payara.config;

import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author avbravo
 */
@Named(value = "configProperties")
@ApplicationScoped
public class ConfigurationProperties {

     // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    @ConfigProperty(name = "login.style")
    private String loginStyle;
    @Inject
    @ConfigProperty(name = "login.title")
    private String loginTitle;
    @Inject
    @ConfigProperty(name = "session.minutosExpiracion")
    private Integer sessionMinutosExpiracion;
    
    
    @Inject
    @ConfigProperty(name = "applicative.title")
    private String applicativeTitle;
    @Inject
    @ConfigProperty(name = "applicative.shortTitle")
    private String applicativeShortTitle;
    
    
 

// </editor-fold>
    /**
     * Creates a new instance of ConfigProperties
     */
    public ConfigurationProperties() {
    }
    
    
    
    // <editor-fold defaultstate="collapsed" desc="get">

    public Integer getSessionMinutosExpiracion() {
        return sessionMinutosExpiracion;
    }
    
    
    
    
        public String getLoginStyle() {
        return loginStyle;
    }

    public String getApplicativeTitle() {
        return applicativeTitle;
    }

    public String getApplicativeShortTitle() {
        return applicativeShortTitle;
    }

    public String getLoginTitle() {
        return loginTitle;
    }
    
    
    
    
    
// </editor-fold>


    
}
