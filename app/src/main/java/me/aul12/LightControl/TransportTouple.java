package me.aul12.LightControl;

/**
 * @author Paul Nykiel
 * @version 0.1
 */

class TransportTouple {
    String address;
    short port;
    int[] data;

    TransportTouple(int[] data, short port, String address) {
        this.address = address;
        this.port = port;
        this.data = data;
    }
}
