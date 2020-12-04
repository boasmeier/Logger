package ch.hslu.vsk.logger.viewer.remote;

import ch.hslu.vsk.logger.common.FileHelper;
import ch.hslu.vsk.logger.common.LogMessage;
import ch.hslu.vsk.logger.common.RemoteCallbackHandler;
import ch.hslu.vsk.logger.common.RemoteLogger;
import ch.hslu.vsk.logger.viewer.LoggerViewer;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.logging.Logger;

public class LoggerClient extends UnicastRemoteObject implements RemoteCallbackHandler {

    private static final Logger LOGGER = Logger.getLogger(LoggerClient.class.getName());
    private static final String REMOTE_OBJECT = "logger";
    private LoggerViewer loggerViewer;

    public LoggerClient(final LoggerViewer loggerViewer) throws RemoteException {
        super();
        this.loggerViewer = loggerViewer;
        try {
            String host = FileHelper
                    .read("." + File.separator + "loggerViewerConfig", Collections.singletonList("host"))
                    .get(0);
            setRemoteConnection(host);
        } catch (IOException ex) {
            LOGGER.warning("Could not open server configuration file: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void handle(final LogMessage message) {
        LOGGER.info(message.toString());
        this.loggerViewer.add(message);
    }

    /**
     * Connects to registry
     * @param host IP-Address of Server.
     */
    private void setRemoteConnection(final String host) {
        try {
            Registry reg = LocateRegistry.getRegistry(host);
            RemoteLogger logger = (RemoteLogger) reg.lookup(REMOTE_OBJECT);
            logger.register(this);
        } catch (NotBoundException | RemoteException ex) {
            LOGGER.severe(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
