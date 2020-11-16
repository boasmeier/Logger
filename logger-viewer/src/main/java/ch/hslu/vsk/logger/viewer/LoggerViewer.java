package ch.hslu.vsk.logger.viewer;

import ch.hslu.vsk.logger.viewer.remote.LoggerClient;

import java.rmi.RemoteException;

public class LoggerViewer {
    /**
     * Runs the logger viewer application.
     * @param args
     */
    public static void main(final String[] args) throws RemoteException {
        final LoggerClient client = new LoggerClient();
        // TODO (Tom): Create GUI
    }
}
