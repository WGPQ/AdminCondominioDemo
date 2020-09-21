package condominio.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the transaccion database table.
 * 
 */
@Entity
@Table(name="transaccion")
@NamedQuery(name="Transaccion.findAll", query="SELECT t FROM Transaccion t")
public class Transaccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_transaccion", unique=true, nullable=false)
	private Integer idTransaccion;

	@Column(length=100)
	private String descripcion;

	private Timestamp fecha;

	@Column(nullable=false, precision=10, scale=1)
	private BigDecimal monto;

	@Column(nullable=false)
	private Boolean pendiente;

	//bi-directional many-to-one association to Cuenta
	@ManyToOne
	@JoinColumn(name="id_cuenta", nullable=false)
	private Cuenta cuenta;

	//bi-directional many-to-one association to TTransaccionDescripcion
	@ManyToOne
	@JoinColumn(name="id_t_descripcion", nullable=false)
	private TTransaccionDescripcion TTransaccionDescripcion;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario", nullable=false)
	private Usuario usuario;

	public Transaccion() {
	}

	public Integer getIdTransaccion() {
		return this.idTransaccion;
	}

	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getMonto() {
		return this.monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Boolean getPendiente() {
		return this.pendiente;
	}

	public void setPendiente(Boolean pendiente) {
		this.pendiente = pendiente;
	}

	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public TTransaccionDescripcion getTTransaccionDescripcion() {
		return this.TTransaccionDescripcion;
	}

	public void setTTransaccionDescripcion(TTransaccionDescripcion TTransaccionDescripcion) {
		this.TTransaccionDescripcion = TTransaccionDescripcion;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}