package ru.pcs.web.gamegalaxy.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.pcs.web.gamegalaxy.security.details.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/games/**").permitAll()
                .antMatchers("/add-to-cart").authenticated()
                .antMatchers("/add-review").authenticated()
                .antMatchers("/cart/**").authenticated()
                .antMatchers("/my-account/**").authenticated()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/sign-up").permitAll()
                .and()
                .formLogin()
                .loginPage("/sign-in")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/games")
                .permitAll()
                .and()
                .logout((logout) -> logout.deleteCookies("remove")
                        .invalidateHttpSession(false)
                        .logoutUrl("/my-account/logout")
                        .logoutSuccessUrl("/sign-in"));
    }
}
