/**
 * LogMessage.java
 * Created on 05.10.2020
 *
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.common;

import ch.hslu.vsk.logger.api.LogLevel;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Code of Class LogMessage.
 *
 * @author Tobias Heller, Silvan Wenk
 */
public final class LogMessage implements Serializable {

    /**
     * Use american datetime format to indicate version.
     */
    private static final long serialVersionUID = 20201009L;

    private final UUID uuid;
    private final String message;
    private final String loggerName;
    private final LogLevel logLevel;
    private final Instant createdAt;
    private Instant receivedAt;

    /**
     * Constructor of class LogMessage. Automatically generates an unique UUID and 
     * stores the date and time of creation.
     * @param logLevel The LogLevel this message has.
     * @param loggerName The Name of the logger from where the Message was created.
     * @param message The actual message that should be stored.
     */
    public LogMessage(final String loggerName, final LogLevel logLevel, final String message) {
        this.uuid = UUID.randomUUID();
        this.message = message;
        this.loggerName = loggerName;
        this.logLevel = logLevel;
        this.createdAt = Instant.now();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getMessage() {
        return message;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getReceivedAt() {
        return receivedAt;
    }

    /**
     * Should be called when server receives message.
     */
    public void setReceived() {
        receivedAt = Instant.now();
    }

    @Override
    public String toString() {
        return "Message{" + "uuid=" + uuid + ", message=" + message + ", logLevel=" + logLevel + ", createdAt=" + createdAt + ", receivedAt=" + receivedAt + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, message, logLevel, createdAt, loggerName);
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LogMessage)) {
            return false;
        }
        final LogMessage other = (LogMessage) obj;
        return Objects.equals(this.uuid, other.uuid)
                && Objects.equals(this.message, other.message)
                && Objects.equals(this.logLevel, other.logLevel)
                && Objects.equals(this.createdAt, other.createdAt)
                && Objects.equals(this.loggerName, other.loggerName);
    }
}
