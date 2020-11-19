/*
 * Copyright(c) 2020 Boas Meier.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.common;

import ch.hslu.vsk.logger.api.LogLevel;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 *
 * @author boasm
 */
class ObjectHelperTest {

    /**
     * Test of objectToString and stringToObject method, of class ObjectHelper.
     */
    @Test
    void testObjectToStringAndBack() {
        LogMessage msg = new LogMessage("TestName", LogLevel.INFO, "TestPayload");
        String objAsString = ObjectHelper.objectToString(msg);
        assertThat(Objects.equals(ObjectHelper.stringToObject(objAsString), msg)).isTrue();
    }
}
