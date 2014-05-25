package de.stefan_brenner.powerpi.app;

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

public class PowerPiActivity extends ActionBarActivity {

    public PowerPiActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_pi);

        if (savedInstanceState == null) {

            Fragment schreibtischlampe = ControlFragment.newInstance(Device.Schreibtischlampe);
            Fragment stehlampe = ControlFragment.newInstance(Device.Stehlampe);
            Fragment ecklampe = ControlFragment.newInstance(Device.Ecklampe);

            Fragment nachttischlampe = ControlFragment.newInstance(Device.Nachttischlampe);
            Fragment schrank = ControlFragment.newInstance(Device.Schrank);
            Fragment kugel = ControlFragment.newInstance(Device.Kugel);

            Fragment alles = ControlFragment.newInstance(Device.Alles);

            FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.fragmentContainer, nachttischlampe);
            transaction.add(R.id.fragmentContainer, schrank);
            transaction.add(R.id.fragmentContainer, kugel);
            transaction.add(R.id.fragmentContainer, schreibtischlampe);
            transaction.add(R.id.fragmentContainer, stehlampe);
            transaction.add(R.id.fragmentContainer, ecklampe);
            transaction.add(R.id.fragmentContainer, alles);
            transaction.commit();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.power_pi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
