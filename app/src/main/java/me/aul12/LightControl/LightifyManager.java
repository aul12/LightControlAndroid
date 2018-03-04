package me.aul12.LightControl;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Paul Nykiel
 * @version 0.1
 */

public class LightifyManager extends AsyncTask<LightifyDetails, Double, Boolean> {
    @Override
    protected Boolean doInBackground(final LightifyDetails... loginInformations) {

        JSONObject loginJson = new JSONObject();
        try {
            loginJson.put("username", loginInformations[0].username);
            loginJson.put("password", loginInformations[0].password);
            loginJson.put("serialNumber", loginInformations[0].serialNo);
        } catch(JSONException e) {
            e.printStackTrace();
            return false;
        }
        final RequestQueue queue = Volley.newRequestQueue(loginInformations[0].context);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, "https://eu.lightify-api.org/lightify/services/session",
                        loginJson, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Lightify", response.toString());
                        try {
                            final String securityToken = response.getString("securityToken");

                            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                                    "https://eu.lightify-api.org/lightify/services/device/set?idx=1&onoff="+
                                            (loginInformations[0].newStatus?"1":"0"),
                                    null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("Lightigy", "Test");
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                }
                            }){
                                @Override
                                public Map<String, String> getHeaders() throws AuthFailureError {
                                    Map<String, String>  params = new HashMap<>();
                                    params.putAll(super.getHeaders());
                                    params.put("authorization", securityToken);
                                    return params;
                                }
                            };
                            queue.add(request);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        // Add the request to the RequestQueue.
        queue.add(jsObjRequest);

        return true;
    }
}
