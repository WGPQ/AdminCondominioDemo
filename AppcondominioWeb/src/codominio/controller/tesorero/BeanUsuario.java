package codominio.controller.tesorero;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import codominio.controller.JSFUtil;
import condominio.model.entities.Rol;
import condominio.model.entities.Usuario;
import condominio.model.manager.ManagerRol;
import condominio.model.manager.ManagerUser;
import condominio.model.manager.tesorero.ManagerUsuario;

@Named
@SessionScoped
public class BeanUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Usuario> listUsuario;
	private Usuario usuario;
	private Usuario usuarioSeleccionado;
	private boolean panelcolapsado;
	
	@EJB
	private ManagerUsuario mUsuario;
	
	public BeanUsuario() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void inicializar() {
		listUsuario = mUsuario.findAllUsuarios();
		usuario = new Usuario();
		panelcolapsado = true;
	}
	
	public void actionListenerPanelColapsado() {
		panelcolapsado = !panelcolapsado;
	}
	
	public void actionListenerInsertarUsuario() {
		try {
			mUsuario.insertarUsuario(usuario);
			listUsuario = mUsuario.findAllUsuarios();
			usuario = new Usuario();
			JSFUtil.crearMensajeInfo("Datos del Condómino ingresado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	
	public void actionListenerEliminarUsuario(String cedula) {
		mUsuario.eliminarUsuario(cedula);
		listUsuario = mUsuario.findAllUsuarios();
		JSFUtil.crearMensajeInfo("Condómino eliminado");
	}
	
	public void actionListenerSeleccionarUsuario(Usuario usuario) {
		usuarioSeleccionado = usuario;
	}
	
	public void actionListenerActualizarUsuario() {
		try {
			mUsuario.actualizarEstudiante(usuarioSeleccionado);
			listUsuario = mUsuario.findAllUsuarios();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListUsuario() {
		return listUsuario;
	}

	public void setListUsuario(List<Usuario> listUsuario) {
		this.listUsuario = listUsuario;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public boolean isPanelcolapsado() {
		return panelcolapsado;
	}

	public void setPanelcolapsado(boolean panelcolapsado) {
		this.panelcolapsado = panelcolapsado;
	}
	
	

}