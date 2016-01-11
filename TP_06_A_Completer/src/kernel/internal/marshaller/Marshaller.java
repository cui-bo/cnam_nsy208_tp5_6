package kernel.internal.marshaller;

import kernel.internal.requestor.Message;

public interface Marshaller 
{
	
	/**
	 * Transcripetion d'un Objet message en un flux binaire 
	 * pour ecriture sur le canal de sortie
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public  byte[] marshall(Message msg) throws Exception;
	
	/**
	 * Lecture depuis le canal d'entrée d'un flux binaire et 
	 * transcription d'un du flux en un objet de type message
	 * @param stream
	 * @return
	 * @throws Exception
	 */
	public  Message unmarshall (byte [] stream) throws Exception;
}