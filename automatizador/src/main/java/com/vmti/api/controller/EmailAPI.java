package com.vmti.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/email")
public class EmailAPI {

    @Autowired
    private JavaMailSender mailSender;

    @Scheduled(cron = "0 * * * * *", zone = "America/Sao_Paulo") //Envio de teste a cada 1 minuto
    //@RequestMapping(path = "/sendemail", method = RequestMethod.GET)
    public String sendEmail(){

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("atendimento@vmti.net.br");
        mail.setSubject("Teste Envio de e-mail");
        mail.setText("Hello from Spring Boot Application");
        mail.setTo("cristiano.meneses2008@gmail.com");

        try {
            mailSender.send(mail);
            System.out.println("Veja seu email...");
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar e-mail: "+e;
        }

    }

    //A cada um minuto
    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        System.out.println("Hora atual: " + new Date());
    }

}
