/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.controller.modal;

/**
 *
 * @author avbravo
 */


import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.alert.NotificationModal;
import com.jmoordb.core.ui.dashboard.DashboardLayout;
import com.jmoordb.core.ui.menu.MenuLink; // Asumimos MenuLink
import com.jmoordb.core.ui.panel.Panel;
import com.jmoordb.core.ui.script.ScriptUtil;
import fish.payara.crud.form.ClienteService;
import fish.payara.crud.form.ClientesPage;
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

@WebServlet(urlPatterns = "/modal")
public class ModalDashboardController extends HttpServlet {

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
          // 1. Crear la instancia del NotificationModal
        WebComponent infoModal = new NotificationModal(
                "myTestModal",
                "Prueba de Notificación",
                "Este es un mensaje de prueba con nivel de Información. Puedes usarlo para éxito o mensajes generales.",
                "Informacion" // Probar con "Error", "Advertencia", "Critico"
        );
        
        // 2. Crear el botón de activación
        Tag activationButton = new Tag("button")
                .withAttribute("onclick", "openTestModal()")
                .withClass("px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600 dark:bg-blue-700 dark:hover:bg-blue-800")
                .withText("Mostrar Modal de Prueba");
        
        // 3. Crear el contenedor principal (donde se ubica el botón)
        Tag mainContent = new Tag("div").withClass("p-8 min-h-screen flex flex-col items-center justify-center bg-gray-100 dark:bg-gray-900")
                .withChild(new Tag("h1").withClass("text-3xl font-bold mb-8 text-gray-900 dark:text-white").withText("Test de Componente Modal"))
                .withChild(activationButton);


        // Cerrar el modal
        Tag scriptTag = new Tag("script").withText(ScriptUtil.closeModal("myTestModal","openTestModal" ));
        // ⭐ CLAVE: Obtener y preparar el contenido
         // 6. Configuración de Tailwind CSS y Dark Mode
        Tag head = new Tag("head")
                .withChild(new Tag("meta").withAttribute("charset", "UTF-8"))
                .withChild(new Tag("meta").withAttribute("name", "viewport").withAttribute("content", "width=device-width, initial-scale=1.0"))
                .withChild(new Tag("title").withText("Modal Test"))
                .withChild(new Tag("script").withAttribute("src", "https://cdn.tailwindcss.com"));
        
        // Aseguramos que Tailwind esté configurado para el modo oscuro con 'class'
        String tailwindConfig = "<script>tailwind.config = { darkMode: 'class', theme: { extend: {}, }, }</script>";
        head.withChild(new Tag("script").withText(tailwindConfig));
        
        WebComponent clienteCrudContent = getClienteContent(request);
        
        WebComponent mainPanel = new Panel("Gestión de Clientes", clienteCrudContent, request);
        
        String htmlCompleto = DashboardLayout.buildPage(
            request, 
            username, 
            mainPanel, 
            sidebarSections, 
            "Modal" 
        );
        
        response.getWriter().write(htmlCompleto);
    }
    
    
 

   
}