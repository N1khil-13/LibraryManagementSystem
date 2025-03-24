package logger;

import java.util.*;
import java.util.logging.*;

// Logger setup
public class LibraryLogger {
    private static final Logger logger = Logger.getLogger(LibraryLogger.class.getName());
    static {
        if (logger.getHandlers().length == 0) {
            ConsoleHandler handler = new ConsoleHandler();
            handler.setLevel(Level.ALL);
            logger.addHandler(handler);
            logger.setLevel(Level.ALL);
            logger.setUseParentHandlers(false); // Prevents duplicate logs from parent handlers
        }
    }


    public static void log(Level level, String message) {
        logger.log(level, message);
    }
}
