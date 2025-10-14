/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.login;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;

public class Login implements WebComponent {

    private final String contextPath;
    private final WebComponent errorAlert;

    public Login(String contextPath, WebComponent errorAlert) {
        this.contextPath = contextPath;
        this.errorAlert = errorAlert;
    }

    @Override
    public String render() {
        // 1. Contenido del Formulario
        Tag formContent = new Tag("form")
            .withAttribute("action", contextPath + "/login")
            .withAttribute("method", "POST");
            
        // 1.1. Campo Usuario
        formContent.withChild(new Tag("div").withClass("mb-3")
            .withChild(new Tag("label").withAttribute("for", "username").withClass("form-label").withText("Username:"))
            .withChild(new Tag("input").withAttribute("type", "text").withClass("form-control").withAttribute("id", "username").withAttribute("name", "username").withAttribute("required", "true"))
        );
        
        // 1.2. Campo Contraseña
        formContent.withChild(new Tag("div").withClass("mb-3")
            .withChild(new Tag("label").withAttribute("for", "password").withClass("form-label").withText("Password:"))
            .withChild(new Tag("input").withAttribute("type", "password").withClass("form-control").withAttribute("id", "password").withAttribute("name", "password").withAttribute("required", "true"))
        );
        
        // 1.3. Botón de Submit
        formContent.withChild(new Tag("div").withClass("d-grid gap-2")
            .withChild(new Tag("button").withAttribute("type", "submit").withClass("btn btn-primary").withText("Log In"))
        );

        // 2. Card Body (Contenido Central del Card)
        Tag cardBody = new Tag("div").withClass("card-body"); // <-- Creamos la variable TAG aquí
        
        // Inyectar alerta de error si existe (safe call en la variable cardBody)
        if (errorAlert != null) {
            cardBody.withChild(errorAlert);
        }
        
        // Inyectar formulario
        cardBody.withChild(formContent);
        
        // 3. Estructura de Tarjeta de Bootstrap
        Tag loginCard = new Tag("div").withClass("card shadow-lg")
            .withChild(new Tag("div").withClass("card-header bg-dark text-white text-center")
                .withChild(new Tag("h2").withText("System Access")))
            .withChild(cardBody); // <-- Añadimos el cardBody ensamblado

        // 4. Ensamblaje de la página completa
        Tag body = new Tag("body").withChild(loginCard)
            .withChild(new Tag("script").withAttribute("src", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"));

        Tag html = new Tag("html")
            .withChild(new Tag("head")
                .withChild(new Tag("meta").withAttribute("charset", "UTF-8"))
                .withChild(new Tag("meta").withAttribute("name", "viewport").withAttribute("content", "width=device-width, initial-scale=1"))
                .withChild(new Tag("title").withText("Login - Java Pure Framework"))
                .withChild(new Tag("link").withAttribute("rel", "stylesheet").withAttribute("href", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"))
                .withChild(new Tag("style").withText("body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background-color: #f8f9fa; } .card { width: 100%; max-width: 400px; } body.dark-mode { background-color: #212529; }"))
            )
            .withChild(body);

        return html.render();
    }
}