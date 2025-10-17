# Markdown for Netbeans ![Description Here](https://raw.githubusercontent.com/moacirrf/netbeans-markdown/main/images/nblogo48x48.png)

# Markdown for Netbeans ![Description Here](https://raw.githubusercontent.com/moacirrf/netbeans-markdown/main/images/nblogo48x48.png)

Here are some of the most common HTML input types: 
text: A single-line text input field for general user input.
Código

    <input type="text" name="username">
password: A single-line text input field where characters are masked for security.
Código

    <input type="password" name="user_password">
email: A text input field specifically for email addresses, often triggering email-optimized keyboards on mobile devices and providing basic client-side validation.
Código

    <input type="email" name="user_email">
number: A numeric input field, allowing only numbers. It can include min, max, and step attributes for range and interval control.
Código

    <input type="number" name="quantity" min="1" max="10">
checkbox: Allows users to select zero or more options from a set.
Código

    <input type="checkbox" name="option1" value="value1">
    <input type="checkbox" name="option2" value="value2">
radio: Allows users to select only one option from a set, typically grouped by a common name attribute.
Código

    <input type="radio" name="gender" value="male">
    <input type="radio" name="gender" value="female">
submit: A button that submits the form data to the server.
Código

    <input type="submit" value="Send">
file: Enables users to select and upload files. The accept attribute can be used to specify allowed file types.
Código

    <input type="file" name="document" accept=".pdf,.doc">
date: Allows users to select a calendar date.
Código

    <input type="date" name="event_date">
time: Allows users to select a specific time.
Código

    <input type="time" name="meeting_time">
color: Provides a color picker interface for selecting a color.
Código

    <input type="color" name="fav_color">
range: A slider control for selecting a numeric value within a specified range.
Código

    <input type="range" name="volume" min="0" max="100" value="50">
hidden: An input field that is not visible to the user but its value is submitted with the form.
Código

    <input type="hidden" name="user_id" value="123">