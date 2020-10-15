package ch.hslu.vsk.logger.component;

import java.io.IOException;
import java.net.InetAddress;

class NetworkService {
    /**
     * Returns true if the given InetAddress is reachable with ICMP.
     * @param address The given InetAddress.
     * @return true or false
     */
    boolean ipAddressIsReachable(InetAddress address) {
        int returnVal = 0;
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec(String.format("ping -n 1 %s", address.getHostName()));
            returnVal = p1.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return (returnVal == 0);
    }
}
