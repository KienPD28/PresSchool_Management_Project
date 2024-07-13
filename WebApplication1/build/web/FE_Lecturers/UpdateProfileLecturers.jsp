<%-- 
    Document   : UpdateProfileLecturers
    Created on : Jun 20, 2024, 6:53:32 PM
    Author     : NGUYEN THI KHANH VI
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Update Lecturer Profile</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="styles.css">
        <style>
            body {
                background-color: #f8f9fa;
                font-family: 'Arial', sans-serif;
            }
            .container {
                margin-top: 50px;
                max-width: 600px;
                padding: 20px;
                background-color: #ffffff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h2 {
                margin-bottom: 20px;
            }
            .form-group {
                margin-bottom: 20px;
            }
            .modal-footer {
                margin-top: 20px;
                text-align: right;
            }
            .btn-cancel {
                margin-right: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2 class="text-center">Update Lecturer Profile</h2>

            <form id="updateLecturerForm" action="update-lecturers" method="post">
                <input type="hidden" id="editLecturerId" name="lid" value="${lecturers.lid}">

                <div class="form-group">
                    <label for="editLecturerName">Lecturer Name</label>
                    <input type="text" class="form-control" id="editLecturerName" name="lname" value="${lecturers.lname}" required>
                </div>

                <div class="form-group">
                    <label for="editLecturerGender">Gender</label>
                    <select class="form-control" id="editLecturerGender" name="gender" required>
                        <option value="Male" ${lecturers.gender == 'Male' ? 'selected' : ''}>Male</option>
                        <option value="Female" ${lecturers.gender == 'Female' ? 'selected' : ''}>Female</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="editLecturerDob">Date of Birth</label>
                    <input type="date" class="form-control" id="editLecturerDob" name="dob" value="${lecturers.dob}" required>
                </div>

                <div class="form-group">
                    <label for="editLecturerPhoneNumber">Phone Number</label>
                    <input type="number" class="form-control" id="editLecturerPhoneNumber" name="phoneNumber" value="${lecturers.phoneNumber}" required>
                </div>

                <div class="form-group">
                    <label for="editLecturerIDCard">ID Card</label>
                    <input type="number" class="form-control" id="editLecturerIDCard" name="IDCard" value="${lecturers.IDcard}" required>
                </div>

                <div class="form-group">
                    <label for="editLecturerAddress">Address</label>
                    <input type="text" class="form-control" id="editLecturerAddress" name="address" value="${lecturers.address}" required>
                </div>

                <div class="form-group">
                    <label for="editLecturerEmail">Email</label>
                    <input type="email" class="form-control" id="editLecturerEmail" name="email" value="${lecturers.email}" required>
                </div>

                <div class="form-group">
                    <label for="editLecturerNickName">Nickname</label>
                    <input type="text" class="form-control" id="editLecturerNickName" name="nickName" value="${lecturers.nickname}" required>
                </div>
                <% String Error = (String) request.getAttribute("Error"); %>
                <% if (Error != null) { %>
                <p style="color: red">${Error}</p>
                <% } %>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" onclick="window.history.back()">Cancel</button>
                    <button type="submit" id="confirmUpdateBtn" class="btn btn-danger">Update</button>
                </div>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    </body>
</html>