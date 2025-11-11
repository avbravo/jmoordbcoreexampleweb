/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.view;

import com.jmoordb.core.ui.Button;
import com.jmoordb.core.ui.ButtonType;
import com.jmoordb.core.ui.div.Div;
import com.jmoordb.core.ui.form.Form;
import com.jmoordb.core.ui.Script;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.dashboard.DashboardLayout;
import com.jmoordb.core.ui.grid.CardGrid;
import com.jmoordb.core.ui.grid.GridItem;
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
@Path("dashboard") // ⭐ Define la URL final: /api/profile-view
@RequestScoped
public class DashboardView extends JettraView {

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
                "Dashboard View",
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

             List<String[]> tableData = List.of(
            new String[]{"John Doe", "Sales", "32"},
            new String[]{"Jane Smith", "Marketing", "28"}
        );

        // ⭐ 3. CONSTRUCCIÓN DEL CONTENIDO PRINCIPAL (ESPECÍFICO DEL DASHBOARD)
        
        // 3.1. Tabla
        String tableClasses = "table " + ("tailwind".equals(webModelSession.getCssFramework()) ? "w-full text-left" : "table-striped");
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
   

            mainContent = new Panel("Dashboard", mainPanelContent, request);
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
