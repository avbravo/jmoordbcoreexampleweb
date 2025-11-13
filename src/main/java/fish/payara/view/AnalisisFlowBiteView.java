/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.view;

import com.jmoordb.core.ui.Button;
import com.jmoordb.core.ui.FieldSet;
import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Link;
import com.jmoordb.core.ui.div.Div;
import com.jmoordb.core.ui.form.Form;
import com.jmoordb.core.ui.Span;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.css.GridColCss;
import com.jmoordb.core.ui.css.RadioBorderCss;
import com.jmoordb.core.ui.css.RadioCss;
import com.jmoordb.core.ui.css.RadioDropdownCss;
import com.jmoordb.core.ui.css.RadioHorizontalListGroupCss;
import com.jmoordb.core.ui.css.RadioListGroupCss;
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
import com.jmoordb.core.ui.radio.RadioBorder;
import com.jmoordb.core.ui.radio.RadioDropdownButton;
import com.jmoordb.core.ui.radio.RadioHelper;
import com.jmoordb.core.ui.radio.RadioHorizontalListGroup;
import com.jmoordb.core.ui.radio.RadioHorizontalListGroupHeader;
import com.jmoordb.core.ui.radio.RadioItem;
import com.jmoordb.core.ui.radio.RadioItemLink;
import com.jmoordb.core.ui.radio.RadioDropdown;
import com.jmoordb.core.ui.radio.RadioListGroup;
import com.jmoordb.core.ui.radio.RadioListGroupElement;
import com.jmoordb.core.ui.radio.RadioListGroupHeader;
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

        headers.add(new Link().rel("stylesheet").href(request.getContextPath() + "/css/radio.css"));

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

            Div divMazda = new Div(RadioCss.Div.css)
                    .add(new RadioItem("mazda", "autos", RadioCss.Input.css))
                    .add(new Label("Mazda", RadioCss.Label.css, "mazda"));
            Div divFerrari = new Div("flex items-center mb-4")
                    .add(new RadioItem("ferrari", "autos", RadioCss.Input.css))
                    .add(new Label("Ferrari", RadioCss.Label.css, "ferrari"));

            mainForm.add(h3Autos);
            mainForm.add(divMazda);
            mainForm.add(divFerrari);

            H3 h3 = new H3("Frutas");
            Radio radioManzana = new Radio()
                    .add(new RadioItem("manzana", "frutas", RadioCss.Input.css).disabled(Boolean.TRUE))
                    .add(new Label("Manzana", RadioCss.Label.css, "manzana"));

            Radio radioUva = new Radio()
                    .add(new RadioItem("uva", "frutas", RadioCss.Input.css).checked(Boolean.TRUE))
                    .add(new Label("Uva", RadioCss.Label.css, "uva"));
            Radio radioPera = new Radio()
                    .add(new RadioItem("pera", "frutas", RadioCss.Input.css).checked(Boolean.TRUE))
                    .add(new Label("Pera", RadioCss.Label.css, "pera"));

            Radio radioLink = new Radio()
                    .add(new RadioItem("guineo", "frutas", RadioCss.Input.css)
                            .checked(Boolean.TRUE)
                    )
                    .add(new Label("Guineo", RadioCss.Label.css, "guineo", new RadioItemLink("#", "link inside")));

            RadioItem radioItemSandia = new RadioItem("sandia", "frutas", RadioCss.Input.css).checked(Boolean.TRUE);
            Label labelSandia = new Label("Sandia", RadioCss.Label.css, "sandia");
            RadioHelper radioHelperSandia = new RadioHelper(
                    radioItemSandia,
                    labelSandia, "Es una fruta tropical"
            );

            RadioBorder radioBorderLimon = new RadioBorder()
                    .add(new RadioItem("limon", "frutas", RadioBorderCss.Input.css).checked(Boolean.TRUE))
                    .add(new Label("Limon", RadioBorderCss.Label.css, "limon"));
            RadioBorder radioBorderPapaya = new RadioBorder()
                    .add(new RadioItem("papaya", "frutas", RadioBorderCss.Input.css).checked(Boolean.TRUE))
                    .add(new Label("Papaya", RadioBorderCss.Label.css, "papaya"));

            /**
             * RadioListGroup
             */
            RadioListGroupHeader rlgh = new RadioListGroupHeader("Identificación");
            List<RadioListGroupElement> radioListGroupElements = new ArrayList<>();

            radioListGroupElements.add(new RadioListGroupElement(
                    new RadioItem("cedula", "identificacion", RadioListGroupCss.Input.css),
                    new Label("Cedula", RadioListGroupCss.Label.css, "cedula")
            ));
            radioListGroupElements.add(new RadioListGroupElement(
                    new RadioItem("pasaporte", "identificacion", RadioListGroupCss.Input.css),
                    new Label("Pasaporte", RadioListGroupCss.Label.css, "pasaporte")
            ));

            RadioListGroup radioListIdentificacion = new RadioListGroup(radioListGroupElements);

            /**
             * RadioHorizontalListGroup
             */
            RadioHorizontalListGroupHeader rhlghPais = new RadioHorizontalListGroupHeader("Pais");
            List<RadioListGroupElement> radioHorizontalListGroupElements = new ArrayList<>();

            radioHorizontalListGroupElements.add(new RadioListGroupElement(
                    new RadioItem("panama", "pais", RadioHorizontalListGroupCss.Input.css),
                    new Label("Panama", RadioHorizontalListGroupCss.Label.css, "cedula")
            ));
            radioHorizontalListGroupElements.add(new RadioListGroupElement(
                    new RadioItem("colombia", "pais", RadioHorizontalListGroupCss.Input.css),
                    new Label("Colombia", RadioHorizontalListGroupCss.Label.css, "pasaporte")
            ));

            RadioHorizontalListGroup radioHorizontalListIdentificacion = new RadioHorizontalListGroup(radioHorizontalListGroupElements);

            /**
             * Radio in dropdown
             */
            RadioDropdownButton radioDropdownButton = new RadioDropdownButton("dropdownHelperRadioButton", "dropdownHelperRadio", "Dropdown radio");

            List<RadioListGroupElement> radioListGroupElementLenguajes = new ArrayList<>();

            radioListGroupElementLenguajes.add(new RadioListGroupElement(
                    new RadioItem("java", "lenguaje", RadioDropdownCss.Input.css),
                    new Label("Java", RadioDropdownCss.Label.css, "java").setSubText("Creado por J. Gosling")
            ));
            radioListGroupElementLenguajes.add(new RadioListGroupElement(
                    new RadioItem("c", "lenguaje", RadioDropdownCss.Input.css),
                    new Label("C", RadioDropdownCss.Label.css, "lenguaje").setSubText("Inicio de todo")
            ));

            RadioDropdown radioDropdownLenguajes = new RadioDropdown("dropdownHelperRadio","dropdownHelperRadioButton", radioListGroupElementLenguajes);
 Grid gridRadioDropdwon = new Grid();
 gridRadioDropdwon.add(radioDropdownButton);
 gridRadioDropdwon.add(radioDropdownLenguajes);
            //            <div class="flex">
            //    <div class="flex items-center h-5">
            //        <input id="helper-radio" aria-describedby="helper-radio-text" type="radio" value="" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
            //    </div>
            //    <div class="ms-2 text-sm">
            //        <label for="helper-radio" class="font-medium text-gray-900 dark:text-gray-300">Free shipping via Flowbite</label>
            //        <p id="helper-radio-text" class="text-xs font-normal text-gray-500 dark:text-gray-300">For orders shipped from $25 in books or $29 in other categories</p>
            //    </div>
            //</div>
            //                
            mainForm.add(h3);
            mainForm.add(radioManzana);
            mainForm.add(radioUva);
            mainForm.add(radioPera);
            mainForm.add(radioLink);
            mainForm.add(radioHelperSandia);
            mainForm.add(radioBorderLimon);
            mainForm.add(radioBorderPapaya);

            mainForm.add(rlgh);
            mainForm.add(radioListIdentificacion);

            mainForm.add(rhlghPais);
            mainForm.add(radioHorizontalListIdentificacion);

//            mainForm.add(radioDropdownButton);
//            mainForm.add(radioDropdownLenguajes);
            
            mainForm.add(gridRadioDropdwon);
            

//            <div class="flex items-center">
//    <input id="link-radio" type="radio" value="" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
//    <label for="link-radio" class="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Radio button with a 
//    <a href="#" class="text-blue-600 dark:text-blue-500 hover:underline">link inside</a>
//            
//            
//            </label>
//</div>
            Div divReasonSection2 = new Div().id("reason-section")
                    .add(new FieldSet().text("Motivo del estudio"))
                    .add(
                            new Div().addClass("radio-group-container two-columns")
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo1").name("motivo").required(Boolean.TRUE).value("Vaginitis"))
                                                    .add(new Label().forField("motivo1").text("Vaginitis").addClass(RadioCss.Label.css))
                                    )
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo2").name("motivo").required(Boolean.TRUE).value("Candidiasis previa"))
                                                    .add(new Label().forField("motivo2").text("Candidiasis previa").addClass(RadioCss.Label.css))
                                    )
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo3").name("motivo").required(Boolean.TRUE).value("Coitorragia"))
                                                    .add(new Label().forField("motivo3").text("Coitorragia").addClass(RadioCss.Label.css))
                                    )
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo4").name("motivo").required(Boolean.TRUE).value("Dispareunia"))
                                                    .add(new Label().forField("motivo4").text("Dispareunia").addClass(RadioCss.Label.css))
                                    )
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo5").name("motivo").required(Boolean.TRUE).value("Disuaria/Cistitis"))
                                                    .add(new Label().forField("motivo5").text("Disuaria/Cistitis").addClass(RadioCss.Label.css))
                                    )
                                    .add(
                                            new Div().addClass("radio-item")
                                                    .add(new RadioItem().id("motivo6").name("motivo").required(Boolean.TRUE).value("Gestante"))
                                                    .add(new Label().forField("motivo6").text("Gestante").addClass(RadioCss.Label.css))
                                    )
                    );
            mainForm.add(divReasonSection2);

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
