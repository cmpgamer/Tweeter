<%-- 
    Document   : viewBoth
    Created on : Nov 22, 2015, 10:53:04 PM
    Author     : Bryan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.setAttribute("filter", "Both");
    response.sendRedirect("index.jsp");  
%>