package utilities.logging;

/**
 *@author nurs
 */

import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import utilities.logging.CustomLoggingFormatter;

public class RotatingFileHandler {
	/**
	 * Class to setup rotating file handler for logging
	 * (logs are saved in files that expand to next log files
	 * when reaching a certain size limit)
	 */
	public static Logger setupLogging() {
		/**
		 *Logger setup 
		 */
		Logger logger = Logger.getLogger("SystemLogger");
		
		try {
			Logger rootLogger = Logger.getLogger("");
			rootLogger.setUseParentHandlers(false);
			
			FileHandler rotatingFileHandler = new FileHandler(
					"app.%g.log", 1024*1024*5, 5, true
			);
			rotatingFileHandler.setFormatter(new CustomLoggingFormatter());
			logger.addHandler(rotatingFileHandler);
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		return logger;
	}
}
