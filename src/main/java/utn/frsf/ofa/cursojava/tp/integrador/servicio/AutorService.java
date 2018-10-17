/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.tp.integrador.servicio;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Autor;

/**
 *
 * @author mdominguez
 */
@Stateless
public class AutorService {
    @PersistenceContext(unitName = "RECETAS_PU")
    private EntityManager em;
    
    public Autor guardar(Autor a){
        if(a.getId()!=null && a.getId()>0) {
            return em.merge(a);
        }
        em.persist(a);
        em.flush();
        em.refresh(a);
        return a;
    }  
    
    public List<Autor> listar(){
        return em.createQuery("SELECT a FROM Autor a").getResultList();
    }
    
        public List<Autor> listar2(){
        //List<Autor> autores2;   
        //autores2 = em.createQuery("SELECT a FROM Autor a").getResultList();
        return em.createQuery("SELECT a FROM Autor a").getResultList();
    }
}
