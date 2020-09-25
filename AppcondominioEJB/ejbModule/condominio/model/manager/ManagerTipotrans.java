package condominio.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import condominio.model.entities.TipoTransaccion;
import condominio.model.entities.Transaccion;

/**
 * Session Bean implementation class ManagerTipotrans
 */
@Stateless
@LocalBean
public class ManagerTipotrans {

    @PersistenceContext
    private EntityManager em;
    public ManagerTipotrans() {
        // TODO Auto-generated constructor stub
    }

	// Metodos de extracion de datos

	public List<TipoTransaccion> findAllTipotrans(){
		String consulta = "select u from TipoTransaccion u";
		Query q = em.createQuery(consulta, TipoTransaccion.class);
		return q.getResultList();
		
	}
	
	public void insertarTipoTrans(TipoTransaccion tipotrans, String nombre) throws Exception{
		tipotrans =  new TipoTransaccion();
		if(nombre.equals(""))
			throw new Exception("Datos vacios");
		tipotrans.setNombre(nombre);
		em.persist(tipotrans);
			
	}
	
	public TipoTransaccion findTipotransById(int id) {
		return em.find(TipoTransaccion.class, id);
	}
	
	public void eliminarTipoTransaccion(int id) {
		TipoTransaccion tipotrans = findTipotransById(id);
		if(tipotrans != null)
			em.remove(tipotrans);
	}
	
	public void actalizarTipotrans(TipoTransaccion tipotrans) throws Exception {
		TipoTransaccion t = findTipotransById(tipotrans.getIdTTransaccion());
		if(t == null)
			throw new Exception("No existe tipo de transaccion que especifica");
		t.setNombre(tipotrans.getNombre());
		em.merge(t);
	}
	
	
	

}