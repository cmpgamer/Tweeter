<%-- 
    Document   : registerCode
    Created on : Nov 21, 2015, 5:17:30 PM
    Author     : Kenny
--%>


<%@page import="java.util.ArrayList"%>
<%  
    String code = "";

    if(request.getParameter("code") != null){
        code = request.getParameter("code");
    }
    //creates an object for an html request
    RequestDispatcher requestDispatcher; 
    requestDispatcher = request.getRequestDispatcher("landing.jsp");
    
    String error = "";
    if (code.isEmpty()) {
        error += "Enter a code to register.";
        request.setAttribute("error", error);
        requestDispatcher.forward(request, response);
        return;
    }    
    
    //arraylist of acceptable invite codes
    ArrayList<String> validCodes = new ArrayList<String>();
    String[] keys = {"55555", "55556", "55557", "55558", "55559"};
    for (String key : keys) {
        validCodes.add(key);
    }
    
    if (validCodes.contains(code)) {
        response.sendRedirect("register.jsp");
        return;
    }
    
    error += "Invalid invite code.";
    request.setAttribute("error", error);
    requestDispatcher.forward(request, response);
 %>
