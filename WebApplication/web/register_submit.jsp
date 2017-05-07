<%-- 
    Document   : register_submit
    Created on : 30-Apr-2017, 22:07:13
    Author     : Aaron
--%>
<%
    

 %>
    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Test</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h2> <%= request.getParameter("firstName")%> </h2>
        <a href='readcookies.jsp'>Read cookies</a>
    </body>
</html>
