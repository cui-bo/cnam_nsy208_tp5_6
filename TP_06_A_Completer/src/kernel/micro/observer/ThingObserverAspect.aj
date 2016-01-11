package kernel.micro.observer;
import kernel.micro.facade.Thing;


/**
 * 
 * @author djamel bellebia
 *
 */
public aspect ThingObserverAspect extends ObserverAspect
{	
	//TODO Q3-19 déclarer chaque Thing comme pouvant être un sujet et un Observateur
	
	
   // TODO Q3-20 point de jonction subjectChange à l'execution de Subject.update(String) 
	// qui reçoit en paramètre le Subject cible (target) et l'identifiant (String) de l'événement 
	
	public void Thing.update(String eventId) throws Exception
	{
		System.out.println("Subject updated " + eventId);
	}

	
	//TODO Q3-21 coté observateur, ajouter la méthode de notification 
	// qui doit afficher l'identifiant de la chose qui a notfié l'observateur et l'identifiant de l'événement
	
	
	

}
