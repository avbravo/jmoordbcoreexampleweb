/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.view;

import com.jmoordb.core.ui.A;
import com.jmoordb.core.ui.div.Div;
import com.jmoordb.core.ui.Form;
import com.jmoordb.core.ui.Image;
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
@Path("image-view") // ⭐ Define la URL final: /api/profile-view
@RequestScoped
public class ImageView extends JettraView {

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
                "Image View",
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
            Form formContent = new Form();
            formContent.add(
                    new Div().withClass("space-y-12")
                            .add(
                                    new A().href("login").text("Image as a Link")
                                            .add(
                                                    new Image().src("https://www.w3schools.com/images/w3schools_green.jpg")
                                                            .alt("HTML Tutorial")
                                                            .style("width:42px;height:42px;")
                                            )
                            )
            );

            WebComponent webContent
                    = new Div().withClass("p-8 min-h-screen flex flex-col items-center justify-center bg-gray-100 dark:bg-gray-900")
                            .withClass(webModelSession.getIsTailwind() ? "space-y-4" : "") // Añadir espaciado de Tailwind
                            .add(formContent);

            mainContent = new Panel("Image View", webContent, request);
        } catch (Exception e) {
            System.out.println("\t content() " + e.getLocalizedMessage());
        }
        return mainContent;
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
