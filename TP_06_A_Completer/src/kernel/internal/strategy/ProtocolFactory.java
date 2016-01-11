package kernel.internal.strategy;

import kernel.internal.strategy.Protocol;

/**
 * 
 * @author djamel bellebia
 *
 */

public  interface  ProtocolFactory 
{	
	public  Protocol createProtocol(String url) throws Exception;
}
