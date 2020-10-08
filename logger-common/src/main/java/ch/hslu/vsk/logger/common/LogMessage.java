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
 * @author Tobias Heller
 */
public final class LogMessage implements Serializable {
    
    //With intention version 1
    private static final long serialVersionUID = 1L;
    private final UUID uuid;
    private final String message;
    private final LogLevel logLevel;
    private final Instant createdAt;
    private Instant receivedAt;

    /**
     * Constructor of class LogMessage. Automatically generates an unique UUID and 
     * stores the date and time of creation.
     * @param logLevel The LogLevel this message has.
     * @param message The actual message that should be stored.
     */
    public LogMessage(final LogLevel logLevel, final String message) {
        this.uuid = UUID.randomUUID();
        this.message = message;
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

    public void setReceived() {
        receivedAt = Instant.now();
    }

    @Override
    public String toString() {
        return "Message{" + "uuid=" + uuid + ", message=" + message + ", logLevel=" + logLevel + ", createdAt=" + createdAt + ", receivedAt=" + receivedAt + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, message, logLevel, createdAt);
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
        return Objects.equals(this.uuid, other.uuid) && Objects.equals(this.message, other.message) && Objects.equals(this.logLevel, other.logLevel) && Objects.equals(this.createdAt, other.createdAt);
    }
}
