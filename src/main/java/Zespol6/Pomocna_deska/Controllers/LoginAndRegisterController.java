package Zespol6.Pomocna_deska.Controllers;

import Zespol6.Pomocna_deska.Model.User;
import Zespol6.Pomocna_deska.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
/**
 * Klasa odpowiaadjąca za obsługę bazy związanej z kontami użytkowinków
 */
public class LoginAndRegisterController {

    @Autowired
    UserRepository userRepository;
    /**
     *
     * @param user obiekt typu User
     * @return dodaje nowego użytkownika do bazy danych
     */
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
    /**
     *
     * @return lista użytkowników
     */
    @GetMapping("/admin/users")
    public ResponseEntity getUsers(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
    /**
     *
     * @param userName nazwa użytkownika
     * @return zmienia statu użytkownika na aktywny
     */
    @PatchMapping("/active")
    public User activeUser(@RequestParam String userName){
        User user = userRepository.findByUserName(userName);
        user.setActive(true);
        return userRepository.save(user);
    }
    /**
     *
     * @param userName nazwa użytkownika
     * @return zmienia statu użytkownika na nieaktywny
     */
    @PatchMapping("/deactive")
    public User deactiveUser(@RequestParam String userName){
        User user = userRepository.findByUserName(userName);
        user.setActive(false);
        return userRepository.save(user);
    }
    /**
     *<p>metoda odpowiadająca za logowanie pracownika supportu</p>
     *
     */
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) throws Exception {
        String tempUserName = user.getUserName();
        String tempPassword = user.getPassword();
        User userObj = null;
        if(tempUserName != null && tempPassword != null){
            userObj = userRepository.findUserByUserNameAndPassword(tempUserName,tempPassword);
        }
        if(userObj != null && userObj.isActive() == true){
            return "Zalogowano";
        }else{
            return "Złe dane logowania";
        }
    }
    @PostMapping("/loginadmin")
    public String loginAdmin(@RequestBody User user) throws Exception {
        String tempUserName = user.getUserName();
        String tempPassword = user.getPassword();
        User userObj = null;
        if(tempUserName != null && tempPassword != null){
            userObj = userRepository.findUserByUserNameAndPassword(tempUserName,tempPassword);
        }
        if(userObj != null && userObj.getRoles().equals("ROLE_ADMIN")){
            return "Zalogowano";
        }else{
            return "Nie posiadasz uprawnień administratora";
        }
    }

    /**
     *
     * @param userName nazwa użytkownika
     * @return usuwa użytkowinka z bazy danych
     */
    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUser(@RequestParam String userName){
        User user = userRepository.findByUserName(userName);
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /**
     *
     * @param userName nazwa użytkownika
     * @param name nowa nazwa użytkownika
     * @return zmienia nazwę użytkowanika w bazie danych
     */
    @PatchMapping("/admin/userName")
    public ResponseEntity<User> updateName(@RequestParam String userName,@RequestBody String name){
        User user = userRepository.findByUserName(userName);
        user.setUserName(name);
        return new ResponseEntity<>(userRepository.save(user),HttpStatus.OK);
    }
    /**
     *
     * @param userName nazwa użytkownika
     * @param password nowe hasło
     * @return zmienia hasło użytkownika
     */
    @PatchMapping("/admin/userPassword")
    public ResponseEntity<User> updatePassword(@RequestParam String userName,@RequestBody String password){
        User user = userRepository.findByUserName(userName);
        user.setPassword(password);
        return new ResponseEntity<>(userRepository.save(user),HttpStatus.OK);
    }
    /**
     *
     * @param userName nazwa użytkownika
     * @param role rola użytkownika
     * @return zmienia rolę użytkownika
     */
    @PatchMapping("/admin/userRole")
    public ResponseEntity<User> updateRole(@RequestParam String userName,@RequestBody String role){
        User user = userRepository.findByUserName(userName);
        user.setRoles(role);
        return new ResponseEntity<>(userRepository.save(user),HttpStatus.OK);
        }
}
