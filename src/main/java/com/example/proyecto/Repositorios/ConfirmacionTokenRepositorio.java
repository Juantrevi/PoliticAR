package com.example.proyecto.Repositorios;

import com.example.proyecto.Entidades.ConfirmacionToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ConfirmacionTokenRepositorio extends JpaRepository<ConfirmacionToken, Long> {


    Optional<ConfirmacionToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmacionToken c SET c.confirmadoEn = ?2 WHERE c.token = ?1")
    int editarConfirmadoEn(String token, LocalDateTime confirmadoEn);

}
