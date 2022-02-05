/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.proyecto.servicios;

import com.example.proyecto.entidades.Foto;
import com.example.proyecto.entidades.Integrante;
import com.example.proyecto.entidades.Partido;
import com.example.proyecto.errores.ErrorServicio;
import com.example.proyecto.repositorios.IntegranteRepositorio;
import com.example.proyecto.repositorios.PartidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;
/**
 *
 * @author mateo
 */
@Service
public class IntegranteServicio {
    
    @Autowired
    private IntegranteRepositorio inteRepo;
    @Autowired
    private PartidoRepositorio partRepo;
    @Autowired
    private FotoServicio fService;
    
    public void crearIntegrante(String nombre, MultipartFile archivo, String partidoId) throws ErrorServicio{
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("Debe introducir un nombre");
        }
        if (archivo == null || archivo.isEmpty()) {
            throw new ErrorServicio("Debe introducir una foto");
        }
        if (partidoId == null || partidoId.isEmpty()) {
            throw new ErrorServicio("Debe introducir la del partido");
        }
        Integrante inte = new Integrante();
        inte.setNombre(nombre);
        
        Optional<Partido> partC=partRepo.findById(partidoId);
        if(partC.isPresent()){
            Partido partido= partC.get();
            inte.setPartido(partido);
        }else{
            throw new ErrorServicio("ID del partido incorrecta"); 
        }
        Foto foto = fService.guardar(archivo);
        inte.setFoto(foto);
        inteRepo.save(inte);
    }
    
   public void modificarIntegrante(String id, String nombre, MultipartFile archivo, String partidoId) throws ErrorServicio{
       if (id == null || id.isEmpty()) {
            throw new ErrorServicio("Debe introducir la ID del integrante");
        }
       if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("Debe introducir un nombre");
        }
        if (archivo == null || archivo.isEmpty()) {
            throw new ErrorServicio("Debe introducir una foto");
        }
        if (partidoId == null || partidoId.isEmpty()) {
            throw new ErrorServicio("Debe introducir la del partido");
        }
        Optional<Integrante> inteC=inteRepo.findById(id);
        if(inteC.isPresent()){
            Integrante integrante= inteC.get();
            integrante.setNombre(nombre);
            
            //PARTIDO POLITICO
            Optional<Partido> partC=partRepo.findById(partidoId);
            if(partC.isPresent()){
            Partido partido= partC.get();
            integrante.setPartido(partido);
            }else{
               throw new ErrorServicio("ID del partido incorrecta"); 
            }
            
            //FOTO INTEGRANTE
            String idFoto = null;
            if (integrante.getFoto() != null) {
                idFoto = integrante.getFoto().getId();
            }
            Foto foto = fService.actualizar(idFoto, archivo);
            integrante.setFoto(foto);
            inteRepo.save(integrante);
            
            }else{
               throw new ErrorServicio("ID del integrante incorrecta"); 
            }
        }

    public void bajaIntegrante(String id) throws ErrorServicio{
        if (id == null || id.isEmpty()) {
            throw new ErrorServicio("Debe introducir la ID del integrante");
        }
        
        //Verificacion de la existencia del ID
        Optional<Integrante> inteC=inteRepo.findById(id);
        if(inteC.isPresent()){
            Integrante integrante=inteC.get();
            inteRepo.deleteById(integrante.getId());
        }else{
            throw new ErrorServicio("ID del integrante incorrecta"); 
        }
    }
   
    public Integrante buscarPorID(String id) throws ErrorServicio{
        if (id == null || id.isEmpty()) {
            throw new ErrorServicio("Debe introducir la id");
        }
        //Verificacion de la existencia del ID
        
        Optional<Integrante> intetC=inteRepo.findById(id);
        if(intetC.isPresent()){
            Integrante integrante=intetC.get();
            return integrante;
        }else{
            System.out.println("NO SE ENCONTRO");
            throw new ErrorServicio("ID del integrante incorrecta"); 
            
        }
    
   }
}

