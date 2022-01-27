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

/**
 *
 * @author mateo
 */
public interface PartidoPoliticoRepositorio extends JpaRepository<Partido, Object>{
    @Query("Select c FROM IntegrantePartido c WHERE c.id = :id")
    public PartidoPolitico buscarPorId(@Param("id") String nombre);
    
    @Query("DELETE c FROM IntegrantePartido c WHERE c.id = :id")
    public PartidoPolitico eliminarPorId(@Param("id") String nombre);
}
