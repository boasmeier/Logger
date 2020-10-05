/**
 * Message.java
 * Created on 05.10.2020
 *
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.common;

import ch.hslu.vsk.logger.api.LogLevel;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Code of Class Message.
 *
 * @author Tobias Heller
 */
public class Message {

    private final UUID uuid;
    private final String content;
    //TODO (hellerto): Replace with real implementation
    private final LogLevel logLevel;
    private final Instant createdAt;
    private Instant receivedAt;

    public Message(LogLevel logLevel, String content) {
        this.uuid = UUID.randomUUID();
        this.content = content;
        this.logLevel = logLevel;
        this.createdAt = Instant.now();
    }
    
    public UUID getUuid() {
        return uuid;
    }

    public String getContent() {
        return content;
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
    
    public void setReceived(){
        receivedAt = Instant.now();
    }

    @Override
    public String toString() {
        return "Message{" + "uuid=" + uuid + ", content=" + content + ", logLevel=" + logLevel + ", createdAt=" + createdAt + ", receivedAt=" + receivedAt + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        final Message other = (Message) obj;
        if (!Objects.equals(this.uuid, other.uuid)) {
            return false;
        }
        return true;
    }
}
