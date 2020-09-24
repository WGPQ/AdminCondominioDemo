package condominio.model.manager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import condominio.model.dto.LoginDTO;
import condominio.model.entities.Usuario;

/**
 * Session Bean implementation class CondominioSeguridad
 */
@Stateless
@LocalBean
public class CondominioSeguridad {
@PersistenceContext
private EntityManager em;
    @EJB
    private ManagerDAO managerDAO;
    public CondominioSeguridad() {
        // TODO Auto-generated constructor stub
    }
    
    public LoginDTO accederalSistema(String email, String clave) throws Exception {
    	Usuario usuario= findUsuarioByEmail(email);
    	
    	if(usuario==null)
			throw new Exception("Error usuario no registrado");
		
		if(!usuario.getPasword().equals(clave))
			throw new Exception("Error clave incorrecta.");
		System.out.print("*******************************************************************************************************************************");
    	LoginDTO logindto=new LoginDTO();
    	int rol=(int)usuario.getRol().getIdRol();
    	logindto.setTipoUsuario(rol);
    	logindto.setUsuario(usuario.getEmail());
    	
    	if(rol==1)
			logindto.setRutaAcceso("/dashboard/index.xhtml");
		else if(rol==15)
			logindto.setRutaAcceso("/dashboardcondomino/index.xhtml");
    	
    	return logindto;
    }
    public Usuario findUsuarioByEmail(String email) {
    	try {
    		String sql = "select u from Usuario u where u.email = : dmpEmail";
    		Query q = em.createQuery(sql, Usuario.class).setParameter("dmpEmail", email);
    		return (Usuario) q.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return null;
		
		
	}
    
}
