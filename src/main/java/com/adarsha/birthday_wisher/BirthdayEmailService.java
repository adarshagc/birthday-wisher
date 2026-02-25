package com.adarsha.birthday_wisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

@Service
public class BirthdayEmailService {
    
    @Autowired
    private JavaMailSender mailSender;

    //Runs every day at 9 AM
    @Scheduled(cron = "0 */1 * * * ?")
    public void sendBirthdayEmails() {

        try {
            System.out.println("Scheduler triggered at: " + LocalDate.now());
            LocalDate today = LocalDate.now();
            int month = today.getMonthValue();
            int day = today.getDayOfMonth();

            InputStream is = new ClassPathResource("birthdays.csv").getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {

                System.out.println("Reading line: " + line);

                
                if(firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] data = line.split(",");

                String name = data[0];
                String email = data[1];
                int bMonth = Integer.parseInt(data[2]);
                int bDay = Integer.parseInt(data[3]);

                if(bMonth == month && bDay == day) {

                    String template = getRandomTemplate();
                    template = template.replace("[NAME]", name);

                    sendEmail(email, template);


                    //just checking
                    
                    System.out.println("Checking: " + bMonth + "/" + bDay);
                    System.out.println("Today: " + month + "/" + day);


                }
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
