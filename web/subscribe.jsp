<%-- 
    Document   : subscribe
    Created on : Nov 23, 2015, 12:15:56 AM
    Author     : Bryan
--%>

<%@page import="tweeter.UserCore"%>
<%@page import="tweeter.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   User user = (User) session.getAttribute("user");
   User otherUser = (User) session.getAttribute("otherUser");
   

   UserCore.subscribe(user, otherUser);
   
   response.sendRedirect("index.jsp");
 %>