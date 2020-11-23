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
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

/**
 * Code of Class ClientMessageHandler.
 *
 * @author Tobias Heller
 */
public final class ClientMessageHandler implements Runnable {

    private static final Logger LOG = Logger.getLogger(ClientMessageHandler.class.getName());
    private final ClientLogPersistor persistor;

    private Socket socket;
    private boolean isConnected;
    private final BlockingQueue<LogMessage> messageQueue;

    private LogMessage message = null;
    private ObjectOutputStream ous = null;

    private final List<DisconnectionListener> listeners = new ArrayList<>();

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
        this.isConnected = this.socket != null;
    }

    @Override
    public void run() {
        while (true) {
            try {
                message = messageQueue.take();
            } catch (InterruptedException ex) {
                LOG.severe("InterruptedException: " + ex.getLocalizedMessage());
            }
            if (isConnected) {
                send(message);
            } else {
                persistor.save(message);
                this.fireDisconnectionEvent();
            }
        }
    }

    public void setSocket(final Socket socket) {
        this.socket = socket;
        setObjectOutputStream();
        sendMissedMessages();
        isConnected = true;
    }

    public void addDisconnectionListener(DisconnectionListener listener) {
        if (listener != null) {
            this.listeners.add(listener);
        } else {
            throw new NullPointerException("Listener mustn't be null!");
        }
    }

    public void removeDisconnectionListener(DisconnectionListener listener) {
        if (listener != null) {
            this.listeners.remove(listener);
        } else {
            throw new NullPointerException("Listener mustn't be null!");
        }
    }

    private void fireDisconnectionEvent() {
        this.listeners.forEach((listener) -> {
            listener.reconnect();
        });
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

    private void send(final LogMessage msg) {
        try {
            ous.writeObject(msg);
            LOG.info("Send: " + msg);
        } catch (IOException ex) {
            LOG.severe("IOException while sending Log-Message: " + ex.getLocalizedMessage());
            LOG.info("Persist in file");
            persistor.save(msg);
            isConnected = false;
        }
    }
}
