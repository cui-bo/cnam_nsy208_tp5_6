package kernel.micro.observer;
import kernel.micro.facade.Thing;


/**
 * 
 * @author djamel bellebia
 *
 */
public aspect ThingObserverAspect extends ObserverAspect
{	
	//TODO Q3-19 d�clarer chaque Thing comme pouvant �tre un sujet et un Observateur
	
	
   // TODO Q3-20 point de jonction subjectChange � l'execution de Subject.update(String) 
	// qui re�oit en param�tre le Subject cible (target) et l'identifiant (String) de l'�v�nement 
	
	public void Thing.update(String eventId) throws Exception
	{
		System.out.println("Subject updated " + eventId);
	}

	
	//TODO Q3-21 cot� observateur, ajouter la m�thode de notification 
	// qui doit afficher l'identifiant de la chose qui a notfi� l'observateur et l'identifiant de l'�v�nement
	
	
	

}
