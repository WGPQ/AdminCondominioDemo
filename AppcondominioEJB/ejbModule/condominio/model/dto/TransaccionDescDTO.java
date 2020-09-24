package condominio.model.dto;

import javax.persistence.Column;

public class TransaccionDescDTO {
	
	// tabla transaccion descripcion
	private int idTransDescripcion;
	private String nombreTransaccionDesc;
	// tabla tipo de tansaccion
	private int idTipoTransaccion;
	private String nombreTipoTrans;
	// tabla de rol
	private int idRol;
	private String nombreRol;
		
	
	public TransaccionDescDTO(int idTransDescripcion, String nombreTransaccionDesc, int idTipoTransaccion,
			String nombreTipoTrans, int idRol, String nombreRol) {
		super();
		this.idTransDescripcion = idTransDescripcion;
		this.nombreTransaccionDesc = nombreTransaccionDesc;
		this.idTipoTransaccion = idTipoTransaccion;
		this.nombreTipoTrans = nombreTipoTrans;
		this.idRol = idRol;
		this.nombreRol = nombreRol;
	}
	
	public int getIdTransDescripcion() {
		return idTransDescripcion;
	}
	public void setIdTransDescripcion(int idTransDescripcion) {
		this.idTransDescripcion = idTransDescripcion;
	}
	public String getNombreTransaccionDesc() {
		return nombreTransaccionDesc;
	}
	public void setNombreTransaccionDesc(String nombreTransaccionDesc) {
		this.nombreTransaccionDesc = nombreTransaccionDesc;
	}
	public int getIdTipoTransaccion() {
		return idTipoTransaccion;
	}
	public void setIdTipoTransaccion(int idTipoTransaccion) {
		this.idTipoTransaccion = idTipoTransaccion;
	}
	public String getNombreTipoTrans() {
		return nombreTipoTrans;
	}
	public void setNombreTipoTrans(String nombreTipoTrans) {
		this.nombreTipoTrans = nombreTipoTrans;
	}
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}



}
