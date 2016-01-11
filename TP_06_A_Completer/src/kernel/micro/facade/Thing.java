package kernel.micro.facade; 

import java.util.Stack;


/**
 * 
 * @author djamel bellebia
 * Utilisation du pattern Wrapper Facade.
 * Chaque système embarqué devient une chose    
 */
public interface Thing 
{	
	public  String 		getThingId();
	public  void 		setThingId(String id);
	

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
	
	/**
	 * On estime qu'un système doit avoir un nombre d'addresse limité.
	 * Aussi la taille de la table est initialisé par défaut.
	 * @return
	 */
	public Stack 	getThingAddresses();
	
	public abstract String getAddressByProtocol(String remoteUrl);
	
}
