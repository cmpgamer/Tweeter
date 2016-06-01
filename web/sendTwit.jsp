<%-- 
    Document   : sendTwit
    Created on : Nov 21, 2015, 12:00:02 AM
    Author     : Bryan
--%>

<%@page import="tweeter.TwitDispatcher"%>
<%@page import="tweeter.User"%>


<%@page import="java.util.Map"%>
<%@page import="tweeter.Register"%>
<%@page import="java.math.BigInteger"%>
<%@page import="java.sql.*" %>
<%@page import="java.util.Date" %>
<%@page import="java.security.MessageDigest" %>
<% Class.forName("com.mysql.jdbc.Driver"); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  
    String message = "";
    Boolean isPrivate = false;
    if(request.getParameter("message") != null){
        message = request.getParameter("message");
    }
    if(request.getParameter("isPrivate") != null) {
        String isPrivateString = request.getParameter("isPrivate");
        if (!isPrivateString.isEmpty())
            isPrivate = true;
    }
    
    String error = "";
    if (message.isEmpty()) {
        error += "<li>Message is empty.</li>";
    } else if (message.length() > 140) {
        error += "<li>Message length exceeds 140 characters.</li>";
    } else if (message.trim().isEmpty()){
        error += "<li>Message cannot contain only Whitespace characters.</li>";
    }
    //Makes an HTTP request to be sent back to a jsp
    RequestDispatcher requestDispatcher; 
    if (!error.equals("")) {
        request.setAttribute("error", error);
        requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
        return;
    }
    
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    
    TwitDispatcher twitDispatcher = new TwitDispatcher();
    twitDispatcher.dispatch(user, message, isPrivate);
    
    String successMessage = "Message sent";
    request.setAttribute("success", successMessage);
    requestDispatcher = request.getRequestDispatcher("index.jsp");
    requestDispatcher.forward(request, response);
    
 %>