<%-- 
    Document   : updateUser
    Created on : Nov 20, 2015, 5:08:13 PM
    Author     : Bryan
--%>

<%@page import="tweeter.User"%>
<%@page import="tweeter.UserExtra"%>
<%
    String email = "", aboutMe = "", interests = "";

    if(request.getParameter("email") != null){
        email = request.getParameter("email");
    } 
    if(request.getParameter("about_me") != null){
        aboutMe = request.getParameter("about_me");
    } 
    if(request.getParameter("interests") != null){
        interests = request.getParameter("interests");
    } 
    
    String error = "";
    if (!error.isEmpty()) {
        RequestDispatcher requestDispatcher; 
        request.setAttribute("error", error);
        requestDispatcher = request.getRequestDispatcher("editProfile.jsp");
        requestDispatcher.forward(request, response);
        return;
    }
    
    User user = (User) session.getAttribute("user");
    UserExtra userExtra = new UserExtra(); 
    userExtra.setAboutMe(user, aboutMe);
    userExtra.setInterests(user, interests);
    
    session.removeAttribute("about_me");
    session.setAttribute("about_me", aboutMe);
    
    session.removeAttribute("interests");
    session.setAttribute("interests", interests);
    
    String success = "Profile successfully updated.";
    
    //Makes an HTTP request to be sent back to a jsp
    RequestDispatcher requestDispatcher; 
    request.setAttribute("success", success);
    requestDispatcher = request.getRequestDispatcher("editProfile.jsp");
    requestDispatcher.forward(request, response);

 %>
