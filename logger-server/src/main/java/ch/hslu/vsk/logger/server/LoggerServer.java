package ch.hslu.vsk.logger.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class LoggerServer {
    private static final Logger LOGGER = Logger.getLogger(MessageHandler.class.getName());

    public static void main(final String[] args) throws IOException {
        final ServerSocket listen = new ServerSocket(5050);
        final ExecutorService executor = Executors.newCachedThreadPool();
        while (true) {
            try {
                final Socket client = listen.accept();
                final MessageHandler handler = new MessageHandler(client);
                executor.execute(handler);
            } catch (Exception ex) {
                LOGGER.severe(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
