package kernel.micro.composite;

import java.util.Hashtable;

/**
 * 
 * @author djamel bellebia
 * 
 */

public abstract aspect CompositeAspect {

	public interface Component {
		public String toString();
	}

	public interface Leaf extends Component {

	}

	// A completer un composite est un Component
	// TODO 01	=> fait
	public interface Composite extends Component {
		public void addChild(String key, Component component);
		
		public void removeChild(String key);
		
		public Hashtable getChildren();
	}

	/**
	 * Instanciation de la table qui contiendra les ref�rences vers les syts�mes
	 * composant
	 */
	// TODO 02	=> fait
	private Hashtable Composite._children = new Hashtable();

	/**
	 * Renvoie la table de ref�rence vers les composants donnant la possibilite
	 * de parcourir la structure
	 */
	// TODO 03	=> fait
	public Hashtable Composite.getChildren() {
		return this._children;
	}

	/**
	 * Ajout d'un syst�me en tant que composant identifiable via un cl� unique
	 */
	// TODO 04	=> fait
	public void Composite.addChild(String key, String value) {
		this._children.put(key, value);
	}
	

	/**
	 * Retrait d'un syst�me au moyen de sa de la table des r�f�rences.
	 */
	// TODO 05	=> fait
	public void Composite.removeChild(String key) {
		if (this._children.containsKey(key)) {
			this._children.remove(key);
			
		}
	}

}
