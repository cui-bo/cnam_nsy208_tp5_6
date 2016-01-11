package kernel.internal.proxy;

import kernel.internal.requestor.Message;
import kernel.internal.requestor.RequestMessage;
import kernel.internal.requestor.Requestor;
import kernel.internal.requestor.ResponseMessage;
import kernel.micro.facade.LocalThing;
import kernel.micro.facade.Thing;
import kernel.micro.facade.ThingProxy;

import kernel.micro.observer.Observer;
import kernel.micro.observer.Subject;

import util.HttpUtil;


public aspect RemoteProxyAspect {
	
	
	/***************************************************************************
	 * Point de coupe � la cr�eation d'un objet procuratrion vers le syst�me
	 * distant 
	 * On re�oit en param�tre l'objet proxy que l'on veut insatancier,
	 * le syst�me local pour lequel on veut cr�er un proxy et l'url du syst�me
	 * distant
	 */
	// TODO Q2-03 cr�ation d'un point de jonction (pointcut) � l'instanciation d'une classe ThingProxy
	
	
	
	after(ThingProxy thingProxy, LocalThing localThing,String remoteUrl)  
	: newThingProxy(thingProxy,localThing,remoteUrl) 
	{	
	 try
	 {
		
		// On veut cr�er un requeteur vers une url ditante avec un delai de garde de 60 
		// TODO Q2-04 cr�ation d'un requ�teur � la palce de null
		Requestor requestor = null ;
						
		// TODO Q2-05 cr�ation du message RequestMessage � place de null
		Message msg = null;
		
		//TODO Q2-06 envoi de la requ�te et reception du message � la place de null
		ResponseMessage responseMessage = null;
		
		thingProxy.setThingId(""+responseMessage.getQueryData().get(util.TagsLib.THING_ID));
		
		thingProxy.setFacade(""+responseMessage.getQueryData().get(util.TagsLib.FACADE));
		
		thingProxy.getThingAddresses().push
		(			
			remoteUrl
		);
		
	 }
	 catch(Exception e)
	 {
		 System.out.println("It was not possible to create the proxy. The error is " +e);
	 }
	}

	
	
	
	/**
	 * Notification d'un syst�me distant
	 * 
	 */
	protected pointcut remoteObserverNotification(Subject subject,
			Observer observer, String eventId) : 
		execution(void Observer.notify (Subject, String) )
		// on v�rifie que l'objet cible est bien un observateur et que c'est un proxy
		&& target(observer) && target(ThingProxy)
		&& args(subject,eventId);
	before(Subject subject, Observer observer, String eventId) throws Exception : 
	 remoteObserverNotification(subject, observer, eventId)
	 {
		  Thing theSubject = (Thing)subject;
		
		  ThingProxy theProxy = (ThingProxy)observer; 

		  // on r�cup�re le protocole de la premi�re adresse de l'observateur 
		  // si il y a plusieurs addresse pour un systeme on utilise la premi�re par d�faut
		  String  protocolString = HttpUtil.extractUrlProtocol((String)theProxy.getThingAddresses().firstElement()) ;
		  
		  String  observerUrl =((String)theProxy.getThingAddresses().firstElement());
		  
		  		  
		  //On creer un requeteur vers observerUrl avec un delai de garde de
		  Requestor requestor = new Requestor(observerUrl, 60);
					
		  Message msg = new 
							RequestMessage(
							new Object().hashCode(),
							theSubject.getThingId(),// identifiant	 du sujet qui veut notifier										 		
							theSubject.getAddressByProtocol(protocolString), 					// adresse du SELoca
							theSubject.getFacade(),
									   // service    ,  methode, argument, appareil
							new String[]{"observation", "notify", eventId ,""} 				// les param�tres pour l'invocation
							);
			
		  //Envoie du message
		  ResponseMessage responseMessage = (ResponseMessage)requestor.send(msg);
		  
	}
}
