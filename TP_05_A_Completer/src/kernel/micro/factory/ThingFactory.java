package kernel.micro.factory;

import kernel.micro.facade.Thing;

/**
 * 
 * @author djamel bellebia
 *
 */

public  interface  ThingFactory 
{	
	/**
	 * @param id 	 : Nom du système embarqué. Si il n'est pas spécifié dans le fichier de configuration, il est égale au hashcode de l'instance de la classe.
	 * @return       : Crée une chose Locale.
	 */
	public	 Thing createThing(String id);
	
	
	
}
