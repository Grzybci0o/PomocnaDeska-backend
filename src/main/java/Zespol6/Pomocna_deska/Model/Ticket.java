package Zespol6.Pomocna_deska.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "ticket")
/**
 * klasa reprezętująca obiekt w bazie danych
 */
public class Ticket {

    @Id
    /**
     * id
     */
    private String id;
    /**
     * temat zgłoszenia
     */
    private String topic;
    /**
     * opis zgłaszanego problemu
     */
    private String problem;
    /**
     * adres email zgłaszającego
     */
    private String email;
    /**
     * kategoria zgłoszenia
     */
    private String category;
    /**
     * status zgłoszenia
     */
    private String status;
    /**
     * priorytet zgłoszenia
     */
    private Number priority;
    /**
     * data zgłoszenia
     */
    private Date date;

    private String response;

    /**
     * <p>konstruktor klasy Ticket</p>
     * @param topic temat
     * @param problem opis problemu
     * @param email adres email
     * @param category kategoria
     * @param status status
     * @param priority priorytet
     * @param date data
     */
    public Ticket(String topic, String problem, String email, String category, String status, Number priority, Date date, String response) {
        this.topic = topic;
        this.problem = problem;
        this.email = email;
        this.category = category;
        this.status = status;
        this.priority = priority;
        this.date = date;
        this.response = response;

    }
    /**
     *<p>getter pola id</p>
     * @return zwraca pole id
     */
    public String get_id() {
        return id;
    }
    /**
     *<p>setter pola id</p>
     * @param id id zgłoszenia
     */
    public void set_id(String id) {
        this.id = id;
    }
    /**
     *<p>getter pola problem</p>
     * @return zwraca opis zgłoszenia
     */
    public String getProblem() {
        return problem;
    }
    /**
     *<p>setter pola problem</p>
     * @param problem opis problemu
     */
    public void setProblem(String problem) {
        this.problem = problem;
    }
    /**
     *<p>getter pola email</p>
     * @return zwraca email zgłaszającego
     */
    public String getEmail() {
        return email;
    }
    /**
     *<p>setter pola email</p>
     * @param email adres email zgłaszającego
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     *<p>getter pola status</p>
     * @return zwraca status zgłoszenia
     */
    public String getStatus() {
        return status;
    }
    /**
     *<p>setter pola status</p>
     * @param status status zgłoszenia
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     *<p>getter pola category</p>
     * @return zwraca kategorię zgłoszenia
     */
    public String getCategory() { return category; }
    /**
     *<p>setter pola category</p>
     * @param category kategoria zgłoszenia
     */
    public void setCategory(String category) { this.category = category; }
    /**
     *<p>setter pola topic</p>
     * @param topic temat zgłoszenia
     */
    public void setTopic(String topic){this.topic = topic;}
    /**
     *<p>getter pola topic</p>
     * @return zwraca temat zgłoszenia
     */
    public String getTopic(){return topic;}
    /**
     *<p>getter pola priority</p>
     * @return zwraca priorytet zgłoszenia
     */
    public Number getPriority(){return priority;}
    /**
     *<p>getter pola priority</p>
     * @return zwraca priorytet zgłoszenia
     */
    public void setPriority(Number priority){this.priority = priority;}
    /**
     *<p>getter pola priority</p>
     * @return zwraca priorytet zgłoszenia
     */
    public Date getDate(){return date;}
    /**
     *<p>getter pola priority</p>
     * @return zwraca priorytet zgłoszenia
     */
    public void setDate(Date date){this.date = date;}

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", problem='" + problem + '\'' +
                ", e_mail='" + email + '\'' +
                ", status=" + status +
                ", category='" + category + '\'' +
                '}';
    }
}
