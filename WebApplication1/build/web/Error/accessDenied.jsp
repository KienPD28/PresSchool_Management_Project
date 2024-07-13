<%-- 
    Document   : accessDenied
    Created on : Jun 21, 2024, 10:30:21 AM
    Author     : DELL
--%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Access Denied</title>
        <style>
            body {
                font-family: 'Comic Sans MS', cursive, sans-serif;
                background-color: #2BB4D0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .container {
                text-align: center;
                background-color: #ffffff;
                padding: 50px;
                border-radius: 15px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .container h1 {
                color: #d02b2b;
                font-size: 48px;
                margin-bottom: 20px;
            }
            .container p {
                color: #333;
                font-size: 18px;
                margin-bottom: 30px;
            }
            .container a {
                display: inline-block;
                padding: 10px 25px;
                background-color: #2BB4D0;
                color: #fff;
                text-decoration: none;
                border-radius: 25px;
                font-size: 16px;
            }
            .container a:hover {
                background-color: #e55d5d;
            }
            .container img {
                width: 150px;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Oops! Access Denied</h1>
            <p>Sorry, you don't have permission to access this page.</p>
            <p>or your session has expired</p>
            <a href="<%= request.getContextPath() %>/login">Go to Login Page</a>
        </div>
    </body>
</html>

