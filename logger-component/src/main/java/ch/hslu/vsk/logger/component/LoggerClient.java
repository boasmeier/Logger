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
 * Code of Class LoggerClient.
 * @author Tobias Heller
 */
public class LoggerClient {
    public static void main(String[] args) throws UnknownHostException{
        VskLoggerSetup setup = new VskLoggerSetup();
        setup.setLoggerName("Hallo");
        setup.setLoggerServer(InetAddress.getByName("10.155.224.95"));
        Logger logger = setup.createLogger();
        logger.info("Hallo Silvan");
    }
}
