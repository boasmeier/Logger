package ch.hslu.vsk.logger.server;

import ch.hslu.vsk.logger.common.LogMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Logger;

public class MessageHandler implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(MessageHandler.class.getName());

    private final Socket client;
    private final StringPersistorAdapter persistor;

    /**
     * Creates a message handler, the received data is forwarded to the StringPersistorAdapter.
     * @param client Connection to the remote client.
     */
    MessageHandler(final Socket client) {
        this.client = client;
        this.persistor = new StringPersistorAdapter();
    }

    /**
     * Waits for messages, deserializes and persists them using the StringPersistorAdapter.
     */
    @Override
    public void run() {
        try (InputStream in = client.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(in)) {
            while (true) {
                final LogMessage message = (LogMessage) ois.readObject();
                message.setReceived();
                persistor.save(message);
            }
        } catch (ClassNotFoundException ex) {
            LOGGER.severe("ClassNotFoundException: " + ex.getMessage());
            ex.printStackTrace();
        } catch (SocketException ex) {
            LOGGER.info("Connection to client closed.");
        } catch (IOException ex) {
            LOGGER.severe("IOException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
