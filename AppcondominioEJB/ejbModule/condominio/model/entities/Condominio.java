package condominio.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the condominio database table.
 * 
 */
@Entity
@Table(name="condominio")
@NamedQuery(name="Condominio.findAll", query="SELECT c FROM Condominio c")
public class Condominio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_condominio", unique=true, nullable=false)
	private Integer idCondominio;

	@Column(nullable=false, length=50)
	private String nombre;

	@Column(length=15)
	private String telefono;

	//bi-directional many-to-one association to Cuenta
	@OneToMany(mappedBy="condominio")
	private List<Cuenta> cuentas;

	public Condominio() {
	}

	public Integer getIdCondominio() {
		return this.idCondominio;
	}

	public void setIdCondominio(Integer idCondominio) {
		this.idCondominio = idCondominio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Cuenta> getCuentas() {
		return this.cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Cuenta addCuenta(Cuenta cuenta) {
		getCuentas().add(cuenta);
		cuenta.setCondominio(this);

		return cuenta;
	}

	public Cuenta removeCuenta(Cuenta cuenta) {
		getCuentas().remove(cuenta);
		cuenta.setCondominio(null);

		return cuenta;
	}

}