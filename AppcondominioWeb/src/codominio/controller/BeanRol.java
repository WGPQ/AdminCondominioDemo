package codominio.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import condominio.model.entities.Rol;
import condominio.model.manager.ManagerRol;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanRol implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// invocacion EJB
	@EJB
	private ManagerRol managerRol;
	
	// variables de sesion
	private List<Rol> listaRoles;
	private Rol rol;
	private String nombre;
	private String descripcion;
	private boolean panelColapsado;
	private Rol rolSelecionado;

	// Metodos de inteacion entre model y vista
	@PostConstruct
	public void inicializar() {
		listaRoles = managerRol.findAllRoles();	
		rol = new Rol();
		panelColapsado =  true;
	}
	
	public void actionListenerInsertarRol() {
		try {
			managerRol.insertarRol(rol,nombre,descripcion);
			listaRoles = managerRol.findAllRoles();
			nombre = "";
			descripcion = "";
			rol = new Rol();
			listaRoles = managerRol.findAllRoles();
			JSFUtil.crearMensajeInfo("Rol ingresado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
		
	}
	
	public void actionListenerColapsar() {
		panelColapsado = !panelColapsado;
	}
	
	public void actionListenerEliminarRol(int id) {
		managerRol.eliminarRol(id);
		listaRoles  = managerRol.findAllRoles();
		JSFUtil.crearMensajeInfo("Rol Eliminado");
	}
	
	public void actionListenerSelecionarRol(Rol rol) {
		rolSelecionado = rol;
	}
	
	public void actionListenerActualizarRol() {
		try {
			managerRol.actualiarRol(rolSelecionado);
			listaRoles = managerRol.findAllRoles();
			JSFUtil.crearMensajeInfo("Rol actualizado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	

	//Accesores
	
	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Rol getRolSelecionado() {
		return rolSelecionado;
	}

	public void setRolSelecionado(Rol rolSelecionado) {
		this.rolSelecionado = rolSelecionado;
	}
	

}
