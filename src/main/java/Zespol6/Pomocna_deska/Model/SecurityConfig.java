package Zespol6.Pomocna_deska.Model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
/**
 * Konfiguracja SpringSecurity
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * Pole danych użytkownika
     */
    @Autowired
    UserDetailsService userDetailsService;
    /**
     * Pole danych źródłowych
     */
    @Autowired
    private DataSource dataSource;
    /**
     *<p>Konfiguracja autoryzacji użytkownika</p>
     * @param auth
     * @throws Exception obsługa niepowodzenia autoryzacji
     */
    //@Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select user_name, password, active from user where user_name=?")
                .authoritiesByUsernameQuery("select user_name, roles from user where user_name=?");
    }
    /**
     *<p>Konfiguracja dostępu użytkowników do danych endpointów</p>
     * @param http przyjęty endpoint
     * @throws Exception w przypadku nieobsługiwanego endpointu
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().authorizeRequests()
                .antMatchers("/admin**").permitAll()
                .antMatchers("/register").permitAll();
        http.csrf().disable();
        http.cors();
    }
    /**
     *<p>Szyfrowanie haseł</p>
     * @return
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {return NoOpPasswordEncoder.getInstance(); //only for tests
    }


}
