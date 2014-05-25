package de.stefan_brenner.powerpi.app;

import android.app.Application;

/**
 * Created by stefan on 26.05.14.
 */
public class GlobalState extends Application {

    private RadioControl rc;

    public GlobalState() {
        rc = new RadioControl();
    }

    RadioControl getRadioControl() {
        return rc;
    }
}
