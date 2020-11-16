package ch.hslu.vsk.logger.common;

import ch.hslu.vsk.logger.common.LogMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCallbackHandler extends Remote {

    /**
     * Informs remote participant by callback.
     *
     * @param message Data for callback
     * @throws RemoteException if the remote execution fails.
     */
    public void handle(LogMessage message) throws RemoteException;
}
