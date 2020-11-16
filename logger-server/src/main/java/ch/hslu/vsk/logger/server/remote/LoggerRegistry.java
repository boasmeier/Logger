package ch.hslu.vsk.logger.server.remote;


import ch.hslu.vsk.logger.api.LogLevel;
import ch.hslu.vsk.logger.common.LogMessage;
import ch.hslu.vsk.logger.common.RemoteCallbackHandler;
import ch.hslu.vsk.logger.common.RemoteLogger;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoggerRegistry extends UnicastRemoteObject implements RemoteLogger {

    private MessageSender sender;

    public LoggerRegistry(MessageSender sender) throws RemoteException {
        this.sender = sender;
    }

    @Override
    public void register(RemoteCallbackHandler client) throws RemoteException {
        this.sender.addReceiver(client);
        client.handle(new LogMessage("Test", LogLevel.ERROR, "Hello World"));
    }
}
