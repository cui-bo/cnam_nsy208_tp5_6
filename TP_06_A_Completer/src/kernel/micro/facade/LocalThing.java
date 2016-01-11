package kernel.micro.facade;

import java.util.Stack;

import util.HttpUtil;


public class  LocalThing implements Thing
{	
	
	
	public LocalThing(String id, String facade, String address)
	{
		this._thingId = id;
		this._facade = facade;
		this._thingAddresses.add(address);
		
		System.out.println("Creation d'une instance de " + this.getClass().getName()  + " ayant pour facade " + facade);
	}
	
	
	/**
	 * Cette table est utilis� pour charger des propri�t� contenues dans le fichier de configuration 
	 * en fonction des m�thode appel�es. En effet nous voulons optimiser l'usage de la m�moire.
	 * Elle conserve les derni�res propri�t�s appel�es. Avant de recherger les propri�t�, on verifie d'abord 
	 */
	private String _thingId;
		
	public void setThingId(String id)
	{
		 this._thingId = id;
	}
	
	public String getThingId()
	{
		return _thingId;
	}
		
	public String toString()
	{
		return this._thingId + "/" + _facade  ;
	}
	
	private String _facade;
	public String getFacade() {
		return _facade;
	}
	
	public void setFacade(String facade) {
		this._facade = facade;
	}
	
	public String getAddressByProtocol(String protocol)
	{ 				
		String localAddress =null;
			
		for(int k =0 ;k < this.getThingAddresses().size(); k++ )
		{
			String address = (String)this.getThingAddresses().elementAt(k);
			
			String addressProtocol = HttpUtil.extractUrlProtocol(address) ;
			
			if(addressProtocol.compareToIgnoreCase(protocol)==0)
				localAddress = address;
		}
		
		return localAddress ;
	}
	
	// Pile o� l'on doit empiler les # addresses support�es par chaque syst�me
	private Stack _thingAddresses = new Stack();
	
	public Stack getThingAddresses()
	{		
		return _thingAddresses;
	}
}
