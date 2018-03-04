package me.aul12.LightControl;

import android.content.Context;

/**
 * @author Paul Nykiel
 * @version 0.1
 */

class LightifyDetails {
    public String username, password, serialNo;
    public boolean newStatus;
    Context context;
    LightifyDetails(String username, String password, String serialNo, boolean newStatus,
                    Context context) {
        this.username = username;
        this.password = password;
        this.serialNo = serialNo;
        this.newStatus = newStatus;
        this.context = context;
    }
}
