/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.controller;

/**
 *
 * @author avbravo
 */
import fish.payara.dashboard.MenuSideBar;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.dashboard.DashboardLayout;
import com.jmoordb.core.ui.table.CustomTable;
import com.jmoordb.core.ui.graph.MapComponent;
import com.jmoordb.core.ui.menu.MenuLink;
import com.jmoordb.core.ui.panel.Panel;
import com.jmoordb.core.ui.table.PaginatedTable;
import com.jmoordb.core.ui.table.TableBasic;
import fish.payara.controller.table.TableDataClienteConverter;
import fish.payara.controller.table.TableDataProductoConverter;
import fish.payara.graph.ProductSalesChart;
import fish.payara.model.Cliente;
import fish.payara.model.Producto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/reports/sales")
public class ReporteVentasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        // 1. Validar Sesión
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String username = (String) session.getAttribute("username");

        // ⭐ SIMULACIÓN DE ROL
        String userRol = (String) session.getAttribute("userRol");
        if (userRol == null) {
            userRol = "ADMIN"; // Asumimos un rol que tenga permiso para reportes
        }
        // 2. Definición del Menú y Marcado Activo
        Map<String, List<MenuLink>> sidebarSections = MenuSideBar.getSidebarSections(
                "SalesReportsController", // Nombre de la clase del controlador (para marcar activo)
                username,
                userRol
        );

        response.setContentType("text/html;charset=UTF-8");

        // 3. CONSTRUCCIÓN DEL CONTENIDO PRINCIPAL (Diseño de Reporte en GRID)
        // 1. Creación de datos de Productos (SIMULADOS)
        List<Producto> productosVendidos = List.of(
                new Producto("P001", "Laptop Pro", 1200.00, 45),
                new Producto("P002", "Monitor 27", 350.00, 90),
                new Producto("P003", "Mousepad XL", 25.00, 150),
                new Producto("P004", "Teclado Mecánico", 150.00, 60),
                new Producto("P005", "Webcam HD", 70.00, 30)
        );

        // 2. CONSTRUCCIÓN DEL CONTENIDO PRINCIPAL
        // ⭐ USO DEL COMPONENTE REAL DE GRÁFICA
        WebComponent chartComponent = new ProductSalesChart(productosVendidos);
        WebComponent graphPanel = new Panel("Ventas por Producto", chartComponent, request);

        // 3.1. Contenido Fila 1 (Gráfica y Productos)
        //  WebComponent graphPanel = new Panel("Ventas Mensuales", new Graph("Ventas"), request);
        WebComponent productsPanel = new Panel("Top Productos", new CustomTable("Productos"), request);
        /**
         * Productos
         */

        // 1. Datos de productos (ejemplo)
        List<Producto> productos = List.of(
                new Producto("P001", "Laptop", 1200.00, 45),
                new Producto("P002", "Monitor", 350.00, 90)
        );
// 2. Definir encabezados (debe coincidir con el orden en TableDataConverter)
        List<String> headersProducto = List.of("Producto", "Precio", "Cantidad");

        // 3. Convertir los productos a Map
        Map<String, List<String>> productoDataMap = TableDataProductoConverter.convert(productos);
// 4. Crear la tabla
        WebComponent editableTableProducto = new TableBasic(headersProducto, productoDataMap);

// 5. Crear el Panel (si es necesario)
        WebComponent tablePanelProducto = new Panel("Inventario Editable", editableTableProducto, request);

//Añade los elementos a la fila 1
        Tag row1 = new Tag("div").withClass("row g-4 mb-4") // g-4 para Tailwind/Bootstrap gap
                // Columna 1: Gráfica de Ventas (8 de 12 columnas)
                .withChild(new Tag("div").withClass("col-md-8").withChild(graphPanel))
                // Columna 2: Tabla de Productos (4 de 12 columnas)
                .withChild(new Tag("div").withClass("col-md-4").withChild(tablePanelProducto));

        // 3.2. Contenido Fila 2 (Clientes y Mapa)
        // Contenido de ejemplo para la tabla de clientes
        WebComponent clientListContent = new CustomTable("Clientes");
