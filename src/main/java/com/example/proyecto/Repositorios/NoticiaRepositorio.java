
package com.example.proyecto.Repositorios;

import com.example.proyecto.Entidades.Noticia;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sergio
 */

@Repository
public interface NoticiaRepositorio extends JpaRepository <Noticia, String> {

    @Query("SELECT n FROM Noticia n WHERE n.baja=null ORDER BY n.fecha DESC")    
    public List <Noticia> noticiasHabilitadas();
    
    
    
}
