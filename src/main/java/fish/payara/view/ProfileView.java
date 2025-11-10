/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.view;

import com.jmoordb.core.ui.Button;
import com.jmoordb.core.ui.ButtonType;
import com.jmoordb.core.ui.div.Div;
import com.jmoordb.core.ui.Form;
import com.jmoordb.core.ui.Script;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.dashboard.DashboardLayout;
import com.jmoordb.core.ui.jettra.JettraView;
import com.jmoordb.core.ui.model.WebModelSession;
import com.jmoordb.core.ui.panel.Panel;
import fish.payara.config.ConfigurationProperties;
import fish.payara.dashboard.MenuSideBar;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Path;
import java.util.ArrayList;

import java.util.List;

/*
 * @author avbravo
 */
@Path("profile-view") // ⭐ Define la URL final: /api/profile-view
@RequestScoped
public class ProfileView extends JettraView {

    // <editor-fold defaultstate="collapsed" desc="attributes()">
    WebModelSession webModelSession = new WebModelSession();
    List<Tag> headers = new ArrayList<>();
    @Inject
    ConfigurationProperties configurationProperties;
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String init()">
    @Override
    protected String init() {

        webModelSession = webModelOfSession(request);

        return DashboardLayout.buildPage(
                request,
                webModelSession.getUsername(),
                content(request),
                MenuSideBar.getSidebarSections(
                        this.getClass().getSimpleName(),
                        webModelSession
                ),
                "Profile View",
                configurationProperties.getDashboardFooterText() + " | " + webModelSession.getUserRol(),
                headers
        );

    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="WebComponent content(HttpServletRequest request)">

    @Override
    public WebComponent content(HttpServletRequest request) {
        WebComponent mainContent = null;
        try {


            String cssFramework =webModelSession.getCssFramework();
            boolean isTailwind = "tailwind".equals(cssFramework);

            // ⭐ 2. Definir Clases CSS Condicionales
            String primaryBtnClass = isTailwind
                    ? "bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded shadow-md transition duration-150"
                    : "btn btn-primary";

            String secondaryBtnClass = isTailwind
                    ? "bg-gray-400 hover:bg-gray-500 text-gray-800 font-bold py-2 px-4 rounded shadow-md ml-2 transition duration-150"
                    : "btn btn-secondary ms-2";

            WebComponent profileContent = new Tag("div")
                    .withClass(isTailwind ? "space-y-4" : "") // Añadir espaciado de Tailwind
                    .withChild(new Tag("p").withText("Username: " + webModelSession.getUsername()))
                    .withChild(new Tag("p").withText("Email: " + webModelSession.getUsername().toLowerCase() + "@example.com"))
                    .withChild(new Tag("p").withText("Role: Administrator"))
                    .withChild(new Tag("hr").withClass(isTailwind ? "my-4 border-gray-300 dark:border-gray-600" : ""))
                    .withChild(new Tag("button").withClass(primaryBtnClass).withText("Edit Profile"))
                    .withChild(new Tag("button").withClass(secondaryBtnClass).withText("Change Password"));

            mainContent = new Panel("Profile", profileContent, request);
        } catch (Exception e) {
            System.out.println("\t content() " + e.getLocalizedMessage());
        }
        return mainContent;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String javaScriptCode()">
    @Override
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
