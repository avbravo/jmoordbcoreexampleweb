/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.controller;

/**
 *
 * @author avbravo
 */


import fish.payara.dashboard.MenuService;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.dashboard.DashboardLayout;
import com.jmoordb.core.ui.grid.CardGrid;
import com.jmoordb.core.ui.grid.GridItem;
import com.jmoordb.core.ui.menu.MenuLink;
import com.jmoordb.core.ui.panel.Panel;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/dashboard")
public class DashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String username = (String) session.getAttribute("usuario");
        
        // ⭐ 1. Obtener Framework CSS (necesario para construir contenido específico)
        String cssFramework = (String) session.getAttribute("cssFramework");
        if (cssFramework == null || (!cssFramework.equals("tailwind") && !cssFramework.equals("bootstrap"))) {
//            cssFramework = "bootstrap";
            cssFramework = "tailwind";
            session.setAttribute("cssFramework", cssFramework);
        }
        
        response.setContentType("text/html;charset=UTF-8");
        
         // ⭐ SIMULACIÓN DE ROL (DEBE VENIR DE LA SESIÓN O DB)
        String userRol = (String) session.getAttribute("rol"); // Asumimos que el rol está en sesión
        if (userRol == null) userRol = "ADMIN"; // Valor por defecto para prueba

        // ⭐ 2. DEFINICIÓN DE MENÚS SIMPLIFICADA
        // Obtener la lista de menús desde el nuevo servicio.
        Map<String, List<MenuLink>> sidebarSections = MenuService.getSidebarSections(
            this.getClass().getSimpleName(), // Pasa "DashboardController"
            username, 
            userRol
        );
        
        
//        // ⭐ 2. DEFINICIÓN DE DATOS Y MENÚS (Estructura de la SideBar)
//        Map<String, List<MenuLink>> sidebarSections = Map.of(
//            "MAIN NAVIGATION", List.of(
//                new MenuLink("Dashboard", "/dashboard", true, "fas fa-tachometer-alt"),
//                new MenuLink("Perfil", "/profile", false, "fas fa-user-circle")
//            ),
//            "REPORTES", List.of(
//                new MenuLink("Ventas", "/reports/sales", false, "fas fa-chart-line"),
//                new MenuLink("Inventario", "/reports/inventory", false, "fas fa-warehouse")
//            )
//        );
//        
        List<String[]> tableData = List.of(
            new String[]{"John Doe", "Sales", "32"},
            new String[]{"Jane Smith", "Marketing", "28"}
        );

        // ⭐ 3. CONSTRUCCIÓN DEL CONTENIDO PRINCIPAL (ESPECÍFICO DEL DASHBOARD)
        
        // 3.1. Tabla
        String tableClasses = "table " + ("tailwind".equals(cssFramework) ? "w-full text-left" : "table-striped");
        WebComponent table = new Tag("table").withClass(tableClasses)
            .withChild(new Tag("thead").withChild(new Tag("tr")
                .withChild(new Tag("th").withText("Name"))
                .withChild(new Tag("th").withText("Department"))
                .withChild(new Tag("th").withText("Age"))))
            .withChild(new Tag("tbody")
                .withChild(new Tag("tr").withChild(new Tag("td").withText(tableData.get(0)[0])).withChild(new Tag("td").withText(tableData.get(0)[1])).withChild(new Tag("td").withText(tableData.get(0)[2])))
                .withChild(new Tag("tr").withChild(new Tag("td").withText(tableData.get(1)[0])).withChild(new Tag("td").withText(tableData.get(1)[1])).withChild(new Tag("td").withText(tableData.get(1)[2])))
            );
            
        // 3.2. Grid de Tarjetas
        WebComponent grid = new CardGrid(List.of(
            new GridItem("Users", "1,250", "fas fa-users", request),
            new GridItem("Revenue", "$5,500", "fas fa-dollar-sign", request),
            new GridItem("Orders", "532", "fas fa-shopping-cart", request),
            new GridItem("Tickets", "12", "fas fa-ticket-alt", request)
        ), 4, request);
        
        // 3.3. Contenido del Panel (Contenido Principal)
        WebComponent mainPanelContent = new Tag("div")
            .withChild(grid)
            .withChild(new Tag("h4").withClass("mt-5").withText("Recent Activity"))
            .withChild(table);

        // 3.4. Panel (Contenedor del contenido específico)
        WebComponent mainPanel = new Panel("Dashboard Overview", mainPanelContent, request);
        
        
        // ⭐ 4. RENDERIZADO FINAL: Se utiliza la clase DashboardLayout
        String htmlCompleto = DashboardLayout.buildPage(
                request, 
                username, 
                mainPanel, // Contenido específico que se inyecta
                sidebarSections, // Estructura del menú
                "Modern Responsive Dashboard" // Título
        );
            
        response.getWriter().write(htmlCompleto);
    }
}