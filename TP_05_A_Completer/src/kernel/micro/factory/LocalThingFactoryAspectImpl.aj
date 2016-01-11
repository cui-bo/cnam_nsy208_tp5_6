package kernel.micro.factory;

import kernel.micro.composite.CompositeThing;
import kernel.micro.composite.SimpleThing;
import kernel.micro.facade.Thing;
import util.Logger;


/**
 * 
 * @author djamel bellebia
 *
 */
public aspect LocalThingFactoryAspectImpl  
{	

	//declare precedence: kernel.micro.composite.CompositeAspect;
	// Simple factory
	declare parents : SimpleThingFactory implements ThingFactory;
	private static Thing SimpleThingFactory._thing = null;
	public synchronized Thing SimpleThingFactory.createThing(String id)
	{
		try
		{				
			_thing = new SimpleThing() ;
			
			_thing.setThingId(id);
		
			_thing.setFacade("SimpleThing");
			
			
		}
		catch(Exception ex)
		{
			Logger.out.println("Ex at  ThingFactoryImpl.createLocalThing()" + ex.toString()) ;
		}
		return _thing;
	}
	
	// Composite factory
	// A completer La factory pour la creation d'une chose compsite
	//TODO 07
	
	
	
}
