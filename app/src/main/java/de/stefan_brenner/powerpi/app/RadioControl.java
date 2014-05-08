package de.stefan_brenner.powerpi.app;

import android.os.AsyncTask;
import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by stefan on 01.05.14.
 */
public class RadioControl {

    public boolean control(Device device, boolean state) {
        String sstate = state ? "1" : "0";
        if(device.equals(Device.Alles)) {
            for(Device d : Device.values()) {
                String messageStr = "setsocket:" + d.toString() + ":" + sstate;
                new ControlTask().execute(messageStr);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return true;
        } else {
            String messageStr="setsocket:"+ device.toString() + ":" + sstate;
            new ControlTask().execute(messageStr);
            return true;
        }
    }

    class ControlTask extends AsyncTask<String, Void, Boolean> {

        private Exception exception;

        protected Boolean doInBackground(String... strings) {
            try {
                DatagramSocket socket = new DatagramSocket();
                InetAddress piAddress = InetAddress.getByName("192.168.178.25");

                String messageStr = strings[0];

                Log.d("RadioControl", messageStr);

                DatagramPacket p = new DatagramPacket(messageStr.getBytes(), messageStr.length(),piAddress,6677);
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
