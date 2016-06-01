<%-- 
    Document   : homeClick
    Created on : Nov 22, 2015, 10:57:16 PM
    Author     : Bryan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  session.removeAttribute("otherUser");
  session.removeAttribute("otherAboutMe");
  session.removeAttribute("otherInterests");
  response.sendRedirect("index.jsp");
%>