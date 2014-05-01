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

    private RadioControl radioControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_pi);
        //if (savedInstanceState == null) {

        RadioControl radioControl = new RadioControl();

        Fragment schreibtischlampe = new ControlFragment(Device.Schreibtischlampe, radioControl);
        Fragment stehlampe = new ControlFragment(Device.Stehlampe, radioControl);
        Fragment ecklampe = new ControlFragment(Device.Ecklampe, radioControl);

        Fragment nachttischlampe = new ControlFragment(Device.Nachttischlampe, radioControl);
        Fragment schrank = new ControlFragment(Device.Schrank, radioControl);
        Fragment kugel = new ControlFragment(Device.Kugel, radioControl);

        FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.fragmentContainer, nachttischlampe);
        transaction.add(R.id.fragmentContainer, schrank);
        transaction.add(R.id.fragmentContainer, kugel);
        transaction.add(R.id.fragmentContainer, schreibtischlampe);
        transaction.add(R.id.fragmentContainer, stehlampe);
        transaction.add(R.id.fragmentContainer, ecklampe);
        transaction.commit();

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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ControlFragment extends Fragment implements View.OnClickListener {

        private Device device;
        private RadioControl control;

        public ControlFragment(Device device, RadioControl control) {
            this.device = device;
            this.control = control;

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_control, container, false);
            TextView label = (TextView) rootView.findViewById(R.id.controlLabel);
            label.setText(device.toString());
            Button btnTurnon = (Button) rootView.findViewById(R.id.button_turnon);
            btnTurnon.setOnClickListener(this);
            Button btnTurnoff = (Button) rootView.findViewById(R.id.button_turnoff);
            btnTurnoff.setOnClickListener(this);
            return rootView;
        }


        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.button_turnon:
                    Log.d("PowerPi", "Turn on " + device.toString());
                    control.control(device,true);
                    break;
                case R.id.button_turnoff:
                    Log.d("PowerPi", "Turn off " + device.toString());
                    control.control(device,false);
                    break;
            }

        }
    }
}
