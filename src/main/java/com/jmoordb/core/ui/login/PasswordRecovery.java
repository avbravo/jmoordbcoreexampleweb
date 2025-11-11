/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.login;

/**
 *
 * @author avbravo
 */
import com.jmoordb.core.ui.A;
import com.jmoordb.core.ui.Body;
import com.jmoordb.core.ui.Button;
import com.jmoordb.core.ui.ButtonType;
import com.jmoordb.core.ui.div.Div;
import com.jmoordb.core.ui.form.Form;
import com.jmoordb.core.ui.headings.H3;
import com.jmoordb.core.ui.Head;
import com.jmoordb.core.ui.Html;
import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Link;
import com.jmoordb.core.ui.Meta;
import com.jmoordb.core.ui.P;
import com.jmoordb.core.ui.Script;
import com.jmoordb.core.ui.Style;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.Title;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.input.InputText;

public class PasswordRecovery implements WebComponent {

    private final String contextPath;
    private final WebComponent messageAlert;

    /**
     * @param contextPath Path base de la aplicación.
     * @param messageAlert Alerta para mostrar si el proceso fue exitoso o falló
     * (puede ser null).
     */
    public PasswordRecovery(String contextPath, WebComponent messageAlert) {
        this.contextPath = contextPath;
        this.messageAlert = messageAlert;
    }

    @Override
    public String render() {
        // 1. Contenido del Formulario
        Form formContent = new Form()
                .action(contextPath + "/forgot-password")
                .method("POST");

        formContent.add(
                new P().text("Ingresá tu nombre de usuario o email para recibir un enlace de recuperación.")
        );

        // Campo de Identificación (Username/Email)
        formContent.add(
                new Div().withClass("mb-3")
                        .add(
                                new Label().forField("identity").addClass("form-label").text("Usuario o Email:"))
                        .add(
                                new InputText().addClass("form-control").id("identity").name("identity").required(Boolean.TRUE)
                        )
        );

        // Botón de Submit
        Div buttonsContainer = new Div().addClass("d-grid gap-2");

        buttonsContainer.add(
                new Button().type(ButtonType.SUBMIT).addClass("btn btn-warning").text("Enviar Enlace")
        );

        formContent.add(buttonsContainer);

        // Enlace para volver al Login
        formContent.add(new P().addClass("mt-3 text-center")
                .add(new A().href(contextPath + "/login").text("Volver al Login")));

        // 2. Card Body
        Div cardBody = new Div().addClass("card-body");

        if (messageAlert != null) {
            cardBody.add(messageAlert);
        }
        cardBody.add(formContent);

        // 3. Estructura de Tarjeta
        Div recoveryCard = new Div().addClass("card shadow-lg")
                .add(
                        new Div().addClass("card-header bg-warning text-dark text-center")
                                .add(
                                        new H3().text("Recuperar Contraseña")))
                .add(cardBody);

        // 4. Ensamblaje de la página completa (Reutilizando la plantilla del Login)
        return getPageTemplate(recoveryCard.render()).render();
    }

    // Método auxiliar para generar la estructura HTML base (lo ideal es compartirlo en una clase BasePage)
    private Tag getPageTemplate(String content) {

        Body body = new Body().text(content)
                .add(
                        new Script().src("https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js")
                );

        
        Html html = new Html()
                .add(new Head()
                        .add(new Meta().charset("UTF-8"))
                        .add(new Meta().name("viewport").content("width=device-width, initial-scale=1"))
                        .add(new Title().text("Recuperar Contraseña"))
                        .add(new Link().rel("stylesheet").href("https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"))
                        .add(new Style().text("body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background-color: #f8f9fa; } .card { width: 100%; max-width: 400px; } body.dark-mode { background-color: #212529; }"))
                )
                .add(body);
        return html;
    }

    public WebComponent build() {
        return this;
    }
}
