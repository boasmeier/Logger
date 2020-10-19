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
    private MessageHandler messageHandler;
    private Socket socket;
    private final int portNumber = 5050;
    private final BlockingQueue<LogMessage> messageQueue;
    
    /**
     * Creates a new Connection to the Logger-Server.
     * @param serverAddress IP-Address of Server.
     */
    public Connection(final InetAddress serverAddress) {
        this.messageQueue = new ArrayBlockingQueue<>(30);
        try {
            this.socket = new Socket(serverAddress, portNumber);
            this.messageHandler = new MessageHandler(this.socket, this.messageQueue);
            new Thread(this.messageHandler).start();
        } catch (IOException ex) {
            LOG.severe("IOException: " + ex.getLocalizedMessage());
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
