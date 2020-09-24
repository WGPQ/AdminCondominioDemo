package codominio.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import condominio.model.entities.Rol;
import condominio.model.entities.TTransaccionDescripcion;
import condominio.model.entities.TipoTransaccion;
import condominio.model.manager.ManagerRol;
import condominio.model.manager.ManagerTipotrans;
import condominio.model.manager.ManagerTransaccion;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanTransaccion implements Serializable {

	private static final long serialVersionUID = 1L;

	// Invocacion EJB
	@EJB
	private ManagerTransaccion managerTransaccion;
	@EJB
	private ManagerTipotrans managerTipotrans;
	@EJB
	private ManagerRol managerRol;

	// Variables de session
	private List<TTransaccionDescripcion> listaTransaccionDescripcion;
	private List<TTransaccionDescripcion> listaTransaccionDescTes;
	private List<TTransaccionDescripcion> listaTransaccionDescCon;
	private List<TipoTransaccion> listaTipoTransaccion;
	private List<Rol> listaRoles;

	private TTransaccionDescripcion transaccionDescripcion;
	private TipoTransaccion tipoTransaccion;
	private Rol rol;
	
	private TTransaccionDescripcion transaccionDescSelecionado;

	// variables de captura desde la vista
	private String nombreTransacionDescripcion;
	private int idTipoTransaccion; // aqui guardare el que seleccione en la vista
	private int idRol;// aqui guardare el que seleccione en la vista

	private boolean panelColapsado;

	public BeanTransaccion() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		listaTransaccionDescripcion = managerTransaccion.findAllTransaccionDescripcion();
		listaTipoTransaccion = managerTipotrans.findAllTipotrans();
		listaRoles = managerRol.findAllRoles();
		// tipoTransaccion = new TipoTransaccion();
		transaccionDescripcion = new TTransaccionDescripcion();
		panelColapsado = true;

	}

	public void actionListenerColapsar() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertarTransacionDescripcion() {
		try {
			managerTransaccion.insertarTransacionDescripcion(transaccionDescripcion, nombreTransacionDescripcion,
					idTipoTransaccion, idRol);
			// reseteo de variables en memoria
			nombreTransacionDescripcion = "";
			idTipoTransaccion = 0;
			idRol = 0;
			listaTransaccionDescripcion = managerTransaccion.findAllTransaccionDescripcion();
			JSFUtil.crearMensajeInfo("Desceipcion ingresada.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEliminarTransaccionDesc(int id) {
		managerTransaccion.eliminarTransaccionDesc(id);
		listaTransaccionDescripcion = managerTransaccion.findAllTransaccionDescripcion();
		JSFUtil.crearMensajeInfo("Descripion de transaccion Eliminado");
	}

	public void actionListenerSelecionarTransaccionDesc(TTransaccionDescripcion tdesc) {
		transaccionDescSelecionado = tdesc;
	}

	public void actionListenerActualizarTransacionDesc() {
		try {
			managerTransaccion.actalizarTransacionDesc(transaccionDescSelecionado, nombreTransacionDescripcion,
					idTipoTransaccion, idRol);
			// reseteo de variables en memoria
			nombreTransacionDescripcion = "";
			idTipoTransaccion = 0;
			idRol = 0;
			listaTransaccionDescripcion = managerTransaccion.findAllTransaccionDescripcion();
			JSFUtil.crearMensajeInfo("Descripion de transaccion actualizada");

		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	// ACCESORES
	public List<TipoTransaccion> getListaTipoTransaccion() {
		return listaTipoTransaccion;
	}

	public void setListaTipoTransaccion(List<TipoTransaccion> listaTipoTransaccion) {
		this.listaTipoTransaccion = listaTipoTransaccion;
	}

	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public String getNombreTransacionDescripcion() {
		return nombreTransacionDescripcion;
	}

	public void setNombreTransacionDescripcion(String nombreTransacionDescripcion) {
		this.nombreTransacionDescripcion = nombreTransacionDescripcion;
	}

	public int getIdTipoTransaccion() {
		return idTipoTransaccion;
	}

	public void setIdTipoTransaccion(int idTipoTransaccion) {
		this.idTipoTransaccion = idTipoTransaccion;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public List<TTransaccionDescripcion> getListaTransaccionDescripcion() {
		return listaTransaccionDescripcion;
	}

	public void setListaTransaccionDescripcion(List<TTransaccionDescripcion> listaTransaccionDescripcion) {
		this.listaTransaccionDescripcion = listaTransaccionDescripcion;
	}

	public List<TTransaccionDescripcion> getListaTransaccionDescTes() {
		return listaTransaccionDescTes;
	}

	public void setListaTransaccionDescTes(List<TTransaccionDescripcion> listaTransaccionDescTes) {
		this.listaTransaccionDescTes = listaTransaccionDescTes;
	}

	public List<TTransaccionDescripcion> getListaTransaccionDescCon() {
		return listaTransaccionDescCon;
	}

	public void setListaTransaccionDescCon(List<TTransaccionDescripcion> listaTransaccionDescCon) {
		this.listaTransaccionDescCon = listaTransaccionDescCon;
	}

	public TTransaccionDescripcion getTransaccionDescSelecionado() {
		return transaccionDescSelecionado;
	}

	public void setTransaccionDescSelecionado(TTransaccionDescripcion transaccionDescSelecionado) {
		this.transaccionDescSelecionado = transaccionDescSelecionado;
	}

}
