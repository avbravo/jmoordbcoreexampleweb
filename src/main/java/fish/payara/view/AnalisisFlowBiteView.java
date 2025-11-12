/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.view;

import com.jmoordb.core.ui.Button;
import com.jmoordb.core.ui.FieldSet;
import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.div.Div;
import com.jmoordb.core.ui.form.Form;
import com.jmoordb.core.ui.Span;
import com.jmoordb.core.ui.stepper.Stepper;
import com.jmoordb.core.ui.stepper.StepperData;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.css.GridColCss;
import com.jmoordb.core.ui.css.RadioSimpleCss;
import com.jmoordb.core.ui.dashboard.DashboardLayout;
import com.jmoordb.core.ui.form.Grid;
import com.jmoordb.core.ui.form.GridCol;
import com.jmoordb.core.ui.form.FormRow;
import com.jmoordb.core.ui.headings.H3;
import com.jmoordb.core.ui.input.InputText;
import com.jmoordb.core.ui.input.TypeInput;
import com.jmoordb.core.ui.jettra.JettraView;
import com.jmoordb.core.ui.model.WebModelSession;
import com.jmoordb.core.ui.panel.Panel;
import com.jmoordb.core.ui.properties.JettraResourcesFiles;
import com.jmoordb.core.ui.radio.Radio;
import com.jmoordb.core.ui.radio.RadioItem;
import fish.payara.config.ConfigurationProperties;
import fish.payara.dashboard.MenuSideBar;
import fish.payara.model.Motivo;
import fish.payara.services.MotivoServices;
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
@Path("analisisflowbite-view") // ⭐ Define la URL final: /api/profile-view
@RequestScoped
public class AnalisisFlowBiteView extends JettraView {
    // <editor-fold defaultstate="collapsed" desc="attributes()">

    WebModelSession webModelSession = new WebModelSession();
    List<Tag> headers = new ArrayList<>();
    @Inject
    ConfigurationProperties configurationProperties;
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inject()">
    @Inject
    MotivoServices motivoServices;

    @Inject
    JettraResourcesFiles jettraResourcesFiles;
// </editor-fold>

