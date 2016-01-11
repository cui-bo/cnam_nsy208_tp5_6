package tests;

import junit.framework.Assert;
import junit.framework.TestCase;
import kernel.internal.requestor.Requestor;
import kernel.internal.strategy.protocols.HTTPProtocol;


public class TestRequestor extends TestCase {
	
	public void testAll() throws Exception
	{
		
		Requestor requestor = new Requestor("http://127.0.0.1", 1000);
		
		Assert.assertEquals(requestor.getProtocol() instanceof HTTPProtocol, true);
		
		Assert.assertEquals(requestor.getProtocol().getRemoteUrl() ,"http://127.0.0.1");
		
	}

}
