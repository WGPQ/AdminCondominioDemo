package condominio.model.manager.tesorero;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import condominio.model.entities.Cuenta;
import condominio.model.entities.TTransaccionDescripcion;
import condominio.model.entities.Transaccion;
import condominio.model.entities.Usuario;

/**
 * Session Bean implementation class ManagerTransaccionTesorero
 */
@Stateless
@LocalBean
public class ManagerTransaccionTesorero {

    @PersistenceContext
    private EntityManager em;
    public ManagerTransaccionTesorero() {
        // TODO Auto-generated constructor stub
    }
    
    public Transaccion findTransaccionById(int id) {
    	return em.find(Transaccion.class, id);
    }
    
    public Cuenta findCuentaById(int id_cuenta) {
    	return em.find(Cuenta.class, id_cuenta);
    }
    
    public Usuario findUsuarioById(int id_usuario) {
    	return em.find(Usuario.class, id_usuario);
    }
    
    public TTransaccionDescripcion findT_DescripcionById(int id_t_descripcion) {
    	return em.find(TTransaccionDescripcion.class, id_t_descripcion);
    }
   
    public List<Transaccion> findAllTransacciones(){
    	String query = "select u from Transaccion u where u.TTransaccionDescripcion.tipoTransaccion = 2";
    	Query q = em.createQuery(query, Transaccion.class);
    	return q.getResultList();
    }
    
    public List<Transaccion> findAllTransaccionesTesorero(){
    	String query = "select u from Transaccion u where u.usuario.rol.idRol = 1";
    	Query q = em.createQuery(query, Transaccion.class);
    	return q.getResultList();
    }
    
    public List<TTransaccionDescripcion> findAllTTransaccionDescripcion(){
    	String query = "select u from TTransaccionDescripcion u where u.tipoTransaccion = 2";
    	Query q = em.createQuery(query, TTransaccionDescripcion.class);
    	return q.getResultList();
    }
    
    public List<TTransaccionDescripcion> findAllTTransaccionDescripcionTesorero(){
    	String query = "select u from TTransaccionDescripcion u where u.tipoTransaccion = 1";
    	Query q = em.createQuery(query, TTransaccionDescripcion.class);
    	return q.getResultList();
    }
    
    public Transaccion findUsuarioById_Usuario(int id_transaccion) {
    	return em.find(Transaccion.class, id_transaccion);
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
    
    public void insertarTransaccion(Transaccion transaccion, 
    		String cedula, int id_t_descripcion ) throws Exception{
    	
    	Usuario u = findUsuarioByCedula(cedula);
    	if(transaccion.getFecha() == null)
    		throw new Exception("Fecha no especificada");
    	if(transaccion.getMonto() == null)
    		throw new Exception("Monto no especificado");
    	if(u == null)
    		throw new Exception("Cédula no encontrada");
    	System.out.println("--------------------");
    	
    	transaccion.setCuenta(findCuentaById(1)); // id_cuenta = 1
    	transaccion.setUsuario(u);
    	transaccion.setTTransaccionDescripcion(findT_DescripcionById(id_t_descripcion));
    	
    	/*System.out.println("--------------------");
    	//System.out.println("Fecha: "+ fecha);
    	System.out.println("cedula: "+ transaccion.getUsuario().getCedula());
    	System.out.println("cuenta: "+ transaccion.getCuenta());
    	System.out.println("monto " + transaccion.getMonto());
    	System.out.println("Fecha: "+ transaccion.getFecha());
    	System.out.println("Pendiente: "+ transaccion.getPendiente());
    	System.out.println("id_t_1: "+ transaccion.getTTransaccionDescripcion().getNombre());
    	System.out.println("id_t_2: "+ id_t_descripcion);*/
    	
    	em.persist(transaccion);
    }
    
    public void insertarTransaccionTesorero(Transaccion transaccion, 
    		String cedula, int id_t_descripcion ) throws Exception{
    	
    	
    	Usuario u = findUsuarioByCedula(cedula);
    	if(transaccion.getFecha() == null)
    		throw new Exception("Fecha no especificada");
    	if(transaccion.getMonto() == null)
    		throw new Exception("Monto no especificado");
    	if(u == null)
    		throw new Exception("Cédula no encontrada");
    	System.out.println("--------------------");
    	
    	transaccion.setCuenta(findCuentaById(1)); // id_cuenta = 1
    	transaccion.setUsuario(u);
    	transaccion.setTTransaccionDescripcion(findT_DescripcionById(id_t_descripcion));
    	transaccion.setPendiente(false);    	System.out.println("--------------------");
    	//System.out.println("Fecha: "+ fecha);
    	/*System.out.println("cedula: "+ transaccion.getUsuario().getCedula());
    	System.out.println("cuenta: "+ transaccion.getCuenta());
    	System.out.println("monto " + transaccion.getMonto());
    	System.out.println("Fecha: "+ transaccion.getFecha());
    	System.out.println("Pendiente: "+ transaccion.getPendiente());*/
    	//System.out.println("id_t_1: "+ transaccion.getTTransaccionDescripcion().getNombre());
    	//System.out.println("id_t_2: "+ id_t_descripcion);
    	
    	em.persist(transaccion);
    }
    
    public void actualizarTransaccion(Transaccion transaccion, int id_t_descripcion ) throws Exception {
    	Transaccion t = findTransaccionById(transaccion.getIdTransaccion());
    	if(t == null)
    		throw new Exception("No existe la transaccion");
    	
    	t.setFecha(transaccion.getFecha());
    	t.setMonto(transaccion.getMonto());
    	t.setDescripcion(transaccion.getDescripcion());
    	t.setPendiente(transaccion.getPendiente());
    	//t.setTTransaccionDescripcion(transaccion.getTTransaccionDescripcion());
    	
    	t.setTTransaccionDescripcion(findT_DescripcionById(id_t_descripcion));
    	
    	em.merge(t);
    }
    
    public void eliminarTransaccion(int id) {
    	Transaccion transaccion = findTransaccionById(id);
    	if(transaccion != null) {
    		em.remove(transaccion);
    	}
    }

}