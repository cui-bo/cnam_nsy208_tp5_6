package kernel.micro.visitor;


public interface  Visitor 
{
	/**
	 * visiteComponent
	 * @param Componenent Visitable 
	 * @return
	 * @throws Exception
	 */
	public  String visiteComponent(Visitable c) throws Exception;
	/**
	 * visiteLeaf
	 * @param leaf: Visitable 
	 * @return
	 * @throws Exception
	 */
	public  String  visiteLeaf(Visitable l) throws Exception;
	
	// A completer methode pour la visite d'une chose composite
	// TODO 09	=> fait
	public String visiteComposite(Visitable c) throws Exception;
	
}