//        WebComponent clientPanel = new Panel("Listado de Clientes", clientListContent, request);

        /**
         * Clientes
         */
        // 1. Datos de productos (ejemplo)
        List<Cliente> clientes = List.of(
                new Cliente(1L, "Ana", 1200.00, 45.63332),
                new Cliente(2L, "Maria", 350.00, 90.624),
                // 15 Clientes Adicionales
                new Cliente(3L, "Carlos", 850.50, 75.12345),
                new Cliente(4L, "Laura", 210.99, 105.78901),
                new Cliente(5L, "Javier", 1500.00, 30.55555),
                new Cliente(6L, "Elena", 925.75, 68.98765),
                new Cliente(7L, "Pedro", 480.00, 15.43210),
                new Cliente(8L, "Sofia", 675.30, 99.88776),
                new Cliente(9L, "Miguel", 180.25, 40.10203),
                new Cliente(10L, "Carmen", 1010.10, 82.50607),
                new Cliente(11L, "David", 330.40, 55.91827),
                new Cliente(12L, "Paula", 1750.80, 22.33445),
                new Cliente(13L, "Ricardo", 512.90, 77.00112),
                new Cliente(14L, "Andrea", 99.99, 115.66778),
                new Cliente(15L, "Juan", 2000.00, 5.05050),
                new Cliente(16L, "Isabel", 640.70, 88.99001),
                new Cliente(17L, "Fernando", 130.00, 35.75382)
        );

        // 2. Definir encabezados (debe coincidir con el orden en TableDataConverter)
        List<String> headersCliente = List.of("Id", "Nombre", "Latitude", "Longitude");

        Map<String, List<String>> clienteDataMap = TableDataClienteConverter.convert(clientes);
// 4. Crear la tabla
        WebComponent editableTableCliente = new TableBasic(headersCliente, clienteDataMap);

        // 5. Crear el Panel (si es necesario)
        WebComponent tablePanelCliente = new Panel("Clientes", editableTableCliente, request);

        WebComponent mapPanel = new Panel("Sucursales", new MapComponent(), request);

        Tag row2 = new Tag("div").withClass("row g-4 mb-4")
                // Columna 1: Listado de Clientes (6 de 12 columnas)
                .withChild(new Tag("div").withClass("col-md-6").withChild(tablePanelCliente))
                // Columna 2: Mapa de Sucursales (6 de 12 columnas)
                .withChild(new Tag("div").withClass("col-md-6").withChild(mapPanel));
        /**
         * Tercera Fila
         *Clientes Paginados
         * Falta: Ajax de los eventos de otras paginas
         */

        int pageSize = 10; // Número fijo de elementos por página
// Obtener 'page' del request, por defecto 1
        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            // Mantener 1
        }

// ⭐ 1. Lógica de la Capa de Servicio (¡Esto debe ocurrir en el backend!)
// long totalRecords = productoService.countAll();
// Map<String, List<String>> pageData = productoService.findPage(currentPage, pageSize);
        long totalRecords = 15; // Simulamos 53 registros totales
        Map<String, List<String>> pageData = TableDataClienteConverter.convert(clientes.subList(0, 8)); // Simulamos obtener 10 de la DB

// URL base para los enlaces de paginación
        String baseUrl = request.getContextPath() + "/reports/sales?page=";
        // 2. Creación del componente de Tabla Paginada
        WebComponent tableClientePaginacion = new PaginatedTable(
                headersCliente,
                pageData, // Solo los 10 registros de la página actual
                pageSize,
                currentPage,
                totalRecords,
                baseUrl
        );
// 3. Añadir el componente al Panel
        WebComponent tablePanelClientePaginacion = new Panel("Listado de Clientes", tableClientePaginacion, request);
// mainContent.withChild(tablePanel);

// 2. Creación del componente de Tabla Paginada
        Tag row3 = new Tag("div").withClass("row g-4 mb-4")
                // Columna 1: Listado de Clientes (6 de 12 columnas)
                .withChild(new Tag("div").withClass("col-md-6").withChild(tablePanelClientePaginacion));

        /**
         * Contenedor Completo
         */
        // Contenedor principal que une las dos filas
        WebComponent mainContent = new Tag("div")
                .withChild(new Tag("h2").withText("Sales Report"))
                .withChild(row1)
                .withChild(row2)
                .withChild(row3);

        // 4. RENDERIZADO FINAL
        String htmlCompleto = DashboardLayout.buildPage(
                request,
                username,
                mainContent,
                sidebarSections,
                "Sales Report"
        );

        response.getWriter().write(htmlCompleto);
    }
}
