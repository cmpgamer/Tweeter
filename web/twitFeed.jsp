<%-- 
    Document   : twitFeed
    Created on : Nov 22, 2015, 5:35:50 PM
    Author     : Kenny
--%>

<%@page import="tweeter.User"%>
<%@page import="tweeter.TwitRepository"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tweeter.Twit"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    String filter = (String) session.getAttribute("filter");
    if ((User)session.getAttribute("user") != null && (User)session.getAttribute("otherUser") == null) { %>
<div class="row">
    <div class="col-sm-4">
        <h2>Twit Feed</h2>
    </div>
    <div class="col-sm-8">
        <div class="panel panel-primary">
            <div class="panel-body">
                <form class="navbar-form navbar-left" action="viewPublic.jsp" method="POST">
                    <button type="submit" class="btn btn-primary">View Public</button>
                </form>
                <form class="navbar-form navbar-left" action="viewPersonal.jsp" method="POST">
                    <button type="submit" class="btn btn-primary">View Personal</button>
                </form>
                <form class="navbar-form navbar-left" action="viewBoth.jsp" method="POST">
                    <button type="submit" class="btn btn-primary">View Both</button>
                </form>
            </div>
           
        </div>
    </div>
</div>
<% } %>
 
<hr>    
    
<div class="panel panel-default">
<%  
    
    User currentUser = null;
    if ((User)session.getAttribute("otherUser") == null) {
        currentUser = (User)session.getAttribute("user");
    } else {
        currentUser = (User)session.getAttribute("otherUser");
    }
    if (filter == null) {
        filter = "Personal";
    }
    ArrayList<Twit> profileTwits = TwitRepository.getProfileTwits(currentUser, (User) session.getAttribute("user") == null ? true : false, (User) session.getAttribute("user"), filter);
    for (Twit twit : profileTwits) {
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
        
        //converts timestamp to 12 hour time
        String amPm = time.getHour()/12 > 0 ? "pm" : "am";
        hour = Integer.toString(time.getHour() % 12);     
        
        
%>
        <div style="margin:10px" class="panel panel-primary">
            <div class="panel-body">
                
                <div style="float:right; color:<%= twit.isIsPrivate() ? "red" : "#00bc8c" %>">
                    P 
                </div>
              
                <div><%= twit.getMessage() %></div>
                <div>
                    <b><%= twit.getAccountName() %></b> 
                    
                    <span style="color:#999999;margin-left: 5px"><%= month %> <%= day %>, <%= year %> <%= hour %>:<%= minute %><%= amPm %></span>
                </div>
                
              

            </div>
        </div>
<%
    }//end for
%>
</div>
