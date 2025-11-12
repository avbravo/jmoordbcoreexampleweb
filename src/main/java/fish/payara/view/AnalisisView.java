/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.view;

import com.jmoordb.core.ui.Button;
import com.jmoordb.core.ui.div.Div;
import com.jmoordb.core.ui.FieldSet;
import com.jmoordb.core.ui.form.Form;
import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Span;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.dashboard.DashboardLayout;
import com.jmoordb.core.ui.headings.H2;
import com.jmoordb.core.ui.input.InputDate;
import com.jmoordb.core.ui.input.InputNumber;
import com.jmoordb.core.ui.input.InputText;
import com.jmoordb.core.ui.jettra.JettraView;
import com.jmoordb.core.ui.model.WebModelSession;
import com.jmoordb.core.ui.panel.Panel;
import com.jmoordb.core.ui.radio.RadioItem;
import fish.payara.config.ConfigurationProperties;
import fish.payara.dashboard.MenuSideBar;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author avbravo
 */
@Path("analisis-view") // ⭐ Define la URL final: /api/profile-view
@RequestScoped
public class AnalisisView extends JettraView {
    // <editor-fold defaultstate="collapsed" desc="attributes()">

    WebModelSession webModelSession = new WebModelSession();
    List<Tag> headers = new ArrayList<>();
    @Inject
    ConfigurationProperties configurationProperties;
// </editor-fold>

    @Override
    protected String init() {
        webModelSession = webModelOfSession(request);
        String contextPath = request.getContextPath();
        headers.add(new Tag("link").withAttribute("rel", "stylesheet").withAttribute("href", contextPath + "/css/microdetection.css"));

        return DashboardLayout.buildPage(
                request,
                webModelSession.getUsername(),
                content(request),
                MenuSideBar.getSidebarSections(
                        this.getClass().getSimpleName(),
                        webModelSession
                ),
                "Profile View",
                configurationProperties.getDashboardFooterText() + " | " + webModelSession.getUserRol(),
                headers
        );
    }

    @Override
    protected WebComponent content(HttpServletRequest request) {
        WebComponent mainContent = null;
        try {
            String labelClass = "block text-gray-700 text-sm font-bold mb-2 dark:text-white";
            String inputClass = "shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline dark:bg-gray-700 dark:border-gray-600 dark:text-white";
            Form mainForm = new Form().id("mainForm");

            Div divPatientData = new Div().id("patient-data")
                    .add(new H2().text("Datos del Paciente"))
                    .add(new Div().addClass("form-row")
                            .add(
                                    new Div().addClass("input-group")
                                            .add(new Label().forField("fechaRegistro").text("Fecha de Registro:").addClass(labelClass))
                                            .add(new InputDate().id("fechaRegistro").name("fechaRegistro").required(Boolean.TRUE).addClass(inputClass))
                            )
                            .add(
                                    new Div().addClass("input-group")
                                            .add(new Label().forField("nhrc").text("NHRC (Número de Historia Clínica)").addClass(labelClass))
                                            .add(new InputText().id("nhrc").name("nhrc").required(Boolean.TRUE).addClass(inputClass))
                            )
                            .add(
                                    new Div().addClass("input-group")
                                            .add(new Label().forField("numeromuestra").text("Número de muestra:").addClass(labelClass))
                                            .add(new InputText().id("numeromuestra").name("numeromuestra").required(Boolean.TRUE).addClass(inputClass))
                            )
                            .add(
                                    new Div().addClass("input-group")
                                            .add(new Label().forField("edad").text("Edad del Paciente:").addClass(labelClass))
                                            .add(new InputNumber().id("edad").name("edad").required(Boolean.TRUE).addClass(inputClass).min("0").max("125"))
                            )
                    );
            Div divReasonSection = new Div().id("reason-section")
                    .add(new FieldSet().text("Motivo del estudio"))
                    .add(
                            new Div().addClass("radio-group-container two-columns")
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo1").name("motivo").required(Boolean.TRUE).value("Vaginitis"))
                                                    .add(new Label().forField("motivo1").text("Vaginitis").addClass(labelClass))
                                    )
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo2").name("motivo").required(Boolean.TRUE).value("Candidiasis previa"))
                                                    .add(new Label().forField("motivo2").text("Candidiasis previa").addClass(labelClass))
                                    )
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo3").name("motivo").required(Boolean.TRUE).value("Coitorragia"))
                                                    .add(new Label().forField("motivo3").text("Coitorragia").addClass(labelClass))
                                    )
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo4").name("motivo").required(Boolean.TRUE).value("Dispareunia"))
                                                    .add(new Label().forField("motivo4").text("Dispareunia").addClass(labelClass))
                                    )
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo5").name("motivo").required(Boolean.TRUE).value("Disuaria/Cistitis"))
                                                    .add(new Label().forField("motivo5").text("Disuaria/Cistitis").addClass(labelClass))
                                    )
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo6").name("motivo").required(Boolean.TRUE).value("Gestante"))
                                                    .add(new Label().forField("motivo6").text("Gestante").addClass(labelClass))
                                    )
                    );

            /**
             *
             */
            Button saveButton = new Button()
                    .hx_post("/jmoordbcoreexampleweb/api/FormularioControllers")
                    .hx_target("#save-feedback")
                    .hx_swap("innerHTML")
                    .hx_include("#mainForm")
                    .hx_indicator("#saveButton .htmx-indicator")
                    .add(new Span().text("Guardar Expediente").addClass("button-text"))
                    .add(new Span().addClass("htmx-indicator spinner"));

            Div divSaveFeedBack = new Div();

            //  <div id="save-feedback"></div>
            mainForm.add(divPatientData);
            mainForm.add(divReasonSection);
            mainForm.add(saveButton);
            mainForm.add(divSaveFeedBack);
            mainContent = new Panel("Analisis", mainForm, request);
        } catch (Exception e) {
            System.out.println("\t content() " + e.getLocalizedMessage());
        }
        return mainContent;
    }

    @Override
    protected String javaScriptCode() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
