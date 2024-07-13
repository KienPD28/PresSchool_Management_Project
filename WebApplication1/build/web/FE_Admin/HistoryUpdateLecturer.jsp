<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Update Lecturers</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }
            .container {
                width: 80%;
                margin: auto;
                overflow: hidden;
            }
            header {
                background: #35424a;
                color: #ffffff;
                padding-top: 30px;
                min-height: 70px;
                border-bottom: #e8491d 3px solid;
            }
            header a, header h1 {
                color: #ffffff;
                text-decoration: none;
                text-align: center;
                margin: 0;
                padding: 0;
                display: block;
            }
            .history-card {
                background: #ffffff;
                margin: 10px 0;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .history-card h2 {
                color: #35424a;
                margin: 0 0 10px 0;
            }
            .history-card p {
                margin: 0 0 10px 0;
                color: #333333;
            }
        </style>
    </head>
    <body>
        <header>
            <div class="container">
                <h1>History Update Lecturers</h1>
            </div>
        </header>
        <div class="container">
            <c:forEach items="${requestScope.history}" var="his">
                <div class="history-card">
                    <h2>Lecturer: ${his.lid.lname}</h2>
                    <p>Class: ${his.csid.classID.clname}</p>
                    <p>Status: <c:out value="${his.status}" default="Đã sửa đổi"/></p>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
