/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.login;

import com.jmoordb.core.ui.A;
import com.jmoordb.core.ui.Body;
import com.jmoordb.core.ui.Button;
import com.jmoordb.core.ui.ButtonType;
import com.jmoordb.core.ui.Div;
import com.jmoordb.core.ui.Form;
import com.jmoordb.core.ui.headings.H2;
import com.jmoordb.core.ui.Head;
import com.jmoordb.core.ui.Html;
import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Link;
import com.jmoordb.core.ui.Meta;
import com.jmoordb.core.ui.Script;
import com.jmoordb.core.ui.Style;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.Title;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.input.InputPassword;
import com.jmoordb.core.ui.input.InputText;

public class LoginSimple implements WebComponent {

    private final String contextPath;
    private final WebComponent errorAlert;
    private final String title;
    private final String metaTitle;

    public LoginSimple(String contextPath, WebComponent errorAlert, String title, String metaTitle) {
        this.contextPath = contextPath;
        this.errorAlert = errorAlert;
        this.title = (title == null || title == "") ? "System Access" : title;
        this.metaTitle = metaTitle;

    }

    @Override
    public String render() {
        // 1. Contenido del Formulario
        Form formContent = new Form()
                .action(contextPath + "/login")
                .method("POST");

        // 1.1. Campo Usuario
        formContent.add(
                new Div().withClass("mb-3")
                        .add(new Label().forField("username").addClass("form-label").text("Username:"))
                        .add(new InputText().addClass("form-control").id("username").name("username").required(Boolean.TRUE))
        );

        // 1.2. Campo Contraseña
        formContent.add(new Div().withClass("mb-3")
                .add(new Label().forField("password").addClass("form-label").text("Password:"))
                .add(new InputPassword().addClass("form-control").id("password").name("password").required(Boolean.TRUE))
        );

        // 1.3. Botón de Submit
        formContent.add(new Div().withClass("d-grid gap-2")
                .add(new Button().type(ButtonType.SUBMIT).addClass("btn btn-primary").text("Log In"))
        );

        // Botón/Enlace para Olvidó Contraseña (Envía al nuevo servlet /forgot-password)
        formContent.add(new A().href(contextPath + "/forgot-password")
                .addClass("btn btn-link text-decoration-none text-center")
                .text("¿Olvidaste tu Contraseña?"));

        // 2. Card Body (Contenido Central del Card)
        Div cardBody = new Div().addClass("card-body"); // <-- Creamos la variable TAG aquí

        // Inyectar alerta de error si existe (safe call en la variable cardBody)
        if (errorAlert != null) {
            cardBody.add(errorAlert);
        }

        // Inyectar formulario
        cardBody.add(formContent);

        // 3. Estructura de Tarjeta de Bootstrap
        Div loginCard = new Div().addClass("card shadow-lg")
                .add(new Div().addClass("card-header bg-dark text-white text-center")
                        .add(new H2().text(title)))
                .add(cardBody); // <-- Añadimos el cardBody ensamblado

        // 4. Ensamblaje de la página completa
        // ⭐ AÑADIR CLASE 'dark-mode' AL BODY
        Body body = new Body().addClass("dark-mode")
                .add(new Div().withClass("container-login") // Contenedor para centrar
                        .add(loginCard))
                .add(new Script().src("https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"));

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

        Html html = new Html()
                .add(new Head()
                        .add(new Meta().charset("UTF-8"))
                        .add(new Meta().name("viewport").content("width=device-width, initial-scale=1"))
                        .add(new Title().text(metaTitle))
                        .add(new Link().rel("stylesheet").href("https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"))
                        .add(new Style().text("body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background-color: #f8f9fa; } .card { width: 100%; max-width: 400px; } body.dark-mode { background-color: #212529; }"))
                        .add(new Style().text(customStyles)) // Estilos Inyectados 
                )
                .add(body);

        return html.render();
    }

    public WebComponent build() {
        return this;
    }
    
   
}
