package appli.command.commands;

import java.util.Enumeration;
import java.util.Hashtable;

import kernel.micro.facade.Thing;
import kernel.micro.facade.ThingProxy;
import kernel.micro.observer.Subject;
import util.BusinessException;
import util.Consts;
import util.Func;
import util.HttpUtil;
import util.TagsLib;
import appli.command.Command;


/**
 * 
 * @author djamel bellebia
 * 
 */
public class observation extends Command {

	StringBuffer valRet = new StringBuffer(TagsLib.HEADER);

	public observation() {
		super._SUPPORTED_METHODS = new String[] { "addObs", "removeObs",
				"getObservers", "update", "notify", "publish" };
	}

	public byte[] execute(Hashtable ctx) {
		valRet.append("<" + TagsLib.RESP + " currentTime='"
				+ Func.getCurrentTime() + "'");
		boolean flag = true;

		try {
			
			// récupération du context
			this._ctx = ctx ;
			
			// check if the context is null
			if((ctx== null))
			{
				valRet.append(">");  
				throw new BusinessException("The context is null");
			}
			
			// récupérer l'instance de chose
			_thing = (Thing)_ctx.get(TagsLib.THING) ;
			
			

			// chek the method name is specified in the URL
			if ((_ctx.get(TagsLib.MTHD) == null)) {
				valRet.append("/>");
				throw new BusinessException(
						"Illegal method name exception. You dont specify the method name. Please chek the URL.");
			}

			// check if this device is not a composite thing
			if (!(_thing instanceof Thing)) {
				valRet.append(">");
				throw new BusinessException(
						"This device is not wrapped as a Thing component");
			}

			valRet.append(" " + TagsLib.MESSA_ID + "='"
					+ _ctx.get(TagsLib.MESSA_ID) + "'");

			valRet.append(" " + TagsLib.THING_ID + "='"	+ _thing.getThingId() + "'");

			valRet.append(" "
					+ TagsLib.THING_URL
					+ "='"
					+ Func.stackToString(_thing.getThingAddresses()) + "' >");

			valRet.append("<" + TagsLib.INVK);

			valRet.append(" " + TagsLib.SERV + "='" + this.getClass().getName()
					+ "'");

			// chek the service has methods sepecified
			if ((this.getSupportedMethods() == null)) {
				valRet.append("/>");
				throw new BusinessException(
						"No supported method is defined for the device. Please check the code of "
								+ this.getClass().getName());
			}

			String method = _ctx.get(TagsLib.MTHD).toString();

			// Check if the method is supported by the service
			if (!(this.isSupportedMethod(method))) {
				valRet.append("/>");
				throw new BusinessException("Unsupported method: " + method
						+ ". This service accept only these methods : "
						+ Func.arrayToString(this.getSupportedMethods()));
			}

			valRet.append(" " + TagsLib.MTHD + "='" + method + "'>");

			// ADD OBS METHOD
			if (method.toUpperCase().equals("ADDOBS")) {
				this.addObs();
			}
			// UPDATE OBS MTHD
			else if (method.toUpperCase().equals("UPDATE")) {
				this.update();
			}
			// NOTIFY OBS MTHD
			else if (method.toUpperCase().equals("NOTIFY")) {
				this.notification();
			}
			// REMOVE OBS MTHD
			else if (method.toUpperCase().equals("REMOVEOBS")) {
				this.removeObs();
			}
			// GET OBS MTHD
			else if (method.toUpperCase().equals("GETOBSERVERS")) {
				this.getObservers();
			}

		

		} catch (BusinessException e) {
			valRet.append("<" + TagsLib.BUSI_EX + " " + TagsLib.EX_MESS + "='"
					+ e.getMessage().replace('\'', ' ') + "'/>");
		} catch (Exception e) {
			valRet.append("<" + TagsLib.BUSI_EX + " " + TagsLib.EX_MESS + "='"
					+ e.getMessage().replace('\'', ' ') + "'/>");
		}

		finally {
			valRet.append( "<" + TagsLib.RESULT + " invocation=" + ((flag) ?  "'OK'" :"'end with error'") +  "/>");
			valRet.append("</" + TagsLib.INVK + ">");
			valRet.append("</" + TagsLib.RESP + ">");
		}

		return valRet.toString().getBytes();
	}

