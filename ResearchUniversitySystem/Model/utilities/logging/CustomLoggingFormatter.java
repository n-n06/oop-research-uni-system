package utilities.logging;

import java.util.logging.SimpleFormatter;
import java.util.logging.LogRecord;

/**
 *@author nurs 
 */

public class CustomLoggingFormatter extends SimpleFormatter {
	/**
	 * Pretty format for logging
	 */
	@Override
	public String format(LogRecord record) {
		return String.format(
				"[%1$tF %1$tT] [%2$s] [%3$s] %4$s %n",
				record.getMillis(), //time
				record.getLevel(),	//log level
				record.getLoggerName(),	//name - may delete later)
				record.getMessage()	//log message
		);
	}
}
