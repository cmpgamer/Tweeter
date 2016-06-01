<%-- 
    Document   : registerUser
    Created on : Nov 20, 2015, 11:18:29 AM
    Author     : Bryan
--%>

<%@page import="tweeter.UserCore"%>
<%@page import="tweeter.MD5Hash"%>
<%@page import="java.util.Map"%>
<%@page import="tweeter.Register"%>
<%@page import="java.math.BigInteger"%>
<%@page import="java.sql.*" %>
<%@page import="java.util.Date" %>
<%@page import="java.security.MessageDigest" %>
<% Class.forName("com.mysql.jdbc.Driver"); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  
    String first = " ", last = " ", account = " ", email = " ", password = " ", birthdate = " ", gender = " ";

    if(request.getParameter("inputFirstName") != null){
        first = request.getParameter("inputFirstName");
    }
    if(request.getParameter("inputLastName") != null){
        last = request.getParameter("inputLastName");
    }
    if(request.getParameter("inputEmail") != null){
        email = request.getParameter("inputEmail");
    }
    if(request.getParameter("inputUsername") != null){
        account = request.getParameter("inputUsername");
    }
    if(request.getParameter("inputPassword2") != null){
        password = request.getParameter("inputPassword2");
    }
    if(request.getParameter("inputBirthDate") != null){
        birthdate = request.getParameter("inputBirthDate");
    }
    if(request.getParameter("inputGender") != null){
        gender = request.getParameter("inputGender");
    }
    
    UserCore userCore = new UserCore();
    String error = "";
    if (account.isEmpty())
    {
        error += "<li>Account name required</li>";
    }
    else if (userCore.userExists(account)) {
        error += "<li>Account with this name already exists.</li>";
    }
    if (first.isEmpty()) {
        error += "<li>First name required</li>";
    }
    if (email.isEmpty()) {
        error += "<li>Email required</li>";
    }
    if (last.isEmpty()) {
        error += "<li>Last name required</li>";
    }
    if (password.isEmpty()) {
        error += "<li>Password required</li>";
    }
    if (birthdate.isEmpty()) {
        error += "<li>Birthdate required</li>";
    }
    if (gender.isEmpty()) {
        error += "<li>Gender required</li>";
    }
    
    if (!error.equals("")) {
        RequestDispatcher requestDispatcher; 
        request.setAttribute("error", error);
        requestDispatcher = request.getRequestDispatcher("register.jsp");
        requestDispatcher.forward(request, response);
        return;
    }
    
    MD5Hash md5Hash = new MD5Hash();
    String hashtext = md5Hash.hash(password);
    // should check against null incase of exception thrown

    String genderChar = " ";
    if (gender.equals("Male")){
        genderChar = "M";
    }else if (gender.equals("Female")){
        genderChar = "F";
    }

    Register registerUser = new Register();
    registerUser.makeUser(first, last, account, email, hashtext, birthdate, genderChar);

    response.sendRedirect("index.jsp");
%>
