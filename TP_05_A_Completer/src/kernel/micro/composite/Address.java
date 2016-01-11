package kernel.micro.composite;

public class Address 
{	
	public Address(String protocol, String url)
	{
		this._protocol = protocol;
		this._url = url ;
	}

	public String getProtocol()
	{
		return this._protocol ;
	}
	
	public String getUrl()
	{
		return this._url ;
	}
	
	private String _protocol ;
	
	private String _url;
	
	public String toString()
	{
		return this._url;
	}
}
