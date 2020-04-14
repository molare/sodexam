package renting.com.service;

import javax.mail.MessagingException;

/**
 * Created by olivier on 19/12/2019.
 */
public interface EmailService {
    public void sendMail(String toEmail, String subject, String text);
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException;
}
