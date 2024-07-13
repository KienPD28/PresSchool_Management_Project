<%-- 
    Document   : ListStudent
    Created on : Jun 25, 2024, 8:05:50 AM
    Author     : hidung
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<% Date today = new Date(); %>
<% String currentDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(today); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Management</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
                font-family: 'Arial', sans-serif;
            }
            .sidebar {
                height: 100%;
                position: fixed;
                width: 200px;
                background-color: #343a40;
                padding-top: 20px;
                transition: all 0.3s;
                left: 0;
            }
            .sidebar.hidden {
                left: -200px;
            }
            .sidebar a {
                display: block;
                color: white;
                padding: 16px;
                text-decoration: none;
            }
            .sidebar a:hover {
                background-color: #575d63;
                text-decoration: none;
            }
            .main {
                margin-left: 210px; /* Same as the width of the sidebar */
                padding: 20px;
                transition: margin-left 0.3s;
            }
            .main.fullwidth {
                margin-left: 10px;
            }
            .name {
                color: #FFFFFF; /* Yellow color for prominence */
                font-size: 1.5em; /* Larger font size */
                font-weight: bold; /* Bold text */
                text-align: center;
                margin-bottom: 20px;
            }
            .dashboard-box {
                text-align: center;
                padding: 20px;
                border-radius: 5px;
                margin: 10px;
                color: white;
            }
            .dashboard-box.blue {
                background-color: #007bff;
            }
            .dashboard-box.green {
                background-color: #28a745;
            }
            .dashboard-box.yellow {
                background-color: #ffc107;
            }
            .form-inline .form-control {
                width: auto;
            }
            .form-inline button {
                margin-left: 10px;
            }
            .modal-header {
                background-color: #007bff;
                color: white;
            }
            .btn-primary {
                background-color: #007bff;
                border-color: #007bff;
            }
            .btn-primary:hover {
                background-color: #0056b3;
                border-color: #004085;
            }
            .btn-secondary {
                background-color: #6c757d;
                border-color: #6c757d;
            }
            .form-group label {
                font-weight: bold;
            }
            .toggle-btn {
                position: fixed;
                top: 20px;
                left: 220px;
                z-index: 1000;
            }
        </style>
    </head>
    <body>

        <div class="sidebar" id="sidebar">
            <div class="name">${lec.nickname}</div>
            <a href="#">Home</a>
            <a href="javascript:void(0)" id="listFeedbackLink">List Feedback</a>
            <a href="#">Profile</a>
        </div>

        <button class="btn btn-primary toggle-btn" id="toggleBtn" onclick="toggleSidebar()">=</button>
        <div class="main" id="mainContent">
            <h2 class="text-center">Management</h2> 
            
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success" role="alert">
                    ${successMessage}
                </div>
            </c:if>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>                   
                        <th>Student Name</th>
                        <th>DOB</th>
                        <th>Gender</th>                  
                        <th>Parent Name</th>
                        <th>Parent Phone</th>
                        <th>Feedback</th>                    
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${allStudent}">
                        <tr>
                            <td>${student.sname}</td>
                            <td>${student.dob}</td>
                            <td>${student.gender ? 'Male' : 'Female'}</td>
                            <td>${student.pid.pname}</td>
                            <td>${student.pid.phoneNumber}</td>
                            <td>
                                <button class="btn btn-primary" onclick="openFeedbackModal(${student.stuid})">Feedback</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Feedback Modal -->
        <div class="modal fade" id="feedbackModal" tabindex="-1" role="dialog" aria-labelledby="feedbackModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="feedbackModalLabel">Add Feedback</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="feedback" method="POST" id="feedbackForm">
                        <div class="modal-body">
                            <input type="hidden" id="studentId" name="studentId" value="">
                            <input type="hidden" id="lid" name="lid" value="${lec.lid}">
                            <div class="form-group">
                                <label for="fid">Feedback ID</label>
                                <input type="text" class="form-control" name="fid" id="fid" readonly>
                            </div>
                            <div class="form-group">
                                <label for="ftitle">Title</label>
                                <input type="text" class="form-control" id="ftitle" name="ftitle" required>
                            </div>
                            <div class="form-group">
                                <label for="fcontent">Content</label>
                                <textarea class="form-control" id="fcontent" name="fcontent" rows="4" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="dateFeedback">Date</label>
                                <input type="date" class="form-control" id="dateFeedback" name="dateFeedback" value="${currentDate}" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary" name="action" value="singleStudent">Submit</button>
                            <button type="submit" class="btn btn-primary" name="action" value="allStudents">Submit All Students</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
        <script>
                                    function toggleSidebar() {
                                        var sidebar = document.getElementById("sidebar");
                                        var mainContent = document.getElementById("mainContent");
                                        var toggleBtn = document.getElementById("toggleBtn");

                                        if (sidebar.classList.contains("hidden")) {
                                            sidebar.classList.remove("hidden");
                                            mainContent.classList.remove("fullwidth");
                                            toggleBtn.style.left = "220px";
                                        } else {
                                            sidebar.classList.add("hidden");
                                            mainContent.classList.add("fullwidth");
                                            toggleBtn.style.left = "20px";
                                        }
                                    }

                                    document.addEventListener("DOMContentLoaded", function () {
                                        var listFeedbackLink = document.getElementById("listFeedbackLink");
                                        var lecturerId = "${lec.lid}";
                                        var currentDate = new Date().toISOString().split('T')[0];
                                        listFeedbackLink.href = "list-feedback?lecturerId=" + lecturerId + "&dateFeedback=" + currentDate;
                                    });

                                    function openFeedbackModal(studentId) {
                                        document.getElementById('studentId').value = studentId;

                                        // Set default value for dateFeedback to current date
                                        var today = new Date();
                                        var dd = String(today.getDate()).padStart(2, '0');
                                        var mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
                                        var yyyy = today.getFullYear();
                                        var currentDate = yyyy + '-' + mm + '-' + dd;

                                        document.getElementById('dateFeedback').value = currentDate;

                                        $('#feedbackModal').modal('show');
                                    }

                                    function setCurrentDate() {
                                        var today = new Date();
                                        var dd = String(today.getDate()).padStart(2, '0');
                                        var mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
                                        var yyyy = today.getFullYear();
                                        var currentDate = yyyy + '-' + mm + '-' + dd;

                                        document.getElementById('displayDate').textContent = currentDate;
                                    }

                                    // Call setCurrentDate when the page loads
                                    window.onload = function () {
                                        setCurrentDate();
                                    };
        </script>
    </body>
</html>
