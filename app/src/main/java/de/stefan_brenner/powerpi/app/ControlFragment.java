package de.stefan_brenner.powerpi.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class ControlFragment extends Fragment implements View.OnClickListener {

    private static final String EXTRA_DEVICE = null;

    private Device device;
    private RadioControl control;

    public ControlFragment() {
    }

    public static final ControlFragment newInstance(Device device) {
        ControlFragment f = new ControlFragment();

        final Bundle args = new Bundle(1);
        args.putString(EXTRA_DEVICE, device.toString());
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PowerPiActivity activity = (PowerPiActivity) getActivity();
        GlobalState context = (GlobalState) activity.getApplicationContext();
        control = context.getRadioControl();
        device = Device.valueOf(getArguments().getString(EXTRA_DEVICE));
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
        if(control == null) {
            System.out.println("no RadioControl");
            return;
        }

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