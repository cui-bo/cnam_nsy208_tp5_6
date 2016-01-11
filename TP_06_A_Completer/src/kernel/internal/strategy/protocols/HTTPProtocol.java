package kernel.internal.strategy.protocols;


import java.net.URL;
import java.net.URLConnection;

import kernel.internal.strategy.Protocol;


public class HTTPProtocol extends Protocol 
{
	private URL _url ;
	
    URLConnection _urlConnection; 
 
	public String getProtocolName() 
	{
		return "http";
	}

	public void openConnection()throws Exception 
	{	
		
		this._url = new URL(this._remoteUrl);
		
		this._urlConnection = _url.openConnection();
		
		_urlConnection.setDoInput(true);
		
		_urlConnection.setDoOutput(true);
		
		this.out = _urlConnection.getOutputStream();
		
	}

	public byte[] receive() throws Exception 
	{
		byte[] stream = new byte[this.in.available()];
		
		this.in.read(stream);
		
		this.in.close();
		
		return stream;
	}

	public void send(byte[] data) throws Exception 
	{	
		
		this.out.write(data);
		
		this.out.flush();
		
		this.out.close();
		
		_urlConnection.connect();
		
		this.in = _urlConnection.getInputStream();
		
		 	
	}
	
}