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

    /**
     * Constructor para el componente de Login Avanzado.
     * @param contextPath Path base de la aplicación.
     * @param errorAlert Componente de alerta (puede ser null).
     * @param userRoles Mapa de roles (value HTML -> texto a mostrar).
     */
    public LoginAdvanced(String contextPath, WebComponent errorAlert, Map<String, String> userRoles) {
        this.contextPath = contextPath;
        this.errorAlert = errorAlert;
        this.userRoles = userRoles;
    }

    @Override
    public String render() {
        // 1. Contenido del Formulario
        Tag formContent = new Tag("form")
            .withAttribute("action", contextPath + "/login-advanced") // Usamos un servlet distinto
            .withAttribute("method", "POST");
            
        // 1.1. Campo SELECT/ROL
        Tag selectTag = new Tag("select")
            .withAttribute("name", "userRole")
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
            .withChild(new Tag("label").withAttribute("for", "userRole").withClass("form-label").withText("Rol:"))
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
                .withChild(new Tag("h2").withText("Advanced System Access")))
            .withChild(cardBody);


        // 3. Ensamblaje de la página completa
        return getPageTemplate(loginCard.render()).render();
    }
    
    // Método auxiliar para no duplicar el código del HTML base
    private Tag getPageTemplate(String content) {
        Tag body = new Tag("body").withText(content)
            .withChild(new Tag("script").withAttribute("src", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"));

        Tag html = new Tag("html")
            .withChild(new Tag("head")
                .withChild(new Tag("meta").withAttribute("charset", "UTF-8"))
                .withChild(new Tag("meta").withAttribute("name", "viewport").withAttribute("content", "width=device-width, initial-scale=1"))
                .withChild(new Tag("title").withText("Login Advanced"))
                .withChild(new Tag("link").withAttribute("rel", "stylesheet").withAttribute("href", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"))
                .withChild(new Tag("style").withText("body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background-color: #f8f9fa; } .card { width: 100%; max-width: 400px; } body.dark-mode { background-color: #212529; }"))
            )
            .withChild(body);
        return html;
    }
}