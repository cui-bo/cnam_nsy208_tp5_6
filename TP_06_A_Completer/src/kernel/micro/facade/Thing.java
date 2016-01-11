package kernel.micro.facade; 

import java.util.Stack;


/**
 * 
 * @author djamel bellebia
 * Utilisation du pattern Wrapper Facade.
 * Chaque syst�me embarqu� devient une chose    
 */
public interface Thing 
{	
	public  String 		getThingId();
	public  void 		setThingId(String id);
	

	/**
	 * Revoie la fa�ade du syst�me embarqu� telle que d�finie dans le fichier de configuration.
	 * @return
	 */
	public String 		getFacade();
	
	/**
	 * Revoie la fa�ade du syst�me embarqu� telle que d�finie dans le fichier de configuration.
	 * @return
	 */
	public void 		setFacade(String facade);
	
	/**
	 * On estime qu'un syst�me doit avoir un nombre d'addresse limit�.
	 * Aussi la taille de la table est initialis� par d�faut.
	 * @return
	 */
	public Stack 	getThingAddresses();
	
	public abstract String getAddressByProtocol(String remoteUrl);
	
}
