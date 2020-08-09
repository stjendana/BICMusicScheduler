package BIC.Vancouver.music_scheduler.service;

import BIC.Vancouver.music_scheduler.model.mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService {

    @Autowired
    JavaMailSender mailSender;

    public void sendSimpleMessage(File tempFile) throws MessagingException {
        System.out.println("Sending email with attachment...");
        mail mailObject = createMailObject();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(mailObject.getSubject());
        helper.setText(mailObject.getContent());
        helper.setTo(mailObject.getTo());
        helper.setFrom("no-reply@bicvancouver.com");
        helper.addAttachment(tempFile.getName(), tempFile);
        mailSender.send(message);
        System.out.println("Email sent successfully!");
    }

    private mail createMailObject() {
        String[] emailAddresses = new String[]{
                "steven.tjendana@gmail.com"
                /*"melisadewi577@yahoo.co.id",
                "ismantara.kevin@yahoo.com",
                "utomo.agnescarissa@gmail.com",
                "ijonkyonathan@gmail.com"*/
        };

        mail newMail = new mail();
        newMail.setFrom("no-reply@bicvancouver.com");
        newMail.setTo(emailAddresses);
        newMail.setSubject("Sending Email Attachment Configuration Example");
        newMail.setContent("This tutorial demonstrates how to send an email with attachment using Spring Framework. <br /> Second line");

        return newMail;
    }
}
