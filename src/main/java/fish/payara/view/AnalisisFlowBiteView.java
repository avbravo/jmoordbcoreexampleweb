/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.view;

import com.jmoordb.core.ui.Button;
import com.jmoordb.core.ui.FieldSet;
import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Link;
import com.jmoordb.core.ui.Script;
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
import com.jmoordb.core.ui.radio.item.RadioItem;
import com.jmoordb.core.ui.radio.RadioItemLink;
import com.jmoordb.core.ui.radio.RadioDropdown;
import com.jmoordb.core.ui.radio.RadioInline;
import com.jmoordb.core.ui.radio.RadioListGroup;
import com.jmoordb.core.ui.radio.RadioTwoColumns;
import com.jmoordb.core.ui.radio.element.RadioElement;
import com.jmoordb.core.ui.css.type.CssType;
import com.jmoordb.core.ui.css.RadioInlineCss;
import com.jmoordb.core.ui.css.RadioTwoColumnsCss;
import com.jmoordb.core.ui.radio.header.RadioHeader;
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
                    new Label("Apellido", CssType.GridCol, "apellido"),
                    new InputText("apellido", "apellido", GridColCss.Input.css).required(Boolean.TRUE)));
            grid.add(new GridCol(
                    new Label("Salario", CssType.GridCol, "salario"),
                    new InputText("salario", "salario", GridColCss.Input.css).required(Boolean.TRUE)));

            mainForm.add(grid);
            /**
             * Stepper son divisor
             */

            H3 h3Autos = new H3("Autos");

            Div divMazda = new Div(RadioCss.Div.css)
                    .add(new RadioItem("mazda", "autos", CssType.Radio))
                    .add(new Label("Mazda", CssType.Radio, "mazda"));
            Div divFerrari = new Div("flex items-center mb-4")
                    .add(new RadioItem("ferrari", "autos", CssType.Radio))
                    .add(new Label("Ferrari", CssType.Radio, "ferrari"));

            mainForm.add(h3Autos);
            mainForm.add(divMazda);
            mainForm.add(divFerrari);

            H3 h3 = new H3("Frutas");
            Radio radioManzana = new Radio()
                    .add(new RadioItem("manzana", "frutas", CssType.Radio).disabled(Boolean.TRUE))
                    .add(new Label("Manzana", CssType.Radio, "manzana"));

            Radio radioUva = new Radio()
                    .add(new RadioItem("uva", "frutas", CssType.Radio).checked(Boolean.TRUE))
                    .add(new Label("Uva", CssType.Radio, "uva"));
            Radio radioPera = new Radio()
                    .add(new RadioItem("pera", "frutas", CssType.Radio).checked(Boolean.TRUE))
                    .add(new Label("Pera", CssType.Radio, "pera"));

            Radio radioLink = new Radio()
                    .add(new RadioItem("guineo", "frutas", CssType.Radio)
                            .checked(Boolean.TRUE)
                    )
                    .add(new Label("Guineo", CssType.Radio, "guineo", new RadioItemLink("#", "link inside")));

            RadioItem radioItemSandia = new RadioItem("sandia", "frutas",CssType.Radio).checked(Boolean.TRUE);
            Label labelSandia = new Label("Sandia", CssType.Radio, "sandia");
            RadioHelper radioHelperSandia = new RadioHelper(
                    radioItemSandia,
                    labelSandia, "Es una fruta tropical"
            );

            RadioBorder radioBorderLimon = new RadioBorder()
                    .add(new RadioItem("limon", "frutas", CssType.RadioBorder).checked(Boolean.TRUE))
                    .add(new Label("Limon", CssType.RadioBorder, "limon"));
            RadioBorder radioBorderPapaya = new RadioBorder()
                    .add(new RadioItem("papaya", "frutas", CssType.RadioBorder).checked(Boolean.TRUE))
                    .add(new Label("Papaya", CssType.RadioBorder, "papaya"));

            /**
             * RadioListGroup
             */
            RadioHeader rlgh = new RadioHeader("Identificación", CssType.RadioListGroup);
            List<RadioElement> radioElements = new ArrayList<>();

            radioElements.add(new RadioElement(
                    new RadioItem("cedula", "identificacion", CssType.RadioListGroup),
                    new Label("Cedula", CssType.RadioListGroup, "cedula")
            ));
            radioElements.add(new RadioElement(
                    new RadioItem("pasaporte", "identificacion", CssType.RadioListGroup),
                    new Label("Pasaporte", CssType.RadioListGroup, "pasaporte")
            ));

            RadioListGroup radioListIdentificacion = new RadioListGroup(radioElements);

            /**
             * RadioHorizontalListGroup
             */
            RadioHeader rhlghPais = new RadioHeader("Pais", CssType.RadioHorizontalListGroup);
            List<RadioElement> radioHorizontalListGroupElements = new ArrayList<>();

            radioHorizontalListGroupElements.add(new RadioElement(
                    new RadioItem("panama", "pais", CssType.RadioHorizontalListGroup),
                    new Label("Panama", CssType.RadioHorizontalListGroup, "cedula")
            ));
            radioHorizontalListGroupElements.add(new RadioElement(
                    new RadioItem("colombia", "pais", CssType.RadioHorizontalListGroup),
                    new Label("Colombia", CssType.RadioHorizontalListGroup, "pasaporte")
            ));

            RadioHorizontalListGroup radioHorizontalListIdentificacion = new RadioHorizontalListGroup(radioHorizontalListGroupElements);

            /**
             * Radio in dropdown
             */
            RadioDropdownButton radioDropdownButton = new RadioDropdownButton("dropdownHelperRadioButton", "dropdownHelperRadio", "Dropdown radio");

            List<RadioElement> radioElementLenguajes = new ArrayList<>();

            radioElementLenguajes.add(new RadioElement(
                    new RadioItem("java", "lenguaje", CssType.RadioDropdown),
                    new Label("Java", CssType.RadioDropdown, "java").setSubText("Creado por J. Gosling")
            ));
            radioElementLenguajes.add(new RadioElement(
                    new RadioItem("c", "lenguaje", CssType.RadioDropdown),
                    new Label("C", CssType.RadioDropdown, "lenguaje").setSubText("Inicio de todo")
            ));

            RadioDropdown radioDropdownLenguajes = new RadioDropdown("dropdownHelperRadio", "dropdownHelperRadioButton", radioElementLenguajes);
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
            /**
             * Radio in dropdown 2
             */
            RadioDropdownButton radioDropdownButton2 = new RadioDropdownButton("dropdownHelperRadioButton3", "", "Peliculas");

            List<RadioElement> radioElementLenguajes2 = new ArrayList<>();

            radioElementLenguajes2.add(new RadioElement(
                    new RadioItem("matrix", "pelicula", CssType.RadioDropdown),
                    new Label("Matrix", CssType.RadioDropdown, "matrix").setSubText("Reloaded")
            ));
            radioElementLenguajes2.add(new RadioElement(
                    new RadioItem("senoranillos", "pelicula", CssType.RadioDropdown),
                    new Label("El señor de los anillos", CssType.RadioDropdown, "senoranillos").setSubText("Las dos torres")
            ));

            RadioDropdown radioDropdownLenguajes2 = new RadioDropdown("dropdownHelperRadio2", "dropdownHelperRadioButton2", radioElementLenguajes2);
            Grid gridRadioDropdwon2 = new Grid();
            gridRadioDropdwon2.add(radioDropdownButton2);
            gridRadioDropdwon2.add(radioDropdownLenguajes2);
            Script scriptFecth = new Script()
                    .code(javaScriptCode());

            /*
            * RadioInline
             */
            RadioHeader radioHeadDeportes = new RadioHeader("Deportes", CssType.RadioInline);
            List<RadioElement> radioElementDeportes = new ArrayList<>();

            radioElementDeportes.add(new RadioElement(
                    new RadioItem("baloncesto", "deporte", CssType.RadioInline).checked(Boolean.TRUE),
                    new Label("Baloncesto", CssType.RadioInline, "baloncesto")
            ));
            radioElementDeportes.add(new RadioElement(
                    new RadioItem("tenis", "deporte", CssType.RadioInline),
                    new Label("Tenis", CssType.RadioInline, "tenis")
            ));

            RadioInline radioInline = new RadioInline(radioElementDeportes);
            
              /**
             * radioTwoColumns
             */
            List<RadioElement> radioElementTowColumns = new ArrayList<>();

            radioElementTowColumns.add(new RadioElement(
                    new RadioItem("auto", "vehiculo", CssType.RadioTwoColumns),
                    new Label("Auto", CssType.RadioTwoColumns, "auto")
            ));
            radioElementTowColumns.add(new RadioElement(
                    new RadioItem("auto", "vehiculo", CssType.RadioTwoColumns),
                    new Label("Auto", CssType.RadioTwoColumns, "auto")
            ));
            radioElementTowColumns.add(new RadioElement(
                    new RadioItem("barco", "vehiculo", CssType.RadioTwoColumns),
                    new Label("Barco", CssType.RadioHorizontalListGroup, "barco")
            ));
            radioElementTowColumns.add(new RadioElement(
                    new RadioItem("tren", "vehiculo", CssType.RadioTwoColumns),
                    new Label("Tren", CssType.RadioTwoColumns, "tren")
            ));
            radioElementTowColumns.add(new RadioElement(
                    new RadioItem("avion", "vehiculo", CssType.RadioTwoColumns),
                    new Label("Avion", CssType.RadioTwoColumns, "avion")
            ));

            RadioTwoColumns radioTwoColumns = new RadioTwoColumns("vehiculo-section","Vehiculos", radioElementTowColumns);

            

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
            mainForm.add(gridRadioDropdwon2);
            mainForm.add(scriptFecth);

            mainForm.add(radioHeadDeportes);
            mainForm.add(radioInline);
            
            mainForm.add(radioTwoColumns);

//            <div class="flex items-center">
//    <input id="link-radio" type="radio" value="" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
//    <label for="link-radio" class="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Radio button with a 
//    <a href="#" class="text-blue-600 dark:text-blue-500 hover:underline">link inside</a>
//            
//            
//            </label>
//</div>
          
           
   

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
        String result = "";
        try {
            result = """
              document.addEventListener('DOMContentLoaded', () => {
                          const targetEl = document.getElementById('dropdownHelperRadio2');
                          const triggerEl = document.getElementById('dropdownHelperRadioButton2');
                          
                          // Verificamos que el constructor de Flowbite esté disponible
                          if (targetEl && triggerEl && typeof Flowbite !== 'undefined') {
                              
                              // 🚀 Inicialización única y controlada para tu dropdown
                              const options = {
                                  placement: 'right' // Usa el posicionamiento que ya corregiste
                              };
                              
                              const dropdown = new Flowbite.Dropdown(targetEl, triggerEl, options);
                          }
                          // NOTA: El resto de los componentes de Flowbite se inicializarán automáticamente.
                      });
                     """;
        } catch (Exception e) {
            System.out.println("\t content() " + e.getLocalizedMessage());
        }
        return result;
    }

}
