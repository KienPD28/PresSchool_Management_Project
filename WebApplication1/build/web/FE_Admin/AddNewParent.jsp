<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Parent</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
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
            <h2 class="text-center">Add New Parent</h2>
            <form id="parentForm" action="add-parent" method="post">
                <div class="form-group">
                    <label for="pname">Parent Name</label>
                    <input type="text" class="form-control" id="pname" name="pname" required>
                </div>
                <div class="form-group">
                    <label for="pgender">Gender</label>
                    <select class="form-control" id="pgender" name="gender" required>
                        <option value="true">Male</option>
                        <option value="false">Female</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="pdob">Date of Birth</label>
                    <input type="date" class="form-control" id="pdob" name="dob" required>
                    <% if (request.getAttribute("dobError") != null) { %>
                    <span class="text-danger"><%= request.getAttribute("dobError") %></span>
                    <% } %>
                </div>
                <div class="form-group">
                    <label for="pphone">Phone Number</label>
                    <input type="number" class="form-control" id="pphone" name="phoneNumber" required>
                </div>
                <div class="form-group">
                    <label for="pidCard">ID Card</label>
                    <input type="number" class="form-control" id="pidCard" name="IDcard" required>
                </div>
                <div class="form-group">
                    <label for="pemail">Email</label>
                    <input type="email" class="form-control" id="pemail" name="email" required>
                    <% if (request.getAttribute("emailError") != null) { %>
                    <span id="emailError" class="text-danger"><%= request.getAttribute("emailError") %></span>
                    <% } %>
                </div>
                <div class="form-group">
                    <label for="paddress">Address</label>
                    <input type="text" class="form-control" id="paddress" name="address" required>
                </div>
                <div class="form-group">
                    <label for="nickname">Nickname</label>
                    <input type="text" class="form-control" id="nickname" name="nickname" required>
                </div>
                <% String Error = (String) request.getAttribute("Error");
                if (Error != null) { %>
                <p class="text-danger" id="message">${Error}</p>
                <% } %>
                <div class="modal-footer">
                <button type="button" class="btn btn-secondary" onclick="window.history.back()">Cancel</button>
                <button type="submit" class="btn btn-danger">Add Parent</button>


            </div>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
