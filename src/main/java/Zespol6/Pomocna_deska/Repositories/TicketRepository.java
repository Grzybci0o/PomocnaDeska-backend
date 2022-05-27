package Zespol6.Pomocna_deska.Repositories;

import Zespol6.Pomocna_deska.Model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interfejs używany w obsłudze bazy ticketów
 */
public interface TicketRepository extends MongoRepository<Ticket, String> {
    /**
     * <p>Metoda pobierania po statusie</p>
     * @param status status
     * @return
     */
    List<Ticket> getByStatus(String status);
    /**
     * <p>Metoda pobierania po nazwie email</p>
     * @param id adres id
     * @return
     */
    Optional<Ticket> findById(String id);
    /**
     * <p>Metoda pobierania po temacie</p>
     * @param topic temat
     * @return
     */
    Ticket findByTopic(String topic);

    //Ticket findByEmailForStatus(String email);

}
