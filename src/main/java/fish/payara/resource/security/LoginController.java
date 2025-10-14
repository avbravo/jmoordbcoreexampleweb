/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.resource.security;

import com.jmoordb.core.ui.Alert;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.login.LoginSimple;
import com.jmoordb.core.ui.login.LoginAdvanced;
import com.jmoordb.core.ui.login.LoginExtended;
import fish.payara.config.ConfigurationProperties;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author avbravo
 */
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="Inject">
//    @Inject
//    @ConfigProperty(name = "login.style")
//    private String loginStyle;
    @Inject
    ConfigurationProperties configurationProperties;

// </editor-fold>
    // Credenciales de prueba
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "denver16";
    private static final String VALID_ROLE = "supervisor"; // Rol esperado para el usuario tester

    // <editor-fold defaultstate="collapsed" desc="Roles disponibles (Value HTML -> Texto visible)">
    private static final Map<String, String> ROLES_LIST = Map.of(
            "admin", "Administrador",
            "supervisor", "Supervisor de Ventas",
            "standard", "Usuario Estándar"
    );

// </editor-fold>
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
        WebComponent login = null;

        System.out.println("loginStyle " + configurationProperties.getLoginStyle());
        switch (configurationProperties.getLoginStyle()) {
            case "login-simple":
                login = new LoginSimple(request.getContextPath(), errorComponent, configurationProperties.getLoginTitle());

                break;
            case "login-extend":
                login = new LoginExtended(request.getContextPath(), errorComponent);
                break;
            case "login-advanced":
                login = new LoginAdvanced(request.getContextPath(), errorComponent, ROLES_LIST);
                break;

        }
        // Instanciar y renderizar el componente de login

        response.getWriter().write(login.render());
    }

    // Maneja el envío del formulario de login (POST)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("\t => doPost.....");
        // 1. Obtener los valores de las cajas de texto del formulario
        String submittedUsername = request.getParameter("username");
        String submittedPassword = request.getParameter("password");
        String submittedRole = "";
        if (configurationProperties.getLoginStyle().equals("login-advanced")) {
            submittedRole = request.getParameter("userRole"); // <-- NUEVO
        }

        System.out.println("\t username " + submittedUsername + " password " + submittedPassword + " role " + submittedRole);

        // 2. Lógica de autenticación: Compara los valores ingresados con los válidos
        boolean credentialsValid = Boolean.FALSE;

        switch (configurationProperties.getLoginStyle()) {
            case "login-simple":
                credentialsValid
                        = VALID_USERNAME.equals(submittedUsername) && VALID_PASSWORD.equals(submittedPassword);

                break;
            case "login-extend":
                credentialsValid
                        = VALID_USERNAME.equals(submittedUsername) && VALID_PASSWORD.equals(submittedPassword);
                break;
            case "login-advanced":
                credentialsValid
                        = VALID_USERNAME.equals(submittedUsername)
                        && VALID_PASSWORD.equals(submittedPassword)
                        && VALID_ROLE.equals(submittedRole);
                break;

        }

        // 3. Control de Evento (Decisión)
        if (credentialsValid) {
            // A. Credenciales Válidas: Crear sesión y redirigir al dashboard
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", submittedUsername);
            session.setMaxInactiveInterval(configurationProperties.getSessionMinutosExpiracion()); // Sesión de 30 minutos

            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            // B. Credenciales Inválidas: Redirigir al GET con parámetro de error
            response.sendRedirect(request.getContextPath() + "/login?error=true");
        }
    }
}
