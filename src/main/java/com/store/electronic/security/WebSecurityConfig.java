package com.store.electronic.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    DataSource dataSource;

    /*@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/

    // login Configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .anyRequest().authenticated()
            ).formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/products")
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutUrl("/logout")
            );

        return http.build();
    }

    // Este es un usuario que se esta alojando en memoria
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("super")
                        .password("123")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

    // Para usuarios alojados en la base de datos
    @Bean
    UserDetailsManager users(DataSource dataSource) {
        /*UserDetails user = User.builder()
                .username("user")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("USER", "ADMIN")
                .build();*/
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        /*users.createUser(user);
        users.createUser(admin);*/
        return users;
    }

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder(){
        return new BCryptPasswordEncoder(); // Se le puede pasar el tamaño de encriptado ejemplo 10 o 16
    }
}
