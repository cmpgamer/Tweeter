<%-- 
    Document   : loginUser
    Created on : Nov 20, 2015, 3:27:40 PM
    Author     : Bryan
--%>


<%@page import="tweeter.UserExtra"%>
<%@page import="tweeter.User"%>
<%@page import="tweeter.Login"%>
<%@page import="java.util.Map"%>
<%@page import="java.math.BigInteger"%>
<%@page import="java.sql.*" %>
<%@page import="java.util.Date" %>
<%@page import="java.security.MessageDigest" %>
<% Class.forName("com.mysql.jdbc.Driver"); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  
    String  account = " ", password = " ";
    if(request.getParameter("inputUsername") != null){
        account = request.getParameter("inputUsername");
    }
    if(request.getParameter("inputPassword") != null){
        password = request.getParameter("inputPassword");
    }
   
    //Here I wanted to hash my password that we were given so that our database is more secure.
    MessageDigest m = MessageDigest.getInstance("MD5");
    m.reset();
    m.update(password.getBytes());
    byte[] digest = m.digest();
    BigInteger bigInt = new BigInteger(1,digest);
    String hashtext = bigInt.toString(16);
    // Now we need to zero pad it if you actually want the full 32 chars.
    while(hashtext.length() < 32 ){
      hashtext = "0"+hashtext;
    }
    
    Login login = new Login();
    User user = login.getUser(account, hashtext);
    String error = "";
    if (user == null) {
        error += "Invalid login information";
        
        //creates an object for an html request
        RequestDispatcher requestDispatcher; 
        request.setAttribute("error", error);
        requestDispatcher = request.getRequestDispatcher("landing.jsp");
        requestDispatcher.forward(request, response);
        return;
    }
    
    UserExtra userExtra = new UserExtra();
    String aboutMe = userExtra.getAboutMe(user);
    String interests = userExtra.getInterests(user);
    session.setAttribute("about_me", aboutMe);
    session.setAttribute("interests", interests);
    
    session.setAttribute("user", user);
    
    session.setAttribute("filter", "Personal");
    response.sendRedirect("index.jsp");
%>

