<%-- 
    Document   : HomePage
    Created on : May 16, 2024, 9:51:33 PM
    Author     : DELL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Trường Mầm Non Sakura</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css" rel="stylesheet">
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #e0f7fa;
            }
            .header {
                background: url('../Image/1488535058_nha-tre.jpg') no-repeat center center;
                background-size: cover;
                color: black;
                padding: 200px 0;
            }
            .header h1 {
                font-size: 3.5rem;
                font-weight: bold;
            }
            .header p {
                font-size: 1.5rem;
            }
            .content-section {
                padding: 60px 0;
            }
            .footer {
                background-color: #343a40;
                color: white;
                padding: 20px 0;
            }
            .navbar {
                background-color: #0c9abc;
            }
            .navbar-brand, .nav-link {
                color: white !important;
            }
            .nav-link:hover {
                color: #ffeb3b !important;
            }
            .registration-section {
                background-color: #ffffff;
                padding: 60px 0;
                border-top: 2px solid #0c9abc;
                border-bottom: 2px solid #0c9abc;
            }
            .registration-section h2 {
                margin-bottom: 30px;
                font-size: 2.5rem;
                color: #0c9abc;
            }
            .registration-section p {
                margin-bottom: 40px;
                font-size: 1.2rem;
                color: #555;
            }
            .form-control {
                border-radius: 5px;
                border: 1px solid #ddd;
                padding: 10px;
                font-size: 1rem;
                transition: all 0.3s ease-in-out;
            }
            .form-control:focus {
                border-color: #0c9abc;
                box-shadow: 0 0 8px rgba(12, 154, 188, 0.2);
            }
            .btn-primary {
                background-color: #0c9abc;
                border-color: #0c9abc;
                border-radius: 5px;
                padding: 10px 20px;
                font-size: 1.2rem;
                transition: all 0.3s ease-in-out;
            }
            .btn-primary:hover {
                background-color: #0a7e8a;
                border-color: #0a7e8a;
                box-shadow: 0 0 10px rgba(10, 126, 138, 0.4);
            }
            .form-group {
                margin-bottom: 1.5rem;
            }
        </style>
    </head>
    <body>

        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="#">Sakura Preschool</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="">Home Page</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="#section_1">About Us</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="#section_2">Contact</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <header class="header text-center">
            <div class="container">
                <h1 data-aos="fade-down">Chào Mừng Đến Trường Mầm Non Sakura</h1>
                <p data-aos="fade-up" data-aos-delay="300">Nơi ươm mầm những tài năng tương lai</p>
            </div>
        </header>

        <section class="content-section bg-light" id ="section_1">
            <div class="container">
                <div class="row">
                    <div class="col-md-6" data-aos="fade-right">
                        <h2>Về Chúng Tôi</h2>
                        <p>Trường Mầm Non Sakura được thành lập với sứ mệnh mang đến môi trường học tập thân thiện, sáng tạo và phát triển toàn diện cho các em nhỏ.</p>
                    </div>
                    <div class="col-md-6" data-aos="fade-left">
                        <img src="../Image/8-1.jpg" alt="About Us" class="img-fluid">
                    </div>
                </div>
            </div>
        </section>

        <section class="content-section">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 order-md-2" data-aos="fade-left">
                        <h2>Chương Trình Học</h2>
                        <p>Chương trình học của chúng tôi được thiết kế theo phương pháp giáo dục tiên tiến, giúp trẻ phát triển toàn diện về cả thể chất lẫn tinh thần.</p>
                    </div>
                    <div class="col-md-6 order-md-1" data-aos="fade-right">
                        <img src="../Image/aHR0cHM6Ly93d3cuaXNzcC5lZHUudm4vZmlsZXMvY2h1b25nLXRyaW5oLWVhbC5qcGc_Tmw0WnV4Q1RGUkR5cGk3S2FfWXFXcFBrTzZFUVhTak8.webp" alt="Curriculum" class="img-fluid">
                    </div>
                </div>
            </div>
        </section>

        <section class="content-section bg-light">
            <div class="container">
                <div class="row">
                    <div class="col-md-6" data-aos="fade-right">
                        <h2>Hoạt Động Ngoại Khóa</h2>
                        <p>Chúng tôi tổ chức các hoạt động ngoại khóa phong phú, từ thể thao, nghệ thuật đến khoa học, giúp trẻ khám phá và phát triển sở thích cá nhân.</p>
                    </div>
                    <div class="col-md-6" data-aos="fade-left">
                        <img src="../Image/hoat-dong-ngoai-khoa-4.jpg" alt="Activities" class="img-fluid">
                    </div>
                </div>
            </div>
        </section>

        <section class="content-section">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 order-md-2" data-aos="zoom-in">
                        <h2>Đội Ngũ Giáo Viên</h2>
                        <p>Đội ngũ giáo viên của chúng tôi là những người có trình độ chuyên môn cao, yêu nghề và luôn tận tâm với từng học sinh.</p>
                    </div>
                    <div class="col-md-6 order-md-1" data-aos="zoom-in">
                        <img src="../Image/vlzgnx5mdmmusf6bgshp.jpg" alt="Teachers" class="img-fluid">
                    </div>
                </div>
            </div>
        </section>

        <section class="content-section bg-light">
            <div class="container">
                <div class="row">
                    <div class="col-md-6" data-aos="flip-left">
                        <h2>Cơ Sở Vật Chất</h2>
                        <p>Trường Mầm Non Sakura được trang bị cơ sở vật chất hiện đại, đáp ứng đầy đủ các nhu cầu học tập và vui chơi của trẻ.</p>
                    </div>
                    <div class="col-md-6" data-aos="flip-right">
                        <img src="../Image/kham-pha-he-thong-co-so-vat-chat-giup-smisers-phat-trien-toan-dien-tai-sakura-duong-kinh-7.jpg" alt="Facilities" class="img-fluid">
                    </div>
                </div>
            </div>
        </section>

        <section class="registration-section text-center" id ="section_2">
            <div class="container" data-aos="fade-up">
                <h2>Đăng Ký Nhập Học</h2>
                <p>Vui lòng điền vào mẫu dưới đây để đăng ký nhập học cho con em của bạn.</p>
                <form>
                    <div class="form-group row">
                        <label for="parentName" class="col-sm-2 col-form-label">Tên Phụ Huynh</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="parentName" placeholder="Tên Phụ Huynh">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="studentName" class="col-sm-2 col-form-label">Tên Học Sinh</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="studentName" placeholder="Tên Học Sinh">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="email" class="col-sm-2 col-form-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="email" placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="phone" class="col-sm-2 col-form-label">Số Điện Thoại</label>
                        <div class="col-sm-10">
                            <input type="tel" class="form-control" id="phone" placeholder="Số Điện Thoại">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="message" class="col-sm-2 col-form-label">Tin Nhắn</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="message" rows="4" placeholder="Tin Nhắn"></textarea>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Gửi Đăng Ký</button>
                </form>
            </div>
        </section>

        <footer class="footer text-center">
            <div class="container">
                <p>&copy; 2024 Trường Mầm Non Sakura. All rights reserved.</p>
            </div>
        </footer>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js"></script>
        <script>
            AOS.init({
                duration: 1000,
                easing: 'ease-in-out',
                once: true
            });
        </script>
    </body>
</html>
