/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.tp.integrador.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DualListModel;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Autor;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Ingrediente;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Receta;
import utn.frsf.ofa.cursojava.tp.integrador.servicio.IngredienteService;
import utn.frsf.ofa.cursojava.tp.integrador.servicio.RecetaService;
import java.util.Date;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Pedido;
import utn.frsf.ofa.cursojava.tp.integrador.servicio.PedidoService;

/**
 *
 * @author gmuller
 */
@SessionScoped
@Named("pedidoController")
public class PedidoController implements Serializable {
        
    @Inject
    RecetaService recetaSrv;

    @Inject
    PedidoService pedidoSrv;
    
    private Pedido pedidoSeleccionada;
    private List<Pedido> listaPedidos;
    private DualListModel<Receta> recetasDisponibles;
    
    
    public Pedido getPedidoSeleccionada() {
        return pedidoSeleccionada;
    }
      
  public void setPedidoSeleccionada(Pedido pedidoSeleccionada) {
        this.pedidoSeleccionada = pedidoSeleccionada;
        this.pedidoSeleccionada.setRecetas(pedidoSrv.recetasPorIdPedido(pedidoSeleccionada.getId()));
        this.recetasDisponibles.setTarget(pedidoSeleccionada.getRecetas());       
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }
    
   @PostConstruct
    public void init() {
        this.pedidoSeleccionada = null;
        this.listaPedidos = pedidoSrv.listar();
        List<Receta> origen = recetaSrv.listar();
        List<Receta> destino = new ArrayList<Receta>();
        this.recetasDisponibles = new DualListModel<>(origen, destino);        
    }  
    
    public DualListModel<Receta> getRecetasDisponibles() {
        return recetasDisponibles;
    }
    
    public void setRecetasDisponibles(DualListModel<Receta> recetasDisponibles) {
        this.recetasDisponibles = recetasDisponibles;
    }
    
     public String guardar() {
        pedidoSeleccionada.setRecetas(this.recetasDisponibles.getTarget());
        Pedido tmp = this.pedidoSrv.guardar(pedidoSeleccionada);
        this.listaPedidos.add(tmp);        
        this.pedidoSeleccionada = null;
        return null;
    }
    
    public String nuevo() {
        this.pedidoSeleccionada = new Pedido();
        this.pedidoSeleccionada.setRecetas(new ArrayList<>());
        this.recetasDisponibles.setTarget(new ArrayList<Receta>());
        return null;
    }
         
    
    
}
