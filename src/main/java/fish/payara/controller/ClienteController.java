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
import com.jmoordb.core.ui.menu.MenuLink; // Asumimos MenuLink
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

@WebServlet(urlPatterns = "/clientes")
public class ClienteController extends HttpServlet {

    private final ClienteService clienteService = new ClienteService(); 
    private final int DEFAULT_PAGE_SIZE = 10;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String username = (String) session.getAttribute("usuario");
        String userRol = (String) session.getAttribute("rol"); 
        if (userRol == null) userRol = "ADMIN"; 
        
        Map<String, List<MenuLink>> sidebarSections = MenuService.getSidebarSections(
            this.getClass().getSimpleName(), 
            username, 
            userRol
        );
        
        response.setContentType("text/html;charset=UTF-8");
        
        // ⭐ CLAVE: Obtener y preparar el contenido
        WebComponent clienteCrudContent = getClienteContent(request);
        
        WebComponent mainPanel = new Panel("Gestión de Clientes", clienteCrudContent, request);
        
        String htmlCompleto = DashboardLayout.buildPage(
            request, 
            username, 
            mainPanel, 
            sidebarSections, 
            "Clientes CRUD" 
        );
        
        response.getWriter().write(htmlCompleto);
    }
    
    // -------------------------------------------------------------------------
    // --- Lógica del Controlador para preparar los datos de la Pagina CRUD ---
    // -------------------------------------------------------------------------
    
    private WebComponent getClienteContent(HttpServletRequest request) {
        
        // --- 1. Parámetros de Paginación ---
        int pageSize = DEFAULT_PAGE_SIZE;
        int currentPage = 1;
        
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                // El navegador envía el nuevo número de página al hacer clic
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                // Mantener 1
            }
        }
        
        // --- 2. Obtener Datos y Paginación ---
        List<Cliente> allClientes = clienteService.findAll();
        long totalRecords = clienteService.count(); 
        
        // ⭐ Lógica de paginación en memoria (CRÍTICA)
        int startIndex = Math.max(0, (currentPage - 1) * pageSize);
        int endIndex = Math.min(startIndex + pageSize, (int) totalRecords);
        
        // Aseguramos que la página no esté vacía si el usuario pone un número alto manualmente
        if (startIndex >= endIndex && totalRecords > 0) {
            currentPage = 1;
            startIndex = 0;
            endIndex = Math.min(pageSize, (int) totalRecords);
        }
        
        List<Cliente> pageClients = allClientes.subList(startIndex, endIndex);
        
        // Cliente para el formulario
        Cliente currentCliente = getSelectedCliente(request, allClientes);

        // --- 3. URL Base para Paginación ---
        String paginationBaseUrl = request.getContextPath() + "/clientes?page="; 
        
        // --- 4. Crear el componente de página ---
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
    
    // El método getSelectedCliente se mantiene igual que en la respuesta anterior.
    private Cliente getSelectedCliente(HttpServletRequest request, List<Cliente> allClients) {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                Long selectedId = Long.parseLong(idParam);
                return allClients.stream()
                        .filter(c -> c.getId().equals(selectedId))
                        .findFirst()
                        .orElseGet(() -> new Cliente(0L, "", 0.0, 0.0));
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return new Cliente(0L, "", 0.0, 0.0);
    }
    
    // @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) { ... }
}