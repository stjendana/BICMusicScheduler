package BIC.Vancouver.music_scheduler.service;

import BIC.Vancouver.music_scheduler.model.mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    JavaMailSender mailSender;

    public void sendSimpleMessage(mail mail) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject(mail.getSubject());
        helper.setText(mail.getContent());
        helper.setTo(mail.getTo());
        helper.setFrom(mail.getTo());

        //helper.addAttachment("attachment-document-name.jpg", new ClassPathResource("memorynotfound-logo.jpg"));

        mailSender.send(message);

    }
}
