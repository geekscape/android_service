package org.geekscape.android.androidservice;

import org.geekscape.android.androidservice.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class AndroidServiceActivity extends Activity {
  public static String DEBUG_TAG = "AndroidService";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i(DEBUG_TAG, "Log() test");
    }
}
