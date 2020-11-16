package ch.hslu.vsk.logger.server.remote;


import ch.hslu.vsk.logger.api.LogLevel;
import ch.hslu.vsk.logger.common.LogMessage;
import ch.hslu.vsk.logger.common.RemoteCallbackHandler;
import ch.hslu.vsk.logger.common.RemoteLogger;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class LoggerImpl extends UnicastRemoteObject implements RemoteLogger {
    private final List<RemoteCallbackHandler> clients;

    public LoggerImpl() throws RemoteException {
        this.clients = new ArrayList<>();
    }

    @Override
    public void register(RemoteCallbackHandler client) throws RemoteException {
        this.clients.add(client);
        client.handle(new LogMessage("Test", LogLevel.ERROR, "Hello World"));
    }
}
