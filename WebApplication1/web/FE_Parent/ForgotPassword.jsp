<%-- 
    Document   : Register
    Created on : May 16, 2024, 9:52:42 PM
    Author     : DELL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Font Awesome Icons  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
              integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA=="
              crossorigin="anonymous" />
        <!-- Google Fonts  -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
        <title>Sakura - Quên Mật Khẩu</title>
        <style>
            * {
                margin: 0;
                padding: 0;
                font-family: 'Poppins', sans-serif;
            }

            body {
                background-color: #2BB4D0;
                color: white;
                display: flex;
                align-items: center;
                justify-content: center;
                padding: 15rem 0;
            }

            .card {
                backdrop-filter: blur(16px) saturate(180%);
                -webkit-backdrop-filter: blur(16px) saturate(180%);
                background-color: rgba(0, 0, 0, 0.75);
                border-radius: 12px;
                border: 1px solid rgba(255, 255, 255, 0.125);
                display: flex;
                flex-direction: column;
                align-items: center;
                padding: 30px 40px;
                width: 400px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            .lock-icon {
                font-size: 3rem;
                color: #6c5ce7;
            }

            h2 {
                font-size: 1.5rem;
                margin-top: 10px;
                text-transform: uppercase;
            }

            p {
                font-size: 12px;
                color: #ccc;
            }

            /* Chỉnh CSS cho phần chọn phương thức */
            .method-select {
                width: 100%;
                margin-top: 15px;
                margin-bottom: 15px;
                background: transparent;
                border: none;
                border-bottom: 2px solid deepskyblue;
                font-size: 15px;
                color: white;
                outline: none;
                padding: 10px;
                border-radius: 4px;
                /* Để căn giữa */
                text-align-last: center;
                /* Dùng text-align-last vì text-align không hoạt động với select */
            }

            .method-select option {
                background-color: #333;
                color: white;
            }

            .passInput {
                width: 100%;
                background: transparent;
                border: none;
                border-bottom: 2px solid deepskyblue;
                font-size: 15px;
                color: white;
                outline: none;
                padding: 10px;
                border-radius: 4px;
                margin-top: 10px;
            }

            button {
                margin-top: 15px;
                width: 100%;
                background-color: deepskyblue;
                color: white;
                padding: 10px;
                text-transform: uppercase;
                border-radius: 4px;
                cursor: pointer;
                border: none;
                outline: none;
            }

            button:hover {
                background-color: rgb(0, 191, 255);
            }

            .back-button {
                margin-top: 15px;
                width: 100%;
                background-color: rgb(255, 69, 0);
                color: white;
                padding: 10px;
                text-transform: uppercase;
                border-radius: 4px;
                cursor: pointer;
                border: none;
                outline: none;
                text-align: center;
                display: block;
                text-decoration: none;
            }

            .back-button:hover {
                background-color: rgb(255, 99, 71);
            }

            .message {
                padding: 10px;
                margin-top: 20px;
                border-radius: 5px;
                text-align: center;
                width: 100%;
            }

            .error {
                background-color: #f8d7da;
                color: #721c24;
            }

            .success {
                background-color: #d4edda;
                color: #155724;
            }
        </style>
    </head>
    <body>
        <div class="card">
            <p class="lock-icon"><i class="fas fa-lock"></i></p>
            <h2>Quên Mật Khẩu?</h2>
            <p>Bạn có thể thay đổi mật khẩu tại đây</p>

            <form action="forgot" method="POST">
                <!-- Select box chọn phương thức -->
                <select name="method" id="method" class="method-select" required>
                    <option value="email">Gửi qua Email</option>
                    <option value="sms">Gửi qua SMS</option>
                </select>

                <!-- Các trường nhập liệu -->
                <div id="emailField">
                    <input type="email" class="passInput" placeholder="Nhập vào email" name="mailForgot">
                </div>
                <div id="phoneField" style="display: none;">
                    <input type="text" class="passInput" placeholder="Nhập vào số điện thoại" name="phoneForgot">
                </div>

                <!-- Hidden field for role, initially empty -->
                <input type="hidden" name="role" id="role" value="">

                <button type="submit">Gửi Mật Khẩu</button>
            </form>

            <a href="login" class="back-button">Quay lại Đăng nhập</a>
            <h3 style="color: greenyellow">${requestScope.confirm}</h3>
            <h3 style="color: red">${requestScope.err}</h3>
        </div>

        <!-- Script điều khiển hiển thị các trường nhập liệu -->
        <script>
            const methodSelect = document.getElementById('method');
            const emailField = document.getElementById('emailField');
            const phoneField = document.getElementById('phoneField');

            methodSelect.addEventListener('change', function () {
                if (this.value === 'email') {
                    emailField.style.display = 'block';
                    phoneField.style.display = 'none';
                } else if (this.value === 'sms') {
                    emailField.style.display = 'none';
                    phoneField.style.display = 'block';
                }
            });
        </script>
    </body>
</html>
