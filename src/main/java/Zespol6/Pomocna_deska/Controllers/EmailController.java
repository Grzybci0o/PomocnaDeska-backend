package Zespol6.Pomocna_deska.Controllers;


import Zespol6.Pomocna_deska.Model.EmailSender;
import Zespol6.Pomocna_deska.Model.Ticket;
import Zespol6.Pomocna_deska.Repositories.TicketRepository;
import Zespol6.Pomocna_deska.Repositories.UserRepository;
import Zespol6.Pomocna_deska.Services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/email")
/**
 *  Jest to klasa, która odpowiada za obslugę endpointu związanego z wysyłaniem emaila z odpowiedzią na zadany ticket.
 */
public class EmailController {
    /**
     * Prywatne pole klasy EmailSenderService
     */
    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    TicketRepository ticketRepository;

    /**
     *
     * @param emailSender Funkcja przyjmuje obiekt klasy EmailSender
     * @return Odpowiedni komunikat w przypadku prawidłowego wykonania się metody
     * @throws AddressException W przypadku źle sformatowaneogo adresu email
     * @throws MessagingException
     * @throws IOException
     *
     */
    @PostMapping ("/sendemail")
    public String sendEmail(@RequestBody EmailSender emailSender) throws AddressException, MessagingException, IOException {
        emailSenderService.sendmail(emailSender);
        Ticket ticket = ticketRepository.findByTopic(emailSender.getSubject());
        ticket.setStatus("solved");
        ticketRepository.save(ticket);
        return "Email sent successfully ";
    }
}
