/*
 * MessageHandler.java
 * Created on 19.10.2020
 * 
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.common.LogMessage;
import java.net.InetAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

/**
 * Code of Class MessageHandler.
 *
 * @author Tobias Heller
 */
public class MessageHandler {
    private static final Logger LOG = Logger.getLogger(MessageClient.class.getName());

    private InetAddress serverAddress;
    private MessageClient messageClient;
    private final BlockingQueue<LogMessage> messageQueue;

    public MessageHandler(final InetAddress serverAddress) {
        this.messageQueue = new ArrayBlockingQueue<LogMessage>(30);
        this.serverAddress = serverAddress;
        this.messageClient = new MessageClient(this.serverAddress, this.messageQueue);
    }

    public boolean send(final LogMessage message) {
        LOG.info("MessageHandler send: " + message);
        return messageQueue.offer(message);
    }
}
