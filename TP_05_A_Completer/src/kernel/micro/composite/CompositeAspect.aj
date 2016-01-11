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

	}

	/**
	 * Instanciation de la table qui contiendra les ref�rences vers les syts�mes
	 * composant
	 */
	// TODO 02

	/**
	 * Renvoie la table de ref�rence vers les composants donnant la possibilite
	 * de parcourir la structure
	 */
	// TODO 03

	/**
	 * Ajout d'un syst�me en tant que composant identifiable via un cl� unique
	 */
	// TODO 04

	/**
	 * Retrait d'un syst�me au moyen de sa de la table des r�f�rences.
	 */
	// TODO 05

}
