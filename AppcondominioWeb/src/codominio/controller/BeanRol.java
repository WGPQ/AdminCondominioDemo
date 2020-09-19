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
	
	// Metodos de inteacion entre model y vista
	@PostConstruct
	public void inicializar() {
		listaRoles = managerRol.findAllRoles();
		rol = new Rol();
	}
	
	public void actionListenerInsertarRol() {
		managerRol.insertarRol(rol);
		listaRoles = managerRol.findAllRoles();
		//rol = new Rol();
		JSFUtil.crearMensajeInfo("Rol ingresado");
	}
	
	
	//Accesores

	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
	
	
	
	

}
