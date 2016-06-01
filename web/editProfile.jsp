<%-- 
    Document   : editProfile
    Created on : Nov 6, 2015, 7:19:15 PM
    Author     : ashawee
--%>

<%@page import="tweeter.User"%>
<%@page import="java.sql.*"%>

<% Class.forName("com.mysql.jdbc.Driver"); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User user = (User) session.getAttribute("user");
    String aboutMe = (String) session.getAttribute("about_me");
    String interests = (String) session.getAttribute("interests");
%>


<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tweeter - Profile</title>

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

            <!-- Blog Post Content Column -->
            <div class="col-lg-8">
                <h4>Edit Profile</h4>
                <%@include file="errorSuccess.jsp"%>
                <form class="form-horizontal" action="updateUser.jsp" method="POST">                      
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-4 control-label">Email</label>
                    <div class="col-sm-6">
                      <input type="email" class="form-control" id="inputEmail" name="email" value="<%= user == null ? "" : user.getEmail() %>">
                    </div>
                  </div>
                   <div class="form-group">
                    <label for="inputAbout" class="col-sm-4 control-label">About Me</label>
                    <div class="col-sm-6">
                        <textarea class="form-control" name="about_me" id="inputAbout" rows="4"><%= aboutMe == null ? "" : aboutMe %></textarea>
                    </div>
                  </div>  
                   <div class="form-group">
                    <label for="inputInterests" class="col-sm-4 control-label">Interests</label>
                    <div class="col-sm-6">
                        <textarea class="form-control" name="interests" id="inputInterests" rows="4"><%= interests == null ? "" : interests %></textarea>
                    </div>
                  </div>     
                  <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                      <button type="reset" class="btn btn-default">Reset</button>
                      <button type="submit" class="btn btn-default">Update</button>
                    </div>
                  </div>
                </form>
                <hr>
                <form class="form-horizontal" action="updatePassword.jsp" method="POST" >
                    <div class="form-group">
                        <label for="inputOldPassword" class="col-sm-4 control-label">Old Password</label>
                        <div class="col-sm-6">
                          <input type="password" class="form-control" id="inputOldPassword" name="oldPassword" placeholder="">
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="inputNewPassword1" class="col-sm-4 control-label">New Password</label>
                        <div class="col-sm-6">
                          <input type="password" class="form-control" id="inputNewPassword" name="newPassword1" placeholder="">
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="inputNewPassword2" class="col-sm-4 control-label">Repeat Password</label>
                        <div class="col-sm-6">
                          <input type="password" class="form-control" id="inputNewPassword2" name="newPassword2" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-8">
                          <button type="reset" class="btn btn-default">Reset</button>
                          <button type="submit" class="btn btn-default">Update</button>
                        </div>
                    </div>
                </form>
            </div>

            <!-- Blog Sidebar Widgets Column -->
            <div class="col-md-4">
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

