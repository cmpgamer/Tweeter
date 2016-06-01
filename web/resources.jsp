<%-- 
    Document   : resources
    Created on : Nov 29, 2015, 11:22:57 AM
    Author     : Ashley
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tweeter - Resources</title>

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
                <h1>Resources</h1>
                <hr>
                
                <h2>Tutorials</h2>
                
                <h3>Susan Ceklosky</h3>
                <ul>
                    <li><a href="https://www.youtube.com/watch?v=Alf8pakgOBU" target="_blank">Introduction to Java Server Pages</a></li>
                    <li><a href="https://www.youtube.com/watch?v=PiEemGuTogU" target="_blank">Creating a JSP Web Application with Form</a></li>
                    <li><a href="https://www.youtube.com/watch?v=TSkvPXRbzZw" target="_blank">Creating a JSP Web Application that Interfaces With a Database</a></li>
                    <li><a href="https://www.youtube.com/watch?v=bLZXJTAcu5I" target="_blank">Creating a JSP Web Application That Selects Specific Data From a Database</a></li>
                    <li><a href="https://www.youtube.com/watch?v=8Cag7UnGo_I" target="_blank">Creating A JSP Web Application That Inserts Data Into A Database</a></li>
                    <li><a href="https://www.youtube.com/watch?v=sX8qpw3bTIM" target="_blank">Creating a JSP Web Application That Deletes Data From A Database</a></li>
                    <li><a href="https://www.youtube.com/watch?v=YB9PjgLZhUk" target="_blank">Applying A CSS Layout To A JSP Web Application</a></li>
                </ul>
                
                <h3>Learning Lad</h3>
                <ul>
                    <li><a href="https://www.youtube.com/watch?v=qdudJfu2C04" target="_blank">Ternary Operator in Java Programming Video Tutorials For Beginners</a></li>
                </ul>
                
                <h3>NetBeans</h3>
                <ul>
                    <li><a href="https://netbeans.org/kb/docs/ide/github_nb_screencast.html" target="_blank">Video of Setting Up a GitHub Repository Using NetBeans IDE</a></li>
                    <li><a href="https://netbeans.org/kb/docs/web/mysql-webapp.html" target="_blank">Creating a Simple Web Application Using a MySQL Database</a></li>

                </ul>
               
                <h2>SQL Resources</h2>
                <ul>
                    <li><a href="http://www.w3schools.com/sql/" target="_blank">W3Schools.com</a></li>
                    <li><a href="http://www.tutorialspoint.com/sql/sql-using-joins.htm" target="_blank">tutorialspoint.com</a></li>
                </ul>
                
                <h2>MD5 Hash Resources</h2>
                <ul>
                    <li><a href="http://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash" target="_blank">StackOverflow.com</a></li>
                </ul>                
                
                <h2>JavaDoc Resources</h2>
                <ul>
                    <li><a href="http://docs.oracle.com/javase/7/docs/api/java/util/logging/Logger.html" target="_blank">Logger</a></li>
                    <li><a href="https://docs.oracle.com/javase/7/docs/api/java/security/MessageDigest.html" target="_blank">MessageDigest</a></li>
                    <li><a href="http://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html" target="_blank">BigInteger</a></li>
                    <li><a href="https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html" target="_blank">LocalDateTime</a></li>
                    <li><a href="https://docs.oracle.com/javaee/6/api/javax/servlet/RequestDispatcher.html?is-external=true" target="_blank">RequestDispatcher</a></li>
                </ul>
                
                <h2>HTML/CSS Resources</h2>
                <ul>
                    <li><a href="http://www.amazon.com/HTML-CSS-Design-Build-Websites/dp/1118008189/ref=sr_1_3?ie=UTF8&qid=1448816337&sr=8-3&keywords=john+duckett" target="_blank">HTML and CSS: Design and Build Websites - John Duckett</a></li>
                </ul>
                <h2>Bootstrap Resources</h2>
                <ul>
                    <li><a href="http://getbootstrap.com/" target="_blank">GetBootstrap.com</a></li>
                    <li><a href="https://bootswatch.com/darkly/" target="_blank">Bootstrap Darkly Theme</a></li>
                    <li><a href="http://startbootstrap.com/template-overviews/blog-post/" target="_blank">Start Bootstrap Blog Post page starter template</a></li>
                </ul>
                
                <h2>HTTP Response Resource</h2>
                <ul>
                    <li><a href="http://www.w3.org/Protocols/HTTP/Response.html" target="_blank">W3.org -HTTP Response</a></li>
                </ul>
                
                <h2>Java EE and GlassFish Server Resources</h2>
                <ul>
                    <li><a href="http://www.oracle.com/technetwork/java/javaee/downloads/index-jsp-140710.html" target="_blank">Java EE SDK Downloads</a></li>
                </ul>
                
                <h2>Database Design Resources</h2>
                <ul>
                    <li><a href="https://blog.8thlight.com/mike-ebert/2013/03/23/the-repository-pattern.html" target="_blank">The Repository Pattern - Mike Ebert</a></li>
                </ul>
               
            </div>

            <!-- Sidebar Column -->
            <div class="col-md-4">
                <div class="row">
        
                </div>
            </div>
        </div>
        <!-- /.row -->

        <hr>

         <%//@include file="footer.html"%>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>