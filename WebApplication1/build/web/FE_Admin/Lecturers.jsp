<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Management</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Additional custom styles if needed */
        .header-buttons .form-control {
            max-width: 200px;
        }
        /* Make the container and table wider */
        .container {
            max-width: 100% !important;
            padding: 0;
        }
        table {
            width: 100%;
        }
    </style>
    <script>
        function confirmDelete(lid) {
            if (confirm("Are you sure you want to delete this lecturer?")) {
                window.location.href = 'delete-lecturer?lid=' + lid;
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <header class="d-flex justify-content-between align-items-center bg-dark text-light p-3 mt-4">
            <div class="date-range fs-5">
                <span id="dateStart">${sc.dateStart}</span> - <span id="dateEnd">${sc.dateEnd}</span>
            </div>
            <div class="header-buttons d-flex">
                <div class="input-group me-2">
                    <input type="text" class="form-control" placeholder="Search">
                    <button class="btn btn-success" onclick="search()">Search</button>
                </div>
                <button class="btn btn-primary mx-2" onclick="window.location.href = 'add-lecturer'">Add</button>
                <button class="btn btn-secondary" onclick="window.location.href = 'history?yid=${sc.yid}'">History</button>
            </div>
        </header>
        <main class="mt-4">
            <table class="table table-bordered table-hover">
                <thead class="thead-light">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Gender</th>
                        <th>DOB</th>
                        <th>Phone Number</th>
                        <th>ID Card</th>
                        <th>Address</th>
                        <th>Email</th>
                        <th>Class Name</th>
                        <th class="text-center">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="lcs" items="${requestScope.list}">
                        <tr>
                            <td>${lcs.lid.lid}</td>
                            <td>${lcs.lid.lname}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${lcs.lid.gender}">
                                        Nam
                                    </c:when>
                                    <c:otherwise>
                                        Nữ
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${lcs.lid.dob}</td>
                            <td>${lcs.lid.phoneNumber}</td>
                            <td>${lcs.lid.IDcard}</td>
                            <td>${lcs.lid.address}</td>
                            <td>${lcs.lid.email}</td>
                            <td>${lcs.csid.classID.clname}</td>
                            <td class="actions text-center">
                                <div class="btn-group" role="group">
                                    <button class="btn btn-sm btn-warning" onclick="window.location.href = 'update-lecturers?lid=${lcs.lid.lid}'">Update</button>
                                    <button class="btn btn-sm btn-danger ml-2" onclick="confirmDelete('${lcs.lid.lid}')">Delete</button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </main>
    </div>

    <!-- Bootstrap JS and dependencies if needed -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
