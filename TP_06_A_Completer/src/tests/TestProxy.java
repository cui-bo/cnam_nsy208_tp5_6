package tests;

import junit.framework.Assert;
import junit.framework.TestCase;
import kernel.micro.facade.LocalThing;
import kernel.micro.facade.Thing;
import kernel.micro.facade.ThingProxy;

public class TestProxy extends TestCase {

	public void testAll() throws Exception
	{
		Thing localThing = new LocalThing("Test", "SimpleThing","http://127.0.0.1");
		Thing remotthing = new ThingProxy(localThing, "http://127.0.0.1:3333");
		Assert.assertEquals(remotthing.getThingId(),"NOKIA-N93") ;
	}
	
}
