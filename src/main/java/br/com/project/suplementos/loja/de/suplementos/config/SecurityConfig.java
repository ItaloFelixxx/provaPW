package br.com.project.suplementos.loja.de.suplementos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/admin/**").hasRole("ADMIN");
//                    auth.requestMatchers("/user/**").hasRole("ADMIN");
//                    auth.requestMatchers("/user/**").hasRole("USER");
//                    auth.anyRequest().authenticated();
//                })
//                .formLogin(Customizer.withDefaults())
//                .logout(Customizer.withDefaults())
//                .build();
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/admin/**").hasRole("ADMIN");
                    auth.requestMatchers("/admin/excluir/").hasRole("ADMIN");
                    auth.requestMatchers("/admin/editar").hasRole("ADMIN");
                    auth.requestMatchers("/admin/all").hasRole("ADMIN");
                    auth.requestMatchers("/admin/cadastro").hasRole("ADMIN");
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers("/user/**").hasRole("USER");
                    auth.requestMatchers("/home").permitAll();
                    auth.requestMatchers("/login").permitAll();
                    auth.requestMatchers("/logout").permitAll();
                    auth.requestMatchers("/ofertas").permitAll();
                    auth.requestMatchers("/user/adicionarCarrinho").hasRole("USER");
                    auth.requestMatchers("/user/chamarCarrinho").hasRole("USER");
                    auth.requestMatchers("/user/finalizarCompra").hasRole("USER");
                    auth.anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .build();
    }

    @Bean
    BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
