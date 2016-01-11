package kernel.micro.composite;

import kernel.micro.facade.LocalThing;

/**
 * @author djamel bellebia
 */
public aspect ThingCompositeAspect  extends CompositeAspect{
	
	/**
	 * Toute chose (i.e. système) est un composant
	 */
	declare parents: LocalThing implements Component;

	/**
	 * Une ChoseSimple est définie localement
	 */
	declare parents: CompositeThing extends LocalThing;

	/**
	 * Une ChoseSimple est définie localement
	 */
	declare parents: SimpleThing  extends LocalThing;
	
	/**
	 * Une ChoseComposite doit se comporter comme un composite
	 */
	declare parents: CompositeThing implements Composite;
	
	/**
	 * Une ChoseSimple doit se comporter comme une feuille
	 */
	declare parents: SimpleThing implements Leaf;

	

}
