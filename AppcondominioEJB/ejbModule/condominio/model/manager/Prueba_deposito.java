package condominio.model.manager;

import java.math.BigDecimal;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.convert.BigDecimalConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import condominio.model.entities.Cuenta;
import condominio.model.entities.Rol;
import condominio.model.entities.TTransaccionDescripcion;

/**
 * Session Bean implementation class Prueba_deposito
 */
@Stateless
@LocalBean
public class Prueba_deposito {

	@PersistenceContext
	private EntityManager em;

	public Prueba_deposito() {
		// TODO Auto-generated constructor stub
	}

	public int tipoT(int idtransa) {
		TTransaccionDescripcion tipotdes = (TTransaccionDescripcion) em.find(TTransaccionDescripcion.class,idtransa);
		return tipotdes.getIdTDescripcion();
	}

	public void actualizarTransaccion(int idTransaccion, int monto) {
		int nsaldo;
		BigDecimal montoactual;
		Cuenta c= em.find(Cuenta.class, 1);
           if(tipoT(idTransaccion)==2) {
        	   nsaldo=Integer.valueOf(c.getSaldo().intValue())+monto;
        	   montoactual=new BigDecimal(nsaldo);
        	  c.setSaldo(montoactual);    		
           }
           
           if(tipoT(idTransaccion)==1)  {
        	   nsaldo=Integer.valueOf(c.getSaldo().intValue())-monto;
        	   montoactual=new BigDecimal(nsaldo);
        	  c.setSaldo(montoactual);  
           }
           em.merge(c);
	}
	/*
	 * //primero obtenemos los datos de la transaccion original: Transaccion
	 * t=(Transaccion)em.find(Transaccion.class, idTransaccion); //actualizamos los
	 * datos: t.setMonto(monto); t.setFecha(fecha);
	 * t.setTipoTransaccion(tipoTransaccion); em.getTransaction().begin();
	 * em.merge(t); e
	 */
}
