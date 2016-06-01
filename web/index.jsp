<%-- 
    Document   : index
    Created on : Nov 20, 2015, 3:57:57 PM
    Author     : Bryan
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tweeter.Twit"%>
<%@page import="tweeter.TwitRepository"%>
<%@page import="tweeter.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("landing.jsp");
        return;
    }
    User otherUser = (User) session.getAttribute("otherUser");
%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tweeter - Home</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/blog-post.css" rel="stylesheet">


</head>

<body>
    <%@include file="header.jsp"%>
    <div class="container">
        <div class="row">
            <%@include file="errorSuccess.jsp"%>
        </div>
        
        <div class="row">
             
            <!-- Blog Post Content Column -->
            <div class="col-lg-8">
                <% if (otherUser == null) { %>
                <h1>Hello <%= user.getFirstName()%>!</h1>
                <hr>
                <% } else { %> 
                <h1 class="text-center">Viewing: <%= otherUser.getFirstName()%> <%= otherUser.getLastName()%></h1>
                <% } %>
                <%@include file="twitFeed.jsp"%>
            </div>

            <!-- Sidebar Widgets Column -->
            <div class="col-md-4">
                <div class="row">
                    <%@include file="twit.jsp"%>
                   <%@include file="profile.jsp"%>
                </div>
            </div>
        </div>
        <!-- /.row -->

        <hr>

         <%@include file="footer.html"%>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>