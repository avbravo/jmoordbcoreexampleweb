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
import com.jmoordb.core.ui.model.WebModelSession;
import com.jmoordb.core.ui.panel.Panel;
import fish.payara.crud.form.ClienteService;
import fish.payara.crud.form.ClientesPage;
import fish.payara.dashboard.MenuSideBar;
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
    WebModelSession webModelSession = new WebModelSession();
    private final ClienteService clienteService = new ClienteService(); 
    private final int DEFAULT_PAGE_SIZE = 10;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
                webModelSession = webModelOfSession(request);
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String username = (String) session.getAttribute("username");
        String userRol = (String) session.getAttribute("userRol"); 
        if (userRol == null) userRol = "ADMIN"; 
        
        Map<String, List<MenuLink>> sidebarSections = MenuSideBar.getSidebarSections(
            this.getClass().getSimpleName(), 
            webModelSession
        );
//        Map<String, List<MenuLink>> sidebarSections = MenuSideBar.getSidebarSections(
//            this.getClass().getSimpleName(), 
//            username, 
//            userRol
//        );
        
        response.setContentType("text/html;charset=UTF-8");
        
        // ⭐ CLAVE: Obtener y preparar el contenido
        WebComponent clienteCrudContent = getClienteContent(request);
        
        WebComponent mainPanel = new Panel("Gestión de Clientes", clienteCrudContent, request);
        
        String htmlCompleto = DashboardLayout.buildPage(
            request, 
            username, 
            mainPanel, 
            sidebarSections, 
            "Clientes CRUD" ,
            "© 2024 Modern Dashboard Framework."
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
    
    
    
    // Dentro de com.jmoordb.controller.ClienteController.java

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // 1. Obtener los parámetros del formulario
        String idParam = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String latitude = request.getParameter("latitude");
        String longitudeParam = request.getParameter("longitude");
        
        // 2. Convertir y validar (Manejo básico de errores)
        Long id = 0L;
        Double monto = 0.0;
        Double longitude = 0.0;
        
        try {
            // El ID es 0 o null si es una nueva creación
            if (idParam != null && !idParam.isEmpty()) {
                id = Long.parseLong(idParam);
            }
            if (latitude != null) monto = Double.parseDouble(latitude);
            if (longitudeParam != null) longitude = Double.parseDouble(longitudeParam);
        } catch (NumberFormatException e) {
            // Manejo de error: podrías redirigir a la misma página con un mensaje de error
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos numéricos inválidos.");
            return;
        }

        // 3. Crear el objeto Cliente
        Cliente cliente = new Cliente(id, nombre, monto, longitude);
        
        // 4. Determinar la operación (Crear o Actualizar)
        if (id == 0L) {
            // ⭐ OPERACIÓN DE CREACIÓN
          Cliente savedCliente=  clienteService.save(cliente);
            String redirectUrl = request.getContextPath() + "/clientes?status=saved&id=" + savedCliente.getId();
    response.sendRedirect(redirectUrl);

        } else {
            // OPERACIÓN DE ACTUALIZACIÓN (Asumimos que el mismo botón manejará la actualización en el futuro)
            // Por ahora, solo queremos que funcione la creación.
            clienteService.save(cliente);
        }

        // 5. Redirigir al usuario a la página principal del CRUD (para ver la lista actualizada)
        response.sendRedirect(request.getContextPath() + "/clientes");
    }
    
      public WebModelSession webModelOfSession(HttpServletRequest request) {
        WebModelSession webModel = new WebModelSession();
        try {
            webModel.setIsAuthentication(Boolean.FALSE);
            webModel.setHasAuthorization(Boolean.FALSE);
            HttpSession session = request.getSession(false);
            if (session == null) {
                return webModel;
            }
            webModel.setUsername((String) session.getAttribute("username"));
            webModel.setName((String) session.getAttribute("name"));
            if (session.getAttribute("iduser").toString() == null || session.getAttribute("iduser").toString().equals("")) {
                webModel.setIduser(0L);
            } else {
                webModel.setIduser(Long.parseLong(session.getAttribute("iduser").toString()));
            }

            webModel.setUserRol((String) session.getAttribute("userRol"));

            if (session.getAttribute("idrol").toString() == null || session.getAttribute("idrol").toString().equals("")) {
                webModel.setIdRol(0L);
            } else {
                webModel.setIdRol(Long.parseLong(session.getAttribute("idrol").toString()));
            }

           

        } catch (Exception e) {
            System.out.println("\t");
        }
        return webModel;
    }
    
}