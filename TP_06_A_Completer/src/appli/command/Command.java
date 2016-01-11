package appli.command;

import java.util.Hashtable;

import kernel.micro.facade.Thing;
/**
 * 
 * @author djamel bellebia
 *
 */

public  abstract class Command 
{
	
	public abstract  byte [] execute(Hashtable hastable);
	
	protected  String [] _SUPPORTED_METHODS = {"no methods"};
	
	protected Thing _thing ;
	
	protected Hashtable _ctx;
	
	public void setSupportedMethods(String[] methods)
	{
		this._SUPPORTED_METHODS = methods;
	}
	
	
	public boolean isSupportedMethod(String methodName)
	{
		if(_SUPPORTED_METHODS != null)
		{
			for(int i =0; i <_SUPPORTED_METHODS.length;i++)
			{
				if(_SUPPORTED_METHODS[i].equals(methodName))
					return true;
			}
		}
		return false;	
	}
	
	
	public String [] getSupportedMethods()
	{
		return this._SUPPORTED_METHODS;
	}
}
