package ro.uvt.fi.dp;

import java.util.logging.Logger;

public class AppLogger {

    private static AppLogger instance;

    private Logger logger;

    private AppLogger() {
        logger = Logger.getLogger("BankLogger");
    }

    public static AppLogger getInstance() {
        if (instance == null) {
            instance = new AppLogger();
        }
        return instance;
    }

    public void log(String message) {
        logger.info(message);
    }
}