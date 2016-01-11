package kernel.internal.marshaller;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;

import kernel.internal.requestor.Message;
import kernel.internal.requestor.RequestMessage;
import kernel.internal.requestor.ResponseMessage;

import util.TagsLib;

import com.alsutton.xmlparser.XMLEventListener;
import com.alsutton.xmlparser.XMLParser;

public class XMLMarshaller implements Marshaller 
{	
	private static StringBuffer valRet ;
	
	public synchronized byte[] marshall(Message msg) throws Exception
	{	
		try
		{
			valRet = new StringBuffer() ;
			
			valRet.append(TagsLib.HEADER);
			
			if (msg.getQueryData() != null)
			{
				valRet.append("<" ); 
				valRet.append(msg.getMessageType() 	+ " " ); 
				valRet.append(TagsLib.MESSA_ID 		+ "='" + msg.getQueryData().get(TagsLib.MESSA_ID ) 	+ "' "); 
				valRet.append(TagsLib.HANDLER 		+ "='" + msg.getQueryData().get(TagsLib.HANDLER )  	+ "' ");
				valRet.append(TagsLib.THING_ID 		+ "='" + msg.getQueryData().get(TagsLib.THING_ID )  + "' ");
				valRet.append(TagsLib.THING_URL 	+ "='" + msg.getQueryData().get(TagsLib.THING_URL )	+ "' ");
				valRet.append(TagsLib.FACADE 		+ "='" + msg.getQueryData().get(TagsLib.FACADE ) 	+ "' ");
				valRet.append(		">") ;
				
				String arguments = ""+msg.getQueryData().get(TagsLib.ARGS) ;
				//arguments=arguments.replace('\'','£');
				
				valRet.append ("<") ;					
				valRet.append(TagsLib.INVK +	" " +  TagsLib.SERV + "='" + msg.getQueryData().get(TagsLib.SERV) + "' "); 
				valRet.append(TagsLib.MTHD + "='" 	+ msg.getQueryData().get(TagsLib.MTHD) +"' "); 
				valRet.append(TagsLib.ARGS + "='" 	+ arguments +"' ");
				valRet.append(TagsLib.DVIC + "='" 	+ msg.getQueryData().get(TagsLib.DVIC) +"' ");
				valRet.append(" >");
				
				valRet.append(msg.getQueryData().get(TagsLib.RESULT));
				
				valRet.append("</" + TagsLib.INVK + ">"); 
				
			}
			
			valRet.append("</"+ msg.getMessageType() + ">") ;
		}
		catch(Exception exp)
		{
			System.out.println("Error @ " + this.getClass().getName() + ".marshall " +  exp.toString());
			throw exp;
		}
		
//		nesmid.util.Logger.out.println("Voici la serialisation de la requete " + valRet.toString()) ;
		
		return valRet.toString().getBytes();
		
	}

	// transformation du flux binaire de l'xml en un messsage
	public synchronized Message  unmarshall(byte[] stream) throws Exception 
	{
		try
		{
			System.out.println("Unmarshaling stream " + util.Func.dump(stream));
		
			XMLDeSerializer unmarshaller = new XMLDeSerializer();
		
			ByteArrayInputStream bin =
				new ByteArrayInputStream( stream );
			
			InputStreamReader is = new InputStreamReader(bin);
			
			XMLParser parser = new XMLParser(unmarshaller);
			
			parser.parse(is);
			
			Message valRet = unmarshaller.getQueryData();  
		
			return valRet;
		}
		catch(Exception ex)
		{
			System.out.println("Error @ " + this.getClass().getName() + " unmarshall. The error message is " + ex.toString());
			throw ex;
		}
	}
	
	class XMLDeSerializer implements XMLEventListener 
	{	
		private int _messageId = -1;
			
		String _thingId="", _thingUrl="", _result="", _facade="";
		
		/**
		 * Chaque message peut avoir au maximim 4 info pour un message de type  
		 * (nom du service, nom mthd, argument, nom du système, facade et resultat,
		 */
		String [] msgdata = new String [6];
		
		/**
		 * Pour savoir quel est le type du message deballé : requete ou reponse
		 */
		String msgType;
		
		/**
		 * 
		 */
		private Message _unmarshalledMessage ;

		public void tagStarted(String name, Hashtable attributes) 
		{				
			try
			{
				// noeud reqt ou resp
				if(name.equals(TagsLib.REQU) || name.equals(TagsLib.RESP))
				{
					msgType = name;
					
					msgdata = new String[4];
					
					_messageId 	= Integer.parseInt((String)attributes.get(TagsLib.MESSA_ID));
					
					_thingId	= (String)attributes.get(TagsLib.THING_ID);
					
					_thingUrl 	= (String)attributes.get(TagsLib.THING_URL);
					
					_facade 	= (String)attributes.get(TagsLib.FACADE);
				}
				else if(name.equals(TagsLib.INVK))
				{
					msgdata[0] = (String)attributes.get(TagsLib.SERV) ;
					msgdata[1] = (String)attributes.get(TagsLib.MTHD) ;
					msgdata[2] = (String)attributes.get(TagsLib.ARGS) ;
					msgdata[3] = (String)attributes.get(TagsLib.DVIC) ;
				}
				
				else
				{
					_result +="<" + name ; 
					if(attributes!= null)
					{
						for(Enumeration enu = attributes.keys(); enu.hasMoreElements();)
						{
							Object key = enu.nextElement();
							
							_result += " " + key + "=" + "'" + attributes.get(key) + "'";
						}
					}
					_result += " >";	
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception @ XMLDeSerializer.tagStarted " + name + ". The error message " + e.getMessage());
			}
		}

		public void plaintextEncountered(String arg0) 
		{
			
		}
		
		

		public void tagEnded(String name) 
		{
			try
			{	
				if(		!name.equals(TagsLib.INVK) 
						&& !name.equals(TagsLib.REQU) 
						&& !name.equals(TagsLib.RESP))
				{
					_result +="</" + name+ ">" ;
				}
				if(name != null && name.equals(TagsLib.INVK))
				{			
					
					if(msgType.equals(TagsLib.REQU))
					{
						//nesmid.util.Logger.out.println("1 tagEnded le nom est  msgType " + msgType);
						_unmarshalledMessage = new RequestMessage(_messageId,_thingId, _thingUrl, _facade, msgdata);
					}
					else if(msgType.equals(TagsLib.RESP))
					{							
						//nesmid.util.Logger.out.println("2 tagEnded le nom est  msgType " + msgType);
						_unmarshalledMessage = new ResponseMessage(_messageId,_thingId, _thingUrl, _facade,msgdata, _result);
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception @ XMLDeSerializer.tagEnded "  + e.getMessage());
			}
		}
		
		public Message getQueryData()
		{
			return this._unmarshalledMessage;
		}
	}

}
