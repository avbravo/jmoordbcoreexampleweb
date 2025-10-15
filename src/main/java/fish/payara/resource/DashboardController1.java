/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.resource;

/**
 *
 * @author avbravo
 */


import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.dashboard.Footer;
import com.jmoordb.core.ui.dashboard.NavBar;
import com.jmoordb.core.ui.dashboard.SideBar;
import com.jmoordb.core.ui.grid.CardGrid;
import com.jmoordb.core.ui.grid.GridItem;
import com.jmoordb.core.ui.menu.MenuLink;
import com.jmoordb.core.ui.panel.Panel;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/dashboard1")
public class DashboardController1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String username = (String) session.getAttribute("usuario");
        
        // ⭐ 1. DETERMINAR FRAMEWORK CSS ACTUAL
        String cssFramework = (String) session.getAttribute("cssFramework");
        if (cssFramework == null || (!cssFramework.equals("tailwind") && !cssFramework.equals("bootstrap"))) {
            cssFramework = "bootstrap"; // Default
            session.setAttribute("cssFramework", cssFramework);
        }

        response.setContentType("text/html;charset=UTF-8");
        
        // 2. DEFINICIÓN DE DATOS Y MENÚS (Se mantiene)
        Map<String, List<MenuLink>> sidebarSections = Map.of(
            "MAIN NAVIGATION", List.of(
                new MenuLink("Dashboard", "/dashboard", true, "fas fa-tachometer-alt"),
                new MenuLink("Perfil", "/profile", false, "fas fa-user-circle")
            ),
            "REPORTES", List.of(
                new MenuLink("Ventas", "/reports/sales", false, "fas fa-chart-line"),
                new MenuLink("Inventario", "/reports/inventory", false, "fas fa-warehouse")
            )
        );
        
        // Datos de la tabla...
        List<String[]> tableData = List.of(
            new String[]{"John Doe", "Sales", "32"},
            new String[]{"Jane Smith", "Marketing", "28"}
        );

        // 3. CONSTRUCCIÓN DEL CONTENIDO PRINCIPAL (Usa las nuevas clases)
        
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
            
        // 3.2. Grid de Tarjetas (Requiere request)
        WebComponent grid = new CardGrid(List.of(
            new GridItem("Users", "1,250", "fas fa-users", request),
            new GridItem("Revenue", "$5,500", "fas fa-dollar-sign", request),
            new GridItem("Orders", "532", "fas fa-shopping-cart", request),
            new GridItem("Tickets", "12", "fas fa-ticket-alt", request)
        ), 4, request);
        
        // 3.3. Contenido del Panel
        WebComponent mainPanelContent = new Tag("div")
            .withChild(grid)
            .withChild(new Tag("h4").withClass("mt-5").withText("Recent Activity"))
            .withChild(table);

        // 3.4. Panel (Requiere request)
        WebComponent mainPanel = new Panel("Dashboard Overview", mainPanelContent, request);

        // 4. CREACIÓN DE COMPONENTES DE ESTRUCTURA (Requieren request)
        NavBar navBar = new NavBar(username, request, Boolean.TRUE, 5);
        SideBar sideBar = new SideBar(request, sidebarSections);
        Footer footer = new Footer("© 2024 Modern Dashboard Framework.", request);

        // Contenido Principal
        Tag mainContent = new Tag("div").withClass("main-content sidebar-open")
            .withChild(new Tag("div").withClass("container-fluid pt-5 mt-3")
                .withChild(mainPanel));
        
        // 5. SCRIPTS DE LÓGICA (Se mantiene)
        String scriptContent = 
            "function setCssFramework(framework) {"
            + "  window.location.href = '" + request.getContextPath() + "/set-framework?framework=' + framework;"
            + "}"
            + "function toggleSidebar() {"
            + "  const sidebar = document.getElementById('mySidebar');"
            + "  const mainContent = document.querySelector('.main-content');"
            + "  sidebar.classList.toggle('active');"
            + "  if (window.innerWidth >= 768) { mainContent.classList.toggle('sidebar-open'); }"
            + "}"
            + "function toggleTheme() {"
            + "  const body = document.body;"
            + "  const icon = document.querySelector('.theme-toggle i');"
            + "  body.classList.toggle('dark-mode');"
            + "  const isDark = body.classList.contains('dark-mode');"
            + "  localStorage.setItem('theme', isDark ? 'dark' : 'light');"
            + "  icon.className = isDark ? 'fas fa-moon' : 'fas fa-sun';"
            + "}"
            + "document.addEventListener('DOMContentLoaded', function() {"
            + "  const savedTheme = localStorage.getItem('theme');"
            + "  const defaultTheme = 'dark';" 
            + "  const currentTheme = savedTheme || defaultTheme;" 
            + "  const icon = document.querySelector('.theme-toggle i');"

            + "  if (icon) { " 
            + "    if (currentTheme === 'dark') {"
            + "      document.body.classList.add('dark-mode');"
            + "      icon.className = 'fas fa-moon';" 
            + "    } else {"
            + "      icon.className = 'fas fa-sun';"  
            + "    }"
            + "  }"
            + "  const sidebar = document.getElementById('mySidebar');"
            + "  if (window.innerWidth >= 768 && sidebar) { sidebar.classList.add('active'); }"
            + "});"; 
            
        Tag scriptTag = new Tag("script").withText(scriptContent);

        // 6. CONSTRUIR BODY Y RENDERIZADO
        Tag body = new Tag("body")
            .withChild(navBar)
            .withChild(sideBar)
            .withChild(mainContent)
            .withChild(footer)
            .withChild(scriptTag) 
            // Siempre necesitamos el JS de Bootstrap para el dropdown de NavBar
            .withChild(new Tag("script").withAttribute("src", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"));
            
        // 7. INCLUSIÓN CONDICIONAL DEL CSS EXTERNO
        
        Tag frameworkLink;
        if (cssFramework.equals("tailwind")) {
            // Usar CDN de Tailwind (Se recomienda un build local para producción, pero usamos CDN para el ejemplo)
            frameworkLink = new Tag("script").withAttribute("src", "https://cdn.tailwindcss.com"); 
        } else {
            // Usar Bootstrap
            frameworkLink = new Tag("link").withAttribute("rel", "stylesheet")
                                            .withAttribute("href", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css");
        }

        String htmlCompleto = new Tag("html")
            .withChild(new Tag("head")
                .withChild(new Tag("meta").withAttribute("charset", "UTF-8"))
                .withChild(new Tag("meta").withAttribute("name", "viewport").withAttribute("content", "width=device-width, initial-scale=1"))
                .withChild(new Tag("title").withText("Modern Responsive Dashboard"))
                
                .withChild(frameworkLink) // Link/Script del framework CSS
                .withChild(new Tag("link").withAttribute("rel", "stylesheet").withAttribute("href", request.getContextPath() + "/css/main-styles.css")) // CSS Personalizado (Temas/Layout)
                .withChild(new Tag("link").withAttribute("rel", "stylesheet").withAttribute("href", "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"))
            )
            .withChild(body)
            .render();
            
        response.getWriter().write(htmlCompleto);
    }
}