package appli.command.commands;

import java.util.Hashtable;

import kernel.micro.facade.Thing;
import util.BusinessException;
import util.Func;
import util.TagsLib;
import appli.command.Command;
/**
 * 
 * @author djamel bellebia
 *
 */
public class discovery extends Command
{
	
	public discovery()
	{	
		super._SUPPORTED_METHODS = new String[]{"getIdentity","default"} ;
	}
	
	public byte [] execute(Hashtable ctx) 
	{	
		StringBuffer valRet = new StringBuffer(TagsLib.HEADER);
		
		valRet.append  ("<" + TagsLib.RESP + " currentTime='" + Func.getCurrentTime() + "'" + " class='"+  this.getClass().getCanonicalName() +"' " ) ;
		// récupération du context
		this._ctx = ctx ;
		
		//récupérer l'instance de chose
		_thing = (Thing)_ctx.get(TagsLib.THING) ;
		boolean flag = false;
	
		try
		{	
			// chek if the conexte is nuu
			if((ctx== null))
			{
				valRet.append(">");  
				throw new BusinessException("The context is null");
			}
			// On s'assure de l'objet sur le quel on demande l'execution de service est bien une chose.
			if(!(_thing instanceof Thing))
			{
				valRet.append(">");  
				throw new BusinessException(_thing + " You're trying to call services on non Thing device.");
			}
			String method  ;
			
			if(_ctx.get(TagsLib.MTHD) == null)
				method="default";
			else
				method = (String)_ctx.get(TagsLib.MTHD);
			// le message id correspondant la requete reçue
			valRet.append(" "+ TagsLib.MESSA_ID + "='" + _ctx.get(TagsLib.MESSA_ID) + "'") ;
				
			valRet.append(" "+ TagsLib.THING_ID + "='" + _thing.getThingId() + "'") ;
			
			valRet.append(" "+ TagsLib.FACADE + "='" + _thing.getFacade() + "'") ;
			
					
			// Si la methode demandé est getIdentity, on doit renvoyer l'url en fonction de protocole utilisé par l'appelant.
			if(method.equals("getIdentity"))
				valRet.append(" "+ TagsLib.THING_URL + "='" + _thing.getAddressByProtocol(""+_ctx.get(TagsLib.PROTOCOL)) + "' >") ;
			else
				valRet.append(" "+ TagsLib.THING_URL + "='" + Func.stackToString(_thing.getThingAddresses()) + "' >") ;
			
					
			valRet.append  ("<" + TagsLib.INVK ) ;	
					
			valRet.append (" " + TagsLib.SERV + "='" + this.getClass().getName() + "' " + TagsLib.MTHD + "='" + method +"'>" ) ;			
			
			valRet.append  ("</" + TagsLib.INVK +  ">" ) ;
			flag=true;
		}
		catch(BusinessException e)
		{
			valRet.append  ("<businessException msg='" + e.getMessage().replace('\'' ,' ') + "'/>" ) ;
		}
		catch(Exception e)
		{
			valRet.append  ("<exception msg='" + e.toString().replace('\'' ,' ') + "'/>" ) ;
		}
		finally
		{		
			valRet.append( "<" + TagsLib.RESULT + " invocation=" + ((flag) ?  "'OK'" :"'end with error'") +  "/>");
			valRet.append  ("</"+TagsLib.RESP + ">" ) ;
		}
		
		System.out.println("RESPONSE RESPONSE  \n" + valRet);
		
		return valRet.toString().getBytes();
	}
}
