
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Student</title>
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
            <h2 class="text-center">Update Student</h2>


            <form id="updateStudentForm" action="update-student" method="post">
                <input type="hidden" id="editStudentId" name="stuid" value="${param.stuid}">
                <div class="form-group">
                    <label for="editStudentName">Student Name</label>
                    <input type="text" class="form-control" id="editStudentName" name="sname" value="${param.sname}" required>
                </div>
                <% String nameError = (String) request.getAttribute("nameError");
                            if (nameError != null) { %>
                <p style="color: red" id="message">${nameError}</p>
                <% } %>
                <div class="form-group">
                    <label for="editStudentDob">Date of Birth</label>
                    <input type="date" class="form-control" id="editStudentDob" name="dob" value="${param.dob}" required>
                </div>
                <% String dobError = (String) request.getAttribute("dobError");
                            if (dobError != null) { %>
                <p style="color: red" id="message">${dobError}</p>
                <% } %>
                <div class="form-group">
                    <label for="editStudentGender">Gender</label>
                    <select class="form-control" id="editStudentGender" name="gender" required>
                        <option value="true" ${param.gender == 'true' ? 'selected' : ''}>Male</option>
                        <option value="false" ${param.gender == 'false' ? 'selected' : ''}>Female</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="editStudentAddress">Address</label>
                    <input type="text" class="form-control" id="editStudentAddress" name="address" value="${param.address}" required>
                </div>
                <% String addressError = (String) request.getAttribute("addressError");
                            if (addressError != null) { %>
                <p style="color: red" id="message">${addressError}</p>
                <% } %>
                <div class="form-group">
                    <label for="editStudentClassName">Class</label>
                    <select class="form-control" id="editStudentClassName" name="className" required>
                      <c:forEach var="classSession" items="${classIDs}">
                                <option value="${classSession.classID.classid}" <c:if test="${param.classid == classSession.classID.classid}">selected</c:if>>
                                    ${classSession.classID.clname} 
                                </option>
                            </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="editStudentStatus">Status</label>
                    <select class="form-control" id="editStudentStatus" name="status" required>
                        <option value="true" ${param.status == 'true' ? 'selected' : ''}>Active</option>
                        <option value="false" ${param.status == 'false' ? 'selected' : ''}>Inactive</option>
                    </select>
                </div>

                <% String classError = (String) request.getAttribute("classError");
                            if (classError != null) { %>
                <p style="color: red" id="message">${classError}</p>
                <% } %>



                <div class="modal-footer">

                    <button type="button" class="btn btn-secondary" onclick="window.history.back()">Cancel</button>
                    <button type="submit" id="confirmUpdateBtn" class="btn btn-danger">Update</button>
                </div>
                <script type="text/javascript">
                    function hideMessage() {
                        var messageElement = document.getElementById("message");
                        if (messageElement) {
                            setTimeout(function () {
                                messageElement.style.display = "none";
                            }, 2000);
                        }
                    }
                    window.onload = hideMessage;
                </script>  

            </form>

        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
