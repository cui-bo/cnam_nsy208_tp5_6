package kernel.micro.visitor;
import kernel.micro.composite.CompositeThing;
import kernel.micro.composite.SimpleThing;
import kernel.micro.facade.Thing;
public aspect AJThingVtor
{
	declare parents: Thing implements  Visitable;
	
	public String Thing.accept(Visitor v)  throws Exception
	{
		return v.visiteComponent(this) ;
	}
	
	// A completer viste d'une chose simple
	//TODO 10
	
	// A completer viste d'une chose composite
	// TODO 11
}
