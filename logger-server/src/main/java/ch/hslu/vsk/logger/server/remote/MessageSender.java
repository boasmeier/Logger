package ch.hslu.vsk.logger.server.remote;

import ch.hslu.vsk.logger.common.LogMessage;
import ch.hslu.vsk.logger.common.RemoteCallbackHandler;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class MessageSender {
    private List<RemoteCallbackHandler> clients;

    public MessageSender() {
        this.clients = new ArrayList<>();
    }

    public void addReceiver(RemoteCallbackHandler client) {
        this.clients.add(client);
    }

    public void send(LogMessage message) {
        clients.forEach(c -> {
            try {
                c.handle(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }
}
