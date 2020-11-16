/*
 * Copyright(c) 2020 Boas Meier.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.common;

import ch.hslu.vsk.logger.api.LogLevel;
import ch.hslu.vsk.logger.common.LogMessage;
import static org.assertj.core.api.Assertions.assertThat;

import ch.hslu.vsk.logger.common.ObjectHelper;
import org.junit.jupiter.api.Test;

/**
 *
 * @author boasm
 */
public class ObjectHelperTest {

    /**
     * Test of objectToString and stringToObject method, of class ObjectHelper.
     */
    @Test
    public void testObjectToStringAndBack() {
        LogMessage msg = new LogMessage("TestName", LogLevel.INFO, "TestPayload");
        String objAsString = ObjectHelper.objectToString(msg);
        assertThat(ObjectHelper.stringToObject(objAsString).equals(msg)).isTrue();
    }
}
