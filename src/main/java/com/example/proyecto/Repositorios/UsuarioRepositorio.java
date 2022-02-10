package com.example.proyecto.Repositorios;

import com.example.proyecto.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {


    Optional<Usuario> findByEmail (String email);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario a SET a.habilitado = TRUE WHERE a.email = ?1" )
    int habilitarUsuario(String email);
}
