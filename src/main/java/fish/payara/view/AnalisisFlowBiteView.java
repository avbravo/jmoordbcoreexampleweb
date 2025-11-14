/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.view;

import com.jmoordb.core.ui.Button;
import com.jmoordb.core.ui.Hr;
import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Link;
import com.jmoordb.core.ui.Script;
import com.jmoordb.core.ui.div.Div;
import com.jmoordb.core.ui.form.Form;
import com.jmoordb.core.ui.Span;
import com.jmoordb.core.ui.Svg;
import com.jmoordb.core.ui.SvgPath;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.css.GridColCss;
import com.jmoordb.core.ui.css.RadioCss;
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
import com.jmoordb.core.ui.radio.RadioAdvancedLayout;
import com.jmoordb.core.ui.radio.RadioAdvancedLayoutIcon;
import com.jmoordb.core.ui.radio.element.RadioElementIcon;
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

            H3 h3Autos = new H3("RadioItem con Div");

            Div divMazda = new Div(RadioCss.Div.css)
                    .add(new RadioItem("mazda", "autos", CssType.Radio))
                    .add(new Label("Mazda", CssType.Radio, "mazda"));
            Div divFerrari = new Div("flex items-center mb-4")
                    .add(new RadioItem("ferrari", "autos", CssType.Radio))
                    .add(new Label("Ferrari", CssType.Radio, "ferrari"));

            mainForm.add(h3Autos);
            mainForm.add(divMazda);
            mainForm.add(divFerrari);

            H3 h3 = new H3("Radio (Disabled, checked, link, helper, border)");
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

            RadioItem radioItemSandia = new RadioItem("sandia", "frutas", CssType.Radio).checked(Boolean.TRUE);
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
            RadioHeader rlgh = new RadioHeader("RadioListGroup", CssType.RadioListGroup);
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
            RadioHeader rhlghPais = new RadioHeader("RadioHorizontalListGroup", CssType.RadioHorizontalListGroup);
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
            RadioDropdownButton radioDropdownButton = new RadioDropdownButton("dropdownHelperRadioButton", "dropdownHelperRadio", "RadioDropdown");

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

            /**
             * Radio in dropdown 2
             */
            RadioDropdownButton radioDropdownButton2 = new RadioDropdownButton("dropdownHelperRadioButton3", "", "RadioDropdownButton Peliculas");

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
            RadioHeader radioHeadDeportes = new RadioHeader("RadioInline", CssType.RadioInline);
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

            RadioTwoColumns radioTwoColumns = new RadioTwoColumns("vehiculo-section", "RadioTwoColumns", radioElementTowColumns);

            /**
             *
             * Advanced Layout
             */
            RadioHeader radioHeadAdvancedLayout = new RadioHeader("RadioAdvancedLayout", CssType.RadioAdvancedLayout);
            List<RadioElement> radioElementAdvancedLayout = new ArrayList<>();

            radioElementAdvancedLayout.add(new RadioElement(
                    new RadioItem("hosting-big", "hosting", CssType.RadioAdvancedLayout),
                    new Label("500-1000 MB", CssType.RadioAdvancedLayout, "hosting-big").setSubText("Good for large websites")
            ));
            radioElementAdvancedLayout.add(new RadioElement(
                    new RadioItem("hosting-xbig", "hosting", CssType.RadioAdvancedLayout),
                    new Label("1100-5000 MB", CssType.RadioAdvancedLayout, "hosting-xbig").setSubText("X Good for large websites")
            ));

            RadioAdvancedLayout radioAdvancedLayout = new RadioAdvancedLayout(radioElementAdvancedLayout);

            /**
             *
             * Advanced Layout con Icono
             */
            RadioHeader radioHeadAdvancedLayoutIcon = new RadioHeader("RadioAdvancedLayout Icono", CssType.RadioAdvancedLayoutIcon);
            List<RadioElementIcon> radioElementAdvancedLayoutIcon = new ArrayList<>();

            radioElementAdvancedLayoutIcon.add(new RadioElementIcon(
                    new RadioItem("react-option", "technologies", CssType.RadioAdvancedLayoutIcon),
                    new Label("React", CssType.RadioAdvancedLayoutIcon, "react-option").setSubText("A JavaScript library for building user interfaces"),
                    new Svg().addClass("mb-2 w-7 h-7 text-sky-600").fill("currentColor").aria_hidden("true").xmlns("http://www.w3.org/2000/svg").viewBox("0 0 512 512")
                            .add(
                                    new SvgPath()
                                            .d("M418.2 177.2c-5.4-1.8-10.8-3.5-16.2-5.1.9-3.7 1.7-7.4 2.5-11.1 12.3-59.6 4.2-107.5-23.1-123.3-26.3-15.1-69.2.6-112.6 38.4-4.3 3.7-8.5 7.6-12.5 11.5-2.7-2.6-5.5-5.2-8.3-7.7-45.5-40.4-91.1-57.4-118.4-41.5-26.2 15.2-34 60.3-23 116.7 1.1 5.6 2.3 11.1 3.7 16.7-6.4 1.8-12.7 3.8-18.6 5.9C38.3 196.2 0 225.4 0 255.6c0 31.2 40.8 62.5 96.3 81.5 4.5 1.5 9 3 13.6 4.3-1.5 6-2.8 11.9-4 18-10.5 55.5-2.3 99.5 23.9 114.6 27 15.6 72.4-.4 116.6-39.1 3.5-3.1 7-6.3 10.5-9.7 4.4 4.3 9 8.4 13.6 12.4 42.8 36.8 85.1 51.7 111.2 36.6 27-15.6 35.8-62.9 24.4-120.5-.9-4.4-1.9-8.9-3-13.5 3.2-.9 6.3-1.9 9.4-2.9 57.7-19.1 99.5-50 99.5-81.7 0-30.3-39.4-59.7-93.8-78.4zM282.9 92.3c37.2-32.4 71.9-45.1 87.7-36 16.9 9.7 23.4 48.9 12.8 100.4-.7 3.4-1.4 6.7-2.3 10-22.2-5-44.7-8.6-67.3-10.6-13-18.6-27.2-36.4-42.6-53.1 3.9-3.7 7.7-7.2 11.7-10.7zM167.2 307.5c5.1 8.7 10.3 17.4 15.8 25.9-15.6-1.7-31.1-4.2-46.4-7.5 4.4-14.4 9.9-29.3 16.3-44.5 4.6 8.8 9.3 17.5 14.3 26.1zm-30.3-120.3c14.4-3.2 29.7-5.8 45.6-7.8-5.3 8.3-10.5 16.8-15.4 25.4-4.9 8.5-9.7 17.2-14.2 26-6.3-14.9-11.6-29.5-16-43.6zm27.4 68.9c6.6-13.8 13.8-27.3 21.4-40.6s15.8-26.2 24.4-38.9c15-1.1 30.3-1.7 45.9-1.7s31 .6 45.9 1.7c8.5 12.6 16.6 25.5 24.3 38.7s14.9 26.7 21.7 40.4c-6.7 13.8-13.9 27.4-21.6 40.8-7.6 13.3-15.7 26.2-24.2 39-14.9 1.1-30.4 1.6-46.1 1.6s-30.9-.5-45.6-1.4c-8.7-12.7-16.9-25.7-24.6-39s-14.8-26.8-21.5-40.6zm180.6 51.2c5.1-8.8 9.9-17.7 14.6-26.7 6.4 14.5 12 29.2 16.9 44.3-15.5 3.5-31.2 6.2-47 8 5.4-8.4 10.5-17 15.5-25.6zm14.4-76.5c-4.7-8.8-9.5-17.6-14.5-26.2-4.9-8.5-10-16.9-15.3-25.2 16.1 2 31.5 4.7 45.9 8-4.6 14.8-10 29.2-16.1 43.4zM256.2 118.3c10.5 11.4 20.4 23.4 29.6 35.8-19.8-.9-39.7-.9-59.5 0 9.8-12.9 19.9-24.9 29.9-35.8zM140.2 57c16.8-9.8 54.1 4.2 93.4 39 2.5 2.2 5 4.6 7.6 7-15.5 16.7-29.8 34.5-42.9 53.1-22.6 2-45 5.5-67.2 10.4-1.3-5.1-2.4-10.3-3.5-15.5-9.4-48.4-3.2-84.9 12.6-94zm-24.5 263.6c-4.2-1.2-8.3-2.5-12.4-3.9-21.3-6.7-45.5-17.3-63-31.2-10.1-7-16.9-17.8-18.8-29.9 0-18.3 31.6-41.7 77.2-57.6 5.7-2 11.5-3.8 17.3-5.5 6.8 21.7 15 43 24.5 63.6-9.6 20.9-17.9 42.5-24.8 64.5zm116.6 98c-16.5 15.1-35.6 27.1-56.4 35.3-11.1 5.3-23.9 5.8-35.3 1.3-15.9-9.2-22.5-44.5-13.5-92 1.1-5.6 2.3-11.2 3.7-16.7 22.4 4.8 45 8.1 67.9 9.8 13.2 18.7 27.7 36.6 43.2 53.4-3.2 3.1-6.4 6.1-9.6 8.9zm24.5-24.3c-10.2-11-20.4-23.2-30.3-36.3 9.6.4 19.5.6 29.5.6 10.3 0 20.4-.2 30.4-.7-9.2 12.7-19.1 24.8-29.6 36.4zm130.7 30c-.9 12.2-6.9 23.6-16.5 31.3-15.9 9.2-49.8-2.8-86.4-34.2-4.2-3.6-8.4-7.5-12.7-11.5 15.3-16.9 29.4-34.8 42.2-53.6 22.9-1.9 45.7-5.4 68.2-10.5 1 4.1 1.9 8.2 2.7 12.2 4.9 21.6 5.7 44.1 2.5 66.3zm18.2-107.5c-2.8.9-5.6 1.8-8.5 2.6-7-21.8-15.6-43.1-25.5-63.8 9.6-20.4 17.7-41.4 24.5-62.9 5.2 1.5 10.2 3.1 15 4.7 46.6 16 79.3 39.8 79.3 58 0 19.6-34.9 44.9-84.8 61.4zm-149.7-15c25.3 0 45.8-20.5 45.8-45.8s-20.5-45.8-45.8-45.8c-25.3 0-45.8 20.5-45.8 45.8s20.5 45.8 45.8 45.8z")
                            )
            ));
            radioElementAdvancedLayoutIcon.add(new RadioElementIcon(
                    new RadioItem("vue-option", "technologies", CssType.RadioAdvancedLayoutIcon),
                    new Label("VueJs", CssType.RadioAdvancedLayoutIcon, "vue-option").setSubText("An model–view front end JavaScript framework"),
                    new Svg().addClass("mb-2 text-success w-7 h-7").fill("currentColor").aria_hidden("true").xmlns("http://www.w3.org/2000/svg").viewBox("0 0 448 51")
                            .add(
                                    new SvgPath()
                                            .d("M356.9 64.3H280l-56 88.6-48-88.6H0L224 448 448 64.3h-91.1zm-301.2 32h53.8L224 294.5 338.4 96.3h53.8L224 384.5 55.7 96.3z")
                            )
            ));
            radioElementAdvancedLayoutIcon.add(new RadioElementIcon(
                    new RadioItem("angular-option", "technologies", CssType.RadioAdvancedLayoutIcon),
                    new Label("Angular", CssType.RadioAdvancedLayoutIcon, "angular-option").setSubText("A TypeScript-based web application framework"),
                    new Svg().addClass("mb-2 text-danger w-7 h-7").fill("currentColor").aria_hidden("true").xmlns("http://www.w3.org/2000/svg").viewBox("0 0 448 512")
                            .add(
                                    new SvgPath()
                                            .d("M185.7 268.1h76.2l-38.1-91.6-38.1 91.6zM223.8 32L16 106.4l31.8 275.7 176 97.9 176-97.9 31.8-275.7zM354 373.8h-48.6l-26.2-65.4H168.6l-26.2 65.4H93.7L223.8 81.5z")
                            )
            ));

            RadioAdvancedLayoutIcon radioAdvancedLayoutIcon = new RadioAdvancedLayoutIcon(radioElementAdvancedLayoutIcon);

            mainForm.add(h3);
            mainForm.add(radioManzana);
            mainForm.add(radioUva);
            mainForm.add(radioPera);
            mainForm.add(radioLink);
            mainForm.add(radioHelperSandia);
            mainForm.add(radioBorderLimon);
            mainForm.add(radioBorderPapaya);
            mainForm.add(new Hr());
            mainForm.add(rlgh);
            mainForm.add(radioListIdentificacion);
            mainForm.add(new Hr());
            mainForm.add(rhlghPais);
            mainForm.add(radioHorizontalListIdentificacion);

//            mainForm.add(radioDropdownButton);
//            mainForm.add(radioDropdownLenguajes);
            mainForm.add(gridRadioDropdwon);
            mainForm.add(new Hr());
            mainForm.add(gridRadioDropdwon2);
            mainForm.add(scriptFecth);
            mainForm.add(new Hr());
            mainForm.add(radioHeadDeportes);
            mainForm.add(radioInline);
            mainForm.add(new Hr());
            mainForm.add(radioTwoColumns);

            mainForm.add(new Hr());
            mainForm.add(radioHeadAdvancedLayout);
            mainForm.add(radioAdvancedLayout);
            mainForm.add(new Hr());
            mainForm.add(radioHeadAdvancedLayoutIcon);
            mainForm.add(radioAdvancedLayoutIcon);

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
