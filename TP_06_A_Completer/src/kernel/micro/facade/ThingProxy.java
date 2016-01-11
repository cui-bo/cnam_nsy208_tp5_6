package kernel.micro.facade;

import java.util.Stack;

import util.HttpUtil;

public class ThingProxy implements Thing
{
	public ThingProxy(Thing localThing, String remoteUrl) throws Exception
	{
		
	}

	private String _thingId;
	public void setThingId(String thingId)
	{
		_thingId=thingId;
	}

	public String getThingId()
	{
		return _thingId;
	}
		
	public String toString()
	{
		return this._thingId + "@" + this._thingAddresses ;
	}
	
	private Stack _thingAddresses = new Stack();
	public void setThingAddresses(Stack thingAddresses)
	{
		
		_thingAddresses=thingAddresses;
	}
	public Stack getThingAddresses()
	{
		return _thingAddresses;
	}

	

	private String _facade="";
	public String getFacade() 
	{
		
		return _facade;
	}
	
	public void setFacade(String facade)
	{
		this._facade = facade;
	}
	
	public String getAddressByProtocol(String protocol)
	{ 				
		String localAddress =null;
			
		for(int k =0 ;k < this.getThingAddresses().size(); k++ )
		{
			String address = (String)this.getThingAddresses().elementAt(k);
			
			if(HttpUtil.extractUrlProtocol(address).equals(protocol.toUpperCase()))
				localAddress = address;
		}
		
		return localAddress ;
	}

	

	
}
