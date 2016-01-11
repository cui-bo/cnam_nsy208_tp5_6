package kernel.micro.facade;

public abstract class LocalThing implements Thing
{
	public LocalThing()
	{
		System.out.println("Creation d'une instance de " + this.getClass().getName());
	}
	/**
	 * Cette table est utilisé pour charger des propriété contenues dans le fichier de configuration 
	 * en fonction des méthode appelées. En effet nous voulons optimiser l'usage de la mémoire.
	 * Elle conserve les dernières propriétés appelées. Avant de recherger les propriété, on verifie d'abord 
	 */
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
		return this._thingId + "/" + _facade  ;
	}
	
	private String _facade;
	public String getFacade() {
		return _facade;
	}
	
	public void setFacade(String facade) {
		this._facade = facade;
	}
		
}
