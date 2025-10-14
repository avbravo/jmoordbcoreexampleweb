/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.resource.security;

import com.jmoordb.core.ui.Alert;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.login.LoginExtended;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/loginextended")
public class LoginExtendServlet extends HttpServlet {

    // Credenciales de prueba
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "denver16";

    // Maneja la visualización del formulario de login (GET)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        WebComponent errorComponent = null;
        String errorParam = request.getParameter("error");
        String logoutParam = request.getParameter("logout"); // <-- NUEVA LÍNEA

        if (errorParam != null && "true".equals(errorParam)) {
            // Usamos la clase AlertComponent para mostrar el error
            errorComponent = new Alert(
                    "❌ Credenciales inválidas. Por favor, intentá de nuevo.",
                    "danger",
                    true
            );
        } // Mostrar mensaje de éxito si viene del logout
        else {
            if (logoutParam != null && "true".equals(logoutParam)) { // <-- NUEVA LÍNEA
                errorComponent = new Alert(
                        "✅ Sesión cerrada exitosamente. ¡Regrese pronto!",
                        "success",
                        true
                );
            }
        }
            // Instanciar y renderizar el componente de login
            LoginExtended login= new LoginExtended(request.getContextPath(), errorComponent);

            response.getWriter().write(login.render());
        }

        // Maneja el envío del formulario de login (POST)
        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response) throws IOException {

            // 1. Obtener los valores de las cajas de texto del formulario
            String submittedUsername = request.getParameter("username");
            String submittedPassword = request.getParameter("password");

            // 2. Lógica de autenticación: Compara los valores ingresados con los válidos
            boolean credentialsValid
                    = VALID_USERNAME.equals(submittedUsername) && VALID_PASSWORD.equals(submittedPassword);

            // 3. Control de Evento (Decisión)
            if (credentialsValid) {
                // A. Credenciales Válidas: Crear sesión y redirigir al dashboard
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", submittedUsername);
                session.setMaxInactiveInterval(30 * 60); // Sesión de 30 minutos

                response.sendRedirect(request.getContextPath() + "/dashboard");
            } else {
                // B. Credenciales Inválidas: Redirigir al GET con parámetro de error
                response.sendRedirect(request.getContextPath() + "/login?error=true");
            }
        }
    }
