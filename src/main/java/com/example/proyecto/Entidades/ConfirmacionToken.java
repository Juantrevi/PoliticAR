package com.example.proyecto.Entidades;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ConfirmacionToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime creadoEn;
    @Column(nullable = false)
    private LocalDateTime venceEn;
    private LocalDateTime confirmadoEn;
    @ManyToOne
    @JoinColumn(nullable = false, name = "usuario_id")
    private Usuario usuario;

    public ConfirmacionToken() {
    }

    public ConfirmacionToken(String token, LocalDateTime creadoEn, LocalDateTime venceEn, Usuario usuario) {
        this.token = token;
        this.creadoEn = creadoEn;
        this.venceEn = venceEn;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public LocalDateTime getVenceEn() {
        return venceEn;
    }

    public void setVenceEn(LocalDateTime venceEn) {
        this.venceEn = venceEn;
    }

    public LocalDateTime getConfirmadoEn() {
        return confirmadoEn;
    }

    public void setConfirmadoEn(LocalDateTime confirmadoEn) {
        this.confirmadoEn = confirmadoEn;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

