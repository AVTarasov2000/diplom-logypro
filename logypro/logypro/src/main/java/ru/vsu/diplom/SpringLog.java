package ru.vsu.diplom;

import ru.vsu.diplom.annotation.LoggingType;
import ru.vsu.diplom.service.logging.Logging;

@LoggingType
public class SpringLog extends Logging {

    @Override
    public String loggingText(String loggingText) {
        return "logger.error(" + loggingText + ");";
    }

    @Override
    public String createInstanceText(String className) {
        return "org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger("+className+".class);";
    }
}
