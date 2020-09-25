package codominio.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import condominio.model.dto.LoginDTO;

@Named
@SessionScoped
public class BeanAutorizacion implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private LoginDTO logindto;

	public BeanAutorizacion() {
		// TODO Auto-generated constructor stub
	}

	public void verificarsession() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			logindto = (LoginDTO) context.getExternalContext().getSessionMap().get("usuarioactualmenteautorizadoparanaegar");
			if (logindto == null) {
				context.getExternalContext().redirect("./../login.xhtml");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}	
	
	public void verificar_rol() {
		if(logindto.getTipoUsuario()==1) {
			
		}
	}
	public void mensaje() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	public void cerrarsession() {

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			logindto = (LoginDTO) context.getExternalContext().getSessionMap();
			if(logindto !=null) {
				 FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			}
		} catch (Exception e) {
			JSFUtil.crearMensajeWarning("Atencion");
		}
		// FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

	}

}
