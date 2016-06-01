<%-- 
    Document   : profile
    Created on : Nov 22, 2015, 6:52:30 PM
    Author     : Ashley
--%>
<%@page import="tweeter.UserCore"%>
<%@page import="tweeter.User"%>
<%
    User theCurrentUser = null;
    String currentAboutMe = null;
    String currentInterests = null;
    if ((User)session.getAttribute("otherUser") == null) {
        theCurrentUser = (User)session.getAttribute("user");
        currentAboutMe = (String) session.getAttribute("about_me");
        currentInterests = (String) session.getAttribute("interests");
    } else {
        theCurrentUser = (User)session.getAttribute("otherUser");
        currentAboutMe = (String) session.getAttribute("otherAboutMe");
        currentInterests = (String) session.getAttribute("otherInterests");
    }
%>
<div class="panel panel-info">
    <div class="panel-heading">Profile Info</div>
    <div class="panel-body">
        <!-- Display about me section -->
         <div class="panel panel-primary" style="margin:10px">
            <div class="panel-body">
                <div class="">
                    About Me:
                </div>
                <div class="text-center">
                    <%= currentAboutMe == null || currentAboutMe.isEmpty() ? "N/A" : currentAboutMe %>
                </div>
                <div class="">
                    Interests:
                </div>
                <div class="text-center">
                    <%= currentInterests == null || currentInterests.isEmpty() ? "N/A" : currentInterests %> 
                </div>
            </div>
        </div>
        <hr>
        <!-- If your looking at another users profile, and you are not subscribed to them, then show the subscribe form. -->
        <% if (session.getAttribute("otherUser") != null && !(new UserCore()).isSubscribed((User)session.getAttribute("user"), (User)session.getAttribute("otherUser"))) { %>
        <form class="form-horizontal" action="subscribe.jsp" method="POST">
           <div class="form-group">
              <div class="col-sm-offset-1 col-sm-10">
                <button style="width:100%" type="submit" class="btn btn-primary">Subscribe</button>
              </div>
           </div> 
        </form>
        <!-- Otherwise, show the unsubscribe form -->
        <% } else if (session.getAttribute("otherUser") != null) {%>
         <form class="form-horizontal" action="unsubscribe.jsp" method="POST">
           <div class="form-group">
              <div class="col-sm-offset-1 col-sm-10">
                <button style="width:100%" type="submit" class="btn btn-danger">Unsubscribe</button>
              </div>
           </div> 
        </form>
        <% } %>
    </div>
</div>
