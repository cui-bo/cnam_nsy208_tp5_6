package kernel.internal.requestor;

import java.util.Hashtable;

import kernel.internal.marshaller.XMLMarshaller;
import util.TagsLib;


public class RequestMessage implements Message
{	
	
	/**
	 * Pile qui va contenir les paramètre pour l'invocation du service
	 */
	private  Hashtable _stack ;
	
	public RequestMessage(int id,String thingId, String thingUrl, String facade, String [] data)
	{	
		try
		{
			this._stack = new Hashtable();
			_stack.put(TagsLib.MESSA_ID, ""+id);
			_stack.put(TagsLib.THING_ID, thingId);
			_stack.put(TagsLib.THING_URL, thingUrl);
			_stack.put(TagsLib.FACADE, facade);
			
			_stack.put(TagsLib.SERV, data[0]);
			_stack.put(TagsLib.MTHD, data[1]);
			_stack.put(TagsLib.ARGS, data[2]);
			_stack.put(TagsLib.DVIC, data[3]);
		}
		catch(Exception e)
		{
			System.out.println("It was not possible to create the Request message. The error is " + e);
		}
		
	}
	
	public String getMessageType() 
	{
		return TagsLib.REQU;
	}

	public Hashtable getQueryData() 
	{
		return this._stack;
	}
	
	public String toString()
	{
		try {
			return util.Func.dump(this.getBytes());
		} catch (Exception e) {
			return e.toString();
		}
	}
	
	public byte[] getBytes()
	{
		byte [] valRet;
		
		try {
			valRet = new XMLMarshaller().marshall(this);
		} catch (Exception e) {
			valRet = e.getMessage().getBytes();
		}
		
		return valRet;
	}
	
}
