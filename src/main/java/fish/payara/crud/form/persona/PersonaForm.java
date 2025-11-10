/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.crud.form.persona;

import com.jmoordb.core.ui.div.Div;
import com.jmoordb.core.ui.Form;
import com.jmoordb.core.ui.Label;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.input.InputText;
import fish.payara.config.ConfigurationProperties;
import fish.payara.model.Persona;
import jakarta.inject.Inject;

/**
 *
 * @author avbravo
 */
public class PersonaForm implements WebComponent {

 

    private final Persona persona;
    private final boolean isEditable;

    public PersonaForm(Persona persona, boolean isEditable) {
        this.persona = persona;
        this.isEditable = isEditable;
    }

    @Override
    public String render() {
//        Tag form = new Tag("form").withAttribute("id", "cliente-form");

        Form form = new Form()
                .id("persona-form")
                .action("/jmoordbcoreexampleweb" + "/api/persona-view") //Apunta WebServlet (/api/view-cliente")
                .method("POST");

        String divClass = "mb-4";

        String labelClass = "block text-gray-700 text-sm font-bold mb-2 dark:text-white";
        String inputClass = "shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline dark:bg-gray-700 dark:border-gray-600 dark:text-white";

        String readOnlyAttr = isEditable ? "" : "readonly";

        form.add(
                new Div().addClass(divClass).add(
                        new Label().addClass(labelClass).forField("id").text("ID")
                )
                        .add(
                                new InputText().id("id").name("id").value(String.valueOf(persona.getId()))
                                        .addClass(inputClass).readonly(true)
                        )
        );

        form.withChild(
                new Tag("div").withClass(divClass).withChild(
                        new Tag("label").withClass(labelClass).withAttribute("for", "nombre").withText("Nombre")
                ).withChild(
                        new Tag("input").withAttribute("type", "text").withAttribute("id", "nombre")
                                .withAttribute("name", "nombre").withAttribute("value", persona.getNombre())
                                .withClass(inputClass).withAttribute("required", "true").withAttribute(readOnlyAttr, "")
                )
        );

        // --- Campo Monto ---
        form.withChild(
                new Tag("div").withClass(divClass).withChild(
                        new Tag("label").withClass(labelClass).withAttribute("for", "monto").withText("Latitude")
                ).withChild(
                        new Tag("input").withAttribute("type", "number").withAttribute("id", "monto")
                                .withAttribute("name", "monto").withAttribute("value", String.valueOf(persona.getLatitude()))
                                .withAttribute("step", "0.01").withClass(inputClass).withAttribute(readOnlyAttr, "")
                )
        );

        // --- Campo Longitude ---
        form.withChild(
                new Tag("div").withClass(divClass).withChild(
                        new Tag("label").withClass(labelClass).withAttribute("for", "longitude").withText("Longitude")
                ).withChild(
                        new Tag("input").withAttribute("type", "number").withAttribute("id", "longitude")
                                .withAttribute("name", "longitude").withAttribute("value", String.valueOf(persona.getLongitude()))
                                .withAttribute("step", "0.00001").withClass(inputClass).withAttribute(readOnlyAttr, "")
                )
        );

        return form.render();
    }
}
