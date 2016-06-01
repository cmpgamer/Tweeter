<%-- 
    Document   : searchResults
    Created on : Nov 21, 2015, 5:55:44 PM
    Author     : Ashley
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="tweeter.TwitRepository"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tweeter.Twit"%>
<%@page import="tweeter.UserExtra"%>
<%@page import="tweeter.UserCore"%>
<%@page import="tweeter.TwitDispatcher"%>
<%@page import="tweeter.User"%>

<%@page import="java.util.Map"%>
<%@page import="tweeter.Register"%>
<%@page import="java.math.BigInteger"%>
<%@page import="java.sql.*" %>
<%@page import="java.util.Date" %>
<%@page import="java.security.MessageDigest" %>
<% Class.forName("com.mysql.jdbc.Driver"); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User currentUser = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tweeter - Search Results</title>

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

                <h1>Search Results</h1>


<%
    String search = "";     //search string
    String userSearch = ""; //Used for profile searching
    String hashSearch = ""; //Used for hash searching
    ArrayList<Twit> searchedTwits = new ArrayList<Twit>();
    String error = "";
    RequestDispatcher requestDispatcher; //Makes an HTTP request to be sent back to a jsp
    
    if(request.getParameter("search") != null){
        search = request.getParameter("search");       
    }
    if(search.isEmpty() || search.trim().isEmpty()){
        
        request.setAttribute("error", "<li>Search cannot be empty</li>");
        requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
        return;   
    }
    
    boolean anonymous = session.getAttribute("user") == null ? true : false;
    if (search.substring(0,1).equals("@")) {
        if(session.getAttribute("user") == null){
            request.setAttribute("error", "<li>Must be logged in to search for other users</li>");
            requestDispatcher = request.getRequestDispatcher("landing.jsp");
            requestDispatcher.forward(request, response);
            return;
        }
        
        for (int i = 0; i != search.length(); ++i) {
            if (search.charAt(i) != '@') continue;

            for (int j = i+1; j < search.length(); ++j) {
                char currentLetter = search.charAt(j);
                if (currentLetter == ' ' || currentLetter == '@') {
                    break;
                }
                userSearch += currentLetter;
            }//end of for
        }//end of for
        //want to search database for userSearch, and return user's twits, profile
        UserCore userCore = new UserCore();
        User user = null;
        if(userCore.userExists(userSearch)){
            user = userCore.getUser(userSearch);
        }
        if(user == null){
            request.setAttribute("error", "<li>User does not exist on Tweeter</li>");
            requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
            return;
        }
        if(user.equals(currentUser)){
            response.sendRedirect("index.jsp");
            return;
        }

        UserExtra userExtra = new UserExtra();
        String aboutMe = userExtra.getAboutMe(user);
        String interests = userExtra.getInterests(user);
        session.setAttribute("otherAboutMe", aboutMe);
        session.setAttribute("otherInterests", interests);
        session.setAttribute("otherUser", user);
        session.setAttribute("filter", "Personal");
        response.sendRedirect("index.jsp");
        return;
    
    } else if (search.substring(0,1).equals("#") && !search.contains(" ")) {
        hashSearch += "#";
        for (int i = 0; i != search.length(); ++i) {
            if (search.charAt(i) != '#') 
                continue;
            for (int j = i+1; j < search.length(); ++j) {
                char currentLetter = search.charAt(j);
                if (currentLetter == ' ' || currentLetter == '#') {
                    break;
                }
                hashSearch += currentLetter;
            }//end nested for
        }//end for
            if (!anonymous) {
                if (UserCore.isSubscribed((User)session.getAttribute("user"),(User) session.getAttribute("otherUser"))) {
                    ArrayList<User> subscribees = UserCore.getSubscribees((User)session.getAttribute("user"));
                    for (User subscribee : subscribees) {
                        searchedTwits.addAll(TwitRepository.getPrivateHashTwits(hashSearch, subscribee));
                    }
                }//end 2nd nested if   
                searchedTwits.addAll(TwitRepository.getPrivateHashTwits(hashSearch, (User)session.getAttribute("user")));
            }//end nested if
                
            searchedTwits.addAll(TwitRepository.getPublicHashTwits(hashSearch));
    } else {
        if (!anonymous) {
            if (UserCore.isSubscribed((User)session.getAttribute("user"),(User) session.getAttribute("otherUser"))) {
                ArrayList<User> subscribees = UserCore.getSubscribees((User)session.getAttribute("user"));
                for (User subscribee : subscribees) {
                    searchedTwits.addAll(TwitRepository.getPrivateHashTwits(search, subscribee));
                }
            }
        }
        if (!anonymous)
            searchedTwits.addAll(TwitRepository.getPrivateHashTwits(search, (User)session.getAttribute("user")));
        searchedTwits.addAll(TwitRepository.getPublicHashTwits(search));
    }
    
    if (searchedTwits.isEmpty()){
        error = "<li>No results found</li>";
    }
    
    if (!error.equals("")) {
        request.setAttribute("error", error);
        if (session.getAttribute("user") != null) {
            requestDispatcher = request.getRequestDispatcher("index.jsp");
        }
        else {
            requestDispatcher = request.getRequestDispatcher("landing.jsp");
        }
        requestDispatcher.forward(request, response);
        return;
    }
    
    for (Twit twit : searchedTwits) {
        String message = twit.getMessage();
        if (message.contains(hashSearch)) {
            LocalDateTime time = twit.getTimestamp();
            String month = time.getMonth().toString().toLowerCase();
            month = month.substring(0,1).toUpperCase() + month.substring(1);
            String day = Integer.toString(time.getDayOfMonth());
            String year = Integer.toString(time.getYear());
            String hour = Integer.toString(time.getHour());
            String minute = Integer.toString(time.getMinute());
            if(minute.length() == 1){
                minute = "0" + minute;
            }
            String am_pm = time.getHour()/12 > 0 ? "pm" : "am";
            hour = Integer.toString(time.getHour() % 12);
    %>
        <div style="margin:10px" class="panel panel-primary">
            <div class="panel-body">
                
                <div style="float:right;color:<%= twit.isIsPrivate() ? "red" : "#00bc8c" %>">
                    P 
                </div>
              
                <div><%= twit.getMessage() %></div>
                <div>
                    <b><%= twit.getAccountName() %></b> 
                    
                    <span style="color:#999999;margin-left: 5px"><%= month %> <%= day %>, <%= year %> <%= hour %>:<%= minute %><%= am_pm %></span>
                </div>
                
              

            </div>
        </div>
    <%
           } 
        }
    %>        
        

                <hr>

            </div>

            <!-- Blog Sidebar Widgets Column -->
            <div class="col-md-4">
                <% if (currentUser != null) { %>
                <div class="row">
                    <%@include file="twit.jsp"%>
                </div>
                <% } %>
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

