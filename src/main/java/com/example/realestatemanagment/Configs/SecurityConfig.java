package com.example.realestatemanagment.Configs;

import com.example.realestatemanagment.Filter.JwtRequestFilter;
import com.example.realestatemanagment.Service.CustomDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomDetailsService customDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(CustomDetailsService customDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customDetailsService = customDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception{

    var auth = new DaoAuthenticationProvider();
    auth.setPasswordEncoder(passwordEncoder);
    auth.setUserDetailsService(customDetailsService);

    return new ProviderManager(auth);
}
    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws  Exception{
    http
            .csrf(csrf -> csrf.disable())
            .httpBasic(basic -> basic.disable())
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests(authorize -> authorize

                            .requestMatchers("/maintenances/**").hasRole("INVESTOR")
                            .requestMatchers("/properties/**").hasRole("INVESTOR")
                    .requestMatchers(HttpMethod.POST,"/tenants").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET,"/tenants").hasAnyRole("ADMIN","INVESTOR")
                    .requestMatchers(HttpMethod.PUT,"/tenants").hasRole("TENANT")
                    .requestMatchers(HttpMethod.DELETE,"/tenants").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST,"/investors").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET,"/investors").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT,"/investors").hasRole("INVESTOR")
                    .requestMatchers(HttpMethod.DELETE,"/investors").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST,"/leaseagreements").hasRole("INVESTOR")
                    .requestMatchers(HttpMethod.GET,"/leaseagreements").hasRole("INVESTOR")
                    .requestMatchers(HttpMethod.PUT,"/leaseagreements").hasRole("INVESTOR")
                    .requestMatchers(HttpMethod.DELETE,"/leaseagreements").hasRole("INVESTOR")
                            .requestMatchers("/auth/**").permitAll()

                    //.requestMatchers(HttpMethod.POST,"/authenticate").permitAll()
                    //.requestMatchers("/authenticate").permitAll()
                    //.anyRequest().denyAll()

            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
}

}
