<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account List</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
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

            .toggle-password {
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div class="content-wrapper">
            <div class="mb-3">
                <button class="btn btn-campus" onclick="window.location.href = 'account-list'">Back</button>
            </div>
            <h2>Assign Roles to New Accounts</h2>
            <form id="accountForm" action="authentication-account" method="POST">
                <div class="">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Account ID</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Role</th>
                                <th>Parent Name</th>
                                <th>Lecturer Name</th>
                                <th>Action</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="acc" items="${sessionScope.newAccountList}" varStatus="idex">
                                <tr>
                                    <td>${idex.index + 1}</td>
                                    <td>${acc.aid}</td>
                                    <td>${acc.username}</td>

                                    <td>
                                        <div class="input-group">
                                            <input type="password" id="password-${idex.index}" value="${acc.password}" class="form-control" readonly>
                                            <div class="input-group-append">
                                                <span class="input-group-text">
                                                    <i id="togglePassword-${idex.index}" class="fa fa-eye toggle-password" onclick="togglePassword(${idex.index})"></i>
                                                </span>
                                            </div>
                                        </div>
                                    </td>

                                    <td>
                                        <select name="role-${acc.aid}" class="form-control" onchange="handleRoleChange(this, ${idex.index})">
                                            <option value="">Select Role</option>
                                            <option value="1" ${acc.role == 1 ? "selected" : ""}>Parent</option>
                                            <option value="2" ${acc.role == 2 ? "selected" : ""}>Lecturers</option>
                                        </select>
                                    </td>

                                    <td>
                                        <select name="pid-${acc.aid}" id="pid-${idex.index}" class="form-control" ${acc.role == 2 || acc.role == 3 ? "disabled" : ""}>
                                            <option value="">Select PID</option>
                                            <c:forEach var="parent" items="${sessionScope.availableParents}">
                                                <option value="${parent.pid}" ${acc.pid != null && parent.pid == acc.pid.pid ? "selected" : ""}>${parent.pname}</option>
                                            </c:forEach>
                                        </select>
                                    </td>

                                    <td>
                                        <select name="lid-${acc.aid}" id="lid-${idex.index}" class="form-control" ${acc.role == 1 || acc.role == 3 ? "disabled" : ""}>
                                            <option value="">Select LID</option>
                                            <c:forEach var="lecturer" items="${sessionScope.availableLecturers}">
                                                <option value="${lecturer.lid}" ${acc.lid != null && lecturer.lid == acc.lid.lid ? "selected" : ""}>${lecturer.lname}</option>
                                            </c:forEach>
                                        </select>
                                    </td>

                                    <td>
                                        <input type="hidden" name="accountId" value="${acc.aid}">
                                        <button type="submit" class="btn btn-campus" onclick="return validateForm(${acc.aid})">Active</button>
                                    </td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>

        <script>
            function handleRoleChange(select, index) {
                var role = select.value;
                var pidField = document.getElementById('pid-' + index);
                var lidField = document.getElementById('lid-' + index);

                if (role == '1') {
                    pidField.disabled = false;
                    lidField.disabled = true;
                    lidField.value = '';
                } else if (role == '2') {
                    pidField.disabled = true;
                    lidField.disabled = false;
                    pidField.value = '';
                } else if (role == '3') {
                    pidField.disabled = true;
                    lidField.disabled = true;
                    pidField.value = '';
                    lidField.value = '';
                } else {
                    pidField.disabled = true;
                    lidField.disabled = true;
                }
            }

            function togglePassword(id) {
                var passwordField = document.getElementById('password-' + id);
                var icon = document.getElementById('togglePassword-' + id);

                if (passwordField.type === "password") {
                    passwordField.type = "text";
                    icon.classList.remove("fa-eye");
                    icon.classList.add("fa-eye-slash");
                } else {
                    passwordField.type = "password";
                    icon.classList.remove("fa-eye-slash");
                    icon.classList.add("fa-eye");
                }
            }

            function validateForm(accountId) {
                var select = document.querySelector('select[name="role-' + accountId + '"]');
                var pidSelect = document.querySelector('select[name="pid-' + accountId + '"]');
                var lidSelect = document.querySelector('select[name="lid-' + accountId + '"]');

                if (select.value === "") {
                    alert("Please select a role for Account ID: " + accountId);
                    return false;
                } else if ((select.value === "1" && pidSelect.value === "") || (select.value === "2" && lidSelect.value === "")) {
                    alert("Please select " + (select.value === "1" ? "PID" : "LID") + " for Account ID: " + accountId);
                    return false;
                }

                return true;
            }




            window.onload = function () {
                var selects = document.querySelectorAll('select[name^="role-"]');
                for (var i = 0; i < selects.length; i++) {
                    handleRoleChange(selects[i], i);
                }
            }

        </script>
    </body>
</html>
