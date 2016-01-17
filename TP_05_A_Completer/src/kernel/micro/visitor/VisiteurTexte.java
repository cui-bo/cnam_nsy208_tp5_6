package kernel.micro.visitor;

import java.util.Enumeration;

import kernel.micro.composite.CompositeThing;
import kernel.micro.facade.Thing;

public class VisiteurTexte implements Visitor {

	public String visiteComponent(Visitable c) throws Exception {
		return "visiteComponent";
	}

	public String visiteComposite(Visitable c) throws Exception {
		System.out.println("visiteComposite");
		StringBuffer valRet = new StringBuffer("") ;
		
		valRet.append("<thing id='" + ((Thing)c).getThingId() + "' facade='"+ ((Thing)c).getFacade() + ">");
		  
		Enumeration enumeration = ((CompositeThing)c).getChildren().elements();
		
		valRet.append  ("<children>");
		
		while(enumeration.hasMoreElements())
		{
			Thing thing = ((Thing)enumeration.nextElement());
			
			valRet.append(((Visitable)thing).accept(this)); 
		}
		valRet.append  ("</children>");
		
		valRet.append("</thing>") ;
		
		return valRet.toString();
	}

	public String visiteLeaf(Visitable l) throws Exception {
		System.out.println("visiteLeaf");
		StringBuffer valRet = new StringBuffer("") ;
		
		Thing thing= ((Thing)l);
		
		valRet.append("<thing id='" + thing.getThingId() + "' facade='"+ thing.getFacade() + "'/>");
		
		return valRet.toString();
	}

}
