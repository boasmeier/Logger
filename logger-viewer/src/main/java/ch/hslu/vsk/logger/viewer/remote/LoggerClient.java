package ch.hslu.vsk.logger.viewer.remote;

import ch.hslu.vsk.logger.common.FileHelper;
import ch.hslu.vsk.logger.common.LogMessage;
import ch.hslu.vsk.logger.common.RemoteCallbackHandler;
import ch.hslu.vsk.logger.common.RemoteLogger;

import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class LoggerClient extends UnicastRemoteObject implements RemoteCallbackHandler {
    private static final Logger LOGGER = Logger.getLogger(LoggerClient.class.getName());

    private static String host;
    private static RemoteLogger logger;

    public LoggerClient() throws RemoteException {
        super();
        this.configure();
    }

    @Override
    public void handle(LogMessage message) throws RemoteException {
        LOGGER.info(message.toString());
    }

    /**
     * Reads the port number from the loggerServerConfig file, which should be placed in the root directory of this
     * project. If no file or port specified, default port is 5050.
     *
     * @return A port number.
     */
    private void configure() {
        try {
            List<String> arguments = FileHelper
                    .read("." + File.separator + "loggerViewerConfig", Arrays.asList("host", "rmi_object"));
            host = arguments.get(0);
            String remoteObjectString = arguments.get(1);
            Registry reg = LocateRegistry.getRegistry(host);
            logger = (RemoteLogger) reg.lookup(remoteObjectString);
            logger.register(this);
        } catch (NotBoundException | RemoteException ex) {
            LOGGER.severe(ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            LOGGER.warning("Could not open server configuration file: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
