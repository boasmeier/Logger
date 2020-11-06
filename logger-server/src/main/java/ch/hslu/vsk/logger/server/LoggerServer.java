/*
 * LoggerServer.java
 * Created on 19.10.2020
 * 
 * Copyright(c) 2020 Silvan Wenk.
 * This software is the proprietary information of Tobias Heller.
*/
package ch.hslu.vsk.logger.server;

import ch.hslu.vsk.logger.common.FileHelper;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class LoggerServer {
    private static final Logger LOGGER = Logger.getLogger(LoggerServer.class.getName());

    public static void main(final String[] args) throws IOException {
        List<String> arguments = FileHelper.read("." + File.separator + "loggerServerConfig", Collections.singletonList("port"));
        final int port = Integer.parseInt(arguments.get(0));
        final ServerSocket listen = new ServerSocket(port);
        final ExecutorService executor = Executors.newCachedThreadPool();
        LOGGER.info("Listening on port " + port);
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
