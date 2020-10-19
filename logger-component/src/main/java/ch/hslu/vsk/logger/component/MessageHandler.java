/*
 * MessageHandler.java
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
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

/**
 * Code of Class MessageHandler.
 *
 * @author Tobias Heller
 */
public class MessageHandler implements Runnable {

    private static final Logger LOG = Logger.getLogger(MessageHandler.class.getName());

    private final Socket socket;
    private final BlockingQueue<LogMessage> messageQueue;

    /**
     * Konstruktor der Klasse Message Handler.
     * @param socket Socket über welchen die Kommunikation zum Server gehen soll.
     * @param queue BlockingQueue, welche die zu sendenden Messages enthält.
     */
    public MessageHandler(final Socket socket, final BlockingQueue<LogMessage> queue) {
        this.socket = socket;
        this.messageQueue = queue;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                LogMessage message = messageQueue.take();
                LOG.info("Send: " + message);
                ous.writeObject(message);
            }
        } catch (IOException ex) {
            LOG.severe("IOException: " + ex.getLocalizedMessage());
        } catch (InterruptedException ex) {
            LOG.severe("InterruptedException: " + ex.getLocalizedMessage());
        }
    }
}
