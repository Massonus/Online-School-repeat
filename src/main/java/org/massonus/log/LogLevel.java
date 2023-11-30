package org.massonus.log;

import lombok.Getter;

@Getter
public enum LogLevel {

    ERROR("ERROR", 3),
    WARNING("WARNING", 2),
    INFO("INFO", 1),
    DEBUG("DEBUG", 0);

    private final String field;

    private final int levelId;

    LogLevel(String field, int levelId) {
        this.field = field;
        this.levelId = levelId;
    }

}
