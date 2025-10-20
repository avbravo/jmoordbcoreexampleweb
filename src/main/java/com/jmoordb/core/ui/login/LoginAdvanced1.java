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
import com.jmoordb.core.ui.Div;
import com.jmoordb.core.ui.Form;
import com.jmoordb.core.ui.input.InputPassword;
import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Option;
import com.jmoordb.core.ui.Select;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.Button;
import com.jmoordb.core.ui.ButtonType;
import com.jmoordb.core.ui.headings.H2;
import com.jmoordb.core.ui.Head;
import com.jmoordb.core.ui.Html;
import com.jmoordb.core.ui.Link;
import com.jmoordb.core.ui.Meta;
import com.jmoordb.core.ui.Script;
import com.jmoordb.core.ui.Style;
import java.util.Map;

public class LoginAdvanced1 implements WebComponent {

    private final String contextPath;
    private final WebComponent errorAlert;
    private final Map<String, String> userRoles; // Clave: value (admin), Valor: texto a mostrar (Administrador)
    private final String title;
    private final String metaTitle;

    /**
     * Constructor para el componente de Login Avanzado.
     *
     * @param contextPath Path base de la aplicación.
     * @param errorAlert Componente de alerta (puede ser null).
     * @param userRoles Mapa de roles (value HTML -> texto a mostrar).
     */
    public LoginAdvanced1(String contextPath, WebComponent errorAlert, Map<String, String> userRoles, String title, String metaTitle) {
        this.contextPath = contextPath;
        this.errorAlert = errorAlert;
        this.userRoles = userRoles;
        this.title = title;
        this.metaTitle = metaTitle;
    }

    @Override
    public String render() {
        // 1. Contenido del Formulario
        Form formContent = new Form()
                .action(contextPath + "/login") // Usamos un servlet distinto
                .method("POST");

        // 1.1. Campo SELECT/ROL
        Select selectTag = new Select()
                .name("userRol")
                .addClass("form-select")
                .required(Boolean.TRUE);

        // Opción por defecto
        selectTag.add(
                new Option()
                        .value("")
                        .disabled(Boolean.TRUE)
                        .selected(Boolean.TRUE)
                        .text("Seleccionar Rol")
        );

        // Generar opciones dinámicamente
        userRoles.forEach((value, text)
                -> selectTag.add(new Option()
                        .value(value)
                        .text(text))
        );

        formContent.add(new Div().addClass("mb-3")
                .add(new Label().forField("userRol").addClass("form-label").text("Rol:"))
                .add(selectTag)
        );


      
//        // 1.2. Campo Username
        formContent.add(new Div().addClass("mb-3")
                .add(
                        new Label().forField("username").addClass("form-label").text("Username:")
                )
                .add(new Tag("input").withAttribute("type", "text").withClass("form-control").withAttribute("id", "username").withAttribute("name", "username").withAttribute("required", "true"))
        );

//        // 1.3. Campo Password
        formContent.add(new Div().addClass("mb-3")
                .add(new Label().forField("password").addClass("form-label").text("Password:"))
                .add(new InputPassword().addClass("form-control").id("password").name("password").required(Boolean.TRUE))
        );

        // 1.4. Botones (Login + Olvidó Contraseña)
        Div buttonsContainer = new Div().addClass("d-grid gap-2 mb-3");

        buttonsContainer.add(
                new Button().type(ButtonType.SUBMIT).addClass("btn btn-primary").text("Log in")
        );

        // Enlace para Olvidó Contraseña
        buttonsContainer.withChild(new A().href(contextPath + "/forgot-password")
                .addClass("btn btn-link text-decoration-none text-center")
                .text("¿Olvidaste tu Contraseña?"));

        formContent.withChild(buttonsContainer);

        // 2. Card Body, Header y Ensamblaje (Similar a Login)
        Div cardBody = new Div().addClass("card-body");

        if (errorAlert != null) {
            cardBody.withChild(errorAlert);
        }
        cardBody.withChild(formContent);

        Div loginCard = new Div().addClass("card shadow-lg")
                .add(
                        new Div().addClass("card-header bg-dark text-white text-center")
                                .add(new H2().text(title))
                )
                .add(cardBody);

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
        Body body = new Body().addClass("dark-mode")
                .add(new Div().text(content))
                .add(
                        new Script().src("https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js")
                );

        Html html = new Html()
                .add(new Head()
                        .add(new Meta().charset("UTF-8"))
                        .add(new Meta().name("viewport").content("width=device-width, initial-scale=1"))
                        .add(new Meta().text(metaTitle))
                        .add(new Link().rel("stylesheet").href("https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"))
                        .add(new Style().text(customStyles)) // Estilos Inyectados
                )
                .add(body);

        return html;
    }

    public WebComponent build() {
        return this;
    }

}
