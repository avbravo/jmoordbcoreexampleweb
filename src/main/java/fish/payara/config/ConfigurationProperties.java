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
    @ConfigProperty(name = "dashboard.footerText")
    private String dashboardFooterText;

    
    
    
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

    // Directory
    
    @Inject
    @ConfigProperty(name = "java.system.getproperty")
    private String javaSystemGetProperty; 
    
    
    @Inject
    @ConfigProperty(name = "image.Directory")
    private String imageDirectory; 
    
    
// Configuracion del endpoint del algoritmo IA
    @Inject
    @ConfigProperty(name = "ia.url.procesarimagen")
    private String iaUrlProcesarimagen;

    @Inject
    @ConfigProperty(name = "ia.url.image")
    private String iaUrlImage;

    @Inject
    @ConfigProperty(name = "ia.url.prediction")
    private String iaUrlPrediction;

    @Inject
    @ConfigProperty(name = "ia.url.listarImagenes")
    private String iaUrlListarImagenes;

// </editor-fold>
    /**
     * Creates a new instance of ConfigProperties
     */
    public ConfigurationProperties() {
    }

    // <editor-fold defaultstate="collapsed" desc="get">

    public String getJavaSystemGetProperty() {
        return javaSystemGetProperty;
    }

    public void setJavaSystemGetProperty(String javaSystemGetProperty) {
        this.javaSystemGetProperty = javaSystemGetProperty;
    }

    public String getDashboardFooterText() {
        return dashboardFooterText;
    }

    public void setDashboardFooterText(String dashboardFooterText) {
        this.dashboardFooterText = dashboardFooterText;
    }

    
    
    
    public String getImageDirectory() {
        return imageDirectory;
    }

    public void setImageDirectory(String imageDirectory) {
        this.imageDirectory = imageDirectory;
    }
    
    
    
    
    
    public String getIaUrlProcesarimagen() {
        return iaUrlProcesarimagen;
    }

    public void setIaUrlProcesarimagen(String iaUrlProcesarimagen) {
        this.iaUrlProcesarimagen = iaUrlProcesarimagen;
    }

    public String getIaUrlImage() {
        return iaUrlImage;
    }

    public void setIaUrlImage(String iaUrlImage) {
        this.iaUrlImage = iaUrlImage;
    }

    public String getIaUrlPrediction() {
        return iaUrlPrediction;
    }

    public void setIaUrlPrediction(String iaUrlPrediction) {
        this.iaUrlPrediction = iaUrlPrediction;
    }

   


    public String getIaUrlListarImagenes() {
        return iaUrlListarImagenes;
    }

    public void setIaUrlListarImagenes(String iaUrlListarImagenes) {
        this.iaUrlListarImagenes = iaUrlListarImagenes;
    }

    public String getApplicativeMetaTitle() {
        return applicativeMetaTitle;
    }

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
