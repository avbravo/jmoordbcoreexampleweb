package fish.payara.controller;

import com.jmoordb.core.ui.Div;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.WebController;
import com.jmoordb.core.ui.Button;
import com.jmoordb.core.ui.ButtonType;
import com.jmoordb.core.ui.Form;
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

@WebServlet(urlPatterns = {"/fetch"})
public class FetchController extends HttpServlet implements WebController {

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
                    new Div().withClass("space-y-12")
                            .add(
                                    new Button().text("Fetch").type(ButtonType.BUTTON).id("btnFetch")
                            )
                            .add(
                                    new Div().id("divCaracteres")
                            )
            );

            Script scriptFecth = new Script()
                    .code(javaScriptCode());

            WebComponent webContent = 
                    new Div().withClass("p-8 min-h-screen flex flex-col items-center justify-center bg-gray-100 dark:bg-gray-900")
                    .withClass(webModel.getIsTailwind() ? "space-y-4" : "") // Añadir espaciado de Tailwind
                    .add(formContent)
                    .add(scriptFecth);

            mainPanel = new Panel("Fecth", webContent, request);
        } catch (Exception e) {
            System.out.println("\t content() " + e.getLocalizedMessage());
        }
        return mainPanel;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String javaScriptCode()">
    public String javaScriptCode() {
        String result = "";
        try {
            result = """
                     const btn = document.getElementById("btnFetch");
                     const div = document.getElementById("divCaracteres");
                     btn.addEventListener('click',() => {
                           console.log('Fetch API');
                          fetch('https://rickandmortyapi.com/api/character')
                              .then((response) => response.json())
                              .then((data) => renderCaracteres(data));
                     });
                     
                     
                     function renderCaracteres(data) { // Cambié 'caracteres' por 'data' para mayor claridad.
                         // **AQUÍ ESTÁ LA CORRECCIÓN CLAVE:**
                     console.log(data);
                         // Acceder a la propiedad 'results' del objeto de respuesta de la API.
                         const characters = data.results; 
                         
                         // Verifica que 'characters' sea un array antes de iterar
                         if (Array.isArray(characters)) {
                             characters.forEach(ch => {
                                 // **CORRECCIÓN DE SINTAXIS:** Usar backticks (`) para el template literal
                                 // y `${...}` para incrustar la variable.
                                 div.innerHTML += `<img src="${ch.image}">`; 
                             });
                         } else {
                             console.error("Error: 'results' no es un array o no existe en la respuesta de la API.");
                         }
                      }                    
                     """;
        } catch (Exception e) {
            System.out.println("\t content() " + e.getLocalizedMessage());
        }
        return result;
    }
// </editor-fold>
}
