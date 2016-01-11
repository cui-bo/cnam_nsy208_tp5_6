package kernel.internal.strategy;

import util.HttpUtil;

/**
 * 
 * @author djamel bellebia
 * 
 */
public aspect ProtocolFactoryAspect {
	// TODO Q1-01 dire de mani�re declarative que la classe ProtocolFactoryImpl r�alise l'interface ProtocolFactory
	
	/**
	 * Cr�ation d'un protocle en lui affectant l'url pour la connexion distante
	 */
	public Protocol ProtocolFactoryImpl.createProtocol(String url)
			throws Exception {
		try {

			
			String protocolString = "";
			// determination du protocole � partir de l'URL (HTTP, btr2cap, rmi)
			protocolString = HttpUtil.extractUrlProtocol(url);
			
			//TODO Q1-02 cr�ation  de l'instance du protocole en utilisant la m�thode forName de la classe Class
			Class cls = null;
			
			Protocol protocol = (Protocol) cls.newInstance();

			protocol.setRemoteUrl(url);

			return protocol;
		} catch (Exception e) {
			System.out
					.println("Execeptio @ ProtocolFactoryAspect.ProtocolFactoryImpl.createProtocol");
			throw e;
		}

	}

}
