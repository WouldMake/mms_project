package br.com.mesttra.project.config;

import br.com.mesttra.project.data.UserRepository;
import br.com.mesttra.project.filter.AuthTokenFilter;
import br.com.mesttra.project.service.AuthService;
import br.com.mesttra.project.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@EnableGlobalMethodSecurity (prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthService authService;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public SecurityConfig (AuthService authService,
                           TokenService tokenService,
                           UserRepository userRepository) {
        this.authService = authService;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    // Authentication config
    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(encoder());
    }

    // Authorization config
    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/projects").hasRole("ADMIN")
                .antMatchers("/auth").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new AuthTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager () throws Exception {
        return super.authenticationManager();
    }
}
