
package com.example.proyecto.repositorios;

import com.example.proyecto.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sergio
 */

@Repository
public interface NoticiaRepositorio extends JpaRepository <Noticia, String> {
    
    
    
}
