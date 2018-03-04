package me.aul12.LightControl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author Paul Nykiel
 * @version 0.1
 */

public class LightifyWakeUpReceiver extends BroadcastReceiver{
    public static final String ACTION_LIGHTIFY_ALARM = "me.aul12.UdpToTcpAdapter.ACTION_LIGHTIFY_ALARM";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getBundleExtra("BUNDLE");
        new LightifyManager().execute(new LightifyDetails(bundle.getString("USER"),
                bundle.getString("PASSWORD"), bundle.getString("SERIAL_NO"),true,
                context));

    }
}
