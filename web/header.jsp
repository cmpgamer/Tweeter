<%-- 
    Document   : header
    Created on : Nov 21, 2015, 5:32:15 PM
    Author     : Ashawee
--%>
<%@page import="tweeter.User"%>
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="homeClick.jsp">Tweeter</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="homeClick.jsp">Home</a>
                </li>
                <% if (session.getAttribute("user") != null) { %>
                <li>
                    <a href="editProfile.jsp">Edit Profile</a>
                </li>
                <% } %>
                <% if (session.getAttribute("user") == null) { %>
                    <!-- show nothing -->
                <% } else { %>
                <li>
                    <a href="logoutUser.jsp">Logout</a>
                </li>
                <% } %>
            </ul>
            
            <ul class="nav navbar-nav navbar-right">
                 <!-- <div class="row">
                 </div> -->
                 <form class="navbar-form navbar-left" role="search" action="searchResults.jsp" method="POST">
                    <div class="form-group">
                      <input type="text" id="search" name="search" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </ul>
            
                    <!-- /.input-group -->
        </div>
            
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>