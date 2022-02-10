package com.example.proyecto.Controladores;

import com.example.proyecto.Entidades.Integrante;
import com.example.proyecto.Entidades.Noticia;
import com.example.proyecto.Entidades.Partido;
import com.example.proyecto.Errores.ErrorServicio;
import com.example.proyecto.Servicios.IntegranteServicio;
import com.example.proyecto.Servicios.NoticiaServicio;
import com.example.proyecto.Servicios.PartidoServicio;
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
    
    @Autowired
    private PartidoServicio partS;
    @Autowired
    private IntegranteServicio inteS;
    
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
