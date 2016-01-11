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
	 * @param id 	 : Nom du syst�me embarqu�. Si il n'est pas sp�cifi� dans le fichier de configuration, il est �gale au hashcode de l'instance de la classe.
	 * @return       : Cr�e une chose Locale.
	 */
	public	 Thing createThing(String id);
	
	
	
}
