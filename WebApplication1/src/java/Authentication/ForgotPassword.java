package Authentication;

import DAO.AccountDBContext;
import DAO.LecturersDBContext;
import DAO.ParentDBContext;
import Entity.Lecturers;
import Entity.Parent;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Properties;

@WebServlet(name = "ForgotPassword", urlPatterns = {"/forgot"})
public class ForgotPassword extends HttpServlet {

    private String generateRandomPassword(int length) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("FE_Parent/ForgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("mailForgot");
        LecturersDBContext lec = new LecturersDBContext();
        ParentDBContext par = new ParentDBContext();
        AccountDBContext acc = new AccountDBContext();

        if (lec.getLecturerByEmail(email) == null && par.getParentByGmail(email) == null) {
            request.setAttribute("err", "Email bạn nhập đã sai hoặc không tồn tại!");
            request.getRequestDispatcher("FE_Parent/ForgotPassword.jsp").forward(request, response);
            return;
        } else {
            String randomPassword = generateRandomPassword(10);
            Lecturers lecturer = lec.getLecturersNickNameByEmail(email);
            Parent parent = par.getParentNickNameByEmail(email);

            if (lec.getLecturerByEmail(email) != null) {
                acc.updatePasswordByLecturerEmail(email, randomPassword);
                String content = createEmailContent(randomPassword, lecturer.getNickname(), "Lecturer");
                sendEmail(email, content, request, response);
            } else if (par.getParentByGmail(email) != null) {
                acc.updatePasswordByParentEmail(email, randomPassword);
                String content = createEmailContent(randomPassword, parent.getNickname(), "Parent");
                sendEmail(email, content, request, response);
            }
        }
    }

    private String createEmailContent(String password, String name, String role) {
        String imageUrl = "https://tse3.mm.bing.net/th?id=OIG3.weXkcUP33aOXiu6XzNnW&pid=ImgGn"; // Replace with your image URL
        String roleSpecificGreeting = role.equals("Parent") ? "Dear Parent" : "Dear Lecturer";

        return "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "    <title>Password Reset</title>\n"
                + "    <style>\n"
                + "        body { font-family: Arial, sans-serif; }\n"
                + "        .container { padding: 20px; max-width: 600px; margin: auto; border: 1px solid #ddd; border-radius: 10px; }\n"
                + "        .header { text-align: center; }\n"
                + "        .header img { max-width: 280px; }\n"
                + "        .banner { background-color: #f4f4f4; padding: 10px; text-align: center; font-size: 18px; color: green; margin-bottom: 20px; }\n"
                + "        .content { margin-top: 20px; }\n"
                + "        .footer { margin-top: 20px; text-align: center; font-size: 12px; color: #777; }\n"
                + "        .greeting { font-size: 20px; font-weight: bold; }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class='container'>\n"
                + "        <div class='header'>\n"
                + "            <img src='" + imageUrl + "' alt='Logo'>\n"
                + "        </div>\n"
                + "        <div class='banner'>\n"
                + "            Password Reset Notification\n"
                + "        </div>\n"
                + "        <div class='content'>\n"
                + "            <p class='greeting'>" + roleSpecificGreeting + " " + name + ",</p>\n"
                + "            <p>Your new password has been generated. Please use the following password to log in:</p>\n"
                + "            <p><strong>" + password + "</strong></p>\n"
                + "            <p>We recommend that you change this password immediately after logging in.</p>\n"
                + "        </div>\n"
                + "        <div class='footer'>\n"
                + "            <p>&copy; 2024 Sakura School. Make your best day</p>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
    }

    private void sendEmail(String email, String content, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String user = "kienpdhe170155@fpt.edu.vn";
        final String pass = "lrrq jpje rszs cann";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Your New Password");
            message.setContent(content, "text/html");
            Transport.send(message);
            request.setAttribute("mailStatus", "success");
        } catch (MessagingException e) {
            e.printStackTrace();
            request.setAttribute("mailStatus", "error");
        }

        request.setAttribute("confirm", "Mật khẩu đã được gửi qua email. Vui lòng kiểm tra email!");
        request.getRequestDispatcher("FE_Parent/ForgotPassword.jsp").forward(request, response);
    }
}
