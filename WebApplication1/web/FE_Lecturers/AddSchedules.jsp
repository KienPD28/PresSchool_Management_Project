<%-- 
    Document   : AddSchedules
    Created on : Jun 15, 2024, 2:29:27 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Schedules</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f0f0f0;
            }
            h2, h3 {
                color: #333;
                text-align: center;
            }
            form {
                max-width: 600px;
                margin: 20px auto;
                padding: 20px;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }
            select, input[type="text"], input[type="submit"] {
                display: block;
                width: 100%;
                margin: 10px 0;
                padding: 10px;
                font-size: 16px;
            }
            table {
                width: 80%;
                margin: 20px auto;
                border-collapse: collapse;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }
            th, td {
                padding: 10px;
                border: 1px solid #ddd;
                text-align: center;
            }
            th {
                background-color: #f4f4f4;
            }
            a {
                display: block;
                text-align: center;
                margin: 20px 0;
                text-decoration: none;
                color: #007BFF;
            }
            a:hover {
                text-decoration: underline;
            }
            .delete-button {
                background-color: #007BFF;
                border: none;
                color: black;
                padding: 3px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 14px;
                cursor: pointer;
                border-radius: 5px;
            }

            .delete-button:hover {
                background-color: #0066cc;
            }

            .return-link {
                text-decoration: none;
                color: black;
                font-size: 14px;
                margin-left: 10px; /* Adjust spacing as needed */
            }
            .button-container {
                display: flex;
                justify-content: center;
                align-items: center;
                margin-top: 1rem;
            }
            button {
                background-color: #4CAF50;
                color: white;
                padding: 3px 20px;
                border: none;
                cursor: pointer;
                border-radius: 4px;
                margin: 0.5rem;
                transition: background-color 0.3s ease;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                display: inline-block;
            }
            button a {
                text-decoration: none;
                color: white;
                display: inline-block;
            }
            button:hover {
                background-color: #45a049;
            }
            .delete-button {
                background-color: #f44336;
            }
            .delete-button:hover {
                background-color: #e53935;
            }
            .return-button {
                background-color: #008CBA;
            }
            .return-button:hover {
                background-color: #007BB5;
            }
            .return-link, .delete-button a {
                color: white;
                text-decoration: none;
            }
        </style>
        <script>
            function confirmDeletion() {
                return confirm("Bạn có chắc chắn muốn xoá lịch học ngày hôm nay không?");
            }
        </script>
    </head>
    <body>
        <c:if test="${sessionScope.csid != null && sessionScope.csid != 0}">
        <h2>Chương trình học của lớp ${sessionScope.className} năm học ${sessionScope.year.dateStart} - ${sessionScope.year.dateEnd}</h2>
        <div class="button-container">
            <button class="return-button">
                <a href="timeTableLecturer?lid=${sessionScope.lid}" class="return-link">Quay Lại</a>
            </button>
            <c:if test="${sessionScope.sche != null}">
                <button class="delete-button">
                    <a href="addSchedules?idToDelete=${sessionScope.sche.scheID}&csid=${sessionScope.csid}" 
                       onclick="return confirmDeletion()">Xoá Lịch học ngày hôm nay</a>
                </button>
            </c:if>

        </div>

        <h3>${requestScope.Delete}</h3>
        <h3>${requestScope.mess}</h3>
        <form action="addSchedules" method="GET" >
            
            <select name="sdid" onchange="this.form.submit()">
                 <option value="0">Chọn buổi học</option>
                <c:forEach items="${sessionScope.listSchedulesUnlearn}" var="s">   
                   
                    <option value="${s.sdid.sdid}" ${s.sdid.sdid == sessionScope.sdid ? 'selected' : ''} > 
                        Buổi ${s.sdid.sessionNumber}
                </c:forEach>  
            </select>
            <input type="hidden" value="${sessionScope.csid}" name="csid"/>
            <c:if test="${sessionScope.sche != null}">
                <input type="submit" value="Update" name="sm"/>
            </c:if>
            <c:if test="${sessionScope.sche == null}">
                <input type="submit" value="Add" name="sm"/>
            </c:if>
        </form>
        <table border="1">
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Giờ học</th>
                    <th>Tên hoạt động</th>
                    <th>Đã fix cứng</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.listCuri}" var="c" varStatus="status">
                                <tbody>
                                    <tr>
                                         <td>${status.index + 1}</td>
                                        <td>${c.timeStart} - ${c.timeEnd}</td>
                                        <td>${c.nameAct}</td>
                                        <c:if test="${c.isFix == true}">
                                            <td>Cố Định</td>
                                        </c:if>
                                            <c:if test="${c.isFix == false}">
                                            <td></td>
                                        </c:if>
                                    </tr>            
                                </tbody>
                            </c:forEach>
            </tbody>
        </table>
            </c:if>
        <c:if test="${sessionScope.csid == null && sessionScope.csid == 0}">
            <h3>Giáo viên chưa được xếp lịch dạy trong năm học này</h3>
        </c:if>
    </body>
</html>
