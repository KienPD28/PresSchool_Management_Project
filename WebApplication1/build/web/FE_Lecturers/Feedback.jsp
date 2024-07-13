<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Feedback Management</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            .header {
                background-color: orange;
                padding: 10px;
                color: white;
                text-align: center;
            }
            .header a {
                color: white;
                margin: 0 15px;
            }
            .header a:hover {
                text-decoration: none;
            }
            .table img {
                width: 100px;
                height: 100px;
            }
            .btn {
                margin: 0 5px;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="text-center">Feedback Management</h2>

            <!-- Display error message if present -->
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger">${errorMessage}</div>
            </c:if>
            <table class="table table-bordered">
                <thead class="thead-light">
                    <tr>
                        <th>Student Name</th>
                        <th>Feedback Title</th>
                        <th>Feedback Content</th>
                        <th>Feedback Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="feedback" items="${feedback}">
                        <tr>
                            <td>${feedback.stuid.sname}</td>
                            <td>${feedback.ftitle}</td>
                            <td>${feedback.fcontent}</td>
                            <td>${feedback.dateFeedback}</td>
                            <td>
                                <button class="btn btn-warning btn-sm" onclick="editFeedback('${feedback.fid}', '${feedback.stuid.stuid}', '${feedback.stuid.sname}', '${feedback.ftitle}', '${feedback.fcontent}', '${feedback.dateFeedback}')">Update</button>
                                <button type="button" class="btn btn-danger btn-sm" onclick="deleteFeedback('${feedback.fid}')">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Update Feedback Modal -->
        <div class="modal fade" id="updateFeedbackModal" tabindex="-1" aria-labelledby="updateFeedbackModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="updateFeedbackModalLabel">Update Feedback</h5>
                    </div>
                    <div class="modal-body"> 
                        <form id="updateFeedbackForm" action="update-feedback" method="POST">
                            <input type="hidden" id="lid" name="lid" value="${lec.lid}">
                            <div class="form-group">
                                <label for="updateFeedbackId">Feedback ID</label>
                                <input type="text" class="form-control" name="fid" id="updateFeedbackId" readonly>
                            </div>
                            <div class="form-group">
                                <label for="updateStudentId">Student ID</label>
                                <input type="text" class="form-control" name="stuid" id="updateStudentId" readonly>
                            </div>
                            <div class="form-group">
                                <label for="updateStudentName">Student Name</label>
                                <input type="text" class="form-control" name="sname" id="updateStudentName" readonly>
                            </div>
                            <div class="form-group">
                                <label for="updateFeedbackTitle">Feedback Title</label>
                                <input type="text" class="form-control" name="ftitle" id="updateFeedbackTitle" required>
                            </div>
                            <div class="form-group">
                                <label for="updateFeedbackContent">Feedback Content</label>
                                <textarea class="form-control" name="fcontent" id="updateFeedbackContent" rows="3" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="updateFeedbackDate">Feedback Date</label>
                                <input type="date" class="form-control" name="dateFeedback" id="updateFeedbackDate" value="${currentDate}" readonly>
                            </div>         
                            <button type="submit" class="btn btn-primary mt-3">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Delete Feedback Modal -->
        <div class="modal fade" id="deleteFeedbackModal" tabindex="-1" aria-labelledby="deleteFeedbackModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteFeedbackModalLabel">Delete Feedback</h5>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to delete this feedback item?</p>
                        <form id="deleteFeedbackForm" action="delete-feedback" method="post">
                            <input type="hidden" name="fid" id="deleteFeedbackId">
                            <input type="hidden" id="lid" name="lid" value="${lec.lid}">
                            <input type="hidden" name="dateFeedback" value="<%= request.getParameter("dateFeedback") %>">
                            <button type="submit" class="btn btn-danger">Delete</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
        <script>
                                    let deleteFeedbackId;

                                    function deleteFeedback(feedbackId) {
                                        document.getElementById('deleteFeedbackId').value = feedbackId;
                                        $('#deleteFeedbackModal').modal('show');
                                    }



                                    function editFeedback(fid, stuid, sname, ftitle, fcontent, currentDate) {
                                        document.getElementById('updateFeedbackId').value = fid;
                                        document.getElementById('updateStudentId').value = stuid;
                                        document.getElementById('updateStudentName').value = sname;
                                        document.getElementById('updateFeedbackTitle').value = ftitle;
                                        document.getElementById('updateFeedbackContent').value = fcontent;
                                        document.getElementById('updateFeedbackDate').value = currentDate;
                                        $('#updateFeedbackModal').modal('show');
                                    }

                                    document.getElementById('confirmDeleteFeedbackBtn').addEventListener('click', function () {
                                        const form = document.createElement('form');
                                        form.method = 'POST';
                                        form.action = 'delete-feedback';
                                        const input = document.createElement('input');
                                        input.type = 'hidden';
                                        input.name = 'fid';
                                        input.value = deleteFeedbackId;
                                        form.appendChild(input);
                                        document.body.appendChild(form);
                                        form.submit();
                                    });
                                    
        </script>
    </body>
</html>
