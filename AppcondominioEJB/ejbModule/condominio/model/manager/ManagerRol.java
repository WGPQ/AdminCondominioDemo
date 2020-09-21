package condominio.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import condominio.model.entities.Rol;

/**
 * Session Bean implementation class ManagerRol
 */
@Stateless
@LocalBean
public class ManagerRol {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerRol() {
		// TODO Auto-generated constructor stub
	}

	// Metodos de extracion de datos

	public List<Rol> findAllRoles() {
		String consulta = "select r from Rol r";
		Query q = em.createQuery(consulta, Rol.class);
		return q.getResultList();
	}

	public void insertarRol(Rol rol, String nombre, String descripcion) throws Exception {
		rol = new Rol();
		System.out.println("------- "+ nombre + "  --  " + descripcion);
		if (nombre.equals("") || descripcion.equals(""))
			throw new Exception("Datos vacios");
		rol.setNombre(nombre);
		rol.setDescripcion(descripcion);
		em.persist(rol);
	}

}
