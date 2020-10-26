/*
 * LoggerClient.java
 * Created on 19.10.2020
 * 
 * Copyright(c) 2020 Tobias Heller.
 * This software is the proprietary information of Tobias Heller.
*/
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.api.Logger;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Klasse zum autonomen testen der Logger Verbindung.
 * @author Tobias Heller
 */
public class LoggerClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 5050;

    public static void main(String[] args) throws UnknownHostException{

        VskLoggerSetup setup = new VskLoggerSetup();
        setup.setLoggerName("TEST");
        setup.setServerIP(HOST);
        setup.setServerPort(PORT);
        Logger logger = setup.createLogger();
        logger.info("Hello World");
    }
}
