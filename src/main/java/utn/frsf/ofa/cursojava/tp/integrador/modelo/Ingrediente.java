/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.tp.integrador.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author mdominguez
 */
@Entity
public class Ingrediente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Integer id;
    private String descripcion;
    private Double costo;

    // TODO Completar mapeo de relacion
    @ManyToMany
    (mappedBy = "ingredientes")
    private List<Receta> usadoEnRecetas;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public List<Receta> getUsadoEnRecetas() {
        return usadoEnRecetas;
    }

    public void setUsadoEnRecetas(List<Receta> usadoEnRecetas) {
        this.usadoEnRecetas = usadoEnRecetas;
    }

    @Override
    public String toString() {
        return id+";"+descripcion+";"+costo;
    }

        @Override
    public int hashCode() {
        int hash = 7;
        hash = 22 * hash + Objects.hashCode(this.id);
        hash = 22 * hash + Objects.hashCode(this.descripcion);
        hash = 22 * hash + Objects.hashCode(this.costo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ingrediente other = (Ingrediente) obj;
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.costo, other.costo)) {
            return false;
        }
        return true;
    }
    
}
