package com.example.proyecto.controladores;

import com.example.proyecto.entidades.Noticia;
import com.example.proyecto.errores.ErrorServicio;
import com.example.proyecto.servicios.NoticiaServicio;
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
    
    @PostMapping("/nuevaNoticia/crear")
    public String crear(ModelMap modelo, @RequestParam String titulo, @RequestParam String copete,
            @RequestParam String cuerpo, @RequestParam String fuente, @RequestParam MultipartFile archivo) {
        
        try { 
            noticiaServicio.crear(archivo, titulo, copete, cuerpo, fuente);
            return "index.html";
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
    
    //no funciona ver
    @PostMapping("/noticia1")
    public String noticia (@RequestParam String id,ModelMap modelo){
        try {
            Noticia noticia= noticiaServicio.buscarPorId(id);
            modelo.put("titulo", noticia.getTitulo());
            
            return "Noticia.html";
        } catch (ErrorServicio ex) {
            Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, ex);
            
            return "index.html";
        }
    }
    
}
