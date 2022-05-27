package Zespol6.Pomocna_deska.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
/**
 * klasa encji User
 */
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * id użytkownika
     */
    private int Id;
    /**
     * nazwa użytkownika
     */
    private String userName;
    /**
     * hasło użytkownika
     */
    private String password;
    /**
     * status konta użytkownika
     */
    private boolean active;
    /**
     * rola użytkownika
     */
    private String roles;
    /**
     * data utwożenia użytkownika
     */
    private Date createDate;
    /**
     * <p>getter pola Id</p>
     * @return zwraca pole Id
     */
    public int getId() {
        return Id;
    }
    /**
     * <p>setter pola Id</p>
     * @param id id użytkownika
     */
    public void setId(int id) {
        Id = id;
    }
    /**
     * <p>getter pola userName</p>
     * @return zwraca nazwę użytkownika
     */
    public String getUserName() {
        return userName;
    }
    /**
     * <p>setter pola userName</p>
     * @param userName nazwa użytkownika
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * <p>getter pola password</p>
     * @return zwraca hasło użytkownika
     */
    public String getPassword() {
        return password;
    }
    /**
     * <p>setter pola password</p>
     * @param password hasło użytkownika
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * <p>getter pola active</p>
     * @return zwraca status konta użytkownika
     */
    public boolean isActive() {
        return active;
    }
    /**
     * <p>setter pola active</p>
     * @param active status konta użytkownika
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    /**
     * <p>getter pola roles</p>
     * @return zwraca rolę użytkownika
     */
    public String getRoles() {
        return roles;
    }
    /**
     * <p>setter pola roles</p>
     * @param roles rola użytkownika
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }
    /**
     * <p>getter pola createDate</p>
     * @return zwraca datę utworzenia konta użytkownika
     */
    public Date getCreateDate(){return createDate;}
    /**
     * <p>setter pola createDate</p>
     * @param createDate data utworzenia konta użytkownika
     */
    public void setCreateDate(Date createDate){ this.createDate = createDate;}

    @Override
    public String toString() {
        return "Tutorial [id=" + Id + ", username=" + userName + ", passwd=" + password + ", active=" + active + ", role = " + roles + "]";
    }
}
