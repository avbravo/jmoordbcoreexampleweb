/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.crud.cliente;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.buttons.CrudButtons;
import com.jmoordb.core.ui.table.PaginatedTable;
import fish.payara.controller.table.TableDataClienteConverter;
import fish.payara.model.Cliente;
import java.util.List;
import java.util.Map;

/**
 *
 * @author avbravo
 */
public class ClientesPage1 implements WebComponent {

    private final List<Cliente> clienteList;
    private final Cliente currentCliente; // Cliente seleccionado o para crear

    // Parámetros de paginación simulados
    private int pageSize = 10;
    private int currentPage = 1;
    private long totalRecords = 17; // Del ejemplo anterior
    private String paginationBaseUrl = "/clientes?page=";

    public ClientesPage1(List<Cliente> clienteList, Cliente currentCliente) {
        this.clienteList = clienteList;
        this.currentCliente = currentCliente;
    }

    public ClientesPage1(List<Cliente> clienteList, Cliente currentCliente, int pageSize,
            int currentPage,
            long totalRecords,
            String paginationBaseUrl) {
        this.clienteList = clienteList;
        this.currentCliente = currentCliente;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalRecords = totalRecords;
    }

    @Override
    public String render() {
        Tag mainContainer = new Tag("div").withClass("container mx-auto p-4");

        // 1. --- Componente de Botones CRUD y Búsqueda ---
        mainContainer.withChild(new CrudButtons());

        // 2. --- Formulario de Cliente (Para Crear/Actualizar/Ver) ---
        // Se puede envolver en un Panel si se usa el componente Panel del Dashboard
        Tag formPanel = new Tag("div").withClass("bg-white p-6 rounded-lg shadow-xl mb-6 dark:bg-gray-800");
        formPanel.withChild(new Tag("h2").withClass("text-xl font-bold mb-4 dark:text-white").withText("Registro de Cliente"));

        // Asume isEditable=true para la demostración
        formPanel.withChild(new ClienteForm(currentCliente, true));
        mainContainer.withChild(formPanel);

        // 3. --- Tabla de Registros (PaginatedTable) ---
        // Conversión de datos
        List<String> headers = List.of("Nombre", "Monto", "Longitude");
        Map<String, List<String>> tableData = TableDataClienteConverter.convert(clienteList); // Usar el conversor

        Tag tablePanel = new Tag("div").withClass("bg-white p-6 rounded-lg shadow-xl dark:bg-gray-800");
        tablePanel.withChild(new Tag("h2").withClass("text-xl font-bold mb-4 dark:text-white").withText("Listado de Clientes"));

        WebComponent paginatedTable = new PaginatedTable(
                headers,
                tableData,
                pageSize,
                currentPage,
                totalRecords,
                paginationBaseUrl
        );
        tablePanel.withChild(paginatedTable);
        mainContainer.withChild(tablePanel);

        // NOTA: Se necesita JavaScript en la página final para que handleCreate/Update/Delete funcione.
        return mainContainer.render();
    }

}
