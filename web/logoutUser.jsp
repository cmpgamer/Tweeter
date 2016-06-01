<%-- 
    Document   : logoutUser
    Created on : Nov 20, 2015, 4:41:03 PM
    Author     : Bryan
--%>
<%@page import="tweeter.User"%>
<%
 
    User user = (User) session.getAttribute("user");
    if (user != null) {
        session.removeAttribute("user");
        session.removeAttribute("about_me");
        session.removeAttribute("intersts");
        
        session.removeAttribute("otherUser");
        session.removeAttribute("otherAboutMe");
        session.removeAttribute("otherInterests");
    }
    response.sendRedirect("index.jsp");
 %>
