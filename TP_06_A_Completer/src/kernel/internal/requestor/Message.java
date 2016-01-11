package kernel.internal.requestor;

import java.util.Hashtable;;

public interface Message 
{	
	public Hashtable getQueryData();
	
	public String getMessageType();
	
	public byte[] getBytes();
			
}
