
package com.example.proyecto.repositorios;

import com.example.proyecto.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Sergio
 */

@Repository
public interface FotoRepositorio extends JpaRepository<IntegrantePartido, String> {
    
    
    
}
