<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lecturer Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            background-color: #f0f8ff;
            font-family: Arial, sans-serif;
        }
        .info-group {
            margin-bottom: 1rem;
        }
        .info-group label {
            font-weight: bold;
            margin-bottom: 0.25rem;
            color: #333;
        }
        .info-group p {
            margin-bottom: 0;
            color: #666;
        }
        .card {
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }
        .card-header {
            background-color: #d3d3d3;
            color: black;
            padding: 1rem;
            border-radius: 10px 10px 0 0;
        }
        .card-body {
            padding: 2rem;
        }
        .sidebar {
            background-color: #d3d3d3;
            color: black;
            padding: 1rem;
            position: fixed;
            top: 0;
            left: 0;
            height: 100%;
            width: 200px;
            padding-top: 20px;
        }
        .sidebar a {
            color: black;
            display: block;
            margin: 50px 0;
            text-decoration: none;
            font-weight: bold;
        }
        .sidebar a:hover {
            text-decoration: none;
            color: #d1ecf1;
        }
        .content {
            margin-left: 220px;
            padding: 20px;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <h2>Dashboard</h2>
        <a  href="update-lecturers?lid=${lecturers.lid}&lname=${lecturers.lname}&dob=${lecturers.dob}&phoneNumber=${lecturers.phoneNumber}&IDcard=${lecturers.IDcard}&address=${lecturers.address}&email=${lecturers.email}&nickname=${lecturers.nickname}">Update Profile</a>
        <a href="changepass">Change Password</a>
    </div>
    <div class="content">
        <div class="container mt-3">
            <form id="lecturersForm" action="lecturers-profile" method="post">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h2>Lecturers Information</h2>
                            </div>
                            <div class="card-body">
                                <div class="info-group">
                                    <label>ID</label>
                                    <p>${lecturers.lid}</p>
                                </div>
                                <div class="info-group">
                                    <label>Name</label>
                                    <p>${lecturers.lname}</p>
                                </div>
                                <div class="info-group">
                                    <label>Gender</label>
                                    <p>${lecturers.gender}</p>
                                </div>
                                <div class="info-group">
                                    <label>Date of Birth</label>
                                    <p>${lecturers.dob}</p>
                                </div>
                                <div class="info-group">
                                    <label>Phone Number</label>
                                    <p>${lecturers.phoneNumber}</p>
                                </div>
                                <div class="info-group">
                                    <label>ID Card</label>
                                    <p>${lecturers.IDcard}</p>
                                </div>
                                <div class="info-group">
                                    <label>Address</label>
                                    <p>${lecturers.address}</p>
                                </div>
                                <div class="info-group">
                                    <label>Email</label>
                                    <p>${lecturers.email}</p>
                                </div>
                                <div class="info-group">
                                    <label>Nickname</label>
                                    <p>${lecturers.nickname}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
