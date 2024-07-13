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
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

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

            .input-group-append .btn {
                border-top-left-radius: 0;
                border-bottom-left-radius: 0;
            }

            .input-group-append .btn i {
                font-size: 1rem;
            }

            .input-group-append .btn:focus {
                box-shadow: none;
            }

            /* CSS cho trạng thái Active */
            .badge-success {
                background-color: green;
            }

            /* CSS cho trạng thái Pending */
            .badge-warning {
                background-color: yellow;
            }

        </style>
    </head>
    <body>
        <div class="content-wrapper">
            <h1>Account List</h1>
            <form action="account-list" method="GET">
                <div class="form-group">
                    <label for="roleSelect">Select Role:</label>
                    <select class="form-control" id="roleSelect" name="role">
                        <option value="">All Roles</option>
                        <option value="1" ${1 == requestScope.role ? 'selected' : ''}>Parent</option>
                        <option value="2" ${2 == requestScope.role ? 'selected' : ''}>Lecturers</option>
                        <option value="3" ${3 == requestScope.role ? 'selected' : ''}>Admin</option>
                    </select>
                </div>
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Search..." name="searchName"
                           value="${param.searchName}">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="submit">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
                <button type="submit" class="btn btn-campus">Show Accounts</button>
            </form>

            <div class="mb-3">
                <button class="btn btn-campus" onclick="window.location.href = 'authentication-account'">Assign Permissions to Account</button>
            </div>

            <div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Account ID</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Role</th>
                            <th>PID</th>
                            <th>Parent Name</th>
                            <th>LID</th>
                            <th>Lecturer Name</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="acc" items="${sessionScope.accountList}" varStatus="idex">
                            
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

                                <td>${acc.role}</td>
                                <td>${acc.pid != null ? acc.pid.pid : ''}</td>
                                <td>${acc.pid != null ? acc.pid.pname : ''}</td>
                                <td>${acc.lid != null ? acc.lid.lid : ''}</td>
                                <td>${acc.lid != null ? acc.lid.lname : ''}</td>

                                <td>
                                    <button type="button" class="btn btn-danger" onclick="deleteAccount(${acc.aid}, ${acc.role})">Delete</button>

                                </td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <script>
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

            // Xóa tài khoản
            function deleteAccount(aid, role) {
                if (role === 3) {
                    // Nếu vai trò là "Admin", hiển thị cảnh báo và không thực hiện xóa
                    Swal.fire({
                        title: "Unable to Delete",
                        text: "You cannot delete an admin account.",
                        icon: "error"
                    });
                } else {
                    // Nếu vai trò không phải là "Admin", hiển thị cảnh báo và xác nhận xóa tài khoản
                    Swal.fire({
                        title: "Are you sure?",
                        text: "You won't be able to revert this!",
                        icon: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#3085d6",
                        cancelButtonColor: "#d33",
                        confirmButtonText: "Yes, delete it!"
                    }).then((result) => {
                        if (result.isConfirmed) {
                            Swal.fire({
                                title: "Deleted!",
                                text: "Your Account has been deleted.",
                                icon: "success"
                            }).then(() => {
                                window.location.href = 'account-list?action=delete&aid=' + aid;
                            });
                        }
                    });
                }
            }

        </script>
    </body>
</html>
