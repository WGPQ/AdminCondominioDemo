package condominio.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import condominio.model.entities.Rol;
import condominio.model.entities.TTransaccionDescripcion;
import condominio.model.entities.TipoTransaccion;

/**
 * Session Bean implementation class ManagerTransaccion
 */
@Stateless
@LocalBean
public class ManagerTransaccion {

    @PersistenceContext
    private EntityManager em;
    public ManagerTransaccion() {
        // TODO Auto-generated constructor stub
    }

	// Metodos de extracion de datos

	public List<TTransaccionDescripcion> findAllTransaccionDescripcion() {
		String consulta = "select h from TTransaccionDescripcion h";
		Query q = em.createQuery(consulta, TTransaccionDescripcion.class);
		return q.getResultList();

	}
	
	
	public TipoTransaccion findTipotransById(int id) {
		return em.find(TipoTransaccion.class, id);
	}
	
	public Rol findRolById(int id) {
		return em.find(Rol.class, id);
	}
	

	public void insertarTransacionDescripcion(TTransaccionDescripcion transacionDescripcion, String descripcion,
			int idTipoTransaccion, int idRol) throws Exception {
		transacionDescripcion = new TTransaccionDescripcion();

		if (descripcion.equals(""))
			throw new Exception("Datos vacios");
		
		TipoTransaccion ttrans = findTipotransById(idTipoTransaccion);
		Rol rl = findRolById(idRol);
		
		transacionDescripcion.setNombre(descripcion);
		transacionDescripcion.setTipoTransaccion(ttrans);
		transacionDescripcion.setRol(rl);
		em.persist(transacionDescripcion);

	}
	
	public TTransaccionDescripcion findTransaccionDescById(int id) {
		return em.find(TTransaccionDescripcion.class, id);
	}
	
	public void eliminarTransaccionDesc(int id) {
		TTransaccionDescripcion trdes = findTransaccionDescById(id);
		if(trdes != null)
			em.remove(trdes);
	}
	public void actalizarTransacionDesc(TTransaccionDescripcion transacionDesc, String descripcion,
			int idTipoTransaccion, int idRol) throws Exception {
		
		TTransaccionDescripcion tdesc = findTransaccionDescById(transacionDesc.getIdTDescripcion());
		if(tdesc == null)
			throw new Exception("No existe la descripcion que especifica");
		
		TipoTransaccion ttrans = findTipotransById(idTipoTransaccion);
		Rol rl = findRolById(idRol);
		
		tdesc.setNombre(descripcion);
		tdesc.setTipoTransaccion(ttrans);;
		tdesc.setTipoTransaccion(ttrans);
		em.merge(tdesc);
	}
	
	

}