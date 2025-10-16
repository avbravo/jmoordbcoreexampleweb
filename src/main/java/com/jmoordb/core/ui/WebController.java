/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.jmoordb.core.ui;

import com.jmoordb.core.ui.model.WebModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author avbravo
 */
public interface WebController {

    default Boolean isAuthentication(HttpServletRequest request) {
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

    default WebModel webModelOfSession(HttpServletRequest request) {
        WebModel result = new WebModel();
        try {
            result.setIsAuthentication(Boolean.FALSE);
            result.setHasAuthorization(Boolean.FALSE);
            HttpSession session = request.getSession(false);
            if (session == null) {
                return result;
            }
            result.setUsername((String) session.getAttribute("username"));
            result.setName((String) session.getAttribute("name"));
            if (session.getAttribute("iduser").toString() == null || session.getAttribute("iduser").toString().equals("")) {
                result.setIduser(0L);
            } else {
                result.setIduser(Long.parseLong(session.getAttribute("iduser").toString()));
            }
          
            result.setUserRol((String) session.getAttribute("userRol"));

            if (session.getAttribute("idrol").toString() == null || session.getAttribute("idrol").toString().equals("")) {
                result.setIdRol(0L);
            } else {
                result.setIdRol(Long.parseLong(session.getAttribute("idrol").toString()));
            }

            result.setPrimaryBtnClass(primaryBtnClass(request));
            result.setSecondaryBtnClass(secondaryBtnClass(request));
            result.setIsAuthentication(isAuthentication(request));
            result.setCssFramework(cssFramework(request));
            result.setIsTailwind(isTailwind(request));

        } catch (Exception e) {
            System.out.println("\t");
        }
        return result;
    }

    default Boolean hasAuthorization(HttpServletRequest request, List<String> validRole) {
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

    default public String cssFramework(HttpServletRequest request) {
        String result = "";
        try {
            result = (String) request.getSession().getAttribute("cssFramework");
        } catch (Exception e) {
            System.out.println("cssFramework() " + e.getLocalizedMessage());
        }
        return result;
    }

    default public Boolean isTailwind(HttpServletRequest request) {
        Boolean result = Boolean.FALSE;
        try {
            boolean isTailwind = "tailwind".equals(cssFramework(request));
        } catch (Exception e) {
            System.out.println("isTailwind() " + e.getLocalizedMessage());
        }
        return result;
    }

    default public String primaryBtnClass(HttpServletRequest request) {
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

    default public String secondaryBtnClass(HttpServletRequest request) {
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

    default void start() {

    }

    public WebComponent content(HttpServletRequest request);
}
