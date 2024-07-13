<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
        <style>
            .container {
                max-width: 600px;
                margin: 0 auto;
                padding: 20px;
                background: #fff;
                border-radius: 20px;
                animation: glow 2s infinite linear;
            }

            .change-password-form {
                text-align: center;
                margin-top: 40px;
            }

            h1 {
                font-size: 24px;
                color: #333;
            }

            .input-group {
                margin-bottom: 20px;
                text-align: left;
            }

            .input-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: 500;
                color: #555;
            }

            .input-group input {
                width: calc(100% - 30px);
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
                padding: 12px 0;
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
            }

            .back-button {
                margin-top: 10px;
                padding: 10px 20px;
                background: #ddd;
                border: none;
                border-radius: 5px;
                font-size: 14px;
                color: #333;
                cursor: pointer;
                transition: background 0.3s;
            }

            .back-button:hover {
                background-color: #ccc;
            }

            #message {
                margin-top: 10px;
                color: red;
            }
        </style>
        </head>
        <body>
            <div class="container">
                <div class="change-password-form">
                <h1>Change Password</h1>

                <form action="changepass" method="post">
                    <div class="input-group">
                        <label for="old-password">Old Password</label>
                        <input type="password" id="old-password" name="old-password" required>
                    </div>
                    <div class="input-group">
                        <label for="new-password">New Password</label>
                        <input type="password" id="new-password" name="new-password" required>
                    </div>
                    <div class="input-group">
                        <label for="confirm-password">Confirm New Password</label>
                        <input type="password" id="confirm-password" name="confirm-password" required>
                    </div>

                    <button type="submit" class="save-button">Update Password</button>
                    <% String mess = (String) request.getAttribute("mess");
                    if (mess != null) { %>
                    <p id="message">${mess}</p>
                    <% } %>

                    <button type="button" class="back-button" onclick="window.history.back()">Back to Previous Page</button>
                </form>
            </div>
        </div>

        <script type="text/javascript">
            function hideMessage() {
                var messageElement = document.getElementById("message");
                if (messageElement) {
                    setTimeout(function () {
                        messageElement.style.display = "none";
                    }, 6000);
                }
            }
            window.onload = hideMessage;



        </script>

    </body>
</html>
