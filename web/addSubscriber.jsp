<%-- 
    Document   : addSubscriber
    Created on : Nov 22, 2015, 7:08:12 PM
    Author     : Kenny
--%>

<%@page import="tweeter.User"%>
<%  
    //for subscribing to another user
    String subscribeTo = "";
    Boolean isPrivate = false;
    if(request.getParameter("user_b_id") != null){
        subscribeTo = request.getParameter("user_b_id");
    }
    //creates an object for an html request
    RequestDispatcher requestDispatcher; 
    
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    
    //displays a success message when a user subscribes
    String successMessage = "Subscribed!";
    request.setAttribute("success", successMessage);
    requestDispatcher = request.getRequestDispatcher("index.jsp");
    requestDispatcher.forward(request, response);
    
 %>
