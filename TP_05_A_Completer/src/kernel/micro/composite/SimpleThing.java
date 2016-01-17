
package kernel.micro.composite;

import kernel.micro.facade.LocalThing;
import kernel.micro.visitor.Visitable;
import kernel.micro.visitor.Visitor;

/**
 * 
 * @author djamel bellebia
 *
 */
public class SimpleThing extends LocalThing implements Visitable {

	public SimpleThing()
	{
		super();
	}
	
	public String accept(Visitor v) {
		try {
			return v.visiteLeaf(this);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
