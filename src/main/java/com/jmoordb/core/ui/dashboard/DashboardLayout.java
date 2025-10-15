/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.dashboard;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.menu.MenuLink;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @author avbravo
 */
public class DashboardLayout {

// ⭐ La lógica de ensamblaje se extrae aquí.
    public static String buildPage(
            HttpServletRequest request,
            String username,
            WebComponent mainContent,
            Map<String, List<MenuLink>> sidebarSections,
            String title) {

        // 1. OBTENER ESTADO ACTUAL Y CONFIGURACIONES
        String contextPath = request.getContextPath();
        String cssFramework = (String) request.getSession().getAttribute("cssFramework");
        if (cssFramework == null || (!cssFramework.equals("tailwind") && !cssFramework.equals("bootstrap"))) {
            cssFramework = "bootstrap";
        }
        boolean isTailwind = "tailwind".equals(cssFramework);

        // 2. CREACIÓN DE COMPONENTES DE ESTRUCTURA
        // (Estos componentes son universales para cualquier página del dashboard)
        // Asumiendo que las notificaciones son siempre TRUE y el conteo es 5 para el ejemplo
        NavBar navBar = new NavBar(username, request, Boolean.TRUE, 5);
        SideBar sideBar = new SideBar(request, sidebarSections);
        Footer footer = new Footer("© 2024 Modern Dashboard Framework.", request);

        // 3. SCRIPTS DE LÓGICA (Se mantienen y se inyectan en el body)
        String scriptContent
                = "function setCssFramework(framework) {"
                + "  window.location.href = '" + contextPath + "/set-framework?framework=' + framework;"
                + "}"
                // ... (Resto de las funciones toggleSidebar, toggleTheme, DOMContentLoaded) ...
                // ... (Por brevedad, se omite el resto de tu código JavaScript) ...
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

        // 4. CONSTRUIR BODY
        Tag body = new Tag("body");
        if (cssFramework.equals("bootstrap")) {
            body.withClass("bootstrap-mode");
        }

        // El mainContent ahora es el cuerpo del dashboard que se inyecta
        Tag dashboardContent = new Tag("div").withClass("main-content sidebar-open")
                .withChild(new Tag("div").withClass("container-fluid pt-5 mt-3")
                        .withChild(mainContent));

        body.withChild(navBar)
                .withChild(sideBar)
                .withChild(dashboardContent)
                .withChild(footer)
                // ⭐ 1. Cargar la librería Chart.js
                .withChild(new Tag("script").withAttribute("src", "https://cdn.jsdelivr.net/npm/chart.js@4.4.3/dist/chart.umd.min.js"))
                // ⭐ 2. Cargar tu función de inicialización de Chart.js
                .withChild(new Tag("script").withAttribute("src", contextPath + "/js/chart-setup.js"))
                .withChild(scriptTag)
                .withChild(new Tag("script").withAttribute("src", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"));

        // 5. INCLUSIÓN CONDICIONAL DEL CSS EXTERNO
        Tag frameworkLink;
        if (isTailwind) {
            frameworkLink = new Tag("script").withAttribute("src", "https://cdn.tailwindcss.com");
        } else {
            frameworkLink = new Tag("link").withAttribute("rel", "stylesheet")
                    .withAttribute("href", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css");
        }

        // 6. ENSAMBLAJE FINAL
        return new Tag("html")
                .withChild(new Tag("head")
                        .withChild(new Tag("meta").withAttribute("charset", "UTF-8"))
                        .withChild(new Tag("meta").withAttribute("name", "viewport").withAttribute("content", "width=device-width, initial-scale=1"))
                        .withChild(new Tag("title").withText(title))
                        .withChild(frameworkLink)
                        // ⭐ 3. Cargar el CSS de la gráfica
                        .withChild(new Tag("link").withAttribute("rel", "stylesheet").withAttribute("href", contextPath + "/css/chart-styles.css"))
                        .withChild(new Tag("link").withAttribute("rel", "stylesheet").withAttribute("href", contextPath + "/css/main-styles.css"))
                        .withChild(new Tag("link").withAttribute("rel", "stylesheet").withAttribute("href", "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"))
                )
                .withChild(body)
                .render();
    }
}
