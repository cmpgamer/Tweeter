<%-- 
    Document   : register
    Created on : Nov 6, 2015, 7:19:15 PM
    Author     : Ashley
--%>
<%@page import="java.sql.*"%>
<% Class.forName("com.mysql.jdbc.Driver"); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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

            <!-- Blog Post Content Column -->
            <div class="col-lg-8">

                <!-- Title -->
                <h1>Register</h1>
                 <%@include file="errorSuccess.jsp"%>
                  <form class="form-horizontal" action="registerUser.jsp" method="POST">
                   <div class="form-group">
                    <label for="inputFirstName" class="col-sm-4 control-label">First Name</label>
                    <div class="col-sm-6">
                      <input type="text" class="form-control" id="inputFirstName" placeholder="" name="inputFirstName">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputLastName" class="col-sm-4 control-label">Last Name</label>
                    <div class="col-sm-6">
                      <input type="text" class="form-control" id="inputLastName" placeholder="" name="inputLastName">
                    </div>
                  </div>                       
                  <div class="form-group">
                    <label for="inputUsername" class="col-sm-4 control-label">Username</label>
                    <div class="col-sm-6">
                      <input type="text" class="form-control" id="inputUserName" name="inputUsername">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-4 control-label">Email Address</label>
                    <div class="col-sm-6">
                      <input type="email" class="form-control" id="inputEmail" name="inputEmail">
                    </div>
                  </div>
                   <div class="form-group">
                    <label for="inputBirthDate" class="col-sm-4 control-label">Birth Date</label>
                    <div class="col-sm-6">
                        <input type="date" class="form-control" id="inputBirthDate" placeholder="MM/DD/YYYY" name="inputBirthDate">
                    </div>
                  </div>    
                  <div class="form-group">
                    <label for="inputGender" class="col-sm-4 control-label">Gender:</label>
                    <div class="col-sm-6">
                        <select class="form-control" id="inputGender" name="inputGender">
                          <option>Male</option>
                          <option>Female</option>
                        </select>
                    </div>
                  </div>

                  <div class="form-group">
                    <label for="inputPassword2" class="col-sm-4 control-label">Password</label>
                    <div class="col-sm-6">
                      <input type="password" class="form-control" id="inputPassword2" placeholder="" name="inputPassword2">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                      <button type="reset" class="btn btn-default">Reset</button>
                      <button type="submit" class="btn btn-default">Register</button>
                    </div>
                  </div>
                </form>

            </div>

            <!-- Sidebar Column -->
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

