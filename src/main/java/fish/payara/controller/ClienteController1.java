/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.controller;

/**
 *
 * @author avbravo
 */


import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.dashboard.DashboardLayout;
import com.jmoordb.core.ui.menu.MenuLink; // Asumimos la existencia de tu MenuLink
import com.jmoordb.core.ui.panel.Panel;
import fish.payara.crud.cliente.ClienteService;
import fish.payara.crud.cliente.ClientesPage;
import fish.payara.dashboard.MenuService;
import fish.payara.model.Cliente;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/cliente/crudold")
public class ClienteController1 extends HttpServlet {

    private final ClienteService clienteService = new ClienteService(); 
    
    // Asumimos que la lógica de CREATE/UPDATE/DELETE se manejará en POST/PUT/DELETE
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        HttpSession session = request.getSession(false);
        // 1. Validar Sesión (Si tu aplicación requiere login)
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String username = (String) session.getAttribute("usuario");
        // ⭐ SIMULACIÓN DE ROL (Necesario para el menú)
        String userRol = (String) session.getAttribute("rol"); 
        if (userRol == null) userRol = "ADMIN"; 
        
        // 2. DEFINICIÓN DE MENÚS (Desde tu MenuService)
        Map<String, List<MenuLink>> sidebarSections = MenuService.getSidebarSections(
            this.getClass().getSimpleName(), // Pasa "ClienteController"
            username, 
            userRol
        );
        
        response.setContentType("text/html;charset=UTF-8");
        
        // 3. CONSTRUCCIÓN DEL CONTENIDO PRINCIPAL (CRUD de Clientes)
        WebComponent clienteCrudContent = getClienteContent(request);
        
        // Envolver el contenido en tu Panel para seguir el patrón del Dashboard
        WebComponent mainPanel = new Panel("Gestión de Clientes", clienteCrudContent, request);
        
        // 4. RENDERIZADO FINAL: Se utiliza la clase DashboardLayout
        String htmlCompleto = DashboardLayout.buildPage(
            request, 
            username, 
            mainPanel, // Contenido específico (Panel con formulario y tabla)
            sidebarSections, // Estructura del menú
            "Clientes CRUD" // Título de la página
        );
        
        response.getWriter().write(htmlCompleto);
    }
    
    // -------------------------------------------------------------------------
    // --- Lógica del Controlador para preparar los datos de la Pagina CRUD ---
    // -------------------------------------------------------------------------
    
    /**
     * Prepara el componente ClientesPage con todos los datos y la lógica de paginación.
     */
    private WebComponent getClienteContent(HttpServletRequest request) {
        
        // --- Parámetros de Paginación y Estado ---
        int pageSize = 10;
        int currentPage = 1;
        
        // Intenta obtener la página de la URL: /clientes?page=X
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                // Dejar currentPage en 1
            }
        }
        
        // --- Obtener Datos del Servicio (con Limit/Offset) ---
        
        // En un entorno real, el servicio manejaría la paginación a nivel de BD.
        List<Cliente> allClientes = clienteService.findAll();
        long totalRecords = allClientes.size(); 
        
        // Lógica de paginación en memoria (solo para la simulación)
        int startIndex = Math.max(0, (currentPage - 1) * pageSize);
        int endIndex = Math.min(startIndex + pageSize, (int) totalRecords);
        
        List<Cliente> pageClients = allClientes.subList(startIndex, endIndex);
        
        // Determinar el cliente actual para el formulario (si se está editando o creando)
        Cliente currentCliente = getSelectedCliente(request, allClientes);

        // --- URL Base para Paginación ---
        String paginationBaseUrl = request.getContextPath() + "/clientes?page="; 
        
        // --- Crear el componente principal de la página de clientes ---
        WebComponent clientesPageContent = new ClientesPage(
            pageClients, 
            currentCliente, 
            pageSize, 
            currentPage, 
            totalRecords, 
            paginationBaseUrl
        );

        return clientesPageContent;
    }
    
    /**
     * Determina qué cliente debe mostrarse en el formulario (nuevo o seleccionado).
     */
    private Cliente getSelectedCliente(HttpServletRequest request, List<Cliente> allClients) {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                Long selectedId = Long.parseLong(idParam);
                // Buscar el cliente para editar (si existe)
                return allClients.stream()
                        .filter(c -> c.getId().equals(selectedId))
                        .findFirst()
                        .orElseGet(() -> new Cliente(0L, "", 0.0, 0.0));
            } catch (NumberFormatException e) {
                // Manejar error de formato del ID
            }
        }
        // Retorna un objeto Cliente vacío para el modo "Crear"
        return new Cliente(0L, "", 0.0, 0.0);
    }
    
    // Opcional: Manejo de POST para operaciones CRUD
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Lógica para manejar la creación, actualización o eliminación enviada desde el formulario ClienteForm.
        // Se usarían los parámetros del request (id, nombre, monto, etc.)
        // response.sendRedirect(request.getContextPath() + "/clientes");
    }
}

/*
 * NOTA: Para que este código compile, debes asegurarte de que las siguientes clases existen y son accesibles:
 * - ClienteService (con método findAll)
 * - DashboardLayout (con método buildPage)
 * - MenuService (con método getSidebarSections)
 * - Panel, WebComponent, Tag, ClientesPage, Cliente
 * - MenuLink
 */
