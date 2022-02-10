package com.example.proyecto.Controladores;

import com.example.proyecto.Entidades.Partido;
import com.example.proyecto.Errores.ErrorServicio;
import com.example.proyecto.Repositorios.PartidoRepositorio;
import com.example.proyecto.Servicios.PartidoServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    
    
    //------------ Mateo ------------
    @Autowired
    private PartidoServicio parS;
    @Autowired
    private PartidoRepositorio partR;
    
    
       @GetMapping("/partidos")
    public String partidos(Model modelo){
        List<Partido> partidos = partR.findAll();
        
        modelo.addAttribute("Partidos", partidos);
        return "partidos.html";     
    }
    @GetMapping("/partidoC")
    public String partidoC(){
        return "partidoC.html";     
    }
    
    
      @PostMapping("/agregarPartidos")
    public String registrarP(ModelMap modelo, @RequestParam String nombre, @RequestParam MultipartFile archivo){
        
     try
        {
            System.out.println("hola");
            parS.crearPartido(nombre, archivo);
        } catch (ErrorServicio ex)
        {
            modelo.put("error", ex);
            modelo.put("nombre", nombre);
            modelo.put("archivo", archivo);
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "partidoC";
        }
        
        return "partidoC.html";     
    }
    @GetMapping("/eliminarP")
    public String eliminarP (@RequestParam String id){
        try {
            parS.bajaPartido(id);
            return "redirect:/app/partidos";
        } catch (ErrorServicio ex) {
            Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "partidos.html";
        }
        
    }
    //------------- Fin Mateo -----------------
    
    
}
