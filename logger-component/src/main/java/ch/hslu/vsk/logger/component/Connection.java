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
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

/**
 * Code of Class Connection.
 *
 * @author Tobias Heller
 */
final class Connection implements DisconnectionListener {

    private static final Logger LOG = Logger.getLogger(Connection.class.getName());
    private final BlockingQueue<LogMessage> messageQueue;
    private final ClientMessageHandler messageHandler;
    private final String host;
    private final int port;

    /**
     * Creates a new Connection to the Logger-Server.
     *
     * @param host IP-Address of Server.
     * @param port Port-Address of Server application.
     */
    Connection(final String host, final int port) {
        this.messageQueue = new ArrayBlockingQueue<>(30);
        this.host = host;
        this.port = port;
        messageHandler = new ClientMessageHandler(null, this.messageQueue);
        startMessageHandler();
    }

    private void startMessageHandler() {
        messageHandler.addDisconnectionListener(this);
        new Thread(messageHandler).start();
        connect(this.host, this.port);
    }

    /**
     * Adds a LogMessage to the Buffer.
     *
     * @param message LogMessage to send.
     */
    void send(final LogMessage message) {
        messageQueue.add(message);
    }

    private void connect(final String host, final int port) {
        try {
            messageHandler.setSocket(new Socket(host, port));
            LOG.info("Connected!");
        } catch (IOException ex) {
            LOG.severe(String.format("IOException (Socket %s:%s): %s", host, port, ex.getLocalizedMessage()));
        }
    }

    @Override
    public void reconnect() {
        connect(host, port);
    }
}
