/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.proyecto.controladores;

import com.example.proyecto.errores.ErrorServicio;
import com.example.proyecto.servicios.IntegranteServicio;
import com.example.proyecto.servicios.PartidoServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author mateo
 */
@Controller
@RequestMapping("/")
public class PortalControlador {
    @Autowired
    private IntegranteServicio intS;
    @Autowired
    private PartidoServicio parS;
    
    @GetMapping("/")
    public String index(){
        return "index.html";     
    }
    
    @GetMapping("/partidoC")
    public String partidoC(){
        return "partidoC.html";     
    }
    
    @GetMapping("/integranteC")
    public String integranteC(){
        return "integranteC.html";     
    }
    
    @PostMapping("/registrarI")
    public String registrarI(@RequestParam String nombre, @RequestParam MultipartFile archivo, @RequestParam String idPartido){
   
        try
        {
            intS.crearIntegrante(nombre, archivo, idPartido);
        } catch (ErrorServicio ex)
        {
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
             return "integranteC.html";
        }
        
        return "index.html";     
    }
    
     @PostMapping("/registrarP")
    public String registrarP(@RequestParam String nombre, @RequestParam MultipartFile archivo){
        
     try
        {
            parS.crearPartido(nombre, archivo);
        } catch (ErrorServicio ex)
        {
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "partidoC";
        }
        
        return "index.html";     
    }
}
