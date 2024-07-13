<%-- 
  Document   : NewSchoolYear
  Created on : May 30, 2024, 6:55:04 PM
  Author     : DELL
--%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Create New School Year</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .btn-campus {
                background-color: #39BACD;
                color: white;
                border: none;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
                transition: all 0.3s ease;
            }
            .btn-campus:hover {
                background-color: #39BACD;
            }
            .custom-link:active {
                font-weight: bold;
            }
            .content-wrapper {
                max-width: 1200px;
                margin: auto;
                padding: 20px;
            }
            .table-responsive {
                max-height: 400px;
                overflow-y: auto;
            }
        </style>
    </head>
    <body>

        <div class="container mt-5">
            <div class="mb-3">
                <button class="btn btn-campus" onclick="window.location.href = 'classController'">Back</button>
            </div>
            <h2>Create New School Year</h2>
            <form action="newyear" method="POST">
                <div class="form-group">
                    <label for="dateStart">Start Date:</label>
                    <input type="date" class="form-control" id="dateStart" name="dateStart" required>
                </div>
                <div class="form-group">
                    <label for="dateEnd">End Date:</label>
                    <input type="date" class="form-control" id="dateEnd" name="dateEnd" required>
                </div>
                <button type="submit" class="btn btn-primary">Create</button>
            </form>
            <c:if test="${not empty err}">
                <p style="color: red;">${err}</p>
            </c:if>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
