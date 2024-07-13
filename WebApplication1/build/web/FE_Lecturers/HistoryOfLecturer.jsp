<%-- 
    Document   : HistoryOfLecturer
    Created on : Jun 24, 2024, 11:09:53 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f0f2f5;
                color: #333;
            }

            h1 {
                text-align: center;
                margin: 20px 0;
                color: #4CAF50;
            }
            h3 {
                text-align: center;
                margin: 20px 0;
                color: #4CAF50;
            }
            .form-container {
                display: flex;
                justify-content: center;
                padding: 20px;
                background-color: #fff;
                border-bottom: 1px solid #ddd;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }

            form {
                display: flex;
                align-items: center;
                gap: 10px;
            }

            select, button {
                padding: 10px;
                font-size: 14px;
                border: 1px solid #ddd;
                border-radius: 4px;
                outline: none;
            }

            select:focus, button:focus {
                border-color: #4CAF50;
            }

            button {
                background-color: #4CAF50;
                color: white;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            button:hover {
                background-color: #45a049;
            }

            .col {
                max-width: 1000px;
                margin: 20px auto;
                padding: 20px;
                background-color: #fff;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }
        .return-link {
                text-decoration: none;
                color: white;
                font-size: 14px;
                margin-left: 10px; /* Adjust spacing as needed */
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            th, td {
                padding: 12px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #f2f2f2;
                color: #333;
            }

            tr:hover {
                background-color: #f9f9f9;
            }

            @media (max-width: 600px) {
                .form-container {
                    flex-direction: column;
                    align-items: flex-start;
                }

                form {
                    flex-direction: column;
                    align-items: stretch;
                }

                select, button {
                    width: 100%;
                    margin: 5px 0;
                }
            }
        </style>
    </head>
    <body>
        <h1>History Of Lecturer</h1>
        <button class="return-button">
                <a href="timeTableLecturer?lid=${sessionScope.lid}" class="return-link">Quay Lại</a>
            </button>
        <div class="form-container">
            <form action="historyOfLecturer" method="POST">
                <input type="hidden" name="lid" value="${requestScope.lid}"/>
                <select name="yidHistoty" onchange="this.form.submit()" >
                    <option value="0">Chọn năm học</option>
                    <c:forEach items="${requestScope.listYid}" var="s">
                        <option value="${s.yid.yid}" ${s.yid.yid == requestScope.yidH ? 'selected' : ''}>
                            ${s.yid.dateStart} - ${s.yid.dateEnd}
                        </option>
                    </c:forEach>
                </select>
                <select name="schedulesID">
                    <option value="0">Chọn ngày</option>
                    <c:forEach items="${requestScope.listSche}" var="s">
                        <option value="${s.scheID}" ${s.scheID == requestScope.schID ? 'selected' : ''}>
                            ${s.date}
                        </option>
                    </c:forEach>
                </select>
                <button type="submit">Search</button>
            </form>
        </div>
                <c:if test="${requestScope.yidChoose.dateStart != null && requestScope.yidChoose.dateEnd != null}" >
                <h3 style="text-align: center">Chương trình dạy ${requestScope.className} của giáo viên ${requestScope.lec.lname} trong năm ${requestScope.yidChoose.dateStart} - ${requestScope.yidChoose.dateEnd}</h3>
     </c:if>
                <c:if test="${requestScope.yidChoose.dateStart == null && requestScope.yidChoose.dateEnd == null}" >
                <h3 style="text-align: center">Giáo viên vui lòng chọn năm học</h3>
     </c:if>
        <div class="col">
            <table border="2" class="activity-table">
                <thead>
                    <tr>
                        <th>Khung giờ</th>
                        <th>Hoạt động</th>
                        <th>Hoạt động cố định</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.curiculum}" var="c">
                        <tr>
                            <td>${c.timeStart} - ${c.timeEnd}</td>
                            <td>${c.nameAct}</td>
                            <c:if test="${c.isFix eq true}">
                                <td>Cố Định</td>
                            </c:if>
                            <c:if test="${c.isFix != true}">
                                <td>Không cố định</td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
