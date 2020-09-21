package condominio.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class ManagerDAO
 */
@Stateless
@LocalBean
public class ManagerDAO {

    @PersistenceContext
    private EntityManager em;
    public ManagerDAO() {
        // TODO Auto-generated constructor stub
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Object findByatribute(Class clase, Object pID) throws Exception {
		if (pID == null)
			throw new Exception("Debe especificar el codigo para buscar el dato.");
		Object o;
		try {
			o = em.find(clase, pID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al buscar la informacion especificada (" + pID + ") : " + e.getMessage());
		}
		return o;
	}
    

}