    @Override
    protected String init() {
        webModelSession = webModelOfSession(request);
        List<Motivo> motivos = motivoServices.findAll();
        if (motivos == null || motivos.isEmpty()) {

        } else {
            for (Motivo m : motivos) {
                System.out.println("\t " + m.getMotivo());
            }
        }

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

            Form mainForm = new Form().id("mainForm")
                    .add(new FormRow("Fecha de Registro", "fechaRegistro", "fechaRegistro", TypeInput.DATE))
                    .add(new FormRow("NHRC (Número de Historia Clínica)", "nhrc", "nhrc", TypeInput.TEXT, Boolean.TRUE, Boolean.FALSE))
                    .add(new FormRow("Número de muestra", "numeromuestra", "numeromuestra", TypeInput.TEXT, Boolean.TRUE, Boolean.FALSE))
                    .add(new FormRow("Edad del Paciente", "edad", "edad", TypeInput.NUMBER, Boolean.TRUE, Boolean.FALSE));

            Grid grid = new Grid();
            grid.add(new GridCol("Fecha de Registro", "fechaRegistro", "fechaRegistro", TypeInput.DATE));
            grid.add(new GridCol("Fecha de Registro3", "fechaRegistro3", "fechaRegistro3", TypeInput.DATE));

            grid.add(new GridCol(
                    new Label("Apellido", GridColCss.Label.css, "apellido"),
                    new InputText("apellido", "apellido", GridColCss.Input.css).required(Boolean.TRUE)));
            grid.add(new GridCol(
                    new Label("Salario", GridColCss.Label.css, "salario"),
                    new InputText("salario", "salario", GridColCss.Input.css).required(Boolean.TRUE)));

            mainForm.add(grid);
            /**
             * Stepper son divisor
             */
           
            H3 h3Autos = new H3("Autos");

            Div divMazda = new Div("flex items-center mb-4")
                    .add(new RadioItem("mazda", "autos", RadioSimpleCss.Input.css))
                    .add(new Label("Mazda", RadioSimpleCss.Label.css, "mazda"));
            Div divFerrari = new Div("flex items-center mb-4")
                    .add(new RadioItem("ferrari", "autos", RadioSimpleCss.Input.css))
                    .add(new Label("Ferrari", RadioSimpleCss.Label.css, "ferrari"));
            
            
            mainForm.add(h3Autos);
            mainForm.add(divMazda);
            mainForm.add(divFerrari);
            
            
            H3 h3 = new H3("Frutas");
            Radio radioManzana = new Radio()
                    .add(new RadioItem("manzana", "frutas", RadioSimpleCss.Input.css).disabled(Boolean.TRUE))    
                    .add(new Label("Manzana", RadioSimpleCss.Label.css, "manzana")) 
                    ;  
            
            Radio radioUva = new Radio()
                    .add(new RadioItem("uva", "frutas", RadioSimpleCss.Input.css).checked(Boolean.TRUE))    
                    .add(new Label("Uva", RadioSimpleCss.Label.css, "uva")) 
                    ;  
            Radio radioPera = new Radio()
                    .add(new RadioItem("pera", "frutas", RadioSimpleCss.Input.css).checked(Boolean.TRUE))    
                    .add(new Label("Pera", RadioSimpleCss.Label.css, "pera")) 
                    ;  
            
            mainForm.add(h3);
            mainForm.add(radioManzana);
            mainForm.add(radioUva);
            mainForm.add(radioPera);

            Div divReasonSection = new Div().id("reason-section")
                    .add(new FieldSet().text("Motivo del estudio"))
                    .add(
                            new Div().addClass("radio-group-container two-columns")
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo1").name("motivo").required(Boolean.TRUE).value("Vaginitis"))
                                                    .add(new Label().forField("motivo1").text("Vaginitis").addClass(GridColCss.Label.css))
                                    )
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo2").name("motivo").required(Boolean.TRUE).value("Candidiasis previa"))
                                                    .add(new Label().forField("motivo2").text("Candidiasis previa").addClass(GridColCss.Label.css))
                                    )
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo3").name("motivo").required(Boolean.TRUE).value("Coitorragia"))
                                                    .add(new Label().forField("motivo3").text("Coitorragia").addClass(GridColCss.Label.css))
                                    )
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo4").name("motivo").required(Boolean.TRUE).value("Dispareunia"))
                                                    .add(new Label().forField("motivo4").text("Dispareunia").addClass(GridColCss.Label.css))
                                    )
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo5").name("motivo").required(Boolean.TRUE).value("Disuaria/Cistitis"))
                                                    .add(new Label().forField("motivo5").text("Disuaria/Cistitis").addClass(GridColCss.Label.css))
                                    )
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo6").name("motivo").required(Boolean.TRUE).value("Gestante"))
                                                    .add(new Label().forField("motivo6").text("Gestante").addClass(GridColCss.Label.css))
                                    )
                    );
            mainForm.add(divReasonSection);

            /**
             *
             */
            //
            //     <button type="submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Submit</button>
            Button saveButton = new Button()
                    .addClass("text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800")
                    .hx_post("/jmoordbcoreexampleweb/api/FormularioControllers")
                    .hx_target("#save-feedback")
                    .hx_swap("innerHTML")
                    .hx_include("#mainForm")
                    .hx_indicator("#saveButton .htmx-indicator")
                    .add(new Span().text("Guardar Expediente").addClass("button-text"))
                    .add(new Span().addClass("htmx-indicator spinner"));

            Div divSaveFeedBack = new Div();

            //  <div id="save-feedback"></div>
//            mainForm.add(div);
//            mainForm.add(div2);
//          mainForm.add(divReasonSection);
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
