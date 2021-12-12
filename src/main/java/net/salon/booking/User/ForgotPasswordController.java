package net.salon.booking.User;

//import Utilities.UtilityPassword;
import net.bytebuddy.utility.RandomString;
import net.salon.booking.Utilities.UtilityPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {
    //    @Autowired
//    private JavaMailSender mailSender;
//
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService service;
    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/setting_password")
    public String showSetPasswordForm(Model model) {
        model.addAttribute("set_password", "set_password");

        return "User/add_user";

    }

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(Model model) {
        model.addAttribute("Forgot Password", "Forgot Password");

        return "User/forgot_password";

    }

    /** Handles the submission of forget Password Form **/
    /** Gets & Check the email submitted and generate Random String token **/
    @PostMapping("/forgot_password")
    public String processForgetPasswordForm(HttpServletRequest request, Model model) {

        String email = request.getParameter("email");
        String token = RandomString.make(45);

        try {
            service.updateResetPasswordToken(token, email);
            /** Generating Reset Password Link - Getting absolute URL From Utility class **/
            String resetPasswordLink = UtilityPassword.getSiteURL(request) + "/reset_password?token=" + token;/** Generating Reset Password Link - Getting absolute URL From Utility class **/

            /** Configuring Mail Properties **/
            /** Sending Email function **/
            sendEmail(email, resetPasswordLink);

            model.addAttribute("message", "We have sent a set password link to the user email. Please check.");

        } catch (UserNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());

        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error",  "error while sending email, please ensure that you have entered correct email !");
            return "User/forgot_password";
        }
        model.addAttribute("Forgot Password", "Forgot Password");
        return "User/forgot_password";
    }

    /** Handles the submission of set Password Form **/
    /** Gets & Check the email submitted and generate Random String token **/
//    @PostMapping("/set_password")
//    public String processSetPasswordForm(HttpServletRequest request, Model model) {
//
//        String email = request.getParameter("email");
//        String tokens = RandomString.make(255);
//
//        try {
//            service.updateSetPasswordToken(tokens, email);
//            /** Generating set Password Link - Getting absolute URL From Utility class **/
//            String setPasswordLink = UtilityPassword.getSiteURL(request) + "/set_password?token=" + tokens;/** Generating Reset Password Link - Getting absolute URL From Utility class **/
//
//            /** Configuring Mail Properties **/
//            /** Sending Email function **/
//            sendEmails(email, setPasswordLink);
//
//            model.addAttribute("message", "We have sent a set password link to the user's email. Please check.");
//
//        } catch (UserNotFoundException ex) {
//            model.addAttribute("error", ex.getMessage());
//
//        } catch (UnsupportedEncodingException | MessagingException e) {
//            model.addAttribute("error",  "error while sending email");
//        }
////        model.addAttribute("Forgot Password", "Forgot Password");
//        return "User/add_user";
//    }


    /** Sending email to set password**/
//    private void sendEmails(String email, String resetPasswordLink)
//            throws UnsupportedEncodingException, MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        helper.setFrom("bianalyst77@gmail.com", "Office Meeting Planner Support");
//        helper.setTo(email);
//
//        String subject = "Here's the link to set your password";
//
//        String content = "<p>Hello,</p>"
//                + "<p>You have been requested by the admin to set your password to access the meetings page.</p>"
//                + "<p>Click the link below to set your password:</p>"
//                + "<p><a href=\"" + resetPasswordLink + "\">set my password</a></p>"
//                + "<br>"
//                + "<p>Ignore this email if, "
//                + "or you have not made the request.</p>";
//
//        helper.setSubject(subject);
//        helper.setText(content, true);
//
//        mailSender.send(message);
//    }
    /** End of Sending email to set password**/

    /** Sending email to set password**/
    private void sendEmail(String email, String resetPasswordLink)
            throws UnsupportedEncodingException, MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("bianalyst77@gmail.com", "Office Meeting Planner Support");
        helper.setTo(email);

        String subject = "Here's the link to Reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have been requested by the Support team to set your password to access the meetings page.</p>"
                + "<p>Click the link below to set your password:</p>"
                + "<p><a href=\"" + resetPasswordLink + "\">set my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if, "
                + "you have not made the request.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }
    /** End of Sending email to set password**/

    /** Displaying Reset password form **/
    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = service.getByResetPasswordToken(token);
        model.addAttribute("token", token);

        if (user == null) {
            model.addAttribute("message1", "Invalid Token!");
            return "User/login";
        }

        return "User/reset_password";
    }

//    /** Displaying set password form **/
//    @GetMapping("/set_password")
//    public String showResetPasswordForms(@Param(value = "token") String tokens, Model model) {
//        User user = service.getBySetPasswordToken(tokens);
//        model.addAttribute("token", tokens);
//
//        if (user == null) {
//            model.addAttribute("message", "Invalid Token");
//            return "messages";
//        }
//
//        return "User/set_password";
//    }


    /** Handling servicing / submitting of the Reset password form **/

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = service.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "User/login";

        } else {
            service.updatePassword(user, password);

            model.addAttribute("message", "You have successfully set your password.");
        }

        return "User/login";
    }

    /** Handling servicing / submitting of the Reset password form **/

//    @PostMapping("/set_password")
//    public String processSetPassword(HttpServletRequest request, Model model) {
//        String tokens = request.getParameter("tokens");
//        String password = request.getParameter("password");
//
//        User user = service.getBySetPasswordToken(tokens);
//        model.addAttribute("title", "Set your password");
//
//        if (user == null) {
//            model.addAttribute("message", "Invalid Token");
////            return "message";
//        } else {
//            service.updatePasswords(user, password);
//
//            model.addAttribute("message", "You have successfully Set your password.");
//        }
//
//        return "User/login";
//    }



}