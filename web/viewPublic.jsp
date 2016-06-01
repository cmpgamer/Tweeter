<%-- 
    Document   : viewPublic
    Created on : Nov 22, 2015, 10:52:46 PM
    Author     : Bryan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.setAttribute("filter", "Public");
    response.sendRedirect("index.jsp");  
%>
