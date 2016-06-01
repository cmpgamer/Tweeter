<%-- 
    Document   : updatePassword
    Created on : Nov 21, 2015, 3:32:10 PM
    Author     : Kenny
--%>

<%@page import="tweeter.User"%>
<%@page import="tweeter.UserPassword"%>
<%
    
    String oldPassword = "", newPassword1 = "", newPassword2 = "";
    if(request.getParameter("oldPassword") != null){
        oldPassword = request.getParameter("oldPassword");
    }
    if(request.getParameter("newPassword1") != null){
        newPassword1 = request.getParameter("newPassword1");
    } 
    if(request.getParameter("newPassword2") != null){
        newPassword2 = request.getParameter("newPassword2");
    } 
    String error = ""; 
    
    //Makes an HTTP request to be sent back to a jsp
    RequestDispatcher requestDispatcher; 
    requestDispatcher = request.getRequestDispatcher("editProfile.jsp");
    
    if (oldPassword.isEmpty()) {
        error += "Old password required";
        request.setAttribute("error", error);
        requestDispatcher.forward(request, response);
        return;
    }
    
    User user = (User) session.getAttribute("user");
    UserPassword userPassword = new UserPassword();
    if (!userPassword.validPassword(user, oldPassword)) {
        error += "Invalid password";
        request.setAttribute("error", error);
        requestDispatcher.forward(request, response);
        return;
    }
    
    if (!newPassword1.equals(newPassword2)) {
        error += "Passwords do not match";
        request.setAttribute("error", error);
        requestDispatcher.forward(request, response);
        return;
    }
    
    userPassword.updatePassword(user, newPassword1);
    String success = "Password successfully updated";
    request.removeAttribute("error");
    request.setAttribute("success", success);
    requestDispatcher.forward(request, response);
 %>