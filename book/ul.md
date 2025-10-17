# UL

```java
public String generateListExample() {
    
    WebComponent ulComponent = new Ul()
            .classStyle("list-disc list-inside space-y-2 text-gray-700")
            .add(new Li()
                    .text("Primer elemento")
                    .classStyle("hover:text-blue-500 transition duration-150"))
            .add(new Li()
                    .text("Segundo elemento")
                    .classStyle("hover:text-blue-500 transition duration-150")
                    // Se puede anidar otro componente dentro del li
                    .add(new Tag("span").withClass("text-xs text-red-500").withText(" - New!")))
            .add(new Li().text("Tercer elemento"))
            .build();

    return ulComponent.render();
}

```
// HTML generado:
```html

/*
<ul class="list-disc list-inside space-y-2 text-gray-700">
  <li class="hover:text-blue-500 transition duration-150">Primer elemento</li>
  <li class="hover:text-blue-500 transition duration-150">Segundo elemento<span> - New!</span></li>
  <li>Tercer elemento</li>
</ul>
*/
```