<%-- 
    Document   : CRUD_Student
    Created on : May 27, 2024, 12:41:24 PM
    Author     : NGUYEN THI KHANH VI
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="styles.css">
        <style>
            body {
                background-color: #f8f9fa;
                font-family: 'Arial', sans-serif;
            }

            h2 {
                margin-bottom: 20px;
            }

            .table th, .table td {
                vertical-align: middle;
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
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="text-center">Student Management</h2>

            <div class="row mt-3">
                <div class="col-sm-6">
                    <form class="form-inline" action="search-student" method="GET">
                        <input class="form-control mr-sm-2" type="search" name="searchInput" placeholder="Search student..." required>
                        <button class="btn btn-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </div>
                <div class="col-sm-6 text-right">
                    <a href="parent" class="btn btn-primary" id="parentManagementBtn">Parent Management</a>
                    <a href="add-student" class="btn btn-primary" id="addNewStudentBtn">Add New Student</a>
                    <a href="inactive-student" class="btn btn-primary" id="viewInactiveStudentsBtn">Inactive Students</a>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-auto">
                    <form action="student" method="GET" class="form-inline mb-3">
                        <label for="classSelect" class="mr-2">Select Class:</label>
                        <select name="classId" id="classSelect" class="form-control" onchange="this.form.submit()">
                            <option value="">Select Class</option>
                            <c:forEach var="classSession" items="${classIDs}">
                                <option value="${classSession.classID.classid}" <c:if test="${param.classId == classSession.classID.classid}">selected</c:if>>
                                    ${classSession.classID.clname}
                                </option>
                            </c:forEach>
                        </select>
                    </form>
                </div>
            </div>

            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Student ID</th>
                        <th>Student Name</th>
                        <th>DOB</th>
                        <th>Gender</th>
                        <th>Address</th>
                        <th>Parent Name</th>
                        <th>Class Name</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty search}">
                            <c:forEach var="studentClass" items="${search}" varStatus="status">
                                <tr>
                                    <td>${status.index + 1}</td>
                                    <td>${studentClass.stuid.stuid}</td>
                                    <td>${studentClass.stuid.sname}</td>
                                    <td>${studentClass.stuid.dob}</td>
                                    <td>${studentClass.stuid.gender ? 'Male' : 'Female'}</td>
                                    <td>${studentClass.stuid.address}</td>
                                    <td>${studentClass.stuid.pid.pname}</td>
                                    <td>${studentClass.csid.classID.clname}</td>
                                    <td>
                                        <a class="btn btn-warning btn-sm" href="update-student?stuid=${studentClass.stuid.stuid}&sname=${studentClass.stuid.sname}&dob=${studentClass.stuid.dob}&gender=${studentClass.stuid.gender}&address=${studentClass.stuid.address}&classid=${studentClass.csid.classID.classid}">Update</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:when test="${not empty allStudent}">
                            <c:forEach var="studentClass" items="${allStudent}" varStatus="status">
                                <tr>
                                    <td>${status.index + 1 + (index - 1) * 10}</td>
                                    <td>${studentClass.stuid.stuid}</td>
                                    <td>${studentClass.stuid.sname}</td>
                                    <td>${studentClass.stuid.dob}</td>
                                    <td>${studentClass.stuid.gender ? 'Male' : 'Female'}</td>
                                    <td>${studentClass.stuid.address}</td>
                                    <td>${studentClass.stuid.pid.pname}</td>
                                    <td>${studentClass.csid.classID.clname}</td>
                                    <td>
                                        <a class="btn btn-warning btn-sm" href="update-student?stuid=${studentClass.stuid.stuid}&sname=${studentClass.stuid.sname}&dob=${studentClass.stuid.dob}&gender=${studentClass.stuid.gender}&address=${studentClass.stuid.address}&classid=${studentClass.csid.classID.classid}">Update</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:when test="${not empty studentList}">
                            <c:forEach var="studentClass" items="${studentList}" varStatus="status">
                                <tr>
                                    <td>${status.index + 1 + (index - 1) * 10}</td>
                                    <td>${studentClass.stuid.stuid}</td>
                                    <td>${studentClass.stuid.sname}</td>
                                    <td>${studentClass.stuid.dob}</td>
                                    <td>${studentClass.stuid.gender ? 'Male' : 'Female'}</td>
                                    <td>${studentClass.stuid.address}</td>
                                    <td>${studentClass.stuid.pid.pname}</td>
                                    <td>${studentClass.csid.classID.clname}</td>
                                    <td>
                                        <a class="btn btn-warning btn-sm" href="update-student?stuid=${studentClass.stuid.stuid}&sname=${studentClass.stuid.sname}&dob=${studentClass.stuid.dob}&gender=${studentClass.stuid.gender}&address=${studentClass.stuid.address}&classid=${studentClass.csid.classID.classid}">Update</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </tbody>
            </table>





            <!-- Paging controls for all students -->
            <div class="d-flex justify-content-center Endpage">
                <c:if test="${!empty allStudent}">
                    <c:if test="${index > 1}">
                        <button class="page-btn" onclick="window.location.href = 'student?index=${index - 1}'">Previous</button>
                    </c:if>
                    <c:forEach begin="1" end="${endPage}" var="i">
                        <button class="page-btn ${i == index ? 'active' : ''}" onclick="window.location.href = 'student?index=${i}'">${i}</button>
                    </c:forEach>
                    <c:if test="${index < endPage}">
                        <button class="page-btn" onclick="window.location.href = 'student?index=${index + 1}'">Next</button>
                    </c:if>
                </c:if>
            </div>

            <!-- Paging controls for students of a specific class -->
            <div class="d-flex justify-content-center Endpage">
                <c:if test="${!empty studentList}">
                    <c:if test="${index > 1}">
                        <button class="page-btn" onclick="window.location.href = 'student?classId=${classId}&index=${index - 1}'">Previous</button>
                    </c:if>
                    <c:forEach begin="1" end="${endPage}" var="i">
                        <button class="page-btn ${i == index ? 'active' : ''}" onclick="window.location.href = 'student?classId=${classId}&index=${i}'">${i}</button>
                    </c:forEach>
                    <c:if test="${index < endPage}">
                        <button class="page-btn" onclick="window.location.href = 'student?classId=${classId}&index=${index + 1}'">Next</button>
                    </c:if>
                </c:if>
            </div>

            <style>
                .Endpage {
                    margin-top: 10px;
                }
                .page-btn {
                    margin: 0 5px;
                    padding: 5px 10px;
                    background-color: #007bff;
                    border: none;
                    color: white;
                    cursor: pointer;
                    border-radius: 5px;
                    transition: background-color 0.3s ease;
                }
                .page-btn:hover {
                    background-color: #0056b3;
                }
                .page-btn.active {
                    background-color: #0056b3;
                    font-weight: bold;
                }
            </style>

            <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
            <script src="script.js"></script>


    </body>
</html>
