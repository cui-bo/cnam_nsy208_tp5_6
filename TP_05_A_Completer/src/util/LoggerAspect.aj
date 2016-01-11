package util;

public privileged aspect LoggerAspect {

	protected synchronized pointcut initLogger(Logger logger) : 
	get (* *.out) && target(logger);

	before(Logger logger):initLogger(logger)
	{

		try {
			Logger.init("util.Func");
		} catch (Exception e) {
			System.out
					.println("Exception @ LoggerAspect . before(Logger logger):initLogger(logger)"
							+ e);
		}

	}
}
