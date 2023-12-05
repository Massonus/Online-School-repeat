package org.massonus.log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoggerTest {

    private Logger target;

    @BeforeEach
    void setUp() {
        target = new Logger("ClassName");
    }

    @Test
    void info() {
        Log objectCreated = target.info("object created");
        String logLevel = objectCreated.getLogLevel();

        assertThat(logLevel)
                .isEqualTo("INFO");


    }
}