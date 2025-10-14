/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.resource;

/**
 *
 * @author avbravo
 */


import com.jmoordb.core.ui.Alert;
import com.jmoordb.core.ui.FooterComponentOld;
import com.jmoordb.core.ui.MenuLink;
import com.jmoordb.core.ui.NavbarComponentOld;
import com.jmoordb.core.ui.SidebarComponentOld;
import com.jmoordb.core.ui.TableEditable;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.panel.CardGrid;
import com.jmoordb.core.ui.panel.GridItem;
// ... (Otros imports necesarios)

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/dashboard-old")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // 1. **SEGURIDAD:** Verificar Sesión
        HttpSession session = request.getSession(false);
        String username = (session != null && session.getAttribute("usuario") != null) ? 
                            (String) session.getAttribute("usuario") : "Guest";

        if (session == null || !"admin".equals(username)) { 
            response.sendRedirect(request.getContextPath() + "/login");
            return; 
        }

        response.setContentType("text/html;charset=UTF-8");
        
        // 2. **DEFINICIÓN DINÁMICA DE MENÚS**
        List<MenuLink> topMenu = List.of(
            new MenuLink("Home", "/dashboard", true, "fas fa-home")
        );
        
        List<MenuLink> sidebarLinks = List.of(
            new MenuLink("Dashboard", "/dashboard", true, "fas fa-tachometer-alt"),
            new MenuLink("Reports", "/reports", false, "fas fa-chart-line"),
            new MenuLink("Settings", "/settings", false, "fas fa-cog")
        );
        
        // 3. **CREACIÓN DE COMPONENTES**
        
        // Navbar (Barra superior)
        NavbarComponentOld navbar = new NavbarComponentOld(username, request, topMenu);
        
        // Sidebar (Panel izquierdo)
        SidebarComponentOld sidebar = new SidebarComponentOld(request, sidebarLinks);
        
        // Footer (Pie de página)
        FooterComponentOld footer = new FooterComponentOld("© 2024 Java Pure Framework. Todos los derechos reservados.");
        
        // Contenido Central (Usando CardGridComponent)
       // 3.1. **DATOS DE LA TABLA** 📊
        
        // Encabezados de la tabla (Headers)
        List<String> tableHeaders = List.of("ID Producto", "Nombre", "Cantidad", "Precio");
        
        // Datos de las filas: Map<ID_Fila, List<Valores_Columnas>>
        Map<String, List<String>> tableData = Map.of(
            "LAP-001", List.of("Laptop Ultra", "15", "$1,200.00"), // ID: LAP-001, Datos: [Nombre, Cantidad, Precio]
            "MON-045", List.of("Monitor Curvo", "25", "$450.00"),
            "KB-102", List.of("Teclado Mecánico", "50", "$95.00"),
            "MS-500", List.of("Ratón Gaming", "100", "$40.00")
        );
        
        // 3.2. **INSTANCIAR LA TABLA CON DATOS**
        
        // Instanciación de la tabla con los encabezados y datos
        TableEditable inventoryTable = new TableEditable(tableHeaders, tableData); 

        
        Alert welcomeAlert = new Alert("Welcome back, " + username + "!", "success", true);
        
        List<GridItem> contentItems = List.of(
            new GridItem(welcomeAlert, 12, 12, 12), // Alerta en una fila completa
            new GridItem(new Alert("Sales: $5,400", "info", false), 4, 6, 3), // Card pequeño
            new GridItem(new Alert("Users: 45", "info", false), 4, 6, 3),   // Card pequeño
            new GridItem(new Tag("div").withChild(inventoryTable), 12, 12, 6)             // Tabla
        );
        CardGrid mainPanel = new CardGrid("Main Dashboard Content", contentItems);

        
        // 4. **ENSAMBLAJE DE LA ESTRUCTURA HTML**
        Tag body = new Tag("body");
        
        // Contenido Principal (Panel Central)
        Tag mainContent = new Tag("div").withClass("main-content")
            .withChild(new Tag("div").withClass("container-fluid mt-4")
                .withChild(mainPanel));
        
        // Scripts de Lógica (Theme Toggle y Sidebar)
        String scriptSidebar = "/* ... script de sidebar ... */"; // Usar el código de 3.2
        String scriptTheme = "/* ... script de tema ... */";     // Usar el código de 3.2
        
        // Construir el BODY
        body.withChild(sidebar)
            .withChild(navbar)
            .withChild(mainContent)
            .withChild(footer)
            .withChild(new Tag("script").withText(scriptSidebar + "\n" + scriptTheme))
            .withChild(new Tag("script").withAttribute("src", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"))
            .withChild(new Tag("script").withAttribute("src", "https://kit.fontawesome.com/your-kit-id.js").withAttribute("crossorigin", "anonymous")); // Íconos
            
        // Renderizado Final
        String htmlCompleto = new Tag("html")
            .withChild(new Tag("head")
                .withChild(new Tag("meta").withAttribute("charset", "UTF-8"))
                .withChild(new Tag("meta").withAttribute("name", "viewport").withAttribute("content", "width=device-width, initial-scale=1"))
                .withChild(new Tag("title").withText("Responsive Java Dashboard"))
                .withChild(new Tag("link").withAttribute("rel", "stylesheet").withAttribute("href", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"))
                .withChild(new Tag("link").withAttribute("rel", "stylesheet").withAttribute("href", request.getContextPath() + "/css/main-styles.css"))
            )
            .withChild(body)
            .render();
            
        response.getWriter().write(htmlCompleto);
    }
}