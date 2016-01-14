package kernel.micro.factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kernel.micro.composite.SimpleThing;
import kernel.micro.facade.LocalThing;
import kernel.micro.facade.Thing;

public class SimpleThingFactory implements ThingFactory
{

	private static SimpleThing simpleThing = new SimpleThing();
	
	public Thing createThing(String id) {
		String simpleIdPattern = "^SIMPLE_[0-9]+";
		LocalThing thing = null;
		
		// Create a Pattern object
	    Pattern simplePattern = Pattern.compile(simpleIdPattern);
	    
	    // Now create matcher object.
	    Matcher simpleMatcher = simplePattern.matcher(id);
	    
	    if (simpleMatcher.find()) {
			thing = getSimpleThing();
			thing.setThingId(id);
			System.out.println("[SimpleThingFactory] Create Simple Thing");
		}else {
			System.out.println("NO MATCH");
		}
	    
		return thing;
	}
	
	public static SimpleThing getSimpleThing() {
		return simpleThing;
	}

}
