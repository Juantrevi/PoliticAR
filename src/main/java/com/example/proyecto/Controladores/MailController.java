package com.example.proyecto.Controladores;

import com.example.proyecto.Entidades.RegistroPedido;
import com.example.proyecto.Servicios.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/usuario")
public class MailController {

    @Autowired
    private RegistroService registroService;

    @GetMapping( "/crearUsuario")
    public String crearUsuario(Model model){

        RegistroPedido pedido = new RegistroPedido();
        model.addAttribute("pedido", pedido);

        return "registrarse";
    }

    @PostMapping( "/confirmacion")
    public String confirmacion(Model model, @ModelAttribute RegistroPedido pedido){

        registroService.registro(pedido);
        model.addAttribute("mensaje", "Ingrese a su email para confirmar su cuenta");
        return "Inicio";
    }

    @GetMapping("/confirmartoken/{token}")
    public String confirmartoken(@PathVariable("token") String token, Model model){
        model.addAttribute("mensaje", "Bienvenido! Ya puede iniciar sesion" );
        registroService.confirmarToken(token);
        return "Inicio";
    }
}
