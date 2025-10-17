# Markdown for Netbeans ![Description Here](https://raw.githubusercontent.com/moacirrf/netbeans-markdown/main/images/nblogo48x48.png)

HTML <button> elements primarily support three types, defined by the type attribute:
type="submit": This is the default type for <button> elements within a form. When clicked, it submits the form data to the server, typically triggering an action defined by the form's action attribute.
Código

    <form action="/submit-form">
      <input type="text" name="username">
      <button type="submit">Submit</button>
    </form>
type="reset": When clicked, this button resets all input elements within the same form to their initial values.
Código

    <form>
      <input type="text" name="email" value="default@example.com">
      <button type="reset">Reset</button>
    </form>
type="button": This type creates a generic clickable button that does not have any default behavior (like submitting or resetting a form). Its functionality is typically controlled by JavaScript, which can be attached to the button's click event to perform custom actions.
Código

    <button type="button" onclick="alert('Button clicked!')">Click Me</button>
Note: While <input type="image"> can also function as a graphical submit button, and <a> tags can be styled to look like buttons, the <button> element with its type attribute is the standard and recommended way to create interactive buttons with specific behaviors within HTML forms.