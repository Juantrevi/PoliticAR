/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.proyecto.controladores;

import com.example.proyecto.entidades.Foto;
import com.example.proyecto.entidades.Integrante;
import com.example.proyecto.entidades.Partido;
import com.example.proyecto.errores.ErrorServicio;
import com.example.proyecto.repositorios.PartidoRepositorio;
import com.example.proyecto.servicios.IntegranteServicio;
import com.example.proyecto.servicios.PartidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mateo
 */
  @Controller
@RequestMapping("/foto")
public class FotoControlador {
    
    @Autowired
    private PartidoServicio partS;
    @Autowired
    private IntegranteServicio inteS;
    
    
    @GetMapping("/partidos")
    public ResponseEntity <byte[]> fotoPartido(@RequestParam String id) throws ErrorServicio {
        
        try {
            Partido partido= partS.buscarPorID(id);
            if (partido.getFoto() == null) {
                throw new ErrorServicio ("La noticia no tiene foto asignada");
            }
            byte[] foto= partido.getFoto().getContenido();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            
            return new ResponseEntity<> (foto,headers,HttpStatus.OK); 
                    } catch (ErrorServicio ex) {
            Logger.getLogger(FotoControlador.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity <> (HttpStatus.NOT_FOUND);
       
                    }
    
    }
    
        @GetMapping("/integrantes")
    public ResponseEntity <byte[]> fotoIntegrante(@RequestParam String id) throws ErrorServicio {
        
        try {
            Integrante integrante= inteS.buscarPorID(id);
            if (integrante.getFoto() == null) {
                throw new ErrorServicio ("El integrante no tiene foto asignada");
            }
            byte[] foto= integrante.getFoto().getContenido();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            
            return new ResponseEntity<> (foto,headers,HttpStatus.OK); 
                    } catch (ErrorServicio ex) {
            Logger.getLogger(FotoControlador.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity <> (HttpStatus.NOT_FOUND);
            
                    }
    
    }
  }

