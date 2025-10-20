package fish.payara.controller;

import com.jmoordb.core.ui.A;
import com.jmoordb.core.ui.Div;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.WebController;
import com.jmoordb.core.ui.Form;
import com.jmoordb.core.ui.Image;
import com.jmoordb.core.ui.Script;
import com.jmoordb.core.ui.dashboard.DashboardLayout;
import com.jmoordb.core.ui.model.WebModelSession;
import com.jmoordb.core.ui.panel.Panel;
import fish.payara.dashboard.MenuSideBar;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/image"})
public class ImageController extends HttpServlet implements WebController {

    WebModelSession webModel = new WebModelSession();
    List<Tag> headers = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /**
         * Valida los roles
         */
        if (!(webModel = webModelOfSession(request)).getIsAuthentication()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        /**
         * Construye los componentes mediante el DashboardLayout
         */
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(
                DashboardLayout.buildPage(
                        request,
                        webModel.getUsername(),
                        content(request), // Contenido específico que se inyecta
                        MenuSideBar.getSidebarSections(
                                this.getClass().getSimpleName(),
                                webModel.getUsername(),
                                webModel.getUserRol()
                        ), // Estructura del menú
                        "Fetch",
                        "© 2024 Modern Dashboard Framework.",
                        headers
                )
        );
    }

    // <editor-fold defaultstate="collapsed" desc="WebComponent content(HttpServletRequest request)">
    @Override
    public WebComponent content(HttpServletRequest request) {
        WebComponent mainPanel = null;
        try {

            Form formContent = new Form();
            formContent.add(
                    new Div().styleClass("space-y-12")
                            .add(
                                    new A().href("login").text("Image as a Link")
                                           .add(
                                           new Image().src("https://www.w3schools.com/images/w3schools_green.jpg")
                                                   .alt("HTML Tutorial")
                                                   .style("width:42px;height:42px;")
                                           )
                            )                           
            );
       

            WebComponent webContent = 
                    new Div().styleClass("p-8 min-h-screen flex flex-col items-center justify-center bg-gray-100 dark:bg-gray-900")
                    .withClass(webModel.getIsTailwind() ? "space-y-4" : "") // Añadir espaciado de Tailwind
                    .add(formContent);

            mainPanel = new Panel("Fecth", webContent, request);
        } catch (Exception e) {
            System.out.println("\t content() " + e.getLocalizedMessage());
        }
        return mainPanel;
    }
    // </editor-fold>


}
