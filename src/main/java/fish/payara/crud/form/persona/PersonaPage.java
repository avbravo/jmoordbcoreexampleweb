/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.crud.form.persona;



import com.jmoordb.core.ui.div.Div;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.table.PaginatedTable;
import fish.payara.controller.table.TableDataPersonaConverter;

import fish.payara.model.Persona;
import java.util.List;
import java.util.Map;

public class PersonaPage implements WebComponent {

    private final List<Persona> personaList;
    private final Persona currentPersona; 
    private final int pageSize;
    private final int currentPage;
    private final long totalRecords;
    private final String paginationBaseUrl; 

    public PersonaPage(List<Persona> personaList, Persona currentPersona, 
                        int pageSize, int currentPage, long totalRecords, String paginationBaseUrl) {
        this.personaList = personaList;
        this.currentPersona = currentPersona;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalRecords = totalRecords;
        this.paginationBaseUrl = paginationBaseUrl;
    }

    @Override
    public String render() {
//        Tag mainContainer = new Tag("div").withClass("container mx-auto p-4");
Div mainContainer = new Div().addClass("container mx-auto p-4");
        // 1. Botones CRUD
        mainContainer.add(new CrudButtonsPersona());
        
        // 2. Formulario de Persona
        Tag formPanel = new Tag("div").withClass("bg-white p-6 rounded-lg shadow-xl mb-6 dark:bg-gray-800");
        formPanel.withChild(new Tag("h2").withClass("text-xl font-bold mb-4 dark:text-white").withText("Registro de Persona"));
        formPanel.withChild(new PersonaForm(currentPersona, true)); 
        mainContainer.withChild(formPanel);

        // 3. Tabla de Registros (Paginada)
        List<String> headers = List.of("Nombre", "Latitude", "Longitude");
        Map<String, List<String>> tableData = TableDataPersonaConverter.convert(personaList); 
        
        Tag tablePanel = new Tag("div").withClass("bg-white p-6 rounded-lg shadow-xl dark:bg-gray-800");
        tablePanel.withChild(new Tag("h2").withClass("text-xl font-bold mb-4 dark:text-white").withText("Listado de Personas"));

        WebComponent paginatedTable = new PaginatedTable(
            headers, 
            tableData, 
            this.pageSize, 
            this.currentPage, 
            this.totalRecords, 
            this.paginationBaseUrl
        );
        tablePanel.withChild(paginatedTable);
        mainContainer.withChild(tablePanel);
        
        return mainContainer.render();
    }
}
