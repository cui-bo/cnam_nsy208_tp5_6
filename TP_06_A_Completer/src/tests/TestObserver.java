package tests;

import junit.framework.Assert;
import junit.framework.TestCase;
import kernel.micro.facade.LocalThing;


public class TestObserver extends TestCase {
	
	public void testAll() throws Exception
	{
		LocalThing thingPC_PORTABLE = new LocalThing("PC-PORTABLE", "CompositeThing", "localhost");
		LocalThing thingNOKIA_N93 = new LocalThing("NOKIA-N93", "SimpleThing", "localhost");
		
		thingPC_PORTABLE.addObserver("NOKIA-N93", thingNOKIA_N93);
		thingPC_PORTABLE.addObserver("NOKIA-N93", thingNOKIA_N93);
		
		Assert.assertEquals(thingPC_PORTABLE.getObservers().size(),1) ;
		
		
		thingPC_PORTABLE.update("123456");
		
		thingPC_PORTABLE.removeObserver("NOKIA-N93");
		
		Assert.assertEquals(thingPC_PORTABLE.getObservers().size(),0) ;
		
		
	}

}
