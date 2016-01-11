package kernel.micro.observer;

import java.util.Hashtable;
import java.util.Enumeration;
/**
 * 
 * @author djamel bellebia
 *
 */

public abstract aspect ObserverAspect 
{
	private Hashtable Subject._observers = new Hashtable();
	
	public void Subject.addObserver(String key, Observer observer)
	{
		this._observers.put(key,observer);
	}
   
    //TODO Q3-15 ajouter la m�thode qui permet de d�tatcher un observateur
	
	//TODO Q3-16 ajouter la m�thode qui renvoie la table des observateurs inscrits 
      
    // TODO Q3-17 impl�menter la m�thode notifyObservers de l'interface Subject
    
	protected abstract pointcut subjectChange(Subject s, String eventId);
	// TODO Q3-18 coupure apres (after) subjectChange
 
}
	
