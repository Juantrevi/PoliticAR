package com.example.proyecto.servicios;

import com.example.proyecto.entidades.Foto;
import com.example.proyecto.entidades.Noticia;
import com.example.proyecto.errores.ErrorServicio;
import com.example.proyecto.repositorios.NoticiaRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Sergio
 */
@Service
public class NoticiaServicio {

    @Autowired
    private NoticiaRepositorio noticiaRepositorio;
    @Autowired
    private FotoServicio fotoServicio;

    @Transactional
    public void crear(MultipartFile archivo, String titulo, String copete, String cuerpo, String fuente) throws ErrorServicio {

        validar(titulo, copete, cuerpo, fuente);
        Noticia noticia = new Noticia();

        noticia.setTitulo(titulo);
        noticia.setCopete(copete);
        noticia.setCuerpo(cuerpo);
        noticia.setFuente(fuente);
        noticia.setBaja(null);
        noticia.setFecha(new Date());

        Foto foto = fotoServicio.guardar(archivo);
        noticia.setFoto(foto);

        noticiaRepositorio.save(noticia);
    }

    @Transactional
    public void modificar(MultipartFile archivo, String id, String titulo, String copete, String cuerpo, String fuente) throws ErrorServicio {

        validar(titulo, copete, cuerpo, fuente);
        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();

            noticia.setTitulo(titulo);
            noticia.setCopete(copete);
            noticia.setCuerpo(cuerpo);
            noticia.setFuente(fuente);
            // noticia.setBaja(null);
            // noticia.setFecha(new Date());

            String idFoto = null;
            if (noticia.getFoto() != null) {
                idFoto = noticia.getFoto().getId();
            }
            Foto foto = fotoServicio.actualizar(idFoto, archivo);
            noticia.setFoto(foto);
            noticiaRepositorio.save(noticia);
        } else {

            throw new ErrorServicio("No se encontro la noticia.");
        }

    }

    @Transactional
    public void deshabilitar(String id) throws ErrorServicio {
        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();
            noticia.setBaja(new Date());
            noticiaRepositorio.save(noticia);
        } else {
            throw new ErrorServicio("No se encontró la noticia solicitada");
        }
    }

    @Transactional
    public void habilitar(String id) throws ErrorServicio {
        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();
            noticia.setBaja(null);
            noticiaRepositorio.save(noticia);
        } else {
            throw new ErrorServicio("No se encontró la noticia solicitada");
        }
    }

    public Noticia buscarPorId(String id) throws ErrorServicio {
        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);
        Noticia noticia =null;
        if (respuesta.isPresent()) {
            noticia = respuesta.get();
        }else{
           throw new ErrorServicio("No se encontró una noticia con ese Id"); 
        }
        return noticia;
        }
        
        // VALIDAR --------------------------------------------------------------------
    public void validar(String titulo, String copete, String cuerpo, String fuente) throws ErrorServicio {

        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El titulo de la noticia no puede estar vacio");
        }
        if (copete == null || copete.isEmpty()) {
            throw new ErrorServicio("El copete de la noticia no puede estar vacio");
        }
        if (cuerpo == null || cuerpo.isEmpty()) {
            throw new ErrorServicio("El cuerpo de la noticia no puede estar vacio");
        }
        if (fuente == null || fuente.isEmpty()) {
            throw new ErrorServicio("La fuente de la noticia no puede estar vacia");
        }
    }

}
