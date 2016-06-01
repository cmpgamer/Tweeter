<%-- 
    Document   : viewPersonal
    Created on : Nov 22, 2015, 10:52:56 PM
    Author     : Bryan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    session.setAttribute("filter", "Personal");
    response.sendRedirect("index.jsp");   
%>
