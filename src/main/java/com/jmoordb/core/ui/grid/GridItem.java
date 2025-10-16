/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.grid;





import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import jakarta.servlet.http.HttpServletRequest;

public class GridItem implements WebComponent {
    private final String title;
    private final String content;
    private final String iconClass;
    private final HttpServletRequest request;

    public GridItem(String title, String content, String iconClass, HttpServletRequest request) {
        this.title = title;
        this.content = content;
        this.iconClass = iconClass;
        this.request = request;
    }

    @Override
    public String render() {
        String framework = (String) request.getSession().getAttribute("cssFramework");
        boolean isTailwind = "tailwind".equals(framework);

        if (isTailwind) {
            // ⭐ ESTRUCTURA CON CLASES DE TAILWIND
            
            // bg-content (de main-styles.css) | flex items-center (d-flex align-items-center) | p-4 rounded-lg shadow-md
            Tag card = new Tag("div").withClass("card flex items-center p-4 rounded-lg shadow-md"); 
            
            // Icono
            Tag iconContainer = new Tag("div").withClass("flex-shrink-0 p-3 mr-4 rounded-full bg-blue-600 text-white");
            iconContainer.withChild(new Tag("i").withClass(iconClass + " text-xl"));
            
            // Contenido
            Tag textContainer = new Tag("div").withClass("flex-grow");
            textContainer.withChild(new Tag("h2").withClass("text-xl font-semibold mb-0").withText(title));
            textContainer.withChild(new Tag("p").withClass("text-gray-400 text-sm").withText(content));
            
            card.withChild(iconContainer).withChild(textContainer);
            return card.render();

        } else {
            // ESTRUCTURA ORIGINAL CON CLASES DE BOOTSTRAP
            
            Tag card = new Tag("div").withClass("card shadow-sm");
            Tag cardBody = new Tag("div").withClass("card-body d-flex align-items-center");

            // Icono
            Tag iconContainer = new Tag("div").withClass("p-3 me-3 rounded-circle bg-primary text-white");
            iconContainer.withChild(new Tag("i").withClass(iconClass + " fa-lg"));
            
            // Contenido
            Tag textContainer = new Tag("div");
            textContainer.withChild(new Tag("h5").withClass("card-title mb-0").withText(title));
            textContainer.withChild(new Tag("p").withClass("card-text mb-0").withText(content));
            
            cardBody.withChild(iconContainer).withChild(textContainer);
            card.withChild(cardBody);
            
            return card.render();
        }
    }
    
      public WebComponent build(){
      return this;
   }
}