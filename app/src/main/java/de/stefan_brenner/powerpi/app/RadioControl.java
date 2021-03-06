package de.stefan_brenner.powerpi.app;

import android.os.AsyncTask;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by stefan on 01.05.14.
 */
public class RadioControl {

    public boolean control(String device, boolean state) {
        String sstate = state ? "1" : "0";

        String messageStr="setsocket:"+ device + ":" + sstate;
        new ControlTask().execute(messageStr);
        return true;

    }

    class ControlTask extends AsyncTask<String, Void, Boolean> {

        private Exception exception;

        protected Boolean doInBackground(String... strings) {
            try {
                DatagramSocket socket = new DatagramSocket();
                String hostname = PowerPiActivity.powerPiHost;
                InetAddress piAddress = InetAddress.getByName(hostname);

                String messageStr = strings[0];
                Log.d("RadioControl", messageStr);

                int port = PowerPiActivity.powerPiPort;
                DatagramPacket p = new DatagramPacket(messageStr.getBytes(), messageStr.length(),piAddress,port);
                socket.send(p);
                return true;
            } catch (Exception e) {
                this.exception = e;
                return false;
            }
        }

        protected void onPostExecute(boolean result) {
            // TODO: check this.exception
            // TODO: do something with the feed
        }
    }
}
