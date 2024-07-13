<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Update Parent Profile</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                margin-top: 20px;
                background-color: #f2f6fc;
                color: #69707a;
            }
            .img-account-profile {
                height: 10rem;
            }
            .rounded-circle {
                border-radius: 50% !important;
            }
            .card {
                box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
            }
            .card .card-header {
                font-weight: 500;
            }
            .card-header:first-child {
                border-radius: 0.35rem 0.35rem 0 0;
            }
            .card-header {
                padding: 1rem 1.35rem;
                margin-bottom: 0;
                background-color: rgba(33, 40, 50, 0.03);
                border-bottom: 1px solid rgba(33, 40, 50, 0.125);
            }
            .form-control {
                display: block;
                width: 100%;
                padding: 0.875rem 1.125rem;
                font-size: 0.875rem;
                font-weight: 400;
                line-height: 1;
                color: #69707a;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #c5ccd6;
                border-radius: 0.35rem;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }
            .nav-borders .nav-link.active {
                color: #0061f2;
                border-bottom-color: #0061f2;
            }
            .nav-borders .nav-link {
                color: #69707a;
                border-bottom-width: 0.125rem;
                border-bottom-style: solid;
                border-bottom-color: transparent;
                padding-top: 0.5rem;
                padding-bottom: 0.5rem;
                padding-left: 0;
                padding-right: 0;
                margin-left: 1rem;
                margin-right: 1rem;
            }
        </style>
    </head>
    <body>
        <div class="container-xl px-4 mt-4">
            <!-- Account page navigation-->
            
            <hr class="mt-0 mb-4">
            <div class="row">
                <div class="col-xl-4">
                    <!-- Profile picture card-->
                    <div class="card mb-4 mb-xl-0">
                        <div class="card-header">Parent Picture</div>
                        <div class="card-body text-center">
                            <!-- Profile picture image-->
                            <img class="img-account-profile rounded-circle mb-2" src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="">
                            <!-- Profile picture help block-->
                            <div class="small font-italic text-muted mb-4">JPG or PNG no larger than 5 MB</div>
                            <!-- Profile picture upload button-->
                            <button class="btn btn-primary" type="button">Upload new image</button>
                        </div>
                    </div>
                </div>
                <div class="col-xl-8">
                    <!-- Account details card-->
                    <div class="card mb-4">
                        <div class="card-header">Parent Details</div>
                        <div class="card-body">
                            <form action="update-profile" method="post">
                                <!-- Form Group (username)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="nickname">Nickname</label>
                                    <input class="form-control" id="nickname" type="text" name="nickname" value="${pa.nickname}" required>
                                </div>
                                <!-- Form Row-->
                                <div class="row gx-3 mb-3">
                                    <!-- Form Group (first name)-->
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="pname">Họ và tên</label>
                                        <input class="form-control" id="pname" type="text" name="pname" value="${pa.pname}" required>
                                    </div>
                                    <!-- Form Group (gender)-->
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="gender">Giới tính</label>
                                        <select class="form-control" id="gender" name="gender" required>
                                            <option value="true" ${pa.gender ? 'selected' : ''}>Nam</option>
                                            <option value="false" ${!pa.gender ? 'selected' : ''}>Nữ</option>
                                        </select>
                                    </div>
                                </div>
                                <!-- Form Row-->
                                <div class="row gx-3 mb-3">
                                    <!-- Form Group (dob)-->
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="dob">Ngày sinh</label>
                                        <input class="form-control" id="dob" type="date" name="dob" value="${pa.dob}" required>
                                    </div>
                                    <!-- Form Group (address)-->
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="address">Địa chỉ</label>
                                        <input class="form-control" id="address" type="text" name="address" value="${pa.address}" required>
                                    </div>
                                </div>
                                <!-- Form Row-->
                                <div class="row gx-3 mb-3">
                                    <!-- Form Group (phone number)-->
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="phoneNumber">Số điện thoại</label>
                                        <input class="form-control" id="phoneNumber" type="text" name="phoneNumber" value="${pa.phoneNumber}" required>
                                    </div>
                                    <!-- Form Group (email)-->
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="email">Email</label>
                                        <input class="form-control" id="email" type="email" name="email" value="${pa.email}" required>
                                    </div>
                                </div>
                                <!-- Form Group (IDcard)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="IDcard">Căn cước công dân</label>
                                    <input class="form-control" id="IDcard" type="text" name="IDcard" value="${pa.IDcard}" required>
                                </div>
                                <!-- Save changes button-->
                                <button class="btn btn-primary" type="submit">Update</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
