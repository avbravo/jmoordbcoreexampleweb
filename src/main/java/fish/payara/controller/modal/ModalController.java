package fish.payara.controller.modal;

import com.jmoordb.core.ui.Div;
import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.WebController;
import com.jmoordb.core.ui.alert.NotificationModal;
import com.jmoordb.core.ui.buttons.Button;
import com.jmoordb.core.ui.Script;
import com.jmoordb.core.ui.dashboard.DashboardLayout;
import com.jmoordb.core.ui.model.WebModel;
import com.jmoordb.core.ui.panel.Panel;
import fish.payara.dashboard.MenuSideBar;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/modal"})
public class ModalController extends HttpServlet implements WebController {

    WebModel webModel = new WebModel();
    List<Tag> headers = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /**
         * Valida los roles
         */
        if(!(webModel = webModelOfSession(request)).getIsAuthentication()){
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
                        "Modal",
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

            Button buttonInfo = new Button("Mostrar Modal")
                    .color("blue")
                    .onClick("openInfoModalEvent()");

            Script scriptCloseInfoModal = new Script()
                    .closeModal("infoModal", "openInfoModalEvent");

            Button buttonError = new Button("Error")
                    .color("red")
                    .onClick("openErrorModal()");

            Script scriptCloseError = new Script().closeModal("errorModal", "openErrorModal");

// 3. CONSTRUCCIÓN DEL CONTENIDO PRINCIPAL 
            WebComponent webContent = new Div().withClass("p-8 min-h-screen flex flex-col items-center justify-center bg-gray-100 dark:bg-gray-900")
                    .withClass(webModel.getIsTailwind() ? "space-y-4" : "") // Añadir espaciado de Tailwind
                    .withChild(infoModal)
                    .withChild(buttonInfo)
                    .withChild(scriptCloseInfoModal)
                    .withChild(errorModal)
                    .withChild(buttonError)
                    .withChild(scriptCloseError);

            // Se anade modalTaildwindConfiguration al header para el modal mediante un Script para otros casos no es necesario
            headers = new ArrayList<>();
            headers.add(new Script().modalTailwindConfig());

            mainPanel = new Panel("Modal", webContent, request);
        } catch (Exception e) {
            System.out.println("\t content() " + e.getLocalizedMessage());
        }
        return mainPanel;
    }
    // </editor-fold>
}
