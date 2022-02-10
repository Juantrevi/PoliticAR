
package com.example.proyecto.Repositorios;

import com.example.proyecto.Entidades.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Sergio
 */

@Repository
public interface FotoRepositorio extends JpaRepository<Foto, String> {
    
    
    
}
