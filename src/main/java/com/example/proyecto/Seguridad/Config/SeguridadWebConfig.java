package com.example.proyecto.Seguridad.Config;

import com.example.proyecto.Servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SeguridadWebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/usuario/crearUsuario", "/**", "/app/registrarse","/app/administrador")
                .permitAll()
                .and().formLogin().loginPage("/login")
                .loginProcessingUrl("/logincheck")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/app/index")
                .permitAll()
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/app/index")
                .permitAll()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(usuarioService);
        return provider;
    }
}
