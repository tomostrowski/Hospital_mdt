package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.entity.User;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendConfirmationEmail(User user, String link) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom(("noreplay@mdtonline.com"));
        mail.setSubject("MDT: "+user.getFirstName()+", confirm your registration");
        mail.setText("Hello "+ user.getFirstName()+ ",\n"+ "please click link below to finish registration process: "+"\r\n" + link);

        javaMailSender.send(mail);
    }

    public void sendNewPasswordEmail(User user, String password) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom(("noreplay@mdtonline.com"));
        mail.setSubject("MDT: "+user.getFirstName()+", you have asked to rest your password");
        mail.setText("Hello "+ user.getFirstName()+ ",\n"+"this is your temporary password, change it after logging in "+"\r\n" + password);

        javaMailSender.send(mail);
    }
}