	private void update() throws Exception {
		((Subject) _thing).update("" + new Object().hashCode());
	}

	private void notification() throws Exception {
		if (!(_ctx.get(TagsLib.ARGS) == null)) {
			((Subject)_thing).update(Func.stringToArray(_ctx.get(TagsLib.ARGS).toString(), ",")[0]);
		} else {
			((Subject)_thing)
					.update("" + new Object().hashCode());
		}
	}

	/**
	 * Ajouter un observateur.
	 * 
	 * @param ctx
	 */
	private void addObs() {
		try {
			String[] args;

			if ((_ctx.get(TagsLib.ARGS) == null)) {
				throw new BusinessException(
						"Illegal method arguments exception. Please chek the URL.");
			} else {
				args = Func.stringToArray(_ctx.get(TagsLib.ARGS)
						.toString(), ",");
			}

			if ((args.length < 0 || args.length > 126)) {
				throw new BusinessException(
						"Illegal argument exception. Due to url lenght limitations, you can only add until 127 children at once.");
			}

			for (int i = 0; i < args.length; i++) {
				boolean flag = false;

				valRet.append("<" + TagsLib.THING + " " + TagsLib.THING_URL
						+ "='" + args[i] + "'");
				try {
					// control the protocol
					if ((HttpUtil.extractUrlProtocol(args[i]) == null))
						throw new BusinessException(
								"The URL path is not valid. Please check the URL, it has to be formated like this : "
										+ Consts._URL_FORMAT);

					// Déterminer l'url de la chose local en fonction du
					// protocole de la chose distante à ajouter
					String localThingUrl = ""
							+ _thing.getAddressByProtocol(
									HttpUtil.extractUrlProtocol(args[i]));

					// check for loops: trying to add a device as its own child
					if (
					// On vérifie que l'on essaye pas de rajouter la chose
					// locale à elle même
					(localThingUrl.equals(args[i])) ||

					// child url = localhost and its listening port is the same
					// as the localThing port
							((args[i].indexOf("127.0.0.1") > 0 || args[i]
									.indexOf("localhost") > 0) && (HttpUtil
									.extractUrlPort(args[i]).equals(HttpUtil
									.extractUrlPort(localThingUrl)))))
						throw new BusinessException(
								"Infinite loop detected. You cannot add a device as an observer of itself.");

					Thing child = new ThingProxy(_thing, args[i]);

					((Subject) _thing).addObserver(""
							+ child.getAddressByProtocol(HttpUtil
									.extractUrlProtocol(args[i])), child);

					valRet.append(" " + TagsLib.THING_ID + "='"
							+ child.getThingId() + "'");

					flag = true;

				} catch (Exception e) {
					valRet.append(" " + TagsLib.ERROR + "='" + e + "'");
				} finally {
					valRet.append((flag) ? " " + TagsLib.RESULT + "='OK'" : " "
							+ TagsLib.RESULT + "='end with error'");
					valRet.append("/>");
				}
			}// for
		} catch (BusinessException e) {
			valRet.append("<" + TagsLib.BUSI_EX + " " + TagsLib.EX_MESS + "='"
					+ e.getMessage().replace('\'', ' ') + "'/>");
		}
	}

	private void removeObs() {

	}

	private void getObservers() throws Exception {
		valRet.append("<observers>") ;
		 
		Enumeration enumeration = ((Subject)_thing).getObservers().elements();
		
		while(enumeration.hasMoreElements())
		{
			Thing thing= ((Thing)enumeration.nextElement());
			
			valRet.append("<" + TagsLib.THING + " " + TagsLib.THING_ID  + "='" + thing.getThingId() +  "' " + TagsLib.THING_URL + "='" + Func.stackToString(thing.getThingAddresses()) + "'/>"); 
		}
		
		valRet.append  ("</observers>");
	}
} // END OF CLASS
