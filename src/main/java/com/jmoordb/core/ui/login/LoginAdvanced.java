/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.login;

/**
 *
 * @author avbravo
 */


import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import java.util.Map;

public class LoginAdvanced implements WebComponent {

    private final String contextPath;
    private final WebComponent errorAlert; 
    private final Map<String, String> userRoles; // Clave: value (admin), Valor: texto a mostrar (Administrador)
   private final String title;
   private final String metaTitle;
    /**
     * Constructor para el componente de Login Avanzado.
     * @param contextPath Path base de la aplicación.
     * @param errorAlert Componente de alerta (puede ser null).
     * @param userRoles Mapa de roles (value HTML -> texto a mostrar).
     */
    public LoginAdvanced(String contextPath, WebComponent errorAlert, Map<String, String> userRoles,   String title,String metaTitle) {
        this.contextPath = contextPath;
        this.errorAlert = errorAlert;
        this.userRoles = userRoles;
        this.title = title;
        this.metaTitle = metaTitle;
    }

    @Override
    public String render() {
        // 1. Contenido del Formulario
        Tag formContent = new Tag("form")
            .withAttribute("action", contextPath + "/login") // Usamos un servlet distinto
            .withAttribute("method", "POST");
            
        // 1.1. Campo SELECT/ROL
        Tag selectTag = new Tag("select")
            .withAttribute("name", "userRol")
            .withClass("form-select")
            .withAttribute("required", "true");
            
        // Opción por defecto
        selectTag.withChild(new Tag("option")
            .withAttribute("value", "")
            .withAttribute("disabled", "true")
            .withAttribute("selected", "true")
            .withText("Seleccionar Rol"));
            
        // Generar opciones dinámicamente
        userRoles.forEach((value, text) -> 
            selectTag.withChild(new Tag("option")
                .withAttribute("value", value)
                .withText(text))
        );
        
        formContent.withChild(new Tag("div").withClass("mb-3")
            .withChild(new Tag("label").withAttribute("for", "userRol").withClass("form-label").withText("Rol:"))
            .withChild(selectTag)
        );
        
        // 1.2. Campo Username
        formContent.withChild(new Tag("div").withClass("mb-3")
            .withChild(new Tag("label").withAttribute("for", "username").withClass("form-label").withText("Username:"))
            .withChild(new Tag("input").withAttribute("type", "text").withClass("form-control").withAttribute("id", "username").withAttribute("name", "username").withAttribute("required", "true"))
        );
        
        // 1.3. Campo Password
        formContent.withChild(new Tag("div").withClass("mb-3")
            .withChild(new Tag("label").withAttribute("for", "password").withClass("form-label").withText("Password:"))
            .withChild(new Tag("input").withAttribute("type", "password").withClass("form-control").withAttribute("id", "password").withAttribute("name", "password").withAttribute("required", "true"))
        );
        
        // 1.4. Botones (Login + Olvidó Contraseña)
        Tag buttonsContainer = new Tag("div").withClass("d-grid gap-2 mb-3");
        
        // Botón de Login
        buttonsContainer.withChild(new Tag("button").withAttribute("type", "submit").withClass("btn btn-primary").withText("Log In"));
        
        // Enlace para Olvidó Contraseña
        buttonsContainer.withChild(new Tag("a").withAttribute("href", contextPath + "/forgot-password")
            .withClass("btn btn-link text-decoration-none text-center")
            .withText("¿Olvidaste tu Contraseña?"));

        formContent.withChild(buttonsContainer);


        // 2. Card Body, Header y Ensamblaje (Similar a Login)
        Tag cardBody = new Tag("div").withClass("card-body"); 
        
        if (errorAlert != null) {
            cardBody.withChild(errorAlert);
        }
        cardBody.withChild(formContent);
        
        Tag loginCard = new Tag("div").withClass("card shadow-lg")
            .withChild(new Tag("div").withClass("card-header bg-dark text-white text-center")
                .withChild(new Tag("h2").withText(title)))
            .withChild(cardBody);


        // 3. Ensamblaje de la página completa
        return getPageTemplate(loginCard.render()).render();
    }
    
    // Método auxiliar para no duplicar el código del HTML base
    private Tag getPageTemplate(String content) {
        
          // Estilos customizados para Dark Mode y layout
        String customStyles = ""
                // Definición de variables CSS para el tema oscuro
                + ":root { --bg-main: #121212; --bg-content: #1e1e1e; --text-color: #F0F4F8; }" 
                // Layout y fondo oscuro del body
                + "body { display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }"
                + "body.dark-mode { background-color: var(--bg-main); color: var(--text-color); }" 
                // Estilos de la tarjeta de login
                + ".container-login { width: 100%; max-width: 400px; }"
                + ".card { border: 1px solid rgba(255, 255, 255, 0.1); }"
                + ".card-body.bg-content { background-color: var(--bg-content) !important; color: var(--text-color); }"
                
                // ⭐ CAMBIO CLAVE: Cambiar el color del label a negro (#000000)
                + "body.dark-mode .form-label { color: #000000 !important; }" 
                
                + "a.btn-link { color: #adb5bd !important; }" 
                
                // Input, Select y focus (fondo oscuro, texto claro)
                + ".form-control, .form-select { background-color: #343a40; color: var(--text-color); border-color: #495057; }"
                + ".form-select option { background-color: #343a40; color: var(--text-color); }"
                
                + ".form-control:focus, .form-select:focus { background-color: #343a40; color: var(--text-color); border-color: #6c757d; box-shadow: 0 0 0 0.25rem rgba(108, 117, 125, 0.25); }";
                
        // Body recibe la clase 'dark-mode'
        Tag body = new Tag("body").withClass("dark-mode")
            .withChild(new Tag("div").withText(content)) 
            .withChild(new Tag("script").withAttribute("src", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"));

        Tag html = new Tag("html")
            .withChild(new Tag("head")
                .withChild(new Tag("meta").withAttribute("charset", "UTF-8"))
                .withChild(new Tag("meta").withAttribute("name", "viewport").withAttribute("content", "width=device-width, initial-scale=1"))
                .withChild(new Tag("title").withText(metaTitle))
                .withChild(new Tag("link").withAttribute("rel", "stylesheet").withAttribute("href", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"))
                .withChild(new Tag("style").withText(customStyles)) // Estilos Inyectados
            )
            .withChild(body);

        return html;
    }
}