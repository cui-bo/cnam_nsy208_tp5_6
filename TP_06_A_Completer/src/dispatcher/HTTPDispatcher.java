package dispatcher;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import kernel.internal.marshaller.Marshaller;
import kernel.internal.marshaller.XMLMarshaller;
import kernel.internal.requestor.Message;
import kernel.micro.facade.LocalThing;
import kernel.micro.facade.Thing;

import sunlabs.brazil.server.Handler;
import sunlabs.brazil.server.Request;
import sunlabs.brazil.server.Server;
import appli.command.Command;


import util.Consts;
import util.TagsLib;

public class HTTPDispatcher implements Handler {

	private String _serviceName = "";

	protected byte[] _response;

	Command _service = null;

	private static Thing _thing = null;

	public boolean init(Server server, String prefix) {
		initThing(server);
		return true;
	}
	
	private void beforeRespond(Hashtable request) throws Exception
	{
		// verifier si la reqûte est en xml. Dans ce cas la déballer
		// d'abord.
		if (request.containsKey("xml")) {

			Marshaller marshaller = new XMLMarshaller();
			Message requestMsg = marshaller.unmarshall(request
					.get("xml").toString().getBytes());
			
			for(Enumeration e = requestMsg.getQueryData().keys(); e.hasMoreElements();)
			{
				Object key  = e.nextElement() ;
				
				request.put(key, requestMsg.getQueryData().get(key)) ;
			}
			
		}
	}

	public boolean respond(Request request) throws IOException {
		try {

			
			// 
			if (!request.url.equals("/thing") && !request.url.equals("/") && !request.url.equals(""))
				return false;
			
			Hashtable context = request.getQueryData();
			
			this.beforeRespond(context);

			// l'utilisateur a spécifié une addresse distante
			if(context.get(TagsLib.DVIC) != null 
					&& !(context.get(TagsLib.DVIC)).equals("null") 
					&& !(context.get(TagsLib.DVIC)).equals(""))
			{
				_serviceName="remote" ;
			}
			// aucun nom de service specifié
			else if (!context.elements().hasMoreElements()
					|| (""+context.get(TagsLib.SERV)).equals("")
					|| (""+context.get(TagsLib.SERV)) == null
					|| (""+context.get(TagsLib.SERV)).equals("null")) {
				_serviceName = "discovery";
			}
			
			else 
			{
				
				_serviceName = (String) context.get(TagsLib.SERV);
			}

			

			context.put(TagsLib.THING, _thing);

			context.put(TagsLib.PROTOCOL, "http");

			Class cls;

			cls = Class.forName("appli.command.commands." + _serviceName);

			_service = (Command) cls.newInstance();
			
			System.out.println("INVOCATION DU SERVICE " + cls.getCanonicalName() ) ;

			this._response = _service.execute(context);
			
			System.out.println("VOICI LA REPONSE DU SERVICE SERVICE  ");
			
			System.out.println(util.Func.dump(this._response) );

			request.sendResponse(_response, "");

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			request.sendResponse(e.getMessage());
			return false;
		}

	}

	private void initThing(Server server) {
		try {

			System.out.println("Thing init...");

			String facade = "" + server.props.get(Consts._FACADE);


			_thing = new LocalThing(
					"" + server.props.get(Consts._THING_ID), 
					facade, 
					"http://"
					+ server.listen.getInetAddress().getLocalHost().getHostAddress() + ":"
					+ server.listen.getLocalPort());

			

		} catch (Exception ex) {
			System.out
					.println("ERROR at BrazilTiniDispatcherAspect.initApplication \n"
							+ ex);
		}
	}
}
