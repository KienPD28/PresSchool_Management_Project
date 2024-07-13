<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Lecturer and Class</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            background-color: #b3d9ff;
            padding-top: 40px; /* for Bootstrap navbar */
        }
        .container {
            max-width: 800px;
            margin: auto;
        }
        .card {
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            display: none; /* Initially hide all cards */
        }
        .card.active {
            display: block; /* Show only the active card */
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
        .form-container {
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
        }
        .form-container .card {
            flex: 1;
            margin-right: 10px;
            min-width: 100%; /* Ensure each card takes full width initially */
        }
        .text-center {
            width: 100%;
            text-align: center;
            margin-bottom: 20px;
        }
        .alert {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="text-center">
        <button onclick="window.location.href = 'lecturers'">Back To Lecturers</button>
        <button class="btn btn-info mr-2" onclick="showForm('updateLecturerCard')">Update Lecturer</button>
        <button class="btn btn-info" onclick="showForm('updateClassCard')">Update Class</button>
    </div>

    <div class="form-container">
        <div class="card" id="updateLecturerCard">
            <div class="card-body">
                <div class="card-header">
                    <h2>Update Lecturer</h2>
                </div>
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success" role="alert">
                        ${successMessage}
                    </div>
                </c:if>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger" role="alert">
                        ${errorMessage}
                    </div>
                </c:if>
                <form id="updateLecturerForm" action="update-lecturers" method="POST">
                    <input type="hidden" name="lid" value="${lec.lid.lid}">
                    <div class="form-group">
                        <label for="lname">Họ và tên</label>
                        <input type="text" class="form-control" id="lname" name="lname" value="${lec.lid.lname}" required>
                    </div>
                    <div class="form-group">
                        <label for="gender">Giới tính</label>
                        <select class="form-control" id="gender" name="gender" required>
                            <option value="true" ${lec.lid.gender ? 'selected' : ''}>Nam</option>
                            <option value="false" ${!lec.lid.gender ? 'selected' : ''}>Nữ</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="dob">Ngày sinh</label>
                        <input type="date" class="form-control" id="dob" name="dob" value="${lec.lid.dob}" required>
                    </div>
                    <div class="form-group">
                        <label for="address">Địa chỉ</label>
                        <input type="text" class="form-control" id="address" name="address" value="${lec.lid.address}" required>
                    </div>
                    <div class="form-group">
                        <label for="phoneNumber">Số điện thoại</label>
                        <input type="number" class="form-control" id="phoneNumber" name="phoneNumber" value="${lec.lid.phoneNumber}" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${lec.lid.email}" required>
                    </div>
                    <div class="form-group">
                        <label for="IDcard">Căn cước công dân</label>
                        <input type="number" class="form-control" id="IDcard" name="IDcard" value="${lec.lid.IDcard}" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Update Profile</button>
                </form>
            </div>
        </div>

        <div class="card" id="updateClassCard">
            <div class="card-body">
                <div class="card-header">
                    <h2>Update Class</h2>
                </div>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger" role="alert">
                        ${errorMessage}
                    </div>
                </c:if>
                <form id="updateClassForm" action="update-lecturer-class" method="POST">
                    <input type="hidden" name="lid" value="${param.lid}">
                    <div class="form-group">
                        <label for="class">Tên Lớp</label>
                        <select class="form-control" id="class" name="classid" required>
                            <option value="${lec1.csid.classID}">${lec1.csid.classID.clname}</option>
                            <option value="">Không Dạy Lớp Nào</option>
                            <c:forEach items="${requestScope.list1}" var="cla">
                                <option value="${cla.classID.classid}">${cla.classID.clname}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Update Class</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Show Update Lecturer form by default
        showForm('updateLecturerCard');
    });

    function showForm(formId) {
        // Hide all cards
        document.querySelectorAll('.card').forEach(card => card.classList.remove('active'));

        // Show the selected form
        document.getElementById(formId).classList.add('active');
    }
</script>

</body>
</html>
