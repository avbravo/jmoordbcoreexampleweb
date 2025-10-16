package fish.payara.controller.modal;

import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.alert.NotificationModal;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( urlPatterns = {"/modal-test"})
public class ModalVariosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // --- 1. Crear DOS instancias del NotificationModal (Reutilización) ---
        
        // Modal de Información (Azul/Check)
        WebComponent infoModal = new NotificationModal(
                "infoModal", // ID único
                "Operación Exitosa",
                "El proceso ha finalizado correctamente y los datos se guardaron.",
                "Informacion" 
        );
        
        // Modal de Error (Rojo/X)
        WebComponent errorModal = new NotificationModal(
                "errorModal", // ID único
                "Error Crítico",
                "No se pudo conectar a la base de datos. Por favor, revisa la conexión.",
                "Error" 
        );
        
        // --- 2. Crear los botones de activación ---
        Tag infoButton = new Tag("button")
                .withAttribute("onclick", "openModal('infoModal')") // Llama a la función JS con el ID
                .withClass("px-4 py-2 mr-4 bg-blue-500 text-white rounded hover:bg-blue-600 dark:bg-blue-700 dark:hover:bg-blue-800")
                .withText("Mostrar Información");

        Tag errorButton = new Tag("button")
                .withAttribute("onclick", "openModal('errorModal')") // Llama a la función JS con el ID
                .withClass("px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600 dark:bg-red-700 dark:hover:bg-red-800")
                .withText("Mostrar Error");
        
        // 3. Crear el contenedor principal
        Tag mainContent = new Tag("div").withClass("p-8 min-h-screen flex flex-col items-center justify-center bg-gray-100 dark:bg-gray-900")
                .withChild(new Tag("h1").withClass("text-3xl font-bold mb-8 text-gray-900 dark:text-white").withText("Test de Componente Modal Reutilizable"))
                .withChild(new Tag("div").withClass("flex").withChild(infoButton).withChild(errorButton));

        // 4. Crear el Script GENÉRICO para abrir y cerrar CUALQUIER modal
//        String scriptContent = 
//                  "function openModal(id) {"
//                + "  document.getElementById(id).classList.remove('hidden');"
//                + "}"
//                + "document.addEventListener('DOMContentLoaded', function() {"
//                + "  // Configuración de Dark Mode para el <html>"
//                + "  if (localStorage.getItem('theme') === 'dark') { document.documentElement.classList.add('dark'); }"
//                
//                + "  // Lógica para cerrar ambos modales usando un selector genérico basado en su ID"
//                + "  const modalIds = ['infoModal', 'errorModal'];"
//                + "  modalIds.forEach(id => {"
//                + "    const modal = document.getElementById(id);"
//                + "    const closeButton = document.getElementById(id + '-close');"
//                + "    if (closeButton) {"
//                + "      closeButton.addEventListener('click', function() {"
//                + "        modal.classList.add('hidden');"
//                + "      });"
//                + "    }"
//                + "  });"
//                + "});";
     String scriptContent = 
                  "function openTestModal() {"
                + "  document.getElementById('myTestModal').classList.remove('hidden');"
                + "}"
                + "document.addEventListener('DOMContentLoaded', function() {"
                + "  const modal = document.getElementById('myTestModal');"
                + "  const closeButton = document.getElementById('myTestModal-close');"
                + "  if (closeButton) {"
                + "    closeButton.addEventListener('click', function() {"
                + "      modal.classList.add('hidden');"
                + "    });"
                + "  }"
                + "});";

        Tag scriptTag = new Tag("script").withText(scriptContent);
        
        System.out.println("scriptContent "+scriptContent);
        
        // 5. Ensamblar la página HTML mínima
        // Incluir ambos modales en el body para que estén disponibles
        Tag body = new Tag("body")
                .withChild(mainContent)
                .withChild(infoModal) // Modal 1
                .withChild(errorModal); // Modal 2
//                .withChild(scriptTag);
        
        // 6. Configuración de Tailwind CSS
        Tag head = new Tag("head")
                .withChild(new Tag("meta").withAttribute("charset", "UTF-8"))
                .withChild(new Tag("meta").withAttribute("name", "viewport").withAttribute("content", "width=device-width, initial-scale=1.0"))
                .withChild(new Tag("title").withText("Modal Test Reusable"))
                // ⭐ SOLUCIÓN: Inyectar el script ANTES de que el body se cargue.
//        .withChild(scriptTag)
                .withChild(new Tag("script").withAttribute("src", "https://cdn.tailwindcss.com"));
        
        String tailwindConfig = "<script>tailwind.config = { darkMode: 'class', theme: { extend: {}, }, }</script>";
        head.withChild(new Tag("script").withText(tailwindConfig));

        // 7. Renderizado Final
        String html = new Tag("html")
                .withChild(head)
                .withChild(body)
                .render();

        response.getWriter().write(html);
    }
}