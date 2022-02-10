package com.example.proyecto.Controladores;

import com.example.proyecto.Entidades.Noticia;
import com.example.proyecto.Errores.ErrorServicio;
import com.example.proyecto.Repositorios.NoticiaRepositorio;
import com.example.proyecto.Servicios.NoticiaServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/")
public class NoticiaControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;
    @Autowired
    private NoticiaRepositorio noticiaRepositorio;


    @PostMapping("/app/nuevaNoticia/crear")
    public String crear(ModelMap modelo, @RequestParam String titulo, @RequestParam String copete,
            @RequestParam String cuerpo, @RequestParam String fuente, @RequestParam MultipartFile archivo) {

        try {
            noticiaServicio.crear(archivo, titulo, copete, cuerpo, fuente);
            return "redirect:/app/administrador/administrar-noticias";
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            // Si hay error se mantengan los campos llenos
            modelo.put("titulo", titulo);
            modelo.put("copete", copete);
            modelo.put("cuerpo", cuerpo);
            modelo.put("fuente", fuente);
            Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "NoticiaCrear.html";
        }
    }

    //Administrador de Noticias
    @GetMapping("app/administrador/administrar-noticias")
    public String administrarNoticias(ModelMap modelo) {
        List<Noticia> noticias = noticiaServicio.todasNoticias();
        modelo.put("noticias", noticias);
        return "administrador_noticias";
    }

    //n
    @GetMapping("/noticia1")
    public String noticia(@RequestParam String id, ModelMap modelo) {
        try {
            Noticia noticia = noticiaServicio.buscarPorId(id);
            modelo.put("titulo", noticia.getTitulo());
            modelo.put("copete", noticia.getCopete());
            modelo.put("fecha", noticia.getFecha());
            modelo.put("fuente", noticia.getFuente());
            modelo.put("cuerpo", noticia.getCuerpo());
            modelo.put("foto", noticia.getId());

            return "Noticia.html";
        } catch (ErrorServicio ex) {
            Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, ex);

            return "inicio.html";
        }
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam String id,ModelMap modelo) {

        try {
            Noticia noticia = noticiaServicio.buscarPorId(id);
            modelo.addAttribute("noticia", noticia);
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "noticiaModificar.html";

    }
@PostMapping("/actualizar")
    public String actualizar(ModelMap modelo,@RequestParam String id, @RequestParam String titulo, @RequestParam String copete,
            @RequestParam String cuerpo, @RequestParam String fuente, @RequestParam(required = false) MultipartFile archivo) {

        try {
            noticiaServicio.modificar(archivo, id, titulo, copete, cuerpo, fuente);
            return "redirect:/app/administrador/administrar-noticias";
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            // Si hay error se mantengan los campos llenos
            modelo.put("titulo", titulo);
            modelo.put("copete", copete);
            modelo.put("cuerpo", cuerpo);
            modelo.put("fuente", fuente);
            Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "noticiaModificar.html";
        }
    }
    //Portal de Noticias
    @GetMapping("app/noticias")
    public String noticias(ModelMap modelo) {
        List<Noticia> noticias = noticiaServicio.noticiasHabilitadas();
        modelo.put("noticias", noticias);
        return "noticias";  
   }  
    //Deshabilitar
    @GetMapping("/deshabilitar")
    public String deshabilitar (@RequestParam String id){
        try {
            noticiaServicio.deshabilitar(id);
            return "redirect:/app/administrador/administrar-noticias";
        } catch (ErrorServicio ex) {
            Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "administrador_noticias";
        }
        
    }
    
        //Habilitar
    @GetMapping("/habilitar")
    public String habilitar (@RequestParam String id){
        try {
            noticiaServicio.habilitar(id);
            return "redirect:/app/administrador/administrar-noticias";
        } catch (ErrorServicio ex) {
            Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "administrador_noticias";
        }
        
    }
    
    
    
}
