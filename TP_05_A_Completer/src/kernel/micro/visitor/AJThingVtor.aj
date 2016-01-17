package kernel.micro.visitor;
import org.aspectj.lang.annotation.DeclareAnnotation;
public aspect AJThingVtor
{
	declare parents: Thing implements  Visitable;
	
	public String Thing.accept(Visitor v)  throws Exception
	{
		return v.visiteComponent(this) ;
	}
	
	// A completer viste d'une chose simple
	//TODO 10	=> fait
	declare parents: kernel.micro.composite.SimpleThing implements Visitable;
	
	public String kernel.micro.composite.SimpleThing.accept(Visitor st) throws Exception {
		return st.visiteLeaf(this);
	}
	
	// A completer viste d'une chose composite
	// TODO 11	=> fait
	declare parents: kernel.micro.composite.CompositeThing implements Visitable;
	
	public String kernel.micro.composite.CompositeThing.accept(Visitor ct) throws Exception {
		return ct.visiteComposite(this);
	}
}
