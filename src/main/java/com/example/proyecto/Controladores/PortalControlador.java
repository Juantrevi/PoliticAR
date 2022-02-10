package com.example.proyecto.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/app")
public class PortalControlador {

    @GetMapping("/index")
    public String index(){
        return "inicio";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model){
        if (error != null){
            model.put("error", "Usuario o contraseña incorrecta");
        }
        if (logout != null){
            model.put("logout", "Ha salido correctamente de la plataforma");
        }
        return "login";
    }
//    @GetMapping("/registrarse")
//    public String registrarse(){
//        return "registrarse";
//    }
    
    //------------ Sergio ------------
       @GetMapping("/nuevaNoticia")
    public String noticiaCrear() {
        return "NoticiaCrear.html";
    }
      @GetMapping("/administrador")
    public String administrador() {
        return "administrardor_noticas";
    }
    //------------- Fin Sergio ----------------
    
    
    
    
}
