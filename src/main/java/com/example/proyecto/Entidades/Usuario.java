package com.example.proyecto.Entidades;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles roles;
    private boolean habilitado = false;
    private boolean deshabilitado = false;

    public Usuario(){

    }

    public Usuario(String nombre, String apellido, String email, String password, Roles roles) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public boolean isDeshabilitado() {
        return deshabilitado;
    }

    public void setDeshabilitado(boolean deshabilitado) {
        this.deshabilitado = deshabilitado;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roles.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !deshabilitado;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return habilitado;
    }
}
