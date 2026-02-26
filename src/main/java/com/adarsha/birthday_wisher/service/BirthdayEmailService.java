package com.adarsha.birthday_wisher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.adarsha.birthday_wisher.model.Birthday;
import com.adarsha.birthday_wisher.repository.BirthdayRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

@Service
public class BirthdayEmailService {
    
    @Autowired
    private BirthdayRepository repository;

    @Autowired
    private JavaMailSender mailSender;

    //Runs every day at 9 AM
    @Scheduled(cron = "0 0 9 * *  ?")
    public void sendBirthdayEmails() {

        try {
            System.out.println("Scheduler triggered at: " + LocalDate.now());
            LocalDate today = LocalDate.now();
            int month = today.getMonthValue();
            int day = today.getDayOfMonth();

            List<Birthday> list = repository.findByMonthAndDay(month, day);

            for(Birthday person : list) {
                String template = getRandomTemplate();
                template = template.replace("[NAME]", person.getName());

                sendEmail(person.getEmail(), template);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    //Load random letter template

    private String getRandomTemplate() throws IOException {
        int num = new Random().nextInt(3) + 1;

        String path = "letter_templates/letter_" + num + ".txt";
        InputStream is = new ClassPathResource(path).getInputStream();
        return new String(is.readAllBytes(), StandardCharsets.UTF_8);
    }

    // Send email

    private void sendEmail(String toEmail, String body) {

        try {

            SimpleMailMessage message = new SimpleMailMessage();


            message.setTo(toEmail);
            message.setSubject("Happy Birthday 🎉");
            message.setText(body);

            mailSender.send(message);

            System.out.println("Birthday email sent to " + toEmail);

        }catch(Exception e) {
            System.out.println("Mail sending failed!");
            e.printStackTrace();
        }
        
    }
}
