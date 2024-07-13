<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lịch Sử Lịch Học</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 20px;
            }
            .session-link {
                display: block;
                color: #007bff;
                text-align: center;
                margin-bottom: 10px;
            }
            .session-link:hover {
                text-decoration: none;
                color: #0056b3;
            }
            .history-item {
                background-color: #ffffff;
                border-radius: 5px;
                padding: 15px;
                margin-bottom: 20px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .history-item h1 {
                font-size: 20px;
                margin: 5px 0;
                color: #333;
            }
            .history-item h1 small {
                font-size: 14px;
                color: #666;
            }
            .card {
                border: none;
                margin-bottom: 20px;
            }
            .card-header {
                background-color: #007bff;
                color: #fff;
                font-weight: bold;
            }
            .card-body {
                background-color: #e9ecef;
            }
            .card-body h5 {
                color: #007bff;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2 class="mb-4">Lịch Sử Lịch Học</h2>
            <div class="list-group mb-4">
                <c:forEach var="ses" items="${requestScope.list1}" varStatus="status">
                    <c:if test="${status.index % 10 == 0}">
                        <div class="row">
                    </c:if>
                    
                    <div class="col-1">
                        <a class="session-link list-group-item list-group-item-action" href="history-curiculum?sid=${param.sid}&sdid=${ses.sdid}">${ses.sessionNumber}</a>
                    </div>
                    
                    <c:if test="${(status.index + 1) % 10 == 0 || status.last}">
                        </div>
                    </c:if>
                </c:forEach>
            </div>
            
            <div class="history-container">
                <c:forEach var="his" items="${requestScope.list}">
                    <div class="card">
                        <div class="card-header">CurID: ${his.curID}</div>
                        <div class="card-body">
                            <h5 class="card-title">${his.nameAct}</h5>
                            <p class="card-text">Time Start : ${his.timeStart}</p>
                            <p class="card-text">Time End : ${his.timeEnd}</p>
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
