package ch.hslu.vsk.logger.server.remote;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Logger;

/**
 * Registry-Tool. Es muss als erstes ausgeführt werden.
 */
public final class RmiRegistry implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(RmiRegistry.class.getName());

    @Override
    public void run() {
        final Registry reg;
        try {
            reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            LOGGER.info(reg.toString());
            LOGGER.info("RMI Registry started...\n");
            Thread.currentThread().join();
        } catch (RemoteException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}