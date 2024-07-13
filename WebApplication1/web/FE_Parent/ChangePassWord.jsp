<%-- 
    Document   : ChangePassword
    Created on : May 16, 2024, 9:56:30 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            /*
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/CascadeStyleSheet.css to edit this template
            */
            /* 
                Created on : May 18, 2024, 2:16:33 PM
                Author     : NGUYEN THI KHANH VI
            */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body, html {
                height: 100%;
                font-family: 'Poppins', sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                background: #f3f4f6;
            }
            .container {
                width: 800px;
                height: 400px;
                max-width: 600px;
                padding: 20px;
                background: #fff;
                border-radius: 20px;
                animation: glow 2s infinite linear;
            }

            @keyframes glow {
                0% {
                    box-shadow: 0 0 10px rgba(255, 153, 204, 0.2),
                        0 0 20px rgba(255, 153, 204, 0.2),
                        0 0 30px rgba(255, 153, 204, 0.2); /* Màu sáng ở đầu */
                }
                100% {
                    box-shadow: 0 0 40px rgba(255, 153, 204, 0.4),
                        0 0 60px rgba(255, 153, 204, 0.4),
                        0 0 80px rgba(255, 153, 204, 0.4); /* Màu sáng ở cuối */
                }
            }

            .change-password-form {
                width: 60%;
                margin: 0 auto;
                text-align: center;
                margin-top: 40px;
            }

            h1 {
                margin-bottom: 20px;
                font-size: 24px;
                text-align: center;
                color: #333;
            }

            .input-group {
                margin-bottom: 15px;
                width: 100%;
            }

            .input-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: 500;
                color: #555;
            }

            .input-group input {
                width: 100%;
                padding: 10px 15px;
                border: 1px solid #ddd;
                border-radius: 5px;
                font-size: 16px;
                color: #333;
                outline: none;
                transition: border-color 0.3s;
            }

            .input-group input:focus {
                border-color: #00b8ec;
            }

            .save-button {
                width: 100%;
                padding: 12px 20px;
                background: #00b8ec;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                font-weight: 600;
                color: #fff;
                cursor: pointer;
                transition: background 0.3s;
            }

            .save-button:hover {
                background-color: #0af6fa;
                box-shadow: 0px 15px 20px rgba (13, 240, 252, 0.4);
                color: #fff;
                transform: translateY(-7px);
            }

        </style>
    </head>
    <body>       

        <div class="container" >
            <div class="change-password-form">
                <h1>Change PassWord</h1>

                <form action="change" method="post">
                    <div class="input-group">

                        <input type="password" id="old-password" name="old-password" placeholder="Enter old password" required>
                        <input type="hidden" name="user" value="${sessionScope.account.username}"> 
                    </div>
                    <div class="input-group">

                        <input type="password" id="new-password" name="new-password" placeholder="Enter new password" required>
                    </div>
                    <div class="input-group">

                        <input type="password" id="confirm-password" name="confirm-password" placeholder="Confirm new password" required>
                    </div>

                    <button type="submit" class="save-button">Update password</button> 
                    <% String mess = (String) request.getAttribute("mess");
                  if (mess != null) { %>
                    <p style="color: red" id="message">${mess}</p>
                    <% } %>

                    <button class="back-button" onclick="window.history.back()">Back to previous page</button>

                    <script type="text/javascript">
                        function hideMessage() {
                            var messageElement = document.getElementById("message");
                            if (messageElement) {
                                setTimeout(function () {
                                    messageElement.style.display = "none";
                                }, 2000);
                            }
                        }
                        window.onload = hideMessage;
                    </script>     
                </form>
            </div>
        </div>
    </body>
</html>
