/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.crud.cliente;



import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.table.PaginatedTable;
import com.jmoordb.core.ui.buttons.CrudButtons;
import fish.payara.controller.table.TableDataClienteConverter;
import fish.payara.model.Cliente;
import java.util.List;
import java.util.Map;

public class ClientesPage implements WebComponent {

    private final List<Cliente> clienteList;
    private final Cliente currentCliente; 
    private final int pageSize;
    private final int currentPage;
    private final long totalRecords;
    private final String paginationBaseUrl; 

    public ClientesPage(List<Cliente> clienteList, Cliente currentCliente, 
                        int pageSize, int currentPage, long totalRecords, String paginationBaseUrl) {
        this.clienteList = clienteList;
        this.currentCliente = currentCliente;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalRecords = totalRecords;
        this.paginationBaseUrl = paginationBaseUrl;
    }

    @Override
    public String render() {
        Tag mainContainer = new Tag("div").withClass("container mx-auto p-4");

        // 1. Botones CRUD
        mainContainer.withChild(new CrudButtons());
        
        // 2. Formulario de Cliente
        Tag formPanel = new Tag("div").withClass("bg-white p-6 rounded-lg shadow-xl mb-6 dark:bg-gray-800");
        formPanel.withChild(new Tag("h2").withClass("text-xl font-bold mb-4 dark:text-white").withText("Registro de Cliente"));
        formPanel.withChild(new ClienteForm(currentCliente, true)); 
        mainContainer.withChild(formPanel);

        // 3. Tabla de Registros (Paginada)
        List<String> headers = List.of("Nombre", "Monto", "Longitude");
        Map<String, List<String>> tableData = TableDataClienteConverter.convert(clienteList); 
        
        Tag tablePanel = new Tag("div").withClass("bg-white p-6 rounded-lg shadow-xl dark:bg-gray-800");
        tablePanel.withChild(new Tag("h2").withClass("text-xl font-bold mb-4 dark:text-white").withText("Listado de Clientes"));

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
