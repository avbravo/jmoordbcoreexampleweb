/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.resource.security;

/**
 *
 * @author avbravo
 */
  

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // 1. Obtener la sesión actual sin crear una nueva
        HttpSession session = request.getSession(false);
        
        // 2. Invalidar la sesión si existe
        if (session != null) {
            session.invalidate();
        }
        
        // 3. Redirigir al usuario a la página de login
        // Se añade un parámetro de éxito para mostrar un mensaje si es necesario
        response.sendRedirect(request.getContextPath() + "/login?logout=true");
    }
}