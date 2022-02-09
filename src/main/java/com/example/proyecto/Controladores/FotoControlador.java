package com.example.proyecto.Controladores;

import com.example.proyecto.Entidades.Noticia;
import com.example.proyecto.Errores.ErrorServicio;
import com.example.proyecto.Servicios.NoticiaServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/Foto")
public class FotoControlador {
    
    @Autowired
    private NoticiaServicio noticiaServicio;
    
    @GetMapping("/Noticia")
    public ResponseEntity <byte[]> fotoNotica(@RequestParam String id) throws ErrorServicio {
        
        try {
            Noticia noticia= noticiaServicio.buscarPorId(id);
            if (noticia.getFoto() == null) {
                throw new ErrorServicio ("La noticia no tiene foto asignada");
            }
            byte[] foto= noticia.getFoto().getContenido();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            
            return new ResponseEntity<> (foto,headers,HttpStatus.OK); 
                    } catch (ErrorServicio ex) {
            Logger.getLogger(FotoControlador.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity <> (HttpStatus.NOT_FOUND);
            // min 10:44
            //http://localhost:8084/Foto/Noticia?id=17cec526-59b6-417d-8f47-10cef202478c
        }
    }
    
        
        
    
}
