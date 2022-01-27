/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.proyecto.repositorios;
import com.example.proyecto.entidades.IntegrantePartido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author mateo
 */
public interface IntegrantePartidoRepositorio extends JpaRepository<IntegrantePartido, Object>{
     @Query("Select c FROM IntegrantePartido c WHERE c.id = :id")
    public IntegrantePartido buscarPorId(@Param("id") String nombre);
    
    @Query("DELETE c FROM IntegrantePartido c WHERE c.id = :id")
    public IntegrantePartido eliminarPorId(@Param("id") String nombre);
}
