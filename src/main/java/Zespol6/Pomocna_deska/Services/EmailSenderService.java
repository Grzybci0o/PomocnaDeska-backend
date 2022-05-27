package Zespol6.Pomocna_deska.Services;


import Zespol6.Pomocna_deska.Model.EmailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;


@Service
/**
 * Serwis obsługujący wysyłanie emaili
 */
public class EmailSenderService {
    /**
     * <p>Metoda obsługująca wysyłanie emaili</p>
     * @param emailSender pole klasy EmailSender
     * @throws AddressException przy podaniu złego adresu
     * @throws MessagingException przy błędach wysyłania
     * @throws IOException przy pozostałych błędach
     */
    public void sendmail(EmailSender emailSender) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("pomocnadeska.test@gmail.com", "imctbzidcdvqyojl");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("pomocnadeska.test@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailSender.getToEmail()));
        msg.setSubject(emailSender.getSubject());
        msg.setContent(emailSender.getSubject(), "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(emailSender.getBody(), "text/plain");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        if (emailSender.getAttachment() != null) {
        MimeBodyPart attachPart = new MimeBodyPart();
        attachPart.attachFile(emailSender.getAttachment());
        multipart.addBodyPart(attachPart);
        }
        msg.setContent(multipart);
        Transport.send(msg);
    }
}