package microplatform.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class DefaultSecurityConfig {

//    /**
//     * calling authorizeRequests.anyRequest().authenticated() to require
//     * authentication for all requests.
//     * We're also providing a form-based authentication by invoking the
//     * formLogin(defaults()) method.
//     */
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests.anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .build();
//
//    }
//
//    @Bean
//    UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(User
//                .withDefaultPasswordEncoder()
//                .username("admin")
//                .password("password")
//                .roles("JAJA")
//                .build());
//    }

}
