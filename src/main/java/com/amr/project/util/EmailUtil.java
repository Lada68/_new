package com.amr.project.util;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

@Component
public class EmailUtil {
    private final String myAcEmail = "tupoavito@gmail.com";
    private final String password = "tupoavito322";


    public void sendMessage(String email, String subject, String text) {
        Properties properties = new Properties();
        try {
            properties.load(EmailUtil.class.getClassLoader().getResourceAsStream("mail.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAcEmail, password);
            }
        });


        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAcEmail));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }



    }


//
}
