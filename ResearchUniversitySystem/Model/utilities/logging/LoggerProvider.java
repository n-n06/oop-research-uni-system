package utilities.logging;

/**
 *@author nurs 
 */

import java.util.logging.Logger;

public class LoggerProvider {
	/**
	 * Static class to provide the app's logger
	 * 
	 */
	private static final Logger logger = RotatingFileHandler.setupLogging();
	
	public static Logger getLogger() {
		return logger;
	}
}
