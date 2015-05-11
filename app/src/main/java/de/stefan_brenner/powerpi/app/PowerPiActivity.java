package de.stefan_brenner.powerpi.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PowerPiActivity extends ActionBarActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences prefs;
    public static String powerPiHost;
    public static int powerPiPort;
    public static String[] powerPiObjects;

    public PowerPiActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        setContentView(R.layout.activity_power_pi);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
        powerPiHost = prefs.getString(getString(R.string.PREF_HOSTNAME), "");
        powerPiPort = Integer.parseInt(prefs.getString(getString(R.string.PREF_PORT), "0"));
        powerPiObjects = prefs.getString(getString(R.string.PREF_OBJECTS), "").split(",");

        Log.d("pref_hostname",powerPiHost);

        FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();

        for(String object : powerPiObjects) {
            Fragment fragment = ControlFragment.newInstance(object.trim());
            transaction.add(R.id.fragmentContainer, fragment);
        }

        //transaction.add(R.id.fragmentContainer, ControlFragment.newInstance("All"));

        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.power_pi, menu);
        return true;
    }

    public void openSettings(MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d("pref changed","");
        if(key.equals(getString(R.string.PREF_HOSTNAME))){
            powerPiHost = prefs.getString(getString(R.string.PREF_HOSTNAME),"");
        } else if(key.equals(getString(R.string.PREF_PORT))) {
            powerPiPort = Integer.parseInt(prefs.getString(getString(R.string.PREF_PORT), "0"));
        } else if(key.equals(getString(R.string.PREF_OBJECTS))) {
            powerPiObjects = prefs.getString(getString(R.string.PREF_OBJECTS), "").split(",");
        }
    }
}
