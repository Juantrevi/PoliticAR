package com.example.proyecto.Servicios;

import com.example.proyecto.Entidades.ConfirmacionToken;
import com.example.proyecto.Entidades.Usuario;
import com.example.proyecto.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService implements UserDetailsService {

    private final static String USUARIO_NO_ENCONTRADO = "Usuario %s no encontrado";

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ConfirmacionTokenServicio confirmacionTokenServicio;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepositorio.findByEmail(email);
        if (usuario!=null){
            Usuario usuario1 = usuario.get();
            List<GrantedAuthority> permisos = new ArrayList<>();
            GrantedAuthority p1 = new SimpleGrantedAuthority("USER");

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario1);

            User user = new User(usuario1.getEmail(), usuario1.getPassword(), permisos);
            return user;
        }else return null;


       /* return usuarioRepositorio.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(String.format(USUARIO_NO_ENCONTRADO, email)));*/
    }

    public String registrarUsuario(Usuario usuario){
        boolean existe = usuarioRepositorio.findByEmail(usuario.getEmail()).isPresent();

        if (existe){
            throw new IllegalStateException("Email ya existe");
        }

        String encodePass = bCryptPasswordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodePass);

        usuarioRepositorio.save(usuario);

        String token = UUID.randomUUID().toString();
        ConfirmacionToken confirmacionToken = new ConfirmacionToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), usuario);

        confirmacionTokenServicio.guardarConfirmacionToken(confirmacionToken);

        return token;
    }
    public int habilitarUsuario(String email){
        return usuarioRepositorio.habilitarUsuario(email);
    }
}
