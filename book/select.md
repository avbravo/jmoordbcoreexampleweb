# Select

``

  Select selectTag = new Select()
                .name("userRol")
                .styleClass("form-select")
                .required(Boolean.TRUE);

        // Opción por defecto
        selectTag.add(
                new Option()
                        .value("")
                        .disabled(Boolean.TRUE)
                        .selected(Boolean.TRUE)
                        .text("Seleccionar Rol")
        );

        // Generar opciones dinámicamente
        userRoles.forEach((value, text)
                -> selectTag.add(new Option()
                        .value(value)
                        .text(text))
        );

        formContent.add(new Div().styleClass("mb-3")
                .add(new Label().forField("userRol").styleClass("form-label").text("Rol:"))
                .add(selectTag)
        );

```

