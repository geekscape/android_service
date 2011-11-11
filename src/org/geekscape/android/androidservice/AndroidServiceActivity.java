package org.geekscape.android.androidservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.geekscape.android.androidservice.R;

public class AndroidServiceActivity extends Activity {

  public static String LOG_TAG = AndroidServiceActivity.class.getSimpleName();

  /** Called when the activity is first created. */
  @Override
  public void onCreate(
    Bundle savedInstanceState) {
  
    super.onCreate(savedInstanceState);
    Log.d(LOG_TAG, "onCreate()");
    setContentView(R.layout.main);

    startService(new Intent(AndroidService.class.getName()));
  }
}