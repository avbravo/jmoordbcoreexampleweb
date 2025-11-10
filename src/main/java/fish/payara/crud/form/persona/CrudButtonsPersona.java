/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.crud.form.persona;

import com.jmoordb.core.ui.Button;
import com.jmoordb.core.ui.div.Div;
import com.jmoordb.core.ui.I;
import com.jmoordb.core.ui.Span;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.input.InputSearch;

/**
 *
 * @author avbravo
 */
public class CrudButtonsPersona implements WebComponent {

    @Override
    public String render() {
//        Tag div = new Tag("div").withClass("flex flex-wrap items-center justify-between gap-3 mb-4 p-4 dark:bg-gray-800 rounded-lg shadow-md");

        Div div = new Div().addClass("flex flex-wrap items-center justify-between gap-3 mb-4 p-4 dark:bg-gray-800 rounded-lg shadow-md");
        // --- Contenedor de Botones CRUD ---
        Div buttonGroup = new Div().addClass("flex gap-2");

//        Tag buttonGroup = new Tag("div").withClass("flex gap-2");
        // Crear
        // ⭐ CAMBIO 2: Cambiar la función 'onclick' a enviar el formulario
        Button createButton = new Button()
                .onClick("document.getElementById('persona-form').submit()")
                .addClass("btn btn-primary bg-blue-600 hover:bg-blue-700 text-white dark:bg-blue-700 dark:hover:bg-blue-600")
                .text("Crear");

// Actualizar
        Button updateButton = new Button()
                .onClick("handleUpdate()")
                .addClass("btn btn-warning bg-yellow-500 hover:bg-yellow-600 text-gray-900 dark:text-white dark:bg-yellow-600 dark:hover:bg-yellow-500")
                .text("Actualizar");
        // Eliminar
        Button deleteButton = new Button()
                .onClick("handleDelete()")
                .addClass("btn btn-danger bg-red-600 hover:bg-red-700 text-white dark:bg-red-700 dark:hover:bg-red-600")
                .text("Eliminar");

        buttonGroup.withChild(createButton).withChild(updateButton).withChild(deleteButton);
        div.withChild(buttonGroup);

        // --- Campo de Búsqueda ---
        Div searchGroup = new Div().addClass("relative w-full sm:w-1/3");
        InputSearch searchInput = new InputSearch()
                .placeholder("Buscar por nombre...")
                .id("searchInput")
                .onkeyup("handleSearch()")
                .addClass("w-full shadow appearance-none border rounded py-2 px-3 pl-10 text-gray-700 leading-tight focus:outline-none focus:shadow-outline dark:bg-gray-700 dark:border-gray-600 dark:text-white");

        // Ícono de búsqueda (simulado con un span)
        
        Span searchIcon = new Span().addClass("absolute inset-y-0 left-0 flex items-center pl-3")
                .add(new I().addClass("fas fa-search text-gray-500 dark:text-gray-400")); 
       
        
//        Tag searchIcon = new Tag("span").withClass("absolute inset-y-0 left-0 flex items-center pl-3")
//                .withChild(new Tag("i").withClass("fas fa-search text-gray-500 dark:text-gray-400")); // Asume FontAwesome

        searchGroup.withChild(searchInput).withChild(searchIcon);
        div.withChild(searchGroup);

        return div.render();
    }

}
