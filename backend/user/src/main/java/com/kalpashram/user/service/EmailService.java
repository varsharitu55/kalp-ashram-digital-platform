package com.kalpashram.user.service;

import com.kalpashram.user.dto.EnquiryRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEnquiryEmail(EnquiryRequestDto req) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("kalpashram@gmail.com");
        msg.setSubject("New Enquiry Received");
        msg.setText("Name: " + req.getName() + "\n" + "Email: " + req.getEmail() + "\n" + "Phone: " + req.getPhone() + "\n" + "Message: " + req.getMessage());
        System.out.println("SMTP PASSWORD = " + System.getenv("SMTP_PASSWORD"));

        mailSender.send(msg);
    }
}
