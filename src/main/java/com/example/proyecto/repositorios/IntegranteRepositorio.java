/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.proyecto.repositorios;
import com.example.proyecto.entidades.Integrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mateo
 */
@Repository
public interface IntegranteRepositorio extends JpaRepository<Integrante, Object>{
    /* 
    @Query("Select c FROM Integrante c WHERE c.id = :id")
    public Integrante buscarPorId(@Param("id") String nombre);
    
    @Query("DELETE c FROM Integrante c WHERE c.id = :id")
    public Integrante eliminarPorId(@Param("id") String nombre);*/
}