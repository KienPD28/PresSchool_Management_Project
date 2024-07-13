<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inactive Students</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="text-center">Inactive Students</h2>

            <div class="row mt-3">
                <div class="col-sm-6">
                    <form class="form-inline" action="search-student-inactive" method="GET">
                        <input class="form-control mr-sm-2" type="search" name="searchInput" placeholder="Search inactive student..." required>
                        <button class="btn btn-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </div>
                <div class="col-sm-6 text-right">
                     <button class="btn btn-secondary" onclick="window.location.href='student'">Back to Student</button>
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
                        <c:when test="${not empty inactive}">
                            <c:forEach var="studentClass" items="${inactive}" varStatus="status">
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
                                        <button type="button" class="btn btn-warning btn-sm" onclick="showUpdateModal('${studentClass.stuid.stuid}')">Update</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
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
                                        <button type="button" class="btn btn-warning btn-sm" onclick="showUpdateModal('${studentClass.stuid.stuid}')">Update</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>

             <!-- Modal Update Parent Status -->
            <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="updateModalLabel">Update Student Status</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form id="updateStudentForm" action="update-inactive-student" method="post">
                            <div class="modal-body">
                                <input type="hidden" id="stuid" name="stuid">
                                <div class="form-group">
                                    <label for="statusSelect"> Status:</label>
                                    <select class="form-control" id="statusSelect" name="status">
                                        <option value="true" ${param.status == 'true' ? 'selected' : ''}>Active</option>
                                        <option value="false" ${param.status == 'false' ? 'selected' : ''}>Inactive</option>
                                    </select>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" onclick="window.history.back()">Cancel</button>
                                <button type="submit" class="btn btn-danger">Update</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
                                    
            <!-- Paging  -->
            <div class="d-flex justify-content-center Endpage">
                <c:if test="${!empty inactive}">
                    <c:if test="${index > 1}">
                        <button class="page-btn" onclick="window.location.href = 'inactive-student?index=${index - 1}'">Previous</button>
                    </c:if>
                    <c:forEach begin="1" end="${endPage}" var="i">
                        <button class="page-btn ${i == index ? 'active' : ''}" onclick="window.location.href = 'inactive-student?index=${i}'">${i}</button>
                    </c:forEach>
                    <c:if test="${index < endPage}">
                        <button class="page-btn" onclick="window.location.href = 'inactive-student?index=${index + 1}'">Next</button>
                    </c:if>
                </c:if>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
        <script src="script.js"></script>
        
       <script>
                               let updateStudentId; // store the ID of the student to be updated

                               // Function to display status update modal
                               function showUpdateModal(stuid) {
                                   updateStudentId = stuid; // Save student ID
                                   $('#stuid').val(stuid); // Set the value of hidden input
                                   $('#updateModal').modal('show'); // display modal
                               }
            </script>

    </body>
</html>
