/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.proyecto.Servicios;

import com.example.proyecto.Entidades.Foto;
import com.example.proyecto.Entidades.Partido;
import com.example.proyecto.Errores.ErrorServicio;
import com.example.proyecto.Repositorios.PartidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;
/**
 *
 * @author mateo
 */
@Service
public class PartidoServicio {
    @Autowired
    private PartidoRepositorio partRepo;
    @Autowired
    private FotoServicio fService;
    
    public void crearPartido(String nombre, MultipartFile archivo) throws ErrorServicio{
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("Debe introducir un nombre");
        }
        if (archivo == null || archivo.isEmpty()) {
            throw new ErrorServicio("Debe introducir una foto");
        }
        Partido partido=new Partido();
        partido.setNombre(nombre);
        Foto foto = fService.guardar(archivo);
        partido.setFoto(foto);
        partRepo.save(partido);
        
    }
    
    public void modificarPartido(String id, String nombre, MultipartFile archivo) throws ErrorServicio{
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("Debe introducir un nombre");
        }
        if (id == null || id.isEmpty()) {
            throw new ErrorServicio("Debe introducir la id");
        }
        if (archivo == null || archivo.isEmpty()) {
            throw new ErrorServicio("Debe introducir una foto");
        }
    Optional<Partido> partC= partRepo.findById(id);
    if(partC.isPresent()){
            Partido partido=partC.get();
            partRepo.deleteById(partido.getId());
        }else{
            throw new ErrorServicio("ID del integrante incorrecta"); 
        }
    }
    
    public void bajaPartido(String id) throws ErrorServicio{
        if (id == null || id.isEmpty()) {
            throw new ErrorServicio("Debe introducir la id");
        }
        //Verificacion de la existencia del ID
        
        Optional<Partido> partC=partRepo.findById(id);
        if(partC.isPresent()){
            Partido partido=partC.get();
            partRepo.deleteById(partido.getId());
        }else{
            throw new ErrorServicio("ID del integrante incorrecta"); 
        }
    }
        public Partido buscarPorID(String id) throws ErrorServicio{
        if (id == null || id.isEmpty()) {
            throw new ErrorServicio("Debe introducir la id");
        }
        //Verificacion de la existencia del ID
        
        Optional<Partido> partC=partRepo.findById(id);
        if(partC.isPresent()){
            Partido partido=partC.get();
            return partido;
        }else{
            
            throw new ErrorServicio("ID del partido incorrecta"); 
            
        }
        
    }
}
