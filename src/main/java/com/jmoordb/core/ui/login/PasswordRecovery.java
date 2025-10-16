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

public class PasswordRecovery implements WebComponent {

    private final String contextPath;
    private final WebComponent messageAlert; 

    /**
     * @param contextPath Path base de la aplicación.
     * @param messageAlert Alerta para mostrar si el proceso fue exitoso o falló (puede ser null).
     */
    public PasswordRecovery(String contextPath, WebComponent messageAlert) {
        this.contextPath = contextPath;
        this.messageAlert = messageAlert;
    }

    @Override
    public String render() {
        // 1. Contenido del Formulario
        Tag formContent = new Tag("form")
            .withAttribute("action", contextPath + "/forgot-password")
            .withAttribute("method", "POST");
            
        formContent.withChild(new Tag("p").withText("Ingresá tu nombre de usuario o email para recibir un enlace de recuperación."));
            
        // Campo de Identificación (Username/Email)
        formContent.withChild(new Tag("div").withClass("mb-3")
            .withChild(new Tag("label").withAttribute("for", "identity").withClass("form-label").withText("Usuario o Email:"))
            .withChild(new Tag("input").withAttribute("type", "text").withClass("form-control").withAttribute("id", "identity").withAttribute("name", "identity").withAttribute("required", "true"))
        );
        
        // Botón de Submit
        Tag buttonsContainer = new Tag("div").withClass("d-grid gap-2");
        buttonsContainer.withChild(new Tag("button").withAttribute("type", "submit").withClass("btn btn-warning").withText("Enviar Enlace"));
        formContent.withChild(buttonsContainer);

        // Enlace para volver al Login
        formContent.withChild(new Tag("p").withClass("mt-3 text-center")
            .withChild(new Tag("a").withAttribute("href", contextPath + "/login").withText("Volver al Login")));


        // 2. Card Body
        Tag cardBody = new Tag("div").withClass("card-body"); 
        
        if (messageAlert != null) {
            cardBody.withChild(messageAlert);
        }
        cardBody.withChild(formContent);
        
        // 3. Estructura de Tarjeta
        Tag recoveryCard = new Tag("div").withClass("card shadow-lg")
            .withChild(new Tag("div").withClass("card-header bg-warning text-dark text-center")
                .withChild(new Tag("h3").withText("Recuperar Contraseña")))
            .withChild(cardBody);


        // 4. Ensamblaje de la página completa (Reutilizando la plantilla del Login)
        return getPageTemplate(recoveryCard.render()).render();
    }

    // Método auxiliar para generar la estructura HTML base (lo ideal es compartirlo en una clase BasePage)
    private Tag getPageTemplate(String content) {
        Tag body = new Tag("body").withText(content)
            .withChild(new Tag("script").withAttribute("src", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"));

        Tag html = new Tag("html")
            .withChild(new Tag("head")
                .withChild(new Tag("meta").withAttribute("charset", "UTF-8"))
                .withChild(new Tag("meta").withAttribute("name", "viewport").withAttribute("content", "width=device-width, initial-scale=1"))
                .withChild(new Tag("title").withText("Recuperar Contraseña"))
                .withChild(new Tag("link").withAttribute("rel", "stylesheet").withAttribute("href", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"))
                .withChild(new Tag("style").withText("body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background-color: #f8f9fa; } .card { width: 100%; max-width: 400px; } body.dark-mode { background-color: #212529; }"))
            )
            .withChild(body);
        return html;
    }
    
      public WebComponent build(){
      return this;
   }
}