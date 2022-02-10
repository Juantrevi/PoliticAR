package com.example.proyecto.entidades;


import com.example.proyecto.entidades.Foto;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
/**
 *
 * @author mateo
 */
@Entity
@Table(name="Integrante")
public class Integrante implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    @OneToOne
    private Foto foto;
    @ManyToOne
    private Partido partido;

    public Partido getPartido() {
        return partido;
    }

    public Integrante() {
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }
    
}
