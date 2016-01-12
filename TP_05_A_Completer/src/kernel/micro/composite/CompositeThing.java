package kernel.micro.composite;

import java.util.Hashtable;

import kernel.micro.facade.LocalThing;

/**
 * 
 * @author djamel bellebia
 *
 */
public class CompositeThing extends LocalThing
{
	// A completer le consutructeur
	//TODO 06	=> fait
	
	private Hashtable enfants = new Hashtable();
	
	public CompositeThing() {
		super();
	}
	
	public void addChild(String thingId, LocalThing lt) {
		this.enfants.put(thingId, lt);
	}
	
	public Hashtable getChildren() {
		return this.enfants;
	}

	public void removeChild(String thingId) {
		if(this.enfants.containsKey(thingId)) {
			this.enfants.remove(thingId);
		}
	}
	
}
