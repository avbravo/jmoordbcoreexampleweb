/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license- .txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.jettra;

import com.jmoordb.core.ui.Body;
import com.jmoordb.core.ui.Html;
import com.jmoordb.core.ui.Link;
import com.jmoordb.core.ui.WebComponent;
import com.jmoordb.core.ui.model.WebModelSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author avbravo
 */
//public abstract class JettraView implements WebController {
public abstract class JettraView {

    WebModelSession webModel = new WebModelSession();
    @Context
    protected HttpServletRequest request;

    @Context
    protected HttpServletResponse response;

    /**
     * El método principal para que las clases hijas generen contenido para GET.
     *
     * @return El String HTML completo a enviar al cliente.
     */
    protected abstract String init();

    
    
   protected abstract WebComponent content(HttpServletRequest request);
       
 protected abstract String javaScriptCode();
    
    /**
     * Endpoint GET que produce HTML y llama a handleGet. La anotación @Path("")
     * aquí significa que la URL es la misma que la clase hija.
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getHtmlView() {
        try {
            /**
             * Valida los roles
             */
            if (!(webModel = webModelOfSession(request)).getIsAuthentication()) {

                Html html = new Html().add(
                        new Body().add(new Link().href("<link href=\"https://cdn.jsdelivr.net/npm/flowbite@3.1.2/dist/flowbite.min.css\" rel=\"stylesheet\" />")
                        )
                );

                // response.sendRedirect(request.getContextPath() + "/login");
  
                String html2 = "<h1>No tiene credenciales para utilizar esta opción</h1><br>"
                        + "<div class=\"p-4 mb-4 text-sm text-red-800 rounded-lg bg-red-50 dark:bg-gray-800 dark:text-red-400\" role=\"alert\">\n"
                        + "  <span class=\"font-medium\">Danger alert!</span> Change a few things up and try submitting again.\n"
                        + "</div>";

                String f = """
                          <!DOCTYPE html>
                          <html lang="es">
                          <head>
                              <meta charset="UTF-8">
                              <meta name="viewport" content="width=device-width, initial-scale=1.0">
                              <title>Acceso denegado</title>
                              
                              <link href="https://cdn.jsdelivr.net/npm/flowbite@3.1.2/dist/flowbite.min.css" rel="stylesheet" />
                          
                              </head>
                          <body class="bg-gray-50 dark:bg-gray-900 p-8">
                          
                       
                           
                           <div class="max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow-sm dark:bg-gray-800 dark:border-gray-700">
                               <a href="#">
                                   <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">Acceso Denegado</h5>
                               </a>
                               <p class="mb-3 font-normal text-gray-700 dark:text-gray-400">No posee las credenciales para esta opción</p>
                               <a href=".../login" class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                                   Login
                                   <svg class="rtl:rotate-180 w-3.5 h-3.5 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                                       <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
                                   </svg>
                               </a>
                           </div>
                     
                           
                              <script src="https://cdn.jsdelivr.net/npm/flowbite@3.1.2/dist/flowbite.min.js"></script>
                          
                          </body>
                          </html>
                          """;

                return Response.serverError()
                        .entity(f)
                        .build();
//                
//                return Response.serverError()
//                        .entity("<h1>No tiene credenciales para utilizar esta opción<ò/h1><p>" + "</p>")
//                        .build();
            }
            String htmlContent = init();
            if (htmlContent != null) {
                return Response.ok(htmlContent).build();
            }
            // Si devuelve null, se asume que hubo un error o redirección.
            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (Exception e) {
            // Manejo de errores genérico
            System.err.println("Error al generar la vista JAX-RS: " + e.getMessage());
            return Response.serverError()
                    .entity("<h1>Error Interno del Servidor</h1><p>" + e.getMessage() + "</p>")
                    .build();
        }
    }

    // NOTA: Para POST/Formularios, la clase hija deberá definir su propio método @POST.
    Boolean isAuthentication(HttpServletRequest request) {
        Boolean result = Boolean.FALSE;
        try {
            HttpSession session = request.getSession(false);
            // 1. Validar Sesión
            if (session == null || session.getAttribute("username") == null) {

            } else {
                result = Boolean.TRUE;
            }
        } catch (Exception e) {
            System.out.println("\t");
        }
        return result;
    }

    public WebModelSession webModelOfSession(HttpServletRequest request) {
        WebModelSession webModel = new WebModelSession();
        try {
            webModel.setIsAuthentication(Boolean.FALSE);
            webModel.setHasAuthorization(Boolean.FALSE);
            HttpSession session = request.getSession(false);
            if (session == null) {
                return webModel;
            }
            webModel.setUsername((String) session.getAttribute("username"));
            webModel.setName((String) session.getAttribute("name"));
            if (session.getAttribute("iduser").toString() == null || session.getAttribute("iduser").toString().equals("")) {
                webModel.setIduser(0L);
            } else {
                webModel.setIduser(Long.parseLong(session.getAttribute("iduser").toString()));
            }

            webModel.setUserRol((String) session.getAttribute("userRol"));

            if (session.getAttribute("idrol").toString() == null || session.getAttribute("idrol").toString().equals("")) {
                webModel.setIdRol(0L);
            } else {
                webModel.setIdRol(Long.parseLong(session.getAttribute("idrol").toString()));
            }

            webModel.setPrimaryBtnClass(primaryBtnClass(request));
            webModel.setSecondaryBtnClass(secondaryBtnClass(request));
            webModel.setIsAuthentication(isAuthentication(request));
            webModel.setCssFramework(cssFramework(request));
            webModel.setIsTailwind(isTailwind(request));

        } catch (Exception e) {
            System.out.println("\t");
        }
        return webModel;
    }

    Boolean hasAuthorization(HttpServletRequest request, List<String> validRole) {
        Boolean result = Boolean.FALSE;
        try {
            HttpSession session = request.getSession(false);
            // 1. Validar Sesión
            if (session == null || session.getAttribute("username") == null) {

            } else {
                result = Boolean.TRUE;
            }
        } catch (Exception e) {
            System.out.println("\t");
        }
        return result;
    }

    public String cssFramework(HttpServletRequest request) {
        String result = "";
        try {
            result = (String) request.getSession().getAttribute("cssFramework");
        } catch (Exception e) {
            System.out.println("cssFramework() " + e.getLocalizedMessage());
        }
        return result;
    }

    public Boolean isTailwind(HttpServletRequest request) {
        Boolean result = Boolean.FALSE;
        try {
            boolean isTailwind = "tailwind".equals(cssFramework(request));
        } catch (Exception e) {
            System.out.println("isTailwind() " + e.getLocalizedMessage());
        }
        return result;
    }

    public String primaryBtnClass(HttpServletRequest request) {
        String result = "";
        try {
            result = isTailwind(request)
                    ? "bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded shadow-md transition duration-150"
                    : "btn btn-primary";
        } catch (Exception e) {
            System.out.println("primaryBtnClass() " + e.getLocalizedMessage());
        }
        return result;
    }

    public String secondaryBtnClass(HttpServletRequest request) {
        String result = "";
        try {
            result = isTailwind(request)
                    ? "bg-gray-400 hover:bg-gray-500 text-gray-800 font-bold py-2 px-4 rounded shadow-md ml-2 transition duration-150"
                    : "btn btn-secondary ms-2";

        } catch (Exception e) {
            System.out.println("secondaryBtnClass() " + e.getLocalizedMessage());
        }
        return result;
    }

}
