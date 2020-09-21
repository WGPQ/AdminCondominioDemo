package condominio.model.manager;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import condominio.model.entities.Usuario;
/**
 * Session Bean implementation class ManagerUser
 */
@Stateless
@LocalBean
public class ManagerUser {

	@PersistenceContext
	private EntityManager em;

	public ManagerUser() {
		// TODO Auto-generated constructor stub
	}

	public List<Usuario> findAllUsers() {
		String consulta = "select r from Usuario r";
		Query q = em.createQuery(consulta, Usuario.class);
		return q.getResultList();
	}

	public void insertarRol(Usuario usuario, String cedula, String nombre, String apellido, String genero,
			String telefono, String email, String password, boolean estado) throws Exception {
		usuario = new Usuario();

		if (!(nombre.isEmpty() && cedula.isEmpty() && apellido.isEmpty() && telefono.isEmpty() && email.isEmpty()))
			throw new Exception("Datos vacios");
		usuario.setApellido(apellido);
		usuario.setNombre(nombre);
		usuario.setCedula(cedula);
		usuario.setEmail(email);
		usuario.setTelefono(telefono);
		usuario.setGenero(genero);
		usuario.setPasword(password);
		usuario.setEstado(estado);
		em.persist(usuario);
	}
	
	public Usuario findUsuarioById(int id_usuario) {
		return em.find(Usuario.class, id_usuario);
	}
	
	
	
	public String verificarUsuario(String email, String clave) {
		Usuario u = findUsuarioByEmail(email);
		if (u == null)
			return "";
		int id_rol = (int) u.getRol().getIdRol();
		System.out.println("-------------------");
		System.out.println("rol: " + id_rol + "  nombre: " + u.getNombre() + " email: "+email+"  rol: " + u.getRol());
		//if (u.getPasword().equals(clave) && (id_rol == rol && rol == 1)) // TESORERO
		if (u.getPasword().equals(clave) && (id_rol == 1)) // TESORERO
			return "/dashboard/index";
		else if (u.getPasword().equals(clave) && (id_rol == 2)) // CONDOMINO
			return "/menu2";
		else
			return "";
	}
	
	public Usuario findUsuarioByEmail(String email) {
		String sql = "select u from Usuario u where u.email = : dmpEmail";
		Query q = em.createQuery(sql, Usuario.class).setParameter("dmpEmail", email);
		return (Usuario) q.getSingleResult();
		
	}
	

}
