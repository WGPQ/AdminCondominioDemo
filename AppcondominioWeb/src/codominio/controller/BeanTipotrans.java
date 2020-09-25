package codominio.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import condominio.model.entities.TipoTransaccion;
import condominio.model.manager.ManagerTipotrans;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanTipotrans implements Serializable {

	private static final long serialVersionUID = 1L;

	// invocacion EJB
	@EJB
	private ManagerTipotrans managerTipotrans;
	

	// variables de sesion
	private List<TipoTransaccion> listTipoTrans;
	private TipoTransaccion tipotrans;
	private String nombrett;
	private boolean panelColapasdo;
	private TipoTransaccion tipotransSelecionado;

	// Metodos de inteacion entre model y vista
	@PostConstruct
	public void inicializar() {
		listTipoTrans = managerTipotrans.findAllTipotrans();
		tipotrans = new TipoTransaccion();
		panelColapasdo = true;

	}

	public void actionListenerInsertarTipotrans() {
		try {
			managerTipotrans.insertarTipoTrans(tipotrans, nombrett);

			// reseteo de variables en memoria
			nombrett = "";
			tipotrans = new TipoTransaccion();
			listTipoTrans = managerTipotrans.findAllTipotrans();
			JSFUtil.crearMensajeInfo("Tipo de transaccion ingresado.");

		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerColapsar() {
		panelColapasdo = !panelColapasdo;
	}

	public void actionListenerEliminarTipotrans(int id) {
		managerTipotrans.eliminarTipoTransaccion(id);
		listTipoTrans = managerTipotrans.findAllTipotrans();
		JSFUtil.crearMensajeInfo("Tipo de transaccion Eliminado");
	}

	public void actionListenerSelecionarTipotrans(TipoTransaccion tipotrans) {
		tipotransSelecionado = tipotrans;
	}

	public void actionListenerActualizarTipotrans() {
		try {
			managerTipotrans.actalizarTipotrans(tipotransSelecionado);
			listTipoTrans = managerTipotrans.findAllTipotrans();
			JSFUtil.crearMensajeInfo("Tipo de transaccion actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	// ACCESORES

	public List<TipoTransaccion> getListTipoTrans() {
		return listTipoTrans;
	}

	public void setListTipoTrans(List<TipoTransaccion> listTipoTrans) {
		this.listTipoTrans = listTipoTrans;
	}

	public String getNombrett() {
		return nombrett;
	}

	public void setNombrett(String nombrett) {
		this.nombrett = nombrett;
	}

	public boolean isPanelColapasdo() {
		return panelColapasdo;
	}

	public void setPanelColapasdo(boolean panelColapasdo) {
		this.panelColapasdo = panelColapasdo;
	}

	public TipoTransaccion getTipotransSelecionado() {
		return tipotransSelecionado;
	}

	public void setTipotransSelecionado(TipoTransaccion tipotransSelecionado) {
		this.tipotransSelecionado = tipotransSelecionado;
	}

}
