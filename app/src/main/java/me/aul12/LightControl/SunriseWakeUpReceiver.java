package me.aul12.LightControl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


/**
 * @author Paul Nykiel
 * @version 0.1
 */

public class SunriseWakeUpReceiver extends BroadcastReceiver {
    public static final String ACTION_SUNRISE_ALARM = "me.aul12.UdpToTcpAdapter.ACTION_SUNRISE_ALARM";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getBundleExtra("BUNDLE");
        short port = bundle.getShort("PORT");
        String address = bundle.getString("IP");
        int []command = {1,0,0,0};
        new CommandSendHandler().execute(new TransportTouple(command, port, address));
    }
}
