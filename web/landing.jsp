<%-- 
    Document   : landing
    Created on : Nov 6, 2015, 7:19:15 PM
    Author     : ashawee
--%>
<%@page import="tweeter.User"%>
<%@page import="java.sql.*"%>
<% Class.forName("com.mysql.jdbc.Driver"); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    User user = (User) session.getAttribute("user");
    if (user != null) {
        response.sendRedirect("profile.jsp");
    }
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

    <!-- Page Content -->
    <div class="container">
        <div class="row">
            <%@include file="errorSuccess.jsp"%>
        </div>
        <div class="row">

            <!-- Blog Post Content Column -->
            <div class="col-lg-8">

                <!-- Title -->
                <h1>Welcome to Tweeter!</h1>
                <hr>

                <!-- Post Content -->
                <p>Welcome to the site for all your anonymous posting! Be a part of something bigger, invite ONLY.</p>
                
                <%@include file="twitFeed.jsp"%>
            </div>

            <!-- Sidebar Column -->
            <div class="col-md-4">
                
                <!-- Side Login Form -->
                <div class="well">
                    <h4>Login</h4>

                    <form class="form-horizontal"  action="loginUser.jsp" method="POST">
                      <div class="form-group">
                        <label for="inputUsername" class="col-sm-4 control-label">Username</label>
                        <div class="col-sm-8">
                          <input type="text" class="form-control" id="inputUsername" name="inputUsername">
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="inputPassword" class="col-sm-4 control-label">Password</label>
                        <div class="col-sm-8">
                          <input type="password" class="form-control" id="inputPassword" name="inputPassword">
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-8">
                          <button style="width:100%" type="submit" class="btn btn-primary">Log in</button>
                        </div>
                      </div>
                    </form>
                    
                </div>
                <!-- Invite Validation Form -->
                <div class="well">
                    <h4>Join your friends</h4>
                    <form class="form-horizontal" action="registerCode.jsp" method="POST">
                        <div class="form-group">
                            <label for="code" class="col-sm-4 control-label">Invite Code</label>
                            <div class="col-sm-8">
                               <input type="text" class="form-control" id="code" name="code">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-4 col-sm-8">
                                <button style="width:100%" type="submit" class="btn btn-default" role="button">Submit</button>
                            </div>
                        </div>
                    </form>
                    
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

