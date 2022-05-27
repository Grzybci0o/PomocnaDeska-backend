package Zespol6.Pomocna_deska.Services;

import Zespol6.Pomocna_deska.Model.MyUserDetails;
import Zespol6.Pomocna_deska.Model.User;
import Zespol6.Pomocna_deska.Repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
/**
 * Serwis obsługujący mapowanie użytkowników
 */
public class MyUserDetailsService implements UserDetailsService {
    /**
     * Serwis obsługujący mapowanie użytkowników
     */
    @Autowired
    UserRepository userRepository;
    /**
     * <p>Metoda pobierająca informacje o danym użytkowniku</p>
     * @param userName nazwa użytkownika
     * @return pobranie informacji zmapowanego użytkownika
     * @throws UsernameNotFoundException w przypadku nieznalezienia użytkownika o podanej nazwie
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //return new MyUserDetails(userName);
        Optional<User> user = userRepository.findByUserNameAndPassword(userName,"pass");

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        return user.map(MyUserDetails::new).get();
    }



}
