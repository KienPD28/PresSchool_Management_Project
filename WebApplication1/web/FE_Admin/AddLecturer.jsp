<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Update Lecturers Profile</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="styles.css">
        <style>
            body {
                background-color: #b3d9ff;
            }
            .form-group label {
                font-weight: bold;
                color: #333;
            }
            .card {
                border-radius: 10px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .card-header {
                background-color: #f0f9ff;
                padding: 1rem;
                border-radius: 10px 10px 0 0;
            }
            .card-body {
                padding: 2rem;
            }
            input[type=number]::-webkit-outer-spin-button,
            input[type=number]::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }
            input[type=number] {
                -moz-appearance: textfield;
            }
        </style>
    </head>
    <body>
        <div class="container mt-3">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <div class="card">
                        <div class="card-header">
                            <h2>Add Lecturers Profile</h2>
                        </div>
                        <div class="card-body">
                           <form action="add-lecturer" method="POST">
                                <c:if test="${not empty requestScope.errorMessage}">
                                    <div class="alert alert-danger" role="alert">
                                        ${requestScope.errorMessage}
                                    </div>
                                </c:if>
                                <div class="form-group">
                                    <label for="lname">Họ và tên</label>
                                    <input type="text" class="form-control" id="lname" name="lname" required>
                                </div>
                                <div class="form-group">
                                    <label for="gender">Giới tính</label>
                                    <select class="form-control" id="gender" name="gender" required>
                                        <option value="true">Nam</option>
                                        <option value="false">Nữ</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="dob">Ngày sinh</label>
                                    <input type="date" class="form-control" id="dob" name="dob" required>
                                </div>
                                <div class="form-group">
                                    <label for="address">Địa chỉ</label>
                                    <input type="text" class="form-control" id="address" name="address" required>
                                </div>
                                <div class="form-group">
                                    <label for="phoneNumber">Số điện thoại</label>
                                    <input type="number" class="form-control" id="phoneNumber" name="phoneNumber" required>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" required>
                                </div>
                                <div class="form-group">
                                    <label for="IDcard">Căn cước công dân</label>
                                    <input type="number" class="form-control" id="IDcard" name="IDcard" required>
                                </div>
                                <div class="form-group">
                                    <label for="classID">Tên Lớp</label>
                                    <select class="form-control" id="classID" name="classID">
                                        <option value="">Chọn Lớp Học</option>
                                        <c:forEach items="${requestScope.listA}" var="cla">
                                             <option value="${cla.classID.classid}">${cla.classID.clname}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Add</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
