package com.example.proyecto.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/")
public class PortalControlador {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/nuevaNoticia")
    public String noticiaCrear() {
        return "NoticiaCrear.html";
    }
    
        @GetMapping("/noticia")
    public String noticia() {
        return "Noticia.html";
    }

}
