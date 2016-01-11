package tests;

import junit.framework.TestCase;

import java.util.Hashtable;

import kernel.micro.composite.*;
import kernel.micro.facade.LocalThing;
import kernel.micro.factory.ThingFactory;
import kernel.micro.factory.CompositeThingFactory;
import kernel.micro.factory.SimpleThingFactory;
import kernel.micro.visitor.Visitable;
import kernel.micro.visitor.VisiteurTexte;

public class Tests extends TestCase {
	
	public void testComposite()
	{	
						
		// creation de COMPOSITE_02
		LocalThing COMPOSITE_02 = new CompositeThing() ;
		
		COMPOSITE_02.setThingId("COMPOSITE_02");
		
		// creation de SIMPLE_01
		LocalThing SIMPLE_01 = new SimpleThing() ;
		
		SIMPLE_01.setThingId("SIMPLE_01");
		
		//ajout de SIMPLE_01 à COMPOSITE_02
		((CompositeThing)COMPOSITE_02).addChild(SIMPLE_01.getThingId(),SIMPLE_01 ) ;
//		
		Hashtable COMPOSITE_02_enfants =  ((CompositeThing)COMPOSITE_02).getChildren();
//		
		assertEquals
		(
				COMPOSITE_02_enfants.containsKey("SIMPLE_01"),true	
		);
						
		//suppression de SIMPLE_01 de COMPOSITE_02
		((CompositeThing)COMPOSITE_02).removeChild("SIMPLE_01") ;
		
		assertEquals
		(
				!COMPOSITE_02_enfants.containsKey("SIMPLE_01"),true	
		);
	}
	/*
	public void testFactory()
	{	
		ThingFactory compositeThingFactory = new CompositeThingFactory() ;
				
		// creation de COMPOSITE_02
		LocalThing COMPOSITE_02 = (LocalThing)compositeThingFactory.createThing("COMPOSITE_02")  ;
		
		ThingFactory simpleThingFactory = new SimpleThingFactory() ;
		
		// creation de SIMPLE_01
		LocalThing SIMPLE_01 = (LocalThing)simpleThingFactory.createThing("SIMPLE_01")  ;
		
		//ajout de SIMPLE_01 à COMPOSITE_02
		((CompositeThing)COMPOSITE_02).addChild(SIMPLE_01.getThingId(),SIMPLE_01 ) ;
		
		Hashtable COMPOSITE_02_enfants =  ((CompositeThing)COMPOSITE_02).getChildren();
		
		assertEquals
		(
				COMPOSITE_02_enfants.containsKey("SIMPLE_01"),true	
		);
						
		//suppression de SIMPLE_01 de COMPOSITE_02
		((CompositeThing)COMPOSITE_02).removeChild("SIMPLE_01") ;
		
		assertEquals
		(
				!COMPOSITE_02_enfants.containsKey("SIMPLE_01"),true	
		);
	
	}
	
	public void testSingleton()
	{	
		ThingFactory compositeThingFactory = new CompositeThingFactory() ;
		// creation de COMPOSITE_02
		LocalThing COMPOSITE_02 = (LocalThing)compositeThingFactory.createThing("COMPOSITE_02")  ;
		
		LocalThing COMPOSITE_03 = (LocalThing)compositeThingFactory.createThing("COMPOSITE_03")  ;
		
		assertEquals
		(COMPOSITE_02.getThingId().equals("COMPOSITE_03"), true) ;
		
		assertEquals
		(COMPOSITE_02.getThingId().equals("COMPOSITE_03"), true) ;
		
	}
	
	public void testVisiteur()
	{	
		ThingFactory compositeThingFactory = new CompositeThingFactory() ;
		
		// creation de COMPOSITE_02
		LocalThing COMPOSITE_02 = (LocalThing)compositeThingFactory.createThing("COMPOSITE_02")  ;
		
		ThingFactory simpleThingFactory = new SimpleThingFactory() ;
		
		// creation de SIMPLE_01
		LocalThing SIMPLE_01 = (LocalThing)simpleThingFactory.createThing("SIMPLE_01")  ;
		
		//ajout de SIMPLE_01 à COMPOSITE_02
		((CompositeThing)COMPOSITE_02).addChild(SIMPLE_01.getThingId(),SIMPLE_01 ) ;
		
		try 
		{
			assertEquals
			(((Visitable)COMPOSITE_02).accept(new VisiteurTexte()).equals("<thing id='COMPOSITE_02' facade='CompositeThing><children><thing id='SIMPLE_01' facade='SimpleThing'/></children></thing>"), true) ;
		
			assertEquals
			(((Visitable)SIMPLE_01).accept(new VisiteurTexte()).equals("<thing id='SIMPLE_01' facade='SimpleThing'/>"), true) ;
			
			//System.out.println(((Visitable)SIMPLE_01).accept(new VisiteurTexte()));
			
		} catch (Exception e) {
			System.out.println("Exception @ Tests.testVisiteur " + e);
		}
		
	
	}
	
	*/
}
