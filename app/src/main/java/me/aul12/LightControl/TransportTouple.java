package me.aul12.LightControl;

/**
 * @author Paul Nykiel
 * @version 0.1
 */

class TransportTouple {
    String address;
    short port;
    byte command;
    TransportTouple(byte command, short port, String address) {
        this.address = address;
        this.port = port;
        this.command = command;
    }
}
