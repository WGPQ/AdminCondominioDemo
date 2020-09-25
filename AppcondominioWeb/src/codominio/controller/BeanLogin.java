package codominio.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import condominio.model.dto.LoginDTO;
import condominio.model.manager.CondominioSeguridad;

@Named
@SessionScoped
public class BeanLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private CondominioSeguridad condominioseguridad;
	private String email;
	private String clave;
	private LoginDTO logindto;

	public BeanLogin() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		logindto = new LoginDTO();
	}

	public String accederSistema() {
		try {
			logindto = condominioseguridad.accederalSistema(email, clave);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioactualmenteautorizadoparanaegar", logindto);
			
			System.out.println("*************************"+logindto.getRutaAcceso()+"********************************");
			return logindto.getRutaAcceso() + "?faces-redirect=true";
			
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
		return "";
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

}
