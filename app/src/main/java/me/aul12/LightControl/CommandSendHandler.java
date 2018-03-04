package me.aul12.LightControl;

import android.os.AsyncTask;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Paul Nykiel
 * @version 0.1
 */

public class CommandSendHandler extends AsyncTask <TransportTouple, Float, Void>{
    @Override
    protected Void doInBackground(TransportTouple... transportTouples) {
        try (DatagramSocket udpSocket = new DatagramSocket()) {
            byte cmd[] = {transportTouples[0].command};
            InetAddress address = InetAddress.getByName(transportTouples[0].address);

            DatagramPacket packet = new DatagramPacket(cmd, cmd.length,
                    address, transportTouples[0].port);
            udpSocket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
