package kernel.micro.singleton;

import java.util.Hashtable;

/**
 * On s'assure que la creation d'un de l'abstraction du systm�me est cr��e
 * qu'une seule fois
 * 
 */
public aspect SingletonAspect {
	private Hashtable _singletons = new Hashtable();

	
	// point de jonction � la cr�ation d'une nouvelle instance SimpleThing d'une chose
	// locale
	pointcut newLocalSimpleThing() : 
	call(kernel.micro.composite.SimpleThing.new(..)) ;

	Object around()
	: newLocalSimpleThing() 
	{
		Object newThing = null;
		try {

			String key = ""
					+ thisJoinPoint.getSignature().getDeclaringTypeName()
							.hashCode();
			
			newThing = _singletons.get(key);
			
			if (newThing == null) {
				
				newThing = proceed();
				
				_singletons.put(key, newThing);
				
			}
		} catch (Exception e) {
			System.out.println("Exception SingletonAspect @ around() 	: newLocalThing() " + e);
			
		}
		return newThing;
	}

	// point de jonction � la cr�ation d'une nouvelle instance CompositeThing d'une chose
	// locale
	// A completer
	//TODO 08	=> fait
	pointcut newLocalCompositeThing() : 
		call(kernel.micro.composite.CompositeThing.new(..)) ;

		Object around()
		: newLocalCompositeThing() 
		{
			Object newThing = null;
			try {

				String key = ""
						+ thisJoinPoint.getSignature().getDeclaringTypeName()
								.hashCode();
				
				newThing = _singletons.get(key);
				
				if (newThing == null) {
					
					newThing = proceed();
					
					_singletons.put(key, newThing);
					
				}
			} catch (Exception e) {
				System.out.println("Exception SingletonAspect @ around() 	: newLocalThing() " + e);
				
			}
			return newThing;
		}

}
