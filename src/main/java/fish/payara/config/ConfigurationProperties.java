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
    //Login
    @Inject
    @ConfigProperty(name = "login.style")
    private String loginStyle;
    @Inject
    @ConfigProperty(name = "login.title")
    private String loginTitle;
    
     @Inject
    @ConfigProperty(name = "login.logo.src")
     private String loginLogoSrc;
 @Inject
    @ConfigProperty(name = "login.logo.class")
 private String loginLogoClass;
        
        
    @Inject
    @ConfigProperty(name = "session.minutosExpiracion")
    private Integer sessionMinutosExpiracion;

    @Inject
    @ConfigProperty(name = "applicative.title")
    private String applicativeTitle;
    @Inject
    @ConfigProperty(name = "applicative.shortTitle")
    private String applicativeShortTitle;
    @Inject
    @ConfigProperty(name = "applicative.metaTitle")
    private String applicativeMetaTitle;

    /**
     * CSS y JS
     *
     */
    @Inject
    @ConfigProperty(name = "flowbite.min.js")
    private String flowbiteMinJs;
    @Inject
    @ConfigProperty(name = "flowbite.min.css")
 private String flowbiteMinCss;
// Taildwin
            @Inject
    @ConfigProperty(name = "tailwindcss.js")
 private String tailwindcssJs;
// Bootstrap
    @Inject
    @ConfigProperty(name = "bootstrap.min.css")
 private String bootstrapMinCss;
        @Inject
    @ConfigProperty(name = "bootstrap.bundle.min.js")
 private String bootstrapBundleMinJs;

//FontAwesome
                    @Inject
    @ConfigProperty(name = "font-awesom.all.min.css")
 private String fontAwesomAllMinCss;

// Chart
            @Inject
    @ConfigProperty(name = "chart.umd.min.js")
     private String chartUmdMinJs;
    
 

// </editor-fold>
    /**
     * Creates a new instance of ConfigProperties
     */
    public ConfigurationProperties() {
    }

    public String getApplicativeMetaTitle() {
        return applicativeMetaTitle;
    }

    // <editor-fold defaultstate="collapsed" desc="get">

    public String getLoginLogoSrc() {
        return loginLogoSrc;
    }

    public void setLoginLogoSrc(String loginLogoSrc) {
        this.loginLogoSrc = loginLogoSrc;
    }

    public String getLoginLogoClass() {
        return loginLogoClass;
    }

    public void setLoginLogoClass(String loginLogoClass) {
        this.loginLogoClass = loginLogoClass;
    }

    
    
    
    public String getFlowbiteMinJs() {
        return flowbiteMinJs;
    }

    public void setFlowbiteMinJs(String flowbiteMinJs) {
        this.flowbiteMinJs = flowbiteMinJs;
    }

    public String getFlowbiteMinCss() {
        return flowbiteMinCss;
    }

    public void setFlowbiteMinCss(String flowbiteMinCss) {
        this.flowbiteMinCss = flowbiteMinCss;
    }

    public String getTailwindcssJs() {
        return tailwindcssJs;
    }

    public void setTailwindcssJs(String tailwindcssJs) {
        this.tailwindcssJs = tailwindcssJs;
    }

    public String getBootstrapMinCss() {
        return bootstrapMinCss;
    }

    public void setBootstrapMinCss(String bootstrapMinCss) {
        this.bootstrapMinCss = bootstrapMinCss;
    }

    public String getBootstrapBundleMinJs() {
        return bootstrapBundleMinJs;
    }

    public void setBootstrapBundleMinJs(String bootstrapBundleMinJs) {
        this.bootstrapBundleMinJs = bootstrapBundleMinJs;
    }

    public String getFontAwesomAllMinCss() {
        return fontAwesomAllMinCss;
    }

    public void setFontAwesomAllMinCss(String fontAwesomAllMinCss) {
        this.fontAwesomAllMinCss = fontAwesomAllMinCss;
    }

    public String getChartUmdMinJs() {
        return chartUmdMinJs;
    }

    public void setChartUmdMinJs(String chartUmdMinJs) {
        this.chartUmdMinJs = chartUmdMinJs;
    }
    
    
    
    
    
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
