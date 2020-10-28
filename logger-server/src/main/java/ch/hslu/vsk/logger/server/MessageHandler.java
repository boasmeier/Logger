package ch.hslu.vsk.logger.server;

import ch.hslu.vsk.logger.common.LogMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class MessageHandler implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(MessageHandler.class.getName());

    private final Socket client;

    /**
     * Erzeugt einen MessageHandler, die Empfangenen Daten werden an den StringPersistor weitergeleitet.
     * @param client Verbindung zum entfernten Client.
     */
    public MessageHandler(final Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try (InputStream in = client.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(in)) {
            while(true) {
                final LogMessage message = (LogMessage) ois.readObject();
                message.setReceived();
                LOGGER.info(message.toString());
                final var persistor = new StringPersistorAdapter();
                persistor.save(message);
            }
        } catch (IOException | ClassNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
