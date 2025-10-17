/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.login;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;

public class LoginSimple1 implements WebComponent {

    private final String contextPath;
    private final WebComponent errorAlert;
    private final String title;
    private final String metaTitle;

    public LoginSimple1(String contextPath, WebComponent errorAlert, String title, String metaTitle) {
        this.contextPath = contextPath;
        this.errorAlert = errorAlert;
        this.title = (title == null || title == "") ? "System Access" : title;
        this.metaTitle = metaTitle;

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

        // Botón/Enlace para Olvidó Contraseña (Envía al nuevo servlet /forgot-password)
        formContent.withChild(new Tag("a").withAttribute("href", contextPath + "/forgot-password")
                .withClass("btn btn-link text-decoration-none text-center")
                .withText("¿Olvidaste tu Contraseña?"));

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
                        .withChild(new Tag("h2").withText(title)))
                .withChild(cardBody); // <-- Añadimos el cardBody ensamblado

         // 4. Ensamblaje de la página completa
        // ⭐ AÑADIR CLASE 'dark-mode' AL BODY
        Tag body = new Tag("body").withClass("dark-mode") 
                .withChild(new Tag("div").withClass("container-login") // Contenedor para centrar
                    .withChild(loginCard))
                .withChild(new Tag("script").withAttribute("src", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"));

        // 5. Estilos y Ensamblaje HTML
        String customStyles = ""
                + ":root { --bg-main: #121212; --bg-content: #1e1e1e; --text-color: #F0F4F8; }" // Definir variables del tema oscuro
                + "body.dark-mode { background-color: var(--bg-main); color: var(--text-color); }"
                + "body { display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }"
                + ".container-login { width: 100%; max-width: 400px; }"
                + ".card { border: 1px solid rgba(255, 255, 255, 0.1); }"
                + ".card-body.bg-content { background-color: var(--bg-content) !important; color: var(--text-color); }"
                
                // ⭐ CAMBIO CLAVE: Colocar el color de los labels en negro (#000000)
                + "body.dark-mode .form-label { color: #000000 !important; }" 
                
                + "a.btn-link { color: #adb5bd !important; }" // Ajustar enlace para tema oscuro
                + ".form-control { background-color: #343a40; color: var(--text-color); border-color: #495057; }"
                + ".form-control:focus { background-color: #343a40; color: var(--text-color); border-color: #6c757d; box-shadow: 0 0 0 0.25rem rgba(108, 117, 125, 0.25); }";

        
        Tag html = new Tag("html")
                .withChild(new Tag("head")
                        .withChild(new Tag("meta").withAttribute("charset", "UTF-8"))
                        .withChild(new Tag("meta").withAttribute("name", "viewport").withAttribute("content", "width=device-width, initial-scale=1"))
                        .withChild(new Tag("title").withText(metaTitle))
                        .withChild(new Tag("link").withAttribute("rel", "stylesheet").withAttribute("href", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"))
                        .withChild(new Tag("style").withText("body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background-color: #f8f9fa; } .card { width: 100%; max-width: 400px; } body.dark-mode { background-color: #212529; }"))
                         .withChild(new Tag("style").withText(customStyles)) // Estilos Inyectados 
                )
                .withChild(body);

        return html.render();
    }
    
      public WebComponent build(){
      return this;
   }
}
