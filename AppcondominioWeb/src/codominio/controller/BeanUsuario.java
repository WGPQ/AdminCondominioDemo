package codominio.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import condominio.model.entities.Rol;
import condominio.model.entities.Usuario;
import condominio.model.manager.ManagerRol;
import condominio.model.manager.ManagerUser;

@Named
@SessionScoped
public class BeanUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerUser manageruser;
	@EJB
	private ManagerRol mRol;

	private List<Usuario> listausuarios;
	private List<Rol> listaRol;
	private String email;
	private String clave;
	private boolean verificado;

	@PostConstruct
	public void inicializar() {
		listausuarios = manageruser.findAllUsers();
		listaRol = mRol.findAllRoles();
	}
	
	public String actionValidarUsuario() {
		String ruta = manageruser.verificarUsuario(email,clave)+"?faces-redirect=true";
		if (!ruta.equals(""))
			verificado = true;
		else
			verificado = false;

		return ruta;
	}

	public List<Usuario> getListausuarios() {
		return listausuarios;
	}

	public void setListausuarios(List<Usuario> listausuarios) {
		this.listausuarios = listausuarios;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isVerificado() {
		return verificado;
	}

	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}

	public List<Rol> getListaRol() {
		return listaRol;
	}

	public void setListaRol(List<Rol> listaRol) {
		this.listaRol = listaRol;
	}
	
	
	
}
