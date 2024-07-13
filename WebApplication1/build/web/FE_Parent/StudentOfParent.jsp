<%-- 
    Document   : StudentOfParent
    Created on : May 19, 2024, 9:46:40 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
              font-family: Arial, sans-serif;
              background-color: #f5f5f5;
              margin: 0;
              padding: 0;
            }

            table {
              width: 100%;
              border-collapse: collapse;
              margin-bottom: 20px;
            }

            table, th, td {
              border: 1px solid #ddd;
            }

            th, td {
              padding: 10px;
              text-align: left;
            }

            th {
              background-color: #f2f2f2;
            }

            a {
              display: block;
              padding: 10px;
              background-color: #0066cc;
              color: white;
              text-decoration: none;
            }

            a:hover {
              background-color: #003399;
            }
        </style>
    </head>
    <body>
        <a href="parent-profile?pid=${requestScope.pid}"> Profile</a>
        <h1 style="text-align: center">Chọn bé bạn muốn xem thông tin</h1>
        <table border="1">
            <c:forEach items="${requestScope.list}" var="l">
                <tbody >
                <tr>
                    <td style="text-align: center"><a href="timetable?stuid=${l.stuid}">${l.sname}</a></td>
                </tr>
            </tbody>
            </c:forEach>
        </table>

    </body>
</html>