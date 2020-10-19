/*
 * MessageClient.java
 * Created on 19.10.2020
 * 
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.common.LogMessage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

/**
 * Code of Class MessageClient.
 *
 * @author Tobias Heller
 */
public class MessageClient implements Runnable {

    private static final Logger LOG = Logger.getLogger(MessageClient.class.getName());

    private int portNumber = 5050;
    private InetAddress serverAddress;
    private Socket socket;
    private BlockingQueue<LogMessage> queue;

    public MessageClient(InetAddress serverAddress, BlockingQueue<LogMessage> queue) {
        this.serverAddress = serverAddress;
        this.queue = queue;
        try {
            this.socket = new Socket(serverAddress, portNumber);
        } catch (IOException ex) {
            LOG.severe("IOException: " + ex.getLocalizedMessage());
        }
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                LogMessage message = queue.take();
                LOG.info("MessageClient send: " + message);
                ous.writeObject(message);
            }
        } catch (IOException ex) {
            LOG.severe("IOException: " + ex.getLocalizedMessage());
        } catch (InterruptedException ex) {
            LOG.severe("InterruptedException: " + ex.getLocalizedMessage());
        }
    }
}
