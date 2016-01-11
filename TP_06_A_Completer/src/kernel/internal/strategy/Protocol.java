package kernel.internal.strategy;
import java.io.InputStream;
import java.io.OutputStream;


public abstract class Protocol 
{
	protected String _remoteUrl ;
	
	public void setRemoteUrl(String url)
	{
		_remoteUrl = url;
	}
	
	public String getRemoteUrl()
	{
		return this._remoteUrl;
	}
	
	/**
	 * Renvoie le nom du protocole. Chaque classe protocole qui implémente revoie une chaine:
	 * http, btl2cap, btspp, apdu, onewire.
	 * @return
	 */
	public abstract String getProtocolName();
	
	/**
	 * Ouverture de connection vers l'url indiquée.
	 * @param url
	 * @throws Exception
	 */
	public abstract void openConnection() throws Exception;
	
	/**
	 * Certain protocol propose cette fonction. On s'en sert pour initialisé le buffer en lecture.
	 * @throws Exception
	 */
	//public abstract void connect() throws Exception ; 
	
	/**
	 * Envoi des données sous forme binaire.
	 * @param data
	 * @throws Exception
	 */
	public abstract void send (byte [] data) throws Exception ;
	
	/**
	 * Reception des donnée sous forme d'une tableau binaire
	 * @return
	 * @throws Exception
	 */
	public abstract byte [] receive () throws Exception ;
	
	/**
	 * Buffer en lecture.
	 */
	protected InputStream in ;
	/**
	 * Buffer en écriture.
	 */
	protected OutputStream out;
}