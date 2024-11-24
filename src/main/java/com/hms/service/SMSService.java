package com.hms.service;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SMSService {

    @Value("${twilio.whatsapp.number}")
    private String whatsAppNumber;

    @Value("${twilio.phone.number}")
    private String number;


    public  void sendWhatsAppMessage(String recipientNumber, String messageBody) {
        try {
            // Format recipient number with WhatsApp prefix
            String formattedRecipient = "whatsapp:+91" + recipientNumber;

            // Create and send the message
            Message message = Message.creator(
                    new PhoneNumber(formattedRecipient), // To WhatsApp number
                    new PhoneNumber(whatsAppNumber), // From Twilio WhatsApp number
                    messageBody // Message content
            ).create();

            // Log success
           // System.out.println("Message sent successfully!");
           // System.out.println("Message SID: " + message.getSid());
        } catch (Exception e) {
            // Handle exceptions
            System.err.println("Failed to send message: " + e.getMessage());
        }
    }
//    public String sendWhatsAppMessage(String to, String body) {
//        Message message = Message.creator(
//                new PhoneNumber("whatsapp:+91" + to),
//                new PhoneNumber("whatsapp:" + whatsAppNumber),
//                body
//        ).create();
//        return message.getSid();
//    }

    public String sendSMS(String to, String body) {

        Message message = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(number),
                body).create();
        return message.getAccountSid();//return the sid of the sent message
    }

}
