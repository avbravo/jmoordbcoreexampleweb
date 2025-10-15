/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.resource;

/**
 *
 * @author avbravo
 */


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

// ⭐ MAPEO CORRECTO: La URL debe coincidir con la que usa el JavaScript
@WebServlet(urlPatterns = "/set-framework")
public class SetFrameworkServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String framework = request.getParameter("framework");
        HttpSession session = request.getSession();

        if (framework != null && (framework.equals("bootstrap") || framework.equals("tailwind"))) {
            // Guarda la preferencia en la sesión
            session.setAttribute("cssFramework", framework);
        }
        
        // Redirige de vuelta al dashboard para aplicar los nuevos estilos
        response.sendRedirect(request.getContextPath() + "/dashboard");
    }
}