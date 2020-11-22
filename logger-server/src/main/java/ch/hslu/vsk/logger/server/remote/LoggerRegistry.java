package ch.hslu.vsk.logger.server.remote;

import ch.hslu.vsk.logger.api.LogLevel;
import ch.hslu.vsk.logger.common.LogMessage;
import ch.hslu.vsk.logger.common.RemoteCallbackHandler;
import ch.hslu.vsk.logger.common.RemoteLogger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public final class LoggerRegistry extends UnicastRemoteObject implements RemoteLogger {

    private final MessageSender sender;

    public LoggerRegistry(final MessageSender sender) throws RemoteException {
        this.sender = sender;
    }

    @Override
    public void register(final RemoteCallbackHandler client) throws RemoteException {
        this.sender.addReceiver(client);
        client.handle(new LogMessage("Test", LogLevel.ERROR, "Hello World"));
    }
}
