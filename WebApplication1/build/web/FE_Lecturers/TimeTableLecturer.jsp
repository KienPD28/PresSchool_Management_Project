<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <title>Time Table Lecturer</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background: linear-gradient(to right, whitesmoke 0%, whitesmoke 100%);
                margin: 0;
                padding: 0;
                color: #333;
            }
            header {
                background: url('https://www.transparenttextures.com/patterns/clean-gray-paper.png');
                color: white;
                text-align: center;
                padding: 1rem;
            }
            nav {
                margin-top: -30px;
                padding: 1rem;
                background: #333;
                color: #fff;
                text-align: right;
                position: absolute;
                top: 50px;
                right: 10px;
                border-radius: 50%;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            nav a {
                color: #fff;
                margin: 0 1rem;
                text-decoration: none;
                transition: color 0.3s ease;
            }
            nav a:hover {
                color: #fcb69f;
            }
            form {
                display: inline-block;
                justify-content: center;
                margin: 1rem;
            }
            button {
                background-color: #4CAF50;
                color: white;
                padding: 0.5rem 1rem;
                border: none;
                cursor: pointer;
                border-radius: 4px;
                align-items: center;
                transition: background-color 0.3s ease;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            button:hover {
                background-color: #45a049;
            }
            hr {
                margin: 2rem 0;
                border: 0;
                border-top: 1px solid #ccc;
            }
            h3 {
                color: #333;
                text-align: center;
            }
            table {
                width: 80%;
                margin: 1rem auto;
                border-collapse: collapse;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                background: #fff;
                border-radius: 8px;
                overflow: hidden;
                border: 2px solid #ddd; /* Specify border width, style, and color */
            }
            th, td {
                padding: 0.75rem;
                text-align: left;
                border-bottom: 1px solid #ddd; /* Add border for rows */
                border-right: 2px solid #ddd; /* Add border for columns */
            }
            th {
                background-color: #00b8ec;
                color: white;
                border-right: 2px solid #ddd; /* Add border for columns */
            }
            tbody tr:last-child td {
                border-bottom: none; /* Remove bottom border for the last row */
            }
            tbody td:last-child {
                border-right: none; /* Remove right border for the last column */
            }
            .meal-table {
                border: 2px solid #ddd;
            }
            @media (max-width: 768px) {
                body {
                    font-size: 14px;
                }
                nav {
                    text-align: center;
                }
                table {
                    width: 100%;
                }
            }
        </style>
    </head>
    <body>
        <nav>
            <a href="lecturers-profile?lid=${sessionScope.lid}">Profile</a>
        </nav>
        <h2 style="text-align: center">Lecturer Time Table</h2>

        <hr/>

        <div style="text-align: center">
            <form action="addSchedules" method="GET">
                <input type="hidden" value="${sessionScope.csid}" name="csid"/>
                <button>Cập nhật lịch học</button>
            </form>
            <form action="historyOfLecturer" method="POST">
                <input type="hidden" value="${sessionScope.lid}" name="lid"/>
                <button>Xem lại lịch sử</button>
            </form>           
                 <form action="liststudent" method="GET">
                <input type="hidden" value="${sessionScope.lid}" name="lid"/>
                
                <button onclick="window.location.href='liststudent'">Đánh giá buổi học hôm nay</button>
            </form>
        </div>
        <hr/>
        <h3>Lịch dạy ngày ${requestScope.dateNow} hôm nay</h3>
        <table>
            <thead>
                <tr>
                    <th style="width: 10%;">STT</th>
                    <th style="width: 30%;">Time</th>
                    <th style="width: 60%;">Name activity</th>
                </tr>
            <tbody>
            </thead>
                <c:if test="${requestScope.schedulesToDay != null}">
                    <tr>
                        <c:set var="sch" value="${requestScope.schedulesToDay}"/>
                        <td>Buổi ${sch.sdid.sessionNumber}</td>
                        <td>
                            <c:forEach items="${requestScope.curi}" var="c">
                                <c:if test="${sch.sdid.sdid == c.sdid.sdid}">
                                    ${c.timeStart} - ${c.timeEnd} <br>
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${requestScope.curi}" var="c">
                                <c:if test="${sch.sdid.sdid == c.sdid.sdid}">
                                    ${c.nameAct} <br>
                                </c:if>
                            </c:forEach>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${requestScope.schedulesToDay == null}">
                    <tr>
                        <td colspan="3"><h3>Giáo viên chưa cập nhật lịch học cho hôm nay.</h3></td>
                    </tr>
                </c:if>
            </tbody>
        </table>
        <h3>Bữa ăn hôm nay của các bé</h3>
        <table class="meal-table">
            <thead>
                <tr>
                    <th>Bữa ăn</th>
                    <th>Món ăn</th>
                </tr>
            </thead>
            <c:forEach items="${requestScope.menu}" var="m">
                <tbody>
                    <tr>
                        <td>${m.mealID.mealName}</td>
                        <td>${m.menu}</td>
                    </tr>
                </tbody>
            </c:forEach>
        </table>
    </body>
</html>
