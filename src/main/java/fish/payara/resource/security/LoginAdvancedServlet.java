/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.resource.security;

/**
 *
 * @author avbravo
 */



import com.jmoordb.core.ui.Alert;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.login.LoginAdvanced;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/login-advanced")
public class LoginAdvancedServlet extends HttpServlet {
    
    // Roles disponibles (Value HTML -> Texto visible)
    private static final Map<String, String> ROLES_LIST = Map.of(
        "admin", "Administrador",
        "supervisor", "Supervisor de Ventas",
        "standard", "Usuario Estándar"
    );
    
    // Credenciales de prueba (debe coincidir el rol)
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "denver";
    private static final String VALID_ROLE = "supervisor"; // Rol esperado para el usuario tester

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        WebComponent errorComponent = null;
        String errorParam = request.getParameter("error");

        if (errorParam != null && "true".equals(errorParam)) {
            errorComponent = new Alert(
                "❌ Credenciales o Rol inválido.", 
                "danger", 
                true
            );
        }

        // 1. Instanciar y renderizar el componente avanzado
        LoginAdvanced loginComponent = new LoginAdvanced(request.getContextPath(), errorComponent, ROLES_LIST);
        
        response.getWriter().write(loginComponent.render());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // 1. Obtener los valores
        String submittedUsername = request.getParameter("username");
        String submittedPassword = request.getParameter("password");
        String submittedRole = request.getParameter("userRole"); // <-- NUEVO
        // 2. Lógica de autenticación: debe coincidir USER, PASS y ROL
        boolean credentialsValid = 
            VALID_USERNAME.equals(submittedUsername) && 
            VALID_PASSWORD.equals(submittedPassword) &&
            VALID_ROLE.equals(submittedRole);
        
        if (credentialsValid) {
            // Válido: Crear sesión
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", submittedUsername);
            session.setAttribute("rol", submittedRole); // Guardar el rol en la sesión
            
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            // Inválido: Redirigir con error
            response.sendRedirect(request.getContextPath() + "/login-advanced?error=true");
        }
    }
}