package kernel.micro.visitor;


public interface Visitable 
{	
	/**
	 * Accept a visitor
	 * @param v
	 * @return
	 * @throws Exception
	 */
	public String accept (Visitor v)  throws Exception;
}