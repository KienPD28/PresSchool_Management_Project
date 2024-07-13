<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Parent Profile</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                background-color: #f8f9fa;
                display: flex;
                height: 100vh;
                margin: 0;
            }
            .sidebar {
                background-color: #343a40;
                width: 250px;
                color: white;
                padding: 1rem;
                display: flex;
                flex-direction: column;
                align-items: center;
            }
            .sidebar img {
                width: 80px;
                height: 80px;
                border-radius: 50%;
                margin-bottom: 1rem;
            }
            .sidebar .nickname {
                font-weight: bold;
                margin-bottom: 1.5rem;
            }
            .sidebar a {
                color: white;
                text-decoration: none;
                margin-bottom: 1rem;
                text-align: center;
                width: 100%;
                padding: 0.5rem 0;
                border-radius: 5px;
                transition: background-color 0.3s;
            }
            .sidebar a:hover {
                background-color: #495057;
            }
            .content {
                flex: 1;
                padding: 2rem;
                overflow-y: auto;
            }
            .card {
                margin-bottom: 2rem;
            }
            .card-header {
                background-color: #007bff;
                color: white;
            }
            .info-group {
                margin-bottom: 1rem;
            }
            .info-group label {
                font-weight: bold;
                margin-bottom: 0.25rem;
                color: #333;
            }
            .info-group p {
                margin-bottom: 0;
                color: #666;
            }
        </style>
    </head>
    <body>
        <div class="sidebar">
            <img src="icons8-user-48 1.png" alt="User Icon">
            <div class="nickname">${pa.nickname}</div>
            <a href="update-profile">Update Profile</a>
            <a href="change">Change Password</a>
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
        <div class="content">
            <div class="card">
                <div class="card-header">
                    <h2>Thông tin cá nhân của phụ huynh</h2>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="info-group">
                                <label>Họ và tên</label>
                                <p>${pa.pname}</p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="info-group">
                                <label>Giới tính</label>
                                <p>
                                    <c:if test="${pa.gender == true}">Nam</c:if>
                                    <c:if test="${pa.gender == false}">Nữ</c:if>
                                    </p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="info-group">
                                    <label>Ngày sinh</label>
                                    <p>${pa.dob}</p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="info-group">
                                <label>Địa chỉ</label>
                                <p>${pa.address}</p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="info-group">
                                <label>Số điện thoại</label>
                                <p>${pa.phoneNumber}</p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="info-group">
                                <label>Email</label>
                                <p>${pa.email}</p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="info-group">
                                <label>Căn cước công dân</label>
                                <p>${pa.IDcard}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <c:forEach var="student" items="${stu}">
                <div class="card">
                    <div class="card-header">
                        <h3>Thông tin cá nhân của bé</h3>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="info-group">
                                    <label>Họ và tên</label>
                                    <p>${student.sname}</p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="info-group">
                                    <label>Giới tính</label>
                                    <p>
                                        <c:if test="${student.gender == true}">Nam</c:if>
                                        <c:if test="${student.gender == false}">Nữ</c:if>
                                        </p>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="info-group">
                                        <label>Ngày sinh</label>
                                        <p>${student.dob}</p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="info-group">
                                    <label>Địa chỉ</label>
                                    <p>${student.address}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
