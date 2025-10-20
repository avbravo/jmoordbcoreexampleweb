# Ajax fetch

***

[Ajax, Fetch, Axios & jQuery ⚡ 5 minutos 🎉 Desde cero](https://www.youtube.com/watch?v=DaGI20KYgS8)

Hace peticiones Ajax a un endpoint. Mediante un boton al dar click invova el enpoint y retorna imagenes.


```java

@Override
    public WebComponent content(HttpServletRequest request) {
        WebComponent mainPanel = null;
        try {

            Form formContent = new Form();
            formContent.add(
                    new Div().styleClass("space-y-12")
                            .add(
                                    new Button().text("Fetch").type(ButtonType.BUTTON).id("btnFetch")
                            )
                            .add(
                                    new Div().id("divCaracteres")
                            )
            );

            Script scriptFecth = new Script()
                    .code(javaScriptCode());

            WebComponent webContent = 
                    new Div().styleClass("p-8 min-h-screen flex flex-col items-center justify-center bg-gray-100 dark:bg-gray-900")
                    .withClass(webModel.getIsTailwind() ? "space-y-4" : "") // Añadir espaciado de Tailwind
                    .add(formContent)
                    .add(scriptFecth);

            mainPanel = new Panel("Fecth", webContent, request);
        } catch (Exception e) {
            System.out.println("\t content() " + e.getLocalizedMessage());
        }
        return mainPanel;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String javaScriptCode()">
    public String javaScriptCode() {
        String result = "";
        try {
            result = """
                     const btn = document.getElementById("btnFetch");
                     const div = document.getElementById("divCaracteres");
                     btn.addEventListener('click',() => {
                           console.log('Fetch API');
                          fetch('https://rickandmortyapi.com/api/character')
                              .then((response) => response.json())
                              .then((data) => renderCaracteres(data));
                     });
                     
                     
                     function renderCaracteres(data) { // Cambié 'caracteres' por 'data' para mayor claridad.
                         // **AQUÍ ESTÁ LA CORRECCIÓN CLAVE:**
                     console.log(data);
                         // Acceder a la propiedad 'results' del objeto de respuesta de la API.
                         const characters = data.results; 
                         
                         // Verifica que 'characters' sea un array antes de iterar
                         if (Array.isArray(characters)) {
                             characters.forEach(ch => {
                                 // **CORRECCIÓN DE SINTAXIS:** Usar backticks (`) para el template literal
                                 // y `${...}` para incrustar la variable.
                                 div.innerHTML += `<img src="${ch.image}">`; 
                             });
                         } else {
                             console.error("Error: 'results' no es un array o no existe en la respuesta de la API.");
                         }
                      }                    
                     """;
        } catch (Exception e) {
            System.out.println("\t content() " + e.getLocalizedMessage());
        }
        return result;


``

