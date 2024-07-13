<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>History SchoolYear Students</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .btn-campus {
                background-color: #39BACD;
                color: white;
                border: none;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
                transition: all 0.3s ease;
            }
            .btn-campus:hover {
                background-color: #39BACD;
            }
            .custom-link:active {
                font-weight: bold;
            }
            .content-wrapper {
                max-width: 1200px;
                margin: auto;
                padding: 20px;
            }
            .table-responsive {
                max-height: 400px;
                overflow-y: auto;
            }
        </style>
    </head>
    <body class="container mt-5">
        <div class="content-wrapper">
            <div class="mb-3">
                <button class="btn btn-campus" onclick="window.location.href = 'classController'">Back To List</button>
            </div>
        </div>
        <h1>History SchoolYear Students</h1>

        <form id="studentForm" action="historyschoolyear" method="GET">
            <div class="form-group">
                <label for="studentSelect">Select a Student:</label>
                <select id="studentSelect" name="selectedStudent" class="form-control" onchange="this.form.submit()">
                    <option value="">Select a student</option>
                    <c:forEach var="stu" items="${sessionScope.students}">
                        <option value="${stu.stuid}" <c:if test="${stu.stuid == selectedStuid}">selected="selected"</c:if>>${stu.stuid} - ${stu.sname}</option>
                    </c:forEach>
                </select>
            </div>
        </form>

        <c:if test="${not empty history}">
            <h2>History for Student ID: ${sessionScope.selectedStuid}</h2>
            <input type="hidden" name="csid" value="${scs.csid}">
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>Student ID</th>
                        <th>Student Name</th>
                        <th>Date Of Birth</th>
                        <th>Age</th>
                        <th>Session Name</th>
                        <th>Class</th>
                        <th>Date Start</th>
                        <th>Date End</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="scs" items="${sessionScope.history}">
                        <tr>
                            <td>${scs.stuid.stuid}</td>
                            <td>${scs.stuid.sname}</td>
                            <td>${scs.stuid.dob}</td>
                            <td>${scs.csid.sid.age.ageid} tuổi</td>
                            <td>${scs.csid.sid.sname}</td>
                            <td>${scs.csid.classID.clname}</td>
                            <td>${scs.csid.yid.dateStart}</td>
                            <td>${scs.csid.yid.dateEnd}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty sessionScope.history}">
            <div class="alert alert-info" role="alert">
                There is no school year history available for this student.
            </div>
        </c:if>
    </body>
</html>
