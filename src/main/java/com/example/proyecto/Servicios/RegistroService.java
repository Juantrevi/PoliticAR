package com.example.proyecto.Servicios;

import com.example.proyecto.Email.EnvioEmail;
import com.example.proyecto.Email.ValidadorEmail;
import com.example.proyecto.Entidades.ConfirmacionToken;
import com.example.proyecto.Entidades.RegistroPedido;
import com.example.proyecto.Entidades.Roles;
import com.example.proyecto.Entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistroService {

    @Autowired
    private ValidadorEmail validadoremail;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ConfirmacionTokenServicio confirmacionTokenServicio;
    @Autowired
    private EnvioEmail envioEmail;


    public String registro(RegistroPedido pedido){
        boolean emailValido = validadoremail.test(pedido.getEmail());

        if (!emailValido){
            throw new IllegalStateException("Email no valido");
        }
        String token = usuarioService.registrarUsuario(
                new Usuario(
                        pedido.getNombre(),
                        pedido.getApellido(),
                        pedido.getEmail(),
                        pedido.getPassword(),
                        Roles.USER
                )
        );

        String link = "http://localhost:8080/usuario/confirmartoken/" + token;

        envioEmail.enviar(pedido.getEmail(), construirEmail(pedido.getNombre(), link));

        return token;
    }

    @Transactional
    public String confirmarToken(String token){

        ConfirmacionToken confirmacionToken = confirmacionTokenServicio.getToken(token).orElseThrow(()-> new IllegalStateException("Token no encontrado"));

        if (confirmacionToken.getConfirmadoEn() != null){
            throw new IllegalStateException("Email ya confirmado");
        }

        LocalDateTime expiraEn = confirmacionToken.getVenceEn();

        if (expiraEn.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Token vencido");
        }
        confirmacionTokenServicio.setConfirmadoEn(token);
        usuarioService.habilitarUsuario(
                confirmacionToken.getUsuario().getEmail()
        );
        return "confirmed";
    }

    private String construirEmail(String nombre, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirme su e-mail</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hola " + nombre + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Muchas gracias por registrarse. Por favor haga click en el siguiente link para activar su cuenta: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n El link vencera en 15 minutos. <p>Nos vemos pronto</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

}

