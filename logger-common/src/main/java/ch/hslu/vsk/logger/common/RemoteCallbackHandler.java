package ch.hslu.vsk.logger.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCallbackHandler extends Remote {

    /**
     * Informs remote participant by callback.
     *
     * @param message Data for callback
     * @throws RemoteException if the remote execution fails.
     */
    void handle(LogMessage message) throws RemoteException;
}
