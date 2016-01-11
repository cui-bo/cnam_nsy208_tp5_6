package kernel.micro.facade; 

import java.util.Hashtable;
import java.util.Stack;

import kernel.micro.composite.Address;

/**
 * 
 * @author djamel bellebia
 * Utilisation du pattern Wrapper Facade.
 * Chaque système embarqué devient une chose    
 */
public interface Thing 
{	
	public  String 		getThingId();
	public  void		setThingId(String thingId);
	

	/**
	 * Revoie la façade du système embarqué telle que définie dans le fichier de configuration.
	 * @return
	 */
	public String 		getFacade();
	
	/**
	 * Revoie la façade du système embarqué telle que définie dans le fichier de configuration.
	 * @return
	 */
	public void 		setFacade(String facade);
}
