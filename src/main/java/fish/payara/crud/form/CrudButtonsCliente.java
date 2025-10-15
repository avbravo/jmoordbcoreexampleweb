/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.crud.form;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;

/**
 *
 * @author avbravo
 */
public class CrudButtonsCliente implements WebComponent {

    @Override
    public String render() {
        Tag div = new Tag("div").withClass("flex flex-wrap items-center justify-between gap-3 mb-4 p-4 dark:bg-gray-800 rounded-lg shadow-md");

        // --- Contenedor de Botones CRUD ---
        Tag buttonGroup = new Tag("div").withClass("flex gap-2");
        // Crear
    // ⭐ CAMBIO 2: Cambiar la función 'onclick' a enviar el formulario
    Tag createButton = new Tag("button").withAttribute("type", "button")
                                        .withAttribute("onclick", "document.getElementById('cliente-form').submit()")
                                        .withClass("btn btn-primary bg-blue-600 hover:bg-blue-700 text-white dark:bg-blue-700 dark:hover:bg-blue-600")
                                        .withText("Crear");
        
//        // Crear
//        Tag createButton = new Tag("button").withAttribute("type", "button")
//                                            .withAttribute("onclick", "handleCreate()")
//                                            .withClass("btn btn-primary bg-blue-600 hover:bg-blue-700 text-white dark:bg-blue-700 dark:hover:bg-blue-600")
//                                            .withText("Crear");
        // Actualizar
        Tag updateButton = new Tag("button").withAttribute("type", "button")
                                            .withAttribute("onclick", "handleUpdate()")
                                            .withClass("btn btn-warning bg-yellow-500 hover:bg-yellow-600 text-gray-900 dark:text-white dark:bg-yellow-600 dark:hover:bg-yellow-500")
                                            .withText("Actualizar");
        // Eliminar
        Tag deleteButton = new Tag("button").withAttribute("type", "button")
                                            .withAttribute("onclick", "handleDelete()")
                                            .withClass("btn btn-danger bg-red-600 hover:bg-red-700 text-white dark:bg-red-700 dark:hover:bg-red-600")
                                            .withText("Eliminar");

        buttonGroup.withChild(createButton).withChild(updateButton).withChild(deleteButton);
        div.withChild(buttonGroup);

        // --- Campo de Búsqueda ---
        Tag searchGroup = new Tag("div").withClass("relative w-full sm:w-1/3");
        Tag searchInput = new Tag("input").withAttribute("type", "search")
                                          .withAttribute("placeholder", "Buscar por nombre...")
                                          .withAttribute("id", "searchInput")
                                          .withAttribute("onkeyup", "handleSearch()")
                                          .withClass("w-full shadow appearance-none border rounded py-2 px-3 pl-10 text-gray-700 leading-tight focus:outline-none focus:shadow-outline dark:bg-gray-700 dark:border-gray-600 dark:text-white");
        
        // Ícono de búsqueda (simulado con un span)
        Tag searchIcon = new Tag("span").withClass("absolute inset-y-0 left-0 flex items-center pl-3")
                                         .withChild(new Tag("i").withClass("fas fa-search text-gray-500 dark:text-gray-400")); // Asume FontAwesome

        searchGroup.withChild(searchInput).withChild(searchIcon);
        div.withChild(searchGroup);

        return div.render();
    }
    
}
