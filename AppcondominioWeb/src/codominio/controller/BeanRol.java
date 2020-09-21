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

	// Metodos de inteacion entre model y vista
	@PostConstruct
	public void inicializar() {
		listaRoles = managerRol.findAllRoles();	
		rol = new Rol();
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
			JSFUtil.crearMensajeInfo(e.getMessage());
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

}
