<%-- 
    Document   : HistoryDetailLecturer
    Created on : Jun 25, 2024, 9:36:03 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lecturer Information</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <style>
            body {
                background-color: #f0f8ff;
                font-family: 'Arial', sans-serif;
            }
            .container {
                margin-top: 50px;
                background: #fff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 0 20px rgba(0, 123, 255, 0.2);
            }
            .header {
                text-align: center;
                margin-bottom: 30px;
            }
            .header h1 {
                color: #007bff;
                font-weight: bold;
            }
            .info-row {
                display: flex;
                align-items: center;
                margin-bottom: 15px;
            }
            .info-row h3 {
                margin: 0;
                color: #343a40;
            }
            .info-label {
                flex: 1;
                font-weight: bold;
                color: #007bff;
            }
            .info-value {
                flex: 2;
                padding-left: 10px;
            }
            .info-value.default {
                color: #6c757d;
            }
            .icon {
                font-size: 50px;
                color: #007bff;
                margin-right: 20px;
            }
        </style>
        <!-- FontAwesome for Icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>
    <body>
        <div class="container">
            <div class="header">
                <i class="icon fas fa-user"></i>
                <h1>Thông tin cá nhân</h1>
            </div>
            <div class="info-row">
                <div class="info-label"><h3>Tên giáo viên :</h3></div>
                <div class="info-value"><h3>${lec.lname}</h3></div>
            </div>
            <div class="info-row">
                <div class="info-label"><h3>Giới tính :</h3></div>
                <div class="info-value"><h3>${lec.gender}</h3></div>
            </div>
            <div class="info-row">
                <div class="info-label"><h3>Số điện thoại :</h3></div>
                <div class="info-value"><h3>${lec.phoneNumber}</h3></div>
            </div>
            <div class="info-row">
                <div class="info-label"><h3>IDCard :</h3></div>
                <div class="info-value"><h3>${lec.IDcard}</h3></div>
            </div>
            <div class="info-row">
                <div class="info-label"><h3>Email :</h3></div>
                <div class="info-value"><h3>${lec.email}</h3></div>
            </div>
            <div class="info-row">
                <div class="info-label"><h3>Trạng thái :</h3></div>
                <div class="info-value">
                    <h3>
                        <c:choose>
                            
                              
                            <c:when test="${lec.status != null || lec.status != ''}">${lec.status}</c:when>
                            <c:otherwise>Đã Nghỉ Việc</c:otherwise>
                        </c:choose>
                            
                          
                    </h3>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
