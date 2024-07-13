<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Session Management</title>
    <style>
        /* CSS Styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 90%;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 8px;
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }
        .action-buttons {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .action-buttons .left,
        .action-buttons .right {
            display: flex;
            align-items: center;
        }
        .action-buttons button {
            border: none;
            padding: 10px 20px;
            margin: 0 10px;
            cursor: pointer;
            border-radius: 4px;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        .action-buttons .left button {
            background-color: #6c757d;
            color: #fff;
        }
        .action-buttons .left button:hover {
            background-color: #5a6268;
        }
        .action-buttons .right button {
            background-color: #28a745;
            color: #fff;
        }
        .action-buttons .right button:hover {
            background-color: #218838;
        }
        .search-bar {
            display: flex;
            align-items: center;
        }
        .search-bar input[type="text"] {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-right: 10px;
        }
        .search-bar button {
            background-color: #007BFF;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 4px;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        .search-bar button:hover {
            background-color: #0056b3;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
            color: #333;
            text-transform: uppercase;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        td a {
            color: #007BFF;
            text-decoration: none;
            transition: color 0.3s ease;
        }
        td a:hover {
            color: #0056b3;
        }
        .table-actions button {
            border: none;
            padding: 5px 10px;
            margin: 0 5px;
            cursor: pointer;
            border-radius: 4px;
            font-size: 14px;
            transition: background-color 0.3s ease;
        }
        .table-actions .update-button {
            background-color: #ffc107;
            color: #333;
        }
        .table-actions .update-button:hover {
            background-color: #e0a800;
        }
        .table-actions .delete-button {
            background-color: #dc3545;
            color: #fff;
        }
        .table-actions .delete-button:hover {
            background-color: #c82333;
        }
    </style>
    <script>
        function confirmDelete(sid) {
            if (confirm("Are you sure you want to delete this session?")) {
                window.location.href = 'delete-session?sid=' + sid;
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>Session Management</h1>
        <div class="action-buttons">
            <div class="left">
                <button onclick="window.location.href = 'home'">Back to Home</button>
            </div>
            <div class="search-bar">
                <form action="session" method="get">
                    <input type="text" name="nameAct" placeholder="Search sessions">
                    <button type="submit">Search</button>
                </form>
            </div>
            <div class="right">
                <button onclick="window.location.href = 'add-sessions'">Add Session</button>
                <button onclick="window.location.href = 'history-session'">History Session</button>
            </div>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Session ID</th>
                    <th>Session Name</th>
                    <th>Age Group</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${!empty requestScope.list1}">
                        <c:forEach var="ses" items="${requestScope.list1}">
                            <tr>
                                <td>${ses.sid}</td>
                                <td><a href="session-detail?sid=${ses.sid}">${ses.sname}</a></td>
                                <td>${ses.age.aname}</td>
                                <td class="table-actions">
                                    <button class="update-button" onclick="window.location.href = 'update-session?sid=${ses.sid}'">Update</button>
                                    <button class="delete-button" onclick="confirmDelete('${ses.sid}')">Delete</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="ses" items="${requestScope.list}">
                            <tr>
                                <td>${ses.sid}</td>
                                <td><a href="session-detail?sid=${ses.sid}">${ses.sname}</a></td>
                                <td>${ses.age.aname}</td>
                                <td class="table-actions">
                                    <button class="update-button" onclick="window.location.href = 'update-session?sid=${ses.sid}'">Update</button>
                                    <button class="delete-button" onclick="confirmDelete('${ses.sid}')">Delete</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</body>
</html>
