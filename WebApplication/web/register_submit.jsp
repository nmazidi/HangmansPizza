<%-- 
    Document   : register_submit
    Created on : 30-Apr-2017, 22:07:13
    Author     : Aaron
--%>
<%
    //Create cookie objects
    Cookie firstName = new Cookie("firstName", request.getParameter("firstName"));
    Cookie lastName = new Cookie("lastName", request.getParameter("lastName"));
    
    //Setting cookie expiry date = 24h
    firstName.setMaxAge(60 * 60 * 24);
    lastName.setMaxAge(60 * 60 * 24);
    
    //Adding cookies to the response header
    response.addCookie(firstName);
    response.addCookie(lastName);
 %>
    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h2> <%= request.getParameter("firstName")%> </h2>
        <a href='readcookies.jsp'>Read cookies</a>
    </body>
</html>
