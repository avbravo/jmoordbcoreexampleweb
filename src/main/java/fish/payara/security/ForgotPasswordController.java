/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.security;

/**
 *
 * @author avbravo
 */


import com.jmoordb.core.ui.alert.Alert;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.alert.AlertType;
import com.jmoordb.core.ui.login.PasswordRecovery;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        WebComponent messageAlert = null;
        String successParam = request.getParameter("success");

        if (successParam != null && "true".equals(successParam)) {
            // Mensaje de éxito después de un envío POST (simulado)
            messageAlert = new Alert(
                "📧 Si tu usuario existe, recibirás un email con instrucciones.", 
                    AlertType.SUCCESS, 
                true
            );
        }

        PasswordRecovery recoveryComponent = new PasswordRecovery(request.getContextPath(), messageAlert);
        
        response.getWriter().write(recoveryComponent.render());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String identity = request.getParameter("identity");
        
        // **AQUÍ IRÍA LA LÓGICA REAL**
        // 1. Buscar el usuario/email en la base de datos.
        // 2. Generar un token de recuperación.
        // 3. Enviar el email con el enlace.
        
        System.out.println("Solicitud de recuperación para: " + identity);
        
        // Por seguridad, siempre redirigimos a un mensaje genérico de éxito
        // para evitar que un atacante determine si un usuario existe o no.
        response.sendRedirect(request.getContextPath() + "/forgot-password?success=true");
    }
}