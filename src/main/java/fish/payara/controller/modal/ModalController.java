package fish.payara.controller.modal;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.WebController;
import com.jmoordb.core.ui.dashboard.DashboardLayout;
import com.jmoordb.core.ui.menu.MenuLink;
import com.jmoordb.core.ui.model.WebModel;
import com.jmoordb.core.ui.panel.Panel;
import fish.payara.dashboard.MenuSideBar;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/modal"})
public class ModalController extends HttpServlet implements WebController {
  WebModel webModel = new WebModel();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        webModel = webModelOfSession(request);
        
        if (!webModel.getIsAuthentication()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
            // 2. Obtiene los elementos del menu
        Map<String, List<MenuLink>> sidebarSections = MenuSideBar.getSidebarSections(
                this.getClass().getSimpleName(),
                webModel.getUsername(),
                webModel.getUserRol()
        );

        


        String htmlCompleto = DashboardLayout.buildPage(
                request,
               webModel.getUsername(),
                content(request), // Contenido específico que se inyecta
                sidebarSections, // Estructura del menú
                "User Profile"
        );
response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(htmlCompleto);
    }

    // <editor-fold defaultstate="collapsed" desc="WebComponent content(HttpServletRequest request)">
    @Override
    public WebComponent content(HttpServletRequest request) {
        WebComponent mainPanel = null;
        try {
// 3. CONSTRUCCIÓN DEL CONTENIDO PRINCIPAL (ESPECÍFICO DEL PERFIL)
            // Contenido de ejemplo para el panel de perfil. Usamos las clases condicionales.
            WebComponent profileContent = new Tag("div")
                    .withClass(webModel.getIsTailwind() ? "space-y-4" : "") // Añadir espaciado de Tailwind
                    .withChild(new Tag("p").withText("Username: " + webModel.getUsername()))
                    .withChild(new Tag("p").withText("Email: " + webModel.getUsername().toLowerCase() + "@example.com"))
                    .withChild(new Tag("p").withText("Role: Administrator"))
                    .withChild(new Tag("hr").withClass(webModel.getIsTailwind() ? "my-4 border-gray-300 dark:border-gray-600" : ""))
                    .withChild(new Tag("button").withClass(webModel.getPrimaryBtnClass()).withText("MODAL"))
                    .withChild(new Tag("button").withClass(webModel.getSecondaryBtnClass()).withText("Change Password"));

            // Panel que contiene la información del perfil
            // NOTA: La clase Panel debe manejar también su estilo interiormente usando el framework de la request.
            mainPanel = new Panel("User Profile", profileContent, request);
        } catch (Exception e) {
            System.out.println("\t content() " + e.getLocalizedMessage());
        }
        return mainPanel;
    }
    // </editor-fold>
}
