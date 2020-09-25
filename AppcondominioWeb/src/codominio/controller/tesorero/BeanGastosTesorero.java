package codominio.controller.tesorero;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import codominio.controller.JSFUtil;
import condominio.model.entities.TTransaccionDescripcion;
import condominio.model.entities.Transaccion;
import condominio.model.entities.Usuario;
import condominio.model.manager.tesorero.ManagerTransaccionTesorero;

@Named
@SessionScoped
public class BeanGastosTesorero implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Transaccion> listTransaccion2;
	private List<TTransaccionDescripcion> listTTransaccionDescripcion2;
	private Transaccion transaccion;
	private Transaccion transaccionSeleccionado;

	private boolean panelcolapsado;
	private String cedula;
	private String fecha;
	private int id_usuario;
	private int id_t_descripcion;
	private int aux_id_t_descripcion;
	
	@EJB
	private ManagerTransaccionTesorero mTransaccion;
	

	@PostConstruct
	public void inicializar() {
		listTransaccion2 = mTransaccion.findAllTransaccionesTesorero();
		listTTransaccionDescripcion2 = mTransaccion.findAllTTransaccionDescripcionTesorero();
		transaccion = new Transaccion();
		panelcolapsado = true;
	}
	
	public void actionListenerPanelColapsado() {
		panelcolapsado = !panelcolapsado;
	}
	
	public void actionListenerInsertarTransaccion() {
		try {
			System.out.println("---------------------");
			System.out.println("Fecha: "+ fecha);
			mTransaccion.insertarTransaccionTesorero(transaccion, cedula, aux_id_t_descripcion);
			listTransaccion2 = mTransaccion.findAllTransaccionesTesorero();
			listTTransaccionDescripcion2 = mTransaccion.findAllTTransaccionDescripcionTesorero();
			transaccion = new Transaccion();
			JSFUtil.crearMensajeInfo("Transacción ingresada");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	
	public void actionListenerEliminarTransaccion(int id) {
		mTransaccion.eliminarTransaccion(id);
		listTransaccion2 = mTransaccion.findAllTransaccionesTesorero();
		listTTransaccionDescripcion2 = mTransaccion.findAllTTransaccionDescripcionTesorero();
		JSFUtil.crearMensajeInfo("Transacción eliminado");
	}
	
	public void actionListenerSeleccionarTransaccion(Transaccion transaccion) {
		
		transaccionSeleccionado = transaccion;
		aux_id_t_descripcion = transaccionSeleccionado.getTTransaccionDescripcion().getIdTDescripcion();
	}
	
	public Usuario actionFindCedula_Usuario(int id) {
		return mTransaccion.findUsuarioById(id);
	}
	
	public void actionListenerActualizarTransaccion() {
		try {
			mTransaccion.actualizarTransaccion(transaccionSeleccionado, aux_id_t_descripcion);
			listTransaccion2 = mTransaccion.findAllTransaccionesTesorero();
			listTTransaccionDescripcion2 = mTransaccion.findAllTTransaccionDescripcionTesorero();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Transaccion> getListTransaccion2() {
		return listTransaccion2;
	}

	public void setListTransaccion2(List<Transaccion> listTransaccion) {
		this.listTransaccion2 = listTransaccion;
	}

	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}

	public Transaccion getTransaccionSeleccionado() {
		return transaccionSeleccionado;
	}

	public void setTransaccionSeleccionado(Transaccion transaccionSeleccionado) {
		this.transaccionSeleccionado = transaccionSeleccionado;
	}

	public boolean isPanelcolapsado() {
		return panelcolapsado;
	}

	public void setPanelcolapsado(boolean panelcolapsado) {
		this.panelcolapsado = panelcolapsado;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getId_t_descripcion() {
		return id_t_descripcion;
	}

	public void setId_t_descripcion(int id_t_descripcion) {
		this.id_t_descripcion = id_t_descripcion;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public List<TTransaccionDescripcion> getListTTransaccionDescripcion2() {
		return listTTransaccionDescripcion2;
	}

	public void setListTTransaccionDescripcion2(List<TTransaccionDescripcion> listTTransaccionDescripcion) {
		this.listTTransaccionDescripcion2 = listTTransaccionDescripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getAux_id_t_descripcion() {
		return aux_id_t_descripcion;
	}

	public void setAux_id_t_descripcion(int aux_id_t_descripcion) {
		this.aux_id_t_descripcion = aux_id_t_descripcion;
	}

}