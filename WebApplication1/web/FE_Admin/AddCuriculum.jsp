<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Curriculum</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="styles.css">
        <style>
            body {
                background-color: #b3d9ff;
            }
            .form-group label {
                font-weight: bold;
                color: #333;
            }
            .card {
                border-radius: 10px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .card-header {
                background-color: #f0f9ff;
                padding: 1rem;
                border-radius: 10px 10px 0 0;
            }
            .card-body {
                padding: 2rem;
            }
            input[type=number]::-webkit-outer-spin-button,
            input[type=number]::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }
            input[type=number] {
                -moz-appearance: textfield;
            }
        </style>
    </head>
    <body>
        <div class="container mt-3">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <div class="card">
                        <div class="card-header">
                            <h2>Add Curriculum</h2>
                        </div>
                        <button onclick="window.location.href = 'session-detail?sid=${param.sid}&sdid=${param.sdid}'">Back To Session Detail</button>
                        <div class="card-body">
                            <c:if test="${not empty errorMessage}">
                                <div class="alert alert-danger">${errorMessage}</div>
                            </c:if>
                            <form action="add-curiculum" method="POST">
                                 <c:if test="${not empty requestScope.success}">
                                    <div class="alert alert-success" role="alert">
                                        ${requestScope.success}
                                    </div>
                                </c:if>
                                <c:if test="${not empty param.sid}">
                                    <input type="hidden" name="sid" value="${param.sid}" >
                                 </c:if>
                                <c:if test="${not empty param.sdid}">
                                    <input type="hidden" name="sdid" value="${param.sdid}">
                                </c:if>
                                <div class="form-group">
                                    <label for="nameAct">Tên Hoạt Động</label>
                                    <input type="text" class="form-control" id="nameAct" name="nameAct" required>
                                </div>
                                <div class="form-group">
                                    <label for="isFix">Loại Hoạt Động</label>
                                    <select class="form-control" id="isFix" name="isFix" required>
                                        <option value="true">Hoạt động cứng</option>
                                        <option value="false">Hoạt động bình thường</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="timeSlot">Khung Giờ Học</label>
                                    <select class="form-control" id="timeSlot" name="timeSlot" required>
                                        <option value="07:30-09:00">07:30 - 09:00</option>
                                        <option value="09:00-11:00">09:00 - 11:00</option>
                                        <option value="11:00-13:00">11:00 - 13:00</option>
                                        <option value="13:00-15:00">13:00 - 15:00</option>
                                        <option value="15:00-16:30">15:00 - 16:30</option>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Add</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
