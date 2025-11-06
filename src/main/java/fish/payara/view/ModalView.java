/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.view;

import com.jmoordb.core.ui.Button;
import com.jmoordb.core.ui.Div;
import com.jmoordb.core.ui.Script;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.alert.NotificationModal;
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
@Path("modal-view") // ⭐ Define la URL final: /api/profile-view
@RequestScoped
public class ModalView extends JettraView {
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
        headers.add(new Script().modalTailwindConfig());
        return DashboardLayout.buildPage(
                request,
                webModelSession.getUsername(),
                content(request),
                MenuSideBar.getSidebarSections(
                        this.getClass().getSimpleName(),
                        webModelSession.getUsername(),
                        webModelSession.getUserRol()
                ),
                "Modal View",
                configurationProperties.getDashboardFooterText() + " | " + webModelSession.getUserRol(),
                headers
        );

    }
// </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="protected WebComponent content(HttpServletRequest request)">

    @Override
    protected WebComponent content(HttpServletRequest request) {
        WebComponent mainContent = null;
        try {
            mainContent = new Tag("div").withText("Vista generada con JAX-RS y WebComponents para: " + webModelSession.getUsername());

            NotificationModal infoModal = new NotificationModal(
                    "infoModal",// 
                    "Prueba de Notificación",
                    "Este es un mensaje de prueba con nivel de Información. Puedes usarlo para éxito o mensajes generales.",
                    "Informacion" // Probar con "Error", "Advertencia", "Critico"
            );

            // Modal de Error (Rojo/X)
            NotificationModal errorModal = new NotificationModal(
                    "errorModal", // ID único
                    "Error Crítico",
                    "No se pudo conectar a la base de datos. Por favor, revisa la conexión.",
                    "Error"
            );

            Button buttonInfo = new Button()
                    .text("Mostrar Modal")
                    .color("blue")
                    .onClick("openInfoModalEvent()");

            Script scriptCloseInfoModal = new Script()
                    .closeModal("infoModal", "openInfoModalEvent");

            Button buttonError = new Button()
                    .text("Error")
                    .color("red")
                    .onClick("openErrorModal()");

            Script scriptCloseError = new Script().closeModal("errorModal", "openErrorModal");
            WebComponent webContent = new Div().withClass("p-8 min-h-screen flex flex-col items-center justify-center bg-gray-100 dark:bg-gray-900")
                    .withClass(webModelSession.getIsTailwind() ? "space-y-4" : "") // Añadir espaciado de Tailwind
                    .add(infoModal)
                    .add(buttonInfo)
                    .add(scriptCloseInfoModal)
                    .add(errorModal)
                    .add(buttonError)
                    .add(scriptCloseError);

            mainContent = new Panel("Fecth", webContent, request);
        } catch (Exception e) {
            System.out.println("\t content() " + e.getLocalizedMessage());
        }
        return mainContent;
    }
// </editor-fold>
}
