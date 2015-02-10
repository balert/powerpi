package de.stefan_brenner.powerpi.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class ControlFragment extends Fragment implements View.OnClickListener {

    private static final String dev = null;

    private String device;
    private RadioControl control;

    public ControlFragment() {
    }

    public static final ControlFragment newInstance(String device) {
        ControlFragment f = new ControlFragment();

        final Bundle args = new Bundle(1);
        args.putString(dev, device);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PowerPiActivity activity = (PowerPiActivity) getActivity();
        GlobalState context = (GlobalState) activity.getApplicationContext();
        control = context.getRadioControl();
        device = getArguments().getString(dev);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_control, container, false);
        TextView label = (TextView) rootView.findViewById(R.id.controlLabel);
        label.setText(device);
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
            Toast.makeText(this.getActivity().getApplicationContext(),"no RadioControl",Toast.LENGTH_LONG).show();
            return;
        }

        switch(view.getId()) {
            case R.id.button_turnon:
                Log.d("PowerPi", "Turn on " + device);
                control.control(device,true);
                break;
            case R.id.button_turnoff:
                Log.d("PowerPi", "Turn off " + device);
                control.control(device,false);
                break;
        }

    }
}