/*
 * LoggerServer.java
 * Created on 19.10.2020
 *
 * Copyright(c) 2020 Silvan Wenk.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.server;

import ch.hslu.vsk.logger.common.FileHelper;
import ch.hslu.vsk.logger.server.remote.LoggerRegistry;
import ch.hslu.vsk.logger.server.remote.MessageSender;
import ch.hslu.vsk.logger.server.remote.RmiRegistry;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public final class LoggerServer {

    private static final Logger LOGGER = Logger.getLogger(LoggerServer.class.getName());

    private LoggerServer() {
    }

    /**
     * Runs the logger server application, reads the configuration and persists the logs with the specified log file
     * type.
     *
     * @param args Main arguments.
     * @throws IOException Triggered when application unexpectedly crashes.
     */
    public static void main(final String[] args) throws IOException, AlreadyBoundException {
        final int port = getPort();
        new Thread(new RmiRegistry()).start();
        final Registry reg = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
        final MessageSender sender = new MessageSender();
        final LoggerRegistry logger = new LoggerRegistry(sender);
        reg.bind("logger", logger);

        final ServerSocket listen = new ServerSocket(port);
        final ExecutorService executor = Executors.newCachedThreadPool();

        LOGGER.info("Listening on port " + port);
        while (true) {
            try {
                final Socket client = listen.accept();
                final ServerMessageHandler handler = new ServerMessageHandler(client, sender);
                executor.execute(handler);
            } catch (Exception ex) {
                LOGGER.severe(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    /**
     * Reads the port number from the loggerServerConfig file, which should be placed in the root directory of this
     * project. If no file or port specified, default port is 5050.
     *
     * @return A port number.
     */
    private static int getPort() {
        try {
            List<String> arguments = FileHelper
                    .read("." + File.separator + "loggerServerConfig", Collections.singletonList("port"));
            final String portArgument = arguments.get(0);
            return portArgument == null ? 5050 : Integer.parseInt(arguments.get(0));
        } catch (IOException ex) {
            LOGGER.warning("Could not open server configuration file: " + ex.getMessage());
            return 5050;
        }
    }
}
