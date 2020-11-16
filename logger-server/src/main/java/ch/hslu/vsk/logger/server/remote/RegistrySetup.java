package ch.hslu.vsk.logger.server.remote;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Logger;

/**
 * Registry-Tool. Es muss als erstes ausgeführt werden.
 */
public final class RegistrySetup {

    private static final Logger LOGGER = Logger.getLogger(RegistrySetup.class.getName());

    /**
     * Startpunkt der RMI Registry.
     *
     * @param args werden nicht benötigt/berücksichtigt.
     * @throws RemoteException wenn in der Remote Methode eine Ausnahme
     * passiert.
     * @throws InterruptedException wenn das Tool interrupted würde.
     */
    public static void main(final String[] args) throws RemoteException, InterruptedException {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = Registry.REGISTRY_PORT;
        }
        final Registry reg = LocateRegistry.createRegistry(port);
        LOGGER.info(reg.toString());
        LOGGER.info("o.k.");
        Thread.currentThread().join();
    }
}