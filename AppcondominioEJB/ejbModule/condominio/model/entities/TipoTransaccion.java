package condominio.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_transaccion database table.
 * 
 */
@Entity
@Table(name="tipo_transaccion")
@NamedQuery(name="TipoTransaccion.findAll", query="SELECT t FROM TipoTransaccion t")
public class TipoTransaccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_t_transaccion", unique=true, nullable=false)
	private Integer idTTransaccion;

	@Column(nullable=false, length=60)
	private String nombre;

	//bi-directional many-to-one association to TTransaccionDescripcion
	@OneToMany(mappedBy="tipoTransaccion")
	private List<TTransaccionDescripcion> TTransaccionDescripcions;

	public TipoTransaccion() {
	}

	public Integer getIdTTransaccion() {
		return this.idTTransaccion;
	}

	public void setIdTTransaccion(Integer idTTransaccion) {
		this.idTTransaccion = idTTransaccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<TTransaccionDescripcion> getTTransaccionDescripcions() {
		return this.TTransaccionDescripcions;
	}

	public void setTTransaccionDescripcions(List<TTransaccionDescripcion> TTransaccionDescripcions) {
		this.TTransaccionDescripcions = TTransaccionDescripcions;
	}

	public TTransaccionDescripcion addTTransaccionDescripcion(TTransaccionDescripcion TTransaccionDescripcion) {
		getTTransaccionDescripcions().add(TTransaccionDescripcion);
		TTransaccionDescripcion.setTipoTransaccion(this);

		return TTransaccionDescripcion;
	}

	public TTransaccionDescripcion removeTTransaccionDescripcion(TTransaccionDescripcion TTransaccionDescripcion) {
		getTTransaccionDescripcions().remove(TTransaccionDescripcion);
		TTransaccionDescripcion.setTipoTransaccion(null);

		return TTransaccionDescripcion;
	}

}