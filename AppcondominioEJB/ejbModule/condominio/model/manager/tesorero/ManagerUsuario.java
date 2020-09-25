package condominio.model.manager.tesorero;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import condominio.model.entities.Rol;
import condominio.model.entities.Usuario;

/**
 * Session Bean implementation class ManagerUsuario
 */
@Stateless
@LocalBean
public class ManagerUsuario {

    @PersistenceContext
    private EntityManager em;
	
    public ManagerUsuario() {
        // TODO Auto-generated constructor stub
    }
    
    public Rol findRolById_rol(int id_rol) {
    	return em.find(Rol.class, id_rol);
    }
    
    public List<Usuario> findAllUsuarios(){
    	String query = "select u from Usuario u order by u.apellido";
    	Query q = em.createQuery(query, Usuario.class);
    	return q.getResultList();
    }
    
    public Usuario findUsuarioById_Usuario(int id_usuario) {
    	return em.find(Usuario.class, id_usuario);
    }
    
    public Usuario findUsuarioByCedula(String cedula) {
    	try {
    		String sql = "select u from Usuario u where u.cedula = : dmpCedula";
    		Query q = em.createQuery(sql, Usuario.class).setParameter("dmpCedula", cedula);
    		return (Usuario) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}		
	}
    
    public void insertarUsuario(Usuario usuario) throws Exception{

    	if(findUsuarioByCedula(usuario.getCedula()) != null)
    		throw new Exception("Ya existe la cerreeeeeeedula indicada.");
    	if(usuario.getCedula() == null || usuario.getCedula().length() == 0)
    		throw new Exception("Debe especificar una cedula.");
    	if(usuario.getCedula().length() > 10)
    		throw new Exception("Cedula Ecuatoriana contiene 10 digitos, verifique por favor.");
    	if(usuario.getTelefono().length() > 10)
    		throw new Exception("El teléfono solo debe contiene 10 digitos, verifique por favor.");
    	
    	Rol rol = findRolById_rol(15); // Rol del condómino 	 	
    	usuario.setRol(rol);
    	int size_pass = 10;
    	usuario.setPasword(getPassword(size_pass));
    	
    	System.out.println("--------------------------");
    	System.out.println("Cedula: " + usuario.getCedula());
    	System.out.println("Apellido: " + usuario.getApellido());
    	System.out.println("Nombre: " + usuario.getNombre());
    	System.out.println("Genero: " + usuario.getGenero());
    	System.out.println("Email: " + usuario.getEmail());
    	System.out.println("Telefono: " + usuario.getTelefono());
    	System.out.println("Estado: " + usuario.getEstado());
    	System.out.println("Password: " + usuario.getPasword());
    	System.out.println("nombreRol: " + rol.getNombre());
    	
    	em.persist(usuario);
    }
    
    public void actualizarEstudiante(Usuario usuario) throws Exception {
    	Usuario u = findUsuarioByCedula(usuario.getCedula());
    	if(u == null)
    		throw new Exception("No existe le estudiante con la cedula especificada.");
    	u.setApellido(usuario.getApellido());
    	u.setNombre(usuario.getNombre());
    	u.setGenero(usuario.getGenero());
    	u.setTelefono(usuario.getTelefono());
    	u.setEmail(usuario.getEmail());
    	u.setPasword(usuario.getPasword());
    	u.setEstado(usuario.getEstado());
    	
    	em.merge(u);
    }
    
    public String getPassword(int length) {
    	String key = "abcdefghijklmnopqrstuvwxyz0123456789";
		String pswd = "";
 
		for (int i = 0; i < length; i++) {
			pswd+=(key.charAt((int)(Math.random() * key.length())));
		}
 
		return pswd;
	}
    
    public void eliminarUsuario(String cedula) {
    	Usuario usuario = findUsuarioByCedula(cedula);
    	if(usuario != null) {
    		em.remove(usuario);
    	}
    }

}