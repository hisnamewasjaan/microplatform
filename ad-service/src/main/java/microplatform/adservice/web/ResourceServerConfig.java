package microplatform.adservice.web;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ResourceServerConfig {

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.mvcMatcher("/ads/**")
//                .authorizeRequests()
//                .mvcMatchers("/ads/**")
//                .access("hasAuthority('SCOPE_ads.read')")
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
//        return http.build();
//    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                /* allow Access-Control headers on the requests. */
                .cors()
                .and()
                .authorizeRequests()
                    .antMatchers("/favicon.ico", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
                    .permitAll()
//                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/user/info", "/api/ads/**")
                .hasAuthority("SCOPE_read")
                .antMatchers(HttpMethod.POST, "/api/ads")
                .hasAuthority("SCOPE_write")
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }

}
