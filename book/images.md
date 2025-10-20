# Images

[https://www.w3schools.com/html/html_images.asp](https://www.w3schools.com/html/html_images.asp)


```java

  @Override
    public WebComponent content(HttpServletRequest request) {
        WebComponent mainPanel = null;
        try {

            Form formContent = new Form();
            formContent.add(
                    new Div().styleClass("space-y-12")
                            .add(
                                    new A().href("login").text("Image as a Link")
                                           .add(
                                           new Image().src("https://www.w3schools.com/images/w3schools_green.jpg")
                                                   .alt("HTML Tutorial")
                                                   .style("width:42px;height:42px;")
                                           )
                            )                           
            );
       

            WebComponent webContent = 
                    new Div().styleClass("p-8 min-h-screen flex flex-col items-center justify-center bg-gray-100 dark:bg-gray-900")
                    .withClass(webModel.getIsTailwind() ? "space-y-4" : "") // Añadir espaciado de Tailwind
                    .add(formContent);

            mainPanel = new Panel("Fecth", webContent, request);
        } catch (Exception e) {
            System.out.println("\t content() " + e.getLocalizedMessage());
        }
        return mainPanel;
    }

```