package Zespol6.Pomocna_deska.Controllers;

import Zespol6.Pomocna_deska.Model.EmailSender;
import Zespol6.Pomocna_deska.Model.Ticket;
import Zespol6.Pomocna_deska.Repositories.TicketRepository;
import Zespol6.Pomocna_deska.Services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
/**
 * Klasa odpowiadająca za obsługę ticketów
 */
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    EmailSenderService emailSenderService;
    /**
     *
     * @return zwarca listę zgłoszeń
     */
    @GetMapping("/all")
    public List<Ticket> getAll(){
        return ticketRepository.findAll();
    }
    /**
     *
     * @return zwraca listę zgłoszeń ze statusem 'unsolved'
     */
    @GetMapping("/unsolved")
    public ResponseEntity<List<Ticket>> getFalse() {
        try {
            List<Ticket> tickets = ticketRepository.getByStatus("unsolved");

            if (tickets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     *
     * @return zwraca listę zgłoszeń ze statusem 'solved'
     */
    @GetMapping("/solved")
    public ResponseEntity<List<Ticket>> getTrue() {
        try {
            List<Ticket> tickets = ticketRepository.getByStatus("solved");

            if (tickets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     *
     * @param ticket obiekt typu Ticket
     * @return dodaje nowe złoszenie do bazy
     */
    @PostMapping("/create")
    public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket){
        try {
            Ticket _tutorial = ticketRepository.save(new Ticket(ticket.getTopic(),ticket.getProblem(), ticket.getEmail(),ticket.getCategory(), "unsolved",ticket.getPriority(), new Date(), ""));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     *
     * @return zwraca listę zgłoszeń ze statusem 'inprogress'
     */
    @GetMapping("/inprogress")
    public ResponseEntity<List<Ticket>> getProccesing() {
        try {
            List<Ticket> tickets = ticketRepository.getByStatus("inprogress");

            if (tickets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     *
     * @param id mail zgłaszającego
     * @return wyszykuje zgłoszenie po adresie email
     */
//    @GetMapping("/email")
//    public ResponseEntity<List<Ticket>> getStatusByEmail(@RequestParam String mail){
//        List<Ticket> email = ticketRepository.findByEmail(mail);
//        return new ResponseEntity<>(email, HttpStatus.OK);
//    }

    /**
     *  Metoda pobierająca status ticketu po id
     * @param id wyszukiwanie po id
     * @return
     */
    @GetMapping("/status")
    public String getStatusById(@RequestParam String id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            return ticket.get().getStatus();
        }else{
            return "Błąd";
        }
    }

    /**
     *  Metoda odzyskująca id ticketu po jego zgłoszeniu
     * @param topic wyszukiwanie po temacie
     * @return
     */
    @GetMapping("/id")
    public String getIdOfTicket(@RequestParam String topic){
        Ticket ticket = ticketRepository.findByTopic(topic);
        return ticket.get_id();
    }
    /**
     *
     * @param topic temat zgłoszenia
     * @param priority priorytet zgłoszenia
     * @return zmienia priorytet zgłoszenia
     */
    @PatchMapping("/priority")
    public ResponseEntity<Ticket> changePriority(@RequestParam String topic,@RequestBody Number priority) throws MessagingException, IOException {
        Ticket ticket = ticketRepository.findByTopic(topic);
        EmailSender emailSender = new EmailSender();
        ticket.setPriority(priority);
        ticket.setStatus("inprogress");
        emailSender.setToEmail(ticket.getEmail());
        emailSender.setSubject("Zmiana statusu twojego zgłoszenia");
        emailSender.setBody("Status twojego zgloszenia o numerze: " + ticket.get_id() + " zostal zmieniony! " +
                "\n" +
                "\n" +
                "\n" +
                "Wiadomosc wygenerowana automatycznie. Nie odpowiadaj na nia!");
        emailSenderService.sendmail(emailSender);
        return new ResponseEntity<>(ticketRepository.save(ticket),HttpStatus.OK);
    }
    /**
     *
     * @param topic temat zgłoszenia
     * @param category kategoria
     * @return zmienia kategorię zgłoszenia
     */
    @PatchMapping("/category")
    public ResponseEntity<Ticket> changeCategory(@RequestParam String topic,@RequestBody String category){
        Ticket ticket = ticketRepository.findByTopic(topic);
        ticket.setCategory(category);
        return new ResponseEntity<>(ticketRepository.save(ticket),HttpStatus.OK);
    }

    @PatchMapping("/setsolved")
    public ResponseEntity<Ticket> setSolved(@RequestParam String topic){
        Ticket ticket = ticketRepository.findByTopic(topic);
        ticket.setStatus("solved");
        return new ResponseEntity<>(ticketRepository.save(ticket),HttpStatus.OK);
    }

    @PatchMapping("/setresponse")
    public ResponseEntity<Ticket> setResponse(@RequestParam String topic, @RequestBody String response){
        Ticket ticket = ticketRepository.findByTopic(topic);
        ticket.setResponse(response);
        return new ResponseEntity<>(ticketRepository.save(ticket),HttpStatus.OK);
    }

    @GetMapping("/getresponse")
    public String getResponse(@RequestParam String topic){
        Ticket ticket = ticketRepository.findByTopic(topic);
        return ticket.getResponse();
    }


}
