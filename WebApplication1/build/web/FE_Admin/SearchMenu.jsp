<%-- 
    Document   : SearchMenu
    Created on : Jun 5, 2024, 10:06:30 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Menu</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f0f2f5;
                color: #333;
            }

            h2 {
                text-align: center;
                margin-top: 20px;
            }

            .table-container-wrapper {
                display: flex;
                justify-content: center;
                padding: 20px;
            }

            .table-container {
                display: flex;
                flex-wrap: wrap;
                gap: 20px;
                justify-content: center;
                padding: 20px;
            }

            .table-container .table-wrapper {
                background-color: skyblue;
                border-radius: 10px;
                overflow: hidden;
                padding: 10px;
                text-align: center;
                width: 300px; /* Adjust the width as needed */
            }

            .table-container table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            .table-container th, .table-container td {
                padding: 8px;
                text-align: left;
            }

            .table-container th {
                background-color: #f2f2f2;
                color: #333;
            }

            .table-container td {
                background-color: white;
                color: #000;
            }

            button a {
                color: #fff;
                text-decoration: none;
            }

            button {
                background-color: #00b8ec;
                border: none;
                border-radius: 5px;
                color: #fff;
                cursor: pointer;
                display: block;
                margin: 20px auto;
                padding: 10px 20px;
                text-align: center;
            }

            button:hover {
                background-color: #555;
            }
        </style>
    </head>
    <body>
        <h2>Meals for Children on ${requestScope.date_raw}</h2>
        <div style="display: block">
            <button><a href="menu">Enter Today's Meals for All Ages</a></button>
        </div>

        <c:if test="${requestScope.listMenu != null}">
            <div class="table-container-wrapper">
                <div class="table-container">
                    <c:forEach items="${requestScope.listAgeCategory}" var="age">
                        <div class="table-wrapper">
                            <h3>Meals for ${age.aname}</h3>
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>Meal</th>
                                        <th>Food Names</th>
                                        <th>Date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.listMenu}" var="m">
                                        <c:if test="${age.ageid == m.ageid.ageid}">
                                            <tr>
                                                <td>${m.mealID.mealName}</td>
                                                <td>${m.menu}</td>
                                                <td>${m.date}</td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>
    </body>
</html>
