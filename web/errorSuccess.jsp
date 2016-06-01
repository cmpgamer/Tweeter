<%-- 
    Document   : errorSuccess
    Created on : Nov 20, 2015, 4:37:10 PM
    Author     : Bryan
--%>

<% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger" role="alert">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <span class="sr-only">Error:</span>
        Please fix the following errors:
        <ul>
        <%= request.getAttribute("error") %>
        </ul>
    </div>
 <% } else if (request.getAttribute("success") != null) { %>
    <div class="alert alert-success" role="alert">
        <span class="glyphicon glyphicon-check" aria-hidden="true"></span>
        <span class="sr-only">Success: </span>                        
        <%= request.getAttribute("success") %>
    </div>
<% } %>