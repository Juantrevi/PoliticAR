/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.proyecto.repositorios;
import com.example.proyecto.entidades.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mateo
 */
@Repository
public interface PartidoRepositorio extends JpaRepository<Partido, Object>{
    /*
    @Query("Select c FROM Partido c WHERE c.id = :id")
    public Partido buscarPorId(@Param("id") String nombre);
    
    @Query("DELETE c FROM Partido c WHERE c.id = :id")
    public Partido eliminarPorId(@Param("id") String nombre);*/
}