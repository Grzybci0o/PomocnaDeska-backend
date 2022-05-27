package Zespol6.Pomocna_deska.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Klasa poświadczeń użytkownika
 */
public class MyUserDetails implements UserDetails {
    /**
     * nazwa użytkownika
     */
    private String userName;
    /**
     * hasło użytkownika
     */
    private String password;
    /**
     * status obecności użytkownika
     */
    private boolean active;
    /**
     * lista użytkowników
     */
    private List<GrantedAuthority> authorities;
    /**
     * <p>Konstruktor klasy MyUserDetails</p>
     * @param user obiekt klasy User
     */
    public MyUserDetails(User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    /**
     * <p></p>
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    /**
     * getter pola password
     * @return password
     */
    @Override
    public String getPassword() {
        return password;
    }
    /**
     * getter pola userName
     * @return userName
     */
    @Override
    public String getUsername() {
        return userName;
    }
    /**
     * <p>Metoda sprawdzająca czy konto użytkownika nie jest przedawnione.</p>
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**
     * <p>Metoda sprawdzająca czy użytkownik jest zablokowany.</p>
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     * <p>Metoda sprawdzająca czy poświadczenia użytkownika nie są przedawnione.</p>
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * <p>Metoda sprawdzająca czy konto użytkownika jest aktywowane.</p>
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return active;
    }
}
