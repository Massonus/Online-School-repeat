package org.massonus.log;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    private final String className;

    public final static List<Log> logStorage = new ArrayList<>();

    public Logger(String className) {
        this.className = className;
    }

    public void error(String message, Throwable throwable) {
        saveLog(message, throwable, LogLevel.ERROR);
    }

    public void error(String message) {
        saveLog(message, null, LogLevel.ERROR);
    }

    public void warning(String message, Throwable throwable) {
        saveLog(message, throwable, LogLevel.WARNING);
    }

    public void warning(String message) {
        saveLog(message, null, LogLevel.WARNING);
    }

    public Log info(String message) {
        return saveLog(message, null, LogLevel.INFO);
    }

    public void debug(String message) {
        saveLog(message, null, LogLevel.DEBUG);
    }

    private Log saveLog(String message, Throwable throwable, LogLevel logLevel) {
        LogService logService = new LogService();
        Log log;
        if (throwable == null) {
            log = new Log(className, logLevel.getField(), message);
        } else {
            log = new Log(className, logLevel.getField(), message, throwable.toString());
        }
        logStorage.add(log);
        logService.writeLogs(log);
        return log;
    }
}
