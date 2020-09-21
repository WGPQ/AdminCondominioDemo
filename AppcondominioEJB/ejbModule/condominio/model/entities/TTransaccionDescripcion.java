package condominio.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_transaccion_descripcion database table.
 * 
 */
@Entity
@Table(name="t_transaccion_descripcion")
@NamedQuery(name="TTransaccionDescripcion.findAll", query="SELECT t FROM TTransaccionDescripcion t")
public class TTransaccionDescripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_t_descripcion", unique=true, nullable=false)
	private Integer idTDescripcion;

	@Column(length=60)
	private String nombre;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="id_rol", nullable=false)
	private Rol rol;

	//bi-directional many-to-one association to TipoTransaccion
	@ManyToOne
	@JoinColumn(name="id_t_transaccion", nullable=false)
	private TipoTransaccion tipoTransaccion;

	//bi-directional many-to-one association to Transaccion
	@OneToMany(mappedBy="TTransaccionDescripcion")
	private List<Transaccion> transaccions;

	public TTransaccionDescripcion() {
	}

	public Integer getIdTDescripcion() {
		return this.idTDescripcion;
	}

	public void setIdTDescripcion(Integer idTDescripcion) {
		this.idTDescripcion = idTDescripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public TipoTransaccion getTipoTransaccion() {
		return this.tipoTransaccion;
	}

	public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public List<Transaccion> getTransaccions() {
		return this.transaccions;
	}

	public void setTransaccions(List<Transaccion> transaccions) {
		this.transaccions = transaccions;
	}

	public Transaccion addTransaccion(Transaccion transaccion) {
		getTransaccions().add(transaccion);
		transaccion.setTTransaccionDescripcion(this);

		return transaccion;
	}

	public Transaccion removeTransaccion(Transaccion transaccion) {
		getTransaccions().remove(transaccion);
		transaccion.setTTransaccionDescripcion(null);

		return transaccion;
	}

}