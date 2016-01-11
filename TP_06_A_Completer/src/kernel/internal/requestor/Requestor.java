package kernel.internal.requestor;

import kernel.internal.marshaller.Marshaller;
//TODO Q2-07 Compléter les imports de la classe
// import ...

import kernel.internal.strategy.Protocol;
// TODO Q2-08 Compléter les imports de la classe
// import ...

public class Requestor {
	private Protocol _protocol;

	private int _timeout;

	public Requestor(String remoteUrl, int timeout) {
		try 
		{
			// TODO Q2-09 affectation du protocol this._protocol = ... à la place de null
			 this._protocol = null ;
			this._timeout = timeout;
		} 
		catch (Exception e) {
			System.out
					.println("The requestor cannot find the protocol for the request "
							+ remoteUrl + ". The message error is " + e);
		}
	}

	/**
	 * On veux envoyer une requete sur le reseau. Le message de est transforme
	 * en un flux binaire. On retourne un message de type ResponseMessage
	 * 
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public synchronized Message send(Message msg) throws Exception {
		try {

			Message responseMessage = null;
			// TODO Q2-10 Les échanges se font en XML instancier le bon Empaqueteur à la place de null
			Marshaller marshaller =null ;

			// TODO Q2-11 envoie du message et récupération du flux retour responseStream =... à la place de null
			byte[] responseStream = null ;
			

			if (responseStream != null && responseStream.length > 4) {
				StringBuffer header = new StringBuffer();
				header.append((char) responseStream[0]);
				header.append((char) responseStream[1]);
				header.append((char) responseStream[2]);
				header.append((char) responseStream[3]);
				header.append((char) responseStream[4]);

				if (header.toString().trim().equals("<html")) {
					responseMessage = new HTTPResponseMessage(util.Func
							.dump(responseStream));
				} else {

					// TODO Q2-12 déballer la réponse
				}
	
				return responseMessage;
				
			} else {
				throw new Exception("La réponse est null");
			}

		} catch (Exception exp) {
			System.out
					.println("Error @ Requestor.send(Message msg) "
							+ exp.toString());

			throw exp;
		}
	}

	/**
	 * Envoi d'un flux binaire sur le réseau et attente de la réponse tant que
	 * le delai d'attente n'est pas arrive a terme. On retourne un tableau
	 * d'octet
	 * 
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public synchronized byte[] send(byte[] body) throws Exception {
		try {
			this._protocol.openConnection();

			byte[] response = null;

			this._protocol.send(body);

			int sleep = 0;

			while (sleep < this._timeout
					&& (response = this._protocol.receive()) == null) {
				try {
					sleep++;
					Thread.sleep(1000);
				} catch (InterruptedException ex) {

				}
			}

			return response;
		} catch (Exception exp) {
			System.out.println("Error @ Requestor.send(byte []) "
					+ exp.toString());

			throw exp;
		}

	}

	public Protocol getProtocol() {
		return this._protocol;
	}
}