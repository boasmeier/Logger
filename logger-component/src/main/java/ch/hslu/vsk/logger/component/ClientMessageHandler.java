/*
 * ClientMessageHandler.java
 * Created on 19.10.2020
 *
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.common.LogMessage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

/**
 * Code of Class ClientMessageHandler.
 *
 * @author Tobias Heller
 */
public class ClientMessageHandler implements Runnable {

    private static final Logger LOG = Logger.getLogger(ClientMessageHandler.class.getName());
    private final ClientLogPersistor persistor;

    private Socket socket;
    boolean isConnected = true;
    private final BlockingQueue<LogMessage> messageQueue;

    private LogMessage message = null;
    private ObjectOutputStream ous = null;

    /**
     * Konstruktor der Klasse Message Handler.
     *
     * @param socket Socket über welchen die Kommunikation zum Server gehen soll.
     * @param queue BlockingQueue, welche die zu sendenden Messages enthält.
     */
    public ClientMessageHandler(final Socket socket, final BlockingQueue<LogMessage> queue) {
        this.socket = socket;
        this.messageQueue = queue;
        this.persistor = new ClientStringPersistorAdapter();
    }

    @Override
    public void run() {
        setObjectOutputStream();
        while (true) {
            try {
                message = messageQueue.take();
            } catch (InterruptedException ex) {
                LOG.severe("InterruptedException: " + ex.getLocalizedMessage());
            }
            if (isConnected) {
                send(message);
            } else {
                LOG.info("Persist in File");
                persistor.save(message);
                reconnect();
            }
        }
    }

    private void reconnect() {
        try {
            socket = new Socket(socket.getInetAddress(), socket.getPort());
            setObjectOutputStream();
            LOG.info("Reconnected!");
            isConnected = true;
            sendMissedMessages();
        } catch (IOException ex) {
            LOG.severe("Reconnection failed: " + ex.getLocalizedMessage());
        }
    }

    private void setObjectOutputStream() {
        try {
            ous = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            LOG.severe("IOException while opening input and output strem: " + ex.getLocalizedMessage());
        }
    }

    private void sendMissedMessages() {
        Queue<LogMessage> missedMessages = persistor.get();
        while (!missedMessages.isEmpty()) {
            send(missedMessages.remove());
        }
    }

    private void send(final LogMessage message) {
        try {
            ous.writeObject(message);
            LOG.info("Send: " + message);
        } catch (IOException ex) {
            LOG.severe("IOException while sending Log-Message: " + ex.getLocalizedMessage());
            LOG.info("Persist in File");
            persistor.save(message);
            isConnected = false;
        }
    }
}
