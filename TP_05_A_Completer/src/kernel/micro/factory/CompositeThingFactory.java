package kernel.micro.factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kernel.micro.composite.CompositeThing;
import kernel.micro.composite.SimpleThing;
import kernel.micro.facade.LocalThing;
import kernel.micro.facade.Thing;
import kernel.micro.visitor.Visitable;
import kernel.micro.visitor.Visitor;

public class CompositeThingFactory implements ThingFactory, Visitable
{
	private static CompositeThing compositeThing = new CompositeThing();
	private static SimpleThing simpleThing = new SimpleThing();
	
	public CompositeThingFactory() {
		
	}
	
	public Thing createThing(String id) {
		String compositeIdPattern = "^COMPOSITE_[0-9]+";
		String simpleIdPattern = "^SIMPLE_[0-9]+";
		LocalThing thing = null;
		
		// Create a Pattern object
	    Pattern compositePattern = Pattern.compile(compositeIdPattern);
	    Pattern simplePattern = Pattern.compile(simpleIdPattern);
	    
	    // Now create matcher object.
	    Matcher compositeMatcher = compositePattern.matcher(id);
	    Matcher simpleMatcher = simplePattern.matcher(id);
	    
	    if (compositeMatcher.find()) {
			thing = getCompositeThing();
			thing.setThingId(id);
			thing.setFacade("CompositeThing");
			System.out.println("[CompositeThingFactory] Create Composite Thing");
		} else if (simpleMatcher.find()) {
			thing = getSimpleThing();
			thing.setThingId(id);
			thing.setFacade("SimpleThing");
			System.out.println("[CompositeThingFactory] Create Simple Thing");
		}else {
			System.out.println("NO MATCH");
		}
	    
		return thing;
	}
	
	public static CompositeThing getCompositeThing() {
		return compositeThing;
	}
	
	public static SimpleThing getSimpleThing() {
		return simpleThing;
	}

	public String accept(Visitor v) throws Exception {
		return "test";
	}
	
}
