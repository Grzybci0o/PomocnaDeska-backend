package Zespol6.Pomocna_deska.Model;
/**
 * Model klasy email
 */
public class EmailSender {
    /**
     * adres e-mail, na który odesłana będzie odpowiedź
     */
    private String toEmail;
    /**
            * treść odpowiedzi
     */
    private String body;
    /**
     * temat podany w tickecie
     */
    private String subject;
    /**
     * opcjonalny załącznik
     */
    private String attachment;

    public EmailSender(){

    }
    /**
     * <p>Konstruktor klasy EmailSender</p>
     * @param toEmail adres e-mail, na który odesłana będzie odpowiedź
     * @param body treść odpowiedzi
     * @param subject temat podany w tickecie
     * @param attachment opcjonalny załącznik
     */
    public EmailSender(String toEmail, String body, String subject, String attachment) {
        this.toEmail = toEmail;
        this.body = body;
        this.subject = subject;
        this.attachment = attachment;
    }
    /**
     * <p>getter pola toEmail</p>
     * @return toEmail
     */
    public String getToEmail() {
        return toEmail;
    }
    /**
     * <p>setter pola toEmail</p>
     * @param toEmail
     */
    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }
    /**
     * <p>getter pola body</p>
     * @return body
     */
    public String getBody() {
        return body;
    }
    /**
     * <p>setter pola body</p>
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }
    /**
     * <p>getter pola subject</p>
     * @return subject
     */
    public String getSubject() {
        return subject;
    }
    /**
     * <p>setter pola subject</p>
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /**
     * <p>getter pola attachment</p>
     * @return attachment
     */
    public String getAttachment() {
        return attachment;
    }
    /**
     * <p>setter pola attachment</p>
     * @param attachment
     */
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    /**
     * toString modelu
     * @return String
     */
    @Override
    public String toString() {
        return "Email{" +
                "toEmail='" + toEmail + '\'' +
                ", body='" + body + '\'' +
                ", subject='" + subject + '\'' +
                ", attachment='" + attachment + '\'' +
                '}';
    }
}
