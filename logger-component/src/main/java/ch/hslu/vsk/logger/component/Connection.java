/*
 * Connection.java
 * Created on 19.10.2020
 * 
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.common.LogMessage;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

/**
 * Code of Class Connection.
 *
 * @author Tobias Heller
 */
public class Connection {

    private static final Logger LOG = Logger.getLogger(Connection.class.getName());
    private final BlockingQueue<LogMessage> messageQueue;
    
    /**
     * Creates a new Connection to the Logger-Server.
     * @param host IP-Address of Server.
     * @param port Port-Address of Server application.
     */
    public Connection(final String host, final int port) {
        this.messageQueue = new ArrayBlockingQueue<>(30);
        try {
            var socket = new Socket(host, port);
            var messageHandler = new MessageHandler(socket, this.messageQueue);
            new Thread(messageHandler).start();
        } catch (IOException ex) {
            LOG.severe(String.format("IOException (Socket %s:%s): %s", host, port, ex.getLocalizedMessage()));
        }
    }

    /**
     * Adds a LogMessage to the Buffer. 
     * @param message LogMessage to send.
     * @return Returns whether it was successful or not.
     */
    public boolean send(final LogMessage message) {
        LOG.info("Send: " + message);
        return messageQueue.offer(message);
    }
}