/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.crud.cliente;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import fish.payara.model.Cliente;

/**
 *
 * @author avbravo
 */
public class ClienteForm implements WebComponent {

    private final Cliente cliente;
    private final boolean isEditable;

    public ClienteForm(Cliente cliente, boolean isEditable) {
        this.cliente = cliente;
        this.isEditable = isEditable;
    }

    @Override
    public String render() {
        Tag form = new Tag("form").withAttribute("id", "cliente-form");

        // Clases de Tailwind/Bootstrap para divs de formulario adaptables
        String divClass = "mb-4";
        String labelClass = "block text-gray-700 text-sm font-bold mb-2 dark:text-gray-300";
        String inputClass = "shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline dark:bg-gray-700 dark:border-gray-600 dark:text-white";

        String readOnlyAttr = isEditable ? "" : "readonly";

        // --- Campo ID (Oculto o No Editable) ---
        form.withChild(
                new Tag("div").withClass(divClass).withChild(
                        new Tag("label").withClass(labelClass).withAttribute("for", "id").withText("ID")
                ).withChild(
                        new Tag("input").withAttribute("type", "text").withAttribute("id", "id")
                                .withAttribute("name", "id").withAttribute("value", String.valueOf(cliente.getId()))
                                .withClass(inputClass).withAttribute("readonly", "true") // ID siempre readonly
                )
        );

        // --- Campo Nombre ---
        form.withChild(
                new Tag("div").withClass(divClass).withChild(
                        new Tag("label").withClass(labelClass).withAttribute("for", "nombre").withText("Nombre")
                ).withChild(
                        new Tag("input").withAttribute("type", "text").withAttribute("id", "nombre")
                                .withAttribute("name", "nombre").withAttribute("value", cliente.getNombre())
                                .withClass(inputClass).withAttribute("required", "true").withAttribute(readOnlyAttr, "")
                )
        );

        // --- Campo Monto ---
        form.withChild(
                new Tag("div").withClass(divClass).withChild(
                        new Tag("label").withClass(labelClass).withAttribute("for", "monto").withText("Latitude")
                ).withChild(
                        new Tag("input").withAttribute("type", "number").withAttribute("id", "monto")
                                .withAttribute("name", "monto").withAttribute("value", String.valueOf(cliente.getLatitude()))
                                .withAttribute("step", "0.01").withClass(inputClass).withAttribute(readOnlyAttr, "")
                )
        );

        // --- Campo Longitude ---
        form.withChild(
                new Tag("div").withClass(divClass).withChild(
                        new Tag("label").withClass(labelClass).withAttribute("for", "longitude").withText("Longitude")
                ).withChild(
                        new Tag("input").withAttribute("type", "number").withAttribute("id", "longitude")
                                .withAttribute("name", "longitude").withAttribute("value", String.valueOf(cliente.getLongitude()))
                                .withAttribute("step", "0.00001").withClass(inputClass).withAttribute(readOnlyAttr, "")
                )
        );

        return form.render();
    }
}
