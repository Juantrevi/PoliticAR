package com.example.proyecto.servicios;

import com.example.proyecto.entidades.Foto;
import com.example.proyecto.errores.ErrorServicio;
import com.example.proyecto.repositorios.FotoRepositorio;
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
public class FotoServicio {

    @Autowired
    private FotoRepositorio fotoRepositorio;
    @Transactional
    public Foto guardar(MultipartFile archivo) throws ErrorServicio {

        if (!archivo.isEmpty() || archivo != null) {
            try {
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
                foto.setContenido(archivo.getBytes());
                foto.setNombre(archivo.getName());

                return fotoRepositorio.save(foto);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
        }
    
    @Transactional
    public Foto actualizar(String idFoto, MultipartFile archivo) throws ErrorServicio {

        if (archivo != null) {
            try {
                Foto foto = new Foto();
                if (idFoto != null) {
                    Optional <Foto> respuesta = fotoRepositorio.findById(idFoto);
                    if (respuesta.isPresent()) {
                        foto=respuesta.get();
                    }
                }
                       
                foto.setMime(archivo.getContentType());
                foto.setContenido(archivo.getBytes());
                foto.setNombre(archivo.getName());

                return fotoRepositorio.save(foto);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;

    }

}
