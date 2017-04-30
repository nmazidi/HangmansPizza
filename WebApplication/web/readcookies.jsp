<%-- 
    Document   : readcookies
    Created on : 30-Apr-2017, 22:43:42
    Author     : Aaron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Cookie cookie = null;
            Cookie[] cookies = null;

            cookies = request.getCookies();
            if (cookies != null) {
                out.println("<h2> List of cookies : </h2>");
                for (int i = 0; i < cookies.length; i++) {
                    cookie = cookies[i];
                    out.print("Name : " + cookie.getName() + ",  ");
                    out.print("Value: " + cookie.getValue() + " <br/>");
                }
            } else {
                out.println("<h2>No cookies founds</h2>");
            }
        %>
    </body>
</html>
