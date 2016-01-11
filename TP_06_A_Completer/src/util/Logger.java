package util;

public abstract class Logger 
{
	public static Logger init(String loggerClassName)
	{
		Logger valRet = null ;
		try {
			
			if(out==null)
			{
				Class cls = Class.forName(loggerClassName);
				
				valRet = (Logger)cls.newInstance();
			}
			
		} catch (Exception e) {
			System.out.println("Exception Logger.init  " + e);
			
		}
		return valRet ;
		
	}
	
	public  abstract void println(String str);
	
	public static Logger out = init("util.Func");
	
	
}
