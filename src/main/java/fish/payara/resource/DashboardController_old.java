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
import com.jmoordb.core.ui.MenuLink;
import com.jmoordb.core.ui.TableEditable;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.dashboard.Footer;
import com.jmoordb.core.ui.dashboard.NavBar;
import com.jmoordb.core.ui.dashboard.SideBar;
import com.jmoordb.core.ui.panel.CardGrid;
import com.jmoordb.core.ui.panel.GridItem;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/dashboard_old2")
public class DashboardController_old extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // 1. **VERIFICACIÓN DE SESIÓN**
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String username = (String) session.getAttribute("usuario");
        
        response.setContentType("text/html;charset=UTF-8");
        
        // 2. **DEFINICIÓN DE DATOS Y MENÚS**
        
        
        
        
        
        // 2. **DEFINICIÓN DE DATOS Y MENÚS**
        
// Menús Laterales (para SideBar)
Map<String, List<MenuLink>> sidebarSections = Map.of(
    "MAIN NAVIGATION", List.of(
        // Asegúrate de que MenuLink es un objeto válido
        new MenuLink("Dashboard", "/dashboard", true, "fas fa-tachometer-alt"),
        new MenuLink("Perfil", "/profile", false, "fas fa-user-circle")
    ),
    "REPORTES", List.of(
        new MenuLink("Ventas", "/reports/sales", false, "fas fa-chart-line"),
        new MenuLink("Inventario", "/reports/inventory", false, "fas fa-warehouse")
    ),
    "", List.of( // Sección vacía para crear solo un separador
        new MenuLink("Configuración", "/settings", false, "fas fa-cog")
    )
);

// ...
SideBar sideBar = new SideBar(request, sidebarSections);
        
        
        
        
        
        
        
        
        
        
        
        // Datos de la Tabla (para TableEditable)
        List<String> tableHeaders = List.of("ID Producto", "Nombre", "Cantidad", "Precio");
        Map<String, List<String>> tableData = Map.of(
            "LAP-001", List.of("Laptop Ultra", "15", "$1,200.00"),
            "MON-045", List.of("Monitor Curvo", "25", "$450.00"),
            "KB-102", List.of("Teclado Mecánico", "50", "$95.00")
        );
        TableEditable inventoryTable = new TableEditable(tableHeaders, tableData); 
        
        // 3. **CREACIÓN DE COMPONENTES DE ESTRUCTURA**
        NavBar navBar = new NavBar(username, request);

        Footer footer = new Footer("© 2024 Modern Dashboard Framework. All rights reserved.");
        
        // 4. **CONTENIDO CENTRAL (PANEL RESPONSIVO)**
        
        WebComponent welcomeAlert = new Alert("Bienvenido de vuelta, " + username + "!", "success", true);

        // Agregamos componentes al sistema de cuadrícula (GridItem)
        List<GridItem> contentItems = List.of(
            new GridItem(welcomeAlert, 12, 12, 12),
            new GridItem(new Alert("KPI Ventas: $5,400", "info", false), 12, 6, 3), 
            new GridItem(new Alert("KPI Usuarios: 45", "info", false), 12, 6, 3),   
            new GridItem(new Tag("div").withChild(inventoryTable), 12, 12, 6)             
        );
        CardGrid mainPanel = new CardGrid("Inventario y Estadísticas", contentItems);
        
        
        // 5. **ENSAMBLAJE FINAL DE LA PÁGINA**

        // Contenido Principal (que contendrá el mainPanel)
        Tag mainContent = new Tag("div").withClass("main-content sidebar-open")
            .withChild(new Tag("div").withClass("container-fluid pt-5 mt-3") // Añadido padding superior para navbar fija
                .withChild(mainPanel));
        
        
        
//        
//        // Scripts de Lógica (Sidebar Toggle y Theme Toggle)
//        String script = 
//            // Script de Sidebar (Menú Hamburguesa) - Sin cambios
//            "function toggleSidebar() {"
//            + "  const sidebar = document.getElementById('mySidebar');"
//            + "  const mainContent = document.querySelector('.main-content');"
//            + "  sidebar.classList.toggle('active');"
//            + "  if (window.innerWidth > 768) { mainContent.classList.toggle('sidebar-open'); }"
//            + "}"
//            // Script de Tema (Dark/White Mode) - Sin cambios en toggleTheme
//            + "function toggleTheme() {"
//            + "  const body = document.body;"
//            + "  const icon = document.querySelector('.theme-toggle i');"
//            + "  body.classList.toggle('dark-mode');"
//            + "  const isDark = body.classList.contains('dark-mode');"
//            + "  localStorage.setItem('theme', isDark ? 'dark' : 'light');"
//            + "  icon.className = isDark ? 'fas fa-moon' : 'fas fa-sun';"
//            + "}"
//            // Cargar tema al inicio: Ahora carga Dark Mode si no hay preferencia guardada.
//            + "(function loadTheme() {"
//            + "  // Obtener la preferencia guardada, si no existe, usar 'dark' como defecto."
//            + "  const savedTheme = localStorage.getItem('theme');"
//            + "  const defaultTheme = 'dark';" // <-- NUEVO: Tema por defecto es 'dark'
//            + "  const currentTheme = savedTheme || defaultTheme;" 
//            + "  const icon = document.querySelector('.theme-toggle i');"
//
//            + "  if (currentTheme === 'dark') {"
//            + "    document.body.classList.add('dark-mode');"
//            + "    icon.className = 'fas fa-moon';" // Ícono de la luna para Dark Mode
//            + "  } else {"
//            + "    icon.className = 'fas fa-sun';"  // Ícono del sol para Light Mode
//            + "  }"
//            
//            // Abrir sidebar en desktop por defecto (Lógica de sidebar se mantiene)
//            + "  if (window.innerWidth > 768) { document.getElementById('mySidebar').classList.add('active'); }"
//            + "})();";
//            
//        
//// Scripts de Lógica (Sidebar Toggle y Theme Toggle)
//String script = 
//    // toggleSidebar y toggleTheme no necesitan ser modificados
//    "function toggleSidebar() {"
//    + "  const sidebar = document.getElementById('mySidebar');"
//    + "  const mainContent = document.querySelector('.main-content');"
//    + "  sidebar.classList.toggle('active');"
//    + "  if (window.innerWidth >= 768) { mainContent.classList.toggle('sidebar-open'); }"
//    + "}"
//    + "function toggleTheme() {"
//    + "  const body = document.body;"
//    + "  const icon = document.querySelector('.theme-toggle i');" // Este selector es correcto
//    + "  body.classList.toggle('dark-mode');"
//    + "  const isDark = body.classList.contains('dark-mode');"
//    + "  localStorage.setItem('theme', isDark ? 'dark' : 'light');"
//    + "  icon.className = isDark ? 'fas fa-moon' : 'fas fa-sun';"
//    + "}"
//    
//    // 💡 CORRECCIÓN CLAVE: Envolver la función loadTheme en DOMContentLoaded
//    + "document.addEventListener('DOMContentLoaded', function() {"
//    + "  // 1. Lógica de inicialización del Tema (loadTheme) "
//    + "  const savedTheme = localStorage.getItem('theme');"
//    + "  const defaultTheme = 'dark';" 
//    + "  const currentTheme = savedTheme || defaultTheme;" 
//    + "  const icon = document.querySelector('.theme-toggle i');" // ¡Ahora este elemento existe!
//
//    + "  if (icon) { " // Verificación de seguridad
//    + "    if (currentTheme === 'dark') {"
//    + "      document.body.classList.add('dark-mode');"
//    + "      icon.className = 'fas fa-moon';" 
//    + "    } else {"
//    + "      icon.className = 'fas fa-sun';"  
//    + "    }"
//    + "  }"
//    
//    // 2. Inicializar Sidebar (mover aquí para garantizar que 'mySidebar' exista)
//    + "  const sidebar = document.getElementById('mySidebar');"
//    + "  if (window.innerWidth >= 768 && sidebar) { sidebar.classList.add('active'); }"
//    
//    + "});"; // Cierre del listener

// Scripts de Lógica (toggleSidebar y toggleTheme)
        // **IMPORTANTE:** Definimos el contenido JS como texto sin los tags <script>
        String scriptContent = 
            // 1. Función toggleSidebar()
            "function toggleSidebar() {"
            + "  const sidebar = document.getElementById('mySidebar');"
            + "  const mainContent = document.querySelector('.main-content');"
            + "  sidebar.classList.toggle('active');"
            + "  if (window.innerWidth >= 768) { mainContent.classList.toggle('sidebar-open'); }"
            + "}"
            // 2. Función toggleTheme()
            + "function toggleTheme() {"
            + "  const body = document.body;"
            + "  const icon = document.querySelector('.theme-toggle i');"
            + "  body.classList.toggle('dark-mode');"
            + "  const isDark = body.classList.contains('dark-mode');"
            + "  localStorage.setItem('theme', isDark ? 'dark' : 'light');"
            + "  icon.className = isDark ? 'fas fa-moon' : 'fas fa-sun';"
            + "}"
            // 3. Lógica de inicialización (DOMContentLoaded)
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
            
        // Construir el TAG <script> con el contenido JS
        Tag scriptTag = new Tag("script").withText(scriptContent);


// Construir el BODY
        Tag body = new Tag("body")
            .withChild(navBar)
            .withChild(sideBar)
            .withChild(mainContent)
            .withChild(footer)
            .withChild(scriptTag)
            .withChild(new Tag("script").withAttribute("src", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"));
            
//        Tag body = new Tag("body")
//            .withChild(navBar)
//            .withChild(sideBar)
//            .withChild(mainContent)
//            .withChild(footer)
//            .withChild(new Tag("script").withText(script))
//            .withChild(new Tag("script").withAttribute("src", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"));
//            
        // Renderizado Final del HTML
        String htmlCompleto = new Tag("html")
            .withChild(new Tag("head")
                .withChild(new Tag("meta").withAttribute("charset", "UTF-8"))
                .withChild(new Tag("meta").withAttribute("name", "viewport").withAttribute("content", "width=device-width, initial-scale=1"))
                .withChild(new Tag("title").withText("Modern Responsive Dashboard"))
                .withChild(new Tag("link").withAttribute("rel", "stylesheet").withAttribute("href", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"))
                .withChild(new Tag("link").withAttribute("rel", "stylesheet").withAttribute("href", request.getContextPath() + "/css/main-styles.css")) // Tu CSS personalizado
                .withChild(new Tag("script").withAttribute("src", "https://kit.fontawesome.com/your-kit-id.js").withAttribute("crossorigin", "anonymous"))
                .withChild(new Tag("link").withAttribute("rel", "stylesheet").withAttribute("href", "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"))
                    
            )
            .withChild(body)
            .render();
            
        response.getWriter().write(htmlCompleto);
    }
}