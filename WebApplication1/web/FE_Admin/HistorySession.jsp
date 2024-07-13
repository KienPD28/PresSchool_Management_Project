<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Sessions</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
            }

            .container {
                width: 90%;
                max-width: 1200px;
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }

            h1 {
                text-align: center;
                color: #333;
                margin-bottom: 20px;
            }

            .btn-back {
                display: block;
                margin: 0 auto 20px;
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                text-align: center;
                cursor: pointer;
                text-decoration: none;
            }

            .btn-back:hover {
                background-color: #0056b3;
            }

            .session-list {
                display: flex;
                flex-wrap: wrap;
                gap: 20px;
                justify-content: center;
            }

            .session-card {
                background-color: #fafafa;
                border: 1px solid #ddd;
                border-radius: 8px;
                padding: 15px;
                width: calc(33.333% - 20px);
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                transition: all 0.3s ease;
            }

            .session-card:hover {
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
                transform: translateY(-5px);
            }

            .session-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 10px;
            }

            .session-id {
                font-weight: bold;
                color: #555;
            }

            .session-status {
                padding: 5px 10px;
                border-radius: 4px;
                color: #fff;
                font-size: 0.9em;
            }

            .session-status.active {
                background-color: #28a745;
            }

            .session-status.inactive {
                background-color: #dc3545;
            }

            .session-body h2 {
                margin: 10px 0;
                color: #333;
                font-size: 1.2em;
            }

            .session-body p {
                margin: 5px 0;
                color: #666;
            }

            .session-body a {
                text-decoration: none;
                color: #007bff;
                font-weight: bold;
            }

            .session-body a:hover {
                text-decoration: underline;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <h1>History Sessions</h1>
            <a class="btn-back" href="session">Back To Session</a>
            <div class="session-list">
                <c:forEach var="his" items="${requestScope.list}">
                    <div class="session-card">
                        <div class="session-header">
                            <span class="session-id">Session ID: ${his.sid}</span>
                            <span class="session-status ${his.status != null && !his.status.isEmpty() ? his.status : 'inactive'}">
                                ${his.status != null && !his.status.isEmpty() ? his.status : 'inactive'}
                            </span>
                        </div>
                        <div class="session-body">
                            <h2><a href="history-curiculum?sid=${his.sid}">${his.sname}</a></h2>
                            <p>Total Sessions: ${his.totalSession}</p>
                            <p>Age: ${his.age.aname}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
