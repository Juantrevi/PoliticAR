package com.example.proyecto.Servicios;

import com.example.proyecto.Email.EnvioEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements EnvioEmail {

    @Autowired
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    private JavaMailSender mailSender;



    @Override
    @Async
    public void enviar(String to, String email) {

        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("confirmar email");
            helper.setFrom("infopoliticarg@gmail.com");
            mailSender.send(mimeMessage);

        }catch (MessagingException e){
            LOGGER.error("No se pudo mandar el mail", e);
            throw new IllegalStateException("fallo enviar email");
        }
    }
}
