package me.aul12.LightControl;

import android.os.AsyncTask;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Paul Nykiel
 * @version 0.1
 */

public class CommandSendHandler extends AsyncTask <TransportTouple, Float, Void>{
    private static byte uid;

    @Override
    protected Void doInBackground(TransportTouple... transportTouples) {
        try (Socket socket = new Socket(transportTouples[0].address, transportTouples[0].port)) {
            int[] channelData = transportTouples[0].data;

            byte[] data = new byte[11];
            data[0] = (byte) 0xC9;
            data[1] = uid++;
            data[2] = 0;
            data[3] = 0b00010101;

            int dataSize = 10 * 4;
            dataSize /= 8;

            int div = 0, mod = 0;
            for(int c=0; c<dataSize; ++c) {
                data[c + 4] = 0;
                for (int b = 0; b < 8; ++b) {
                    int bit = (channelData[div] >>> mod) & 1;
                    if (++mod >= 10) {
                        ++div;
                        mod = 0;
                    }
                    data[c + 4] |= bit << b;
                }
            }

            data[9] = 0;
            data[10] = (byte) 0x93;

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
