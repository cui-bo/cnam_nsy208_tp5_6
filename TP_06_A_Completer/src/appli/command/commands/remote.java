package appli.command.commands;


import java.util.Hashtable;

import kernel.internal.requestor.Message;
import kernel.internal.requestor.RequestMessage;
import kernel.internal.requestor.Requestor;
import kernel.micro.facade.Thing;
import util.BusinessException;
import util.Func;
import util.Logger;
import util.TagsLib;
import appli.command.Command;

/**
 * 
 * @author djamel bellebia
 *
 */
public class remote extends Command 
{
		
	public byte[] execute(Hashtable ctx) 
	{	
		
		boolean flag = false;
		StringBuffer valRet = new StringBuffer();
		try
		{
			// récupération du context
			this._ctx = ctx ;
			//récupérer l'instance de chose
			_thing = (Thing)_ctx.get(TagsLib.THING) ;
			
			String [] devicesUrl ;
		
			if((_ctx.get(TagsLib.DVIC) == null))
			{
				throw new BusinessException("Illegal method arguments exception. Please chek the URL.");
			}
			else
			{
				devicesUrl = Func.stringToArray(_ctx.get(TagsLib.DVIC).toString(),",");
			}
			
			if((devicesUrl.length < 0 || devicesUrl.length > 126 ) )
			{
				throw new BusinessException("Illegal argument exception. Due to url lenght limitations, you can only add until 127 children at once.");
			}
			
			// chek if the conexte is nuu
			if((ctx== null))
			{
				  
				throw new BusinessException("The context is null");
			}
				
			for(int i=0;i<devicesUrl.length;i++)
			{
				try
				{										
					Logger.out.println("Execution remote command on " + devicesUrl[i] );
					
					Requestor requestor = new Requestor(  devicesUrl[i],60);
					
					Message msg = new RequestMessage
					(
							new Object().hashCode(),
							_thing.getThingId(), 
							""+_thing.getAddressByProtocol(util.HttpUtil.extractUrlProtocol(devicesUrl[i])),
							_thing.getFacade(),
							new String[]
							{
								""+_ctx.get(TagsLib.SERV), 
								""+_ctx.get(TagsLib.MTHD), 
								""+_ctx.get(TagsLib.ARGS),
								""
							}
					);
					
					
					Message responseMessage = requestor.send(msg) ;
					
					System.out.println("DEBUG THE ResponseMessage IS ") ;
					
					System.out.println(""+responseMessage);
					
					
					return responseMessage.getBytes();
										
				}
				catch(Exception exp)
				{
					exp.printStackTrace() ;
					
					System.out.println("Error @t remote.execute. The error is " + exp.toString());
					
					valRet.append  ("<"+ TagsLib.TIME_EX + " " + TagsLib.EX_MESS +"='Error @t remote.execute. The error is " + exp.getMessage().replace('\'' ,' ') + "'/>" ) ;
				}
			}// end of for

			flag=true;
		}
		
		catch(Exception e)
		{
			Logger.out.println("Exception @ remote.execute " + e.toString()) ;
			
			valRet.append  ("<"+ TagsLib.TIME_EX + " " + TagsLib.EX_MESS +"='" + e.getMessage().replace('\'' ,' ') + "'/>" ) ;
		}
		valRet.append( "<" + TagsLib.RESULT + " invocation=" + ((flag) ?  "'OK'" :"'end with error'") +  "/>");
		return valRet.toString().getBytes();
	}	

	

} // END OF CLASS
