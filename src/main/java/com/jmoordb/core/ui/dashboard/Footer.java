/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.dashboard;

/**
 *
 * @author avbravo
 */




import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import jakarta.servlet.http.HttpServletRequest;

public class Footer implements WebComponent {
    private final String text;
    private final HttpServletRequest request;

    public Footer(String text, HttpServletRequest request) {
        this.text = text;
        this.request = request;
    }

    @Override
    public String render() {
        String framework = (String) request.getSession().getAttribute("cssFramework");
        boolean isTailwind = "tailwind".equals(framework);

        // Clases Condicionales
        String containerClass = isTailwind ? "w-full mx-auto px-4" : "container-fluid";
        String pClass = isTailwind ? "text-center mb-0 text-sm" : "text-center mb-0";

        return new Tag("footer").withClass("footer py-3 mt-4")
            .withChild(new Tag("div").withClass(containerClass)
                .withChild(new Tag("p").withClass(pClass)
                    .withText(text)))
            .render();
    }
}