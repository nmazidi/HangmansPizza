<%
    if ((request.getParameter("username") == "test") && (request.getParameter("password") == "qwerty")) {
        Cookie username = new Cookie("username", request.getParameter("username"));
        username.setMaxAge(60 * 60 * 24);
        response.addCookie(username);

        Cookie password = new Cookie("password", request.getParameter("password"));
        password.setMaxAge(60 * 60 * 24);
        response.addCookie(password);

        Cookie isLoggedOn = new Cookie("isLoggedOn", "true");
        isLoggedOn.setMaxAge(60 * 60 * 24);
        response.addCookie(isLoggedOn);

        //String loginResponse = "Login Succeded, redirecting to home page in 5 seconds.";

        response.setHeader("Location", "index.jsp");
    } else {
        response.setHeader("Location", "login.jsp");
    }

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> <%= request.getParameter("username") %></h1>
    </body>
</html>
