package condominio.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rol database table.
 * 
 */
@Entity
@Table(name="rol")
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_rol", unique=true, nullable=false)
	private Integer idRol;

	@Column(nullable=false, length=100)
	private String descripcion;

	@Column(nullable=false, length=50)
	private String nombre;

	//bi-directional many-to-one association to TTransaccionDescripcion
	@OneToMany(mappedBy="rol")
	private List<TTransaccionDescripcion> TTransaccionDescripcions;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="rol")
	private List<Usuario> usuarios;

	public Rol() {
	}

	public Integer getIdRol() {
		return this.idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		TTransaccionDescripcion.setRol(this);

		return TTransaccionDescripcion;
	}

	public TTransaccionDescripcion removeTTransaccionDescripcion(TTransaccionDescripcion TTransaccionDescripcion) {
		getTTransaccionDescripcions().remove(TTransaccionDescripcion);
		TTransaccionDescripcion.setRol(null);

		return TTransaccionDescripcion;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setRol(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setRol(null);

		return usuario;
	}

}