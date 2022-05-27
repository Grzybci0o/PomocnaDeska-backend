package Zespol6.Pomocna_deska.Repositories;

import Zespol6.Pomocna_deska.Model.MyUserDetails;
import Zespol6.Pomocna_deska.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
/**
 * inteface używany w obsłudze użytkowników
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     *
     * @param userName
     * @param password
     * @return
     */
    Optional<User> findByUserNameAndPassword(String userName, String password);
    /**
     *
     * @param userName
     * @param password
     * @return
     */
    User findUserByUserNameAndPassword(String userName, String password);

    /**
     *
     * @param userName
     * @return
     */
    User findByUserName(String userName);




}
