/**
 * Please do not remove the following notices.
 * Copyright (c) 2011 by Geekscape Pty. Ltd.
 * License: AGPLv3 http://geekscape.org/static/aiko_license.html
 */ 

package org.geekscape.android.androidservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import org.geekscape.android.androidservice.R;

public class AndroidServiceActivity extends Activity {

  public static String LOG_TAG = AndroidServiceActivity.class.getSimpleName();

  private static Intent serviceIntent = new Intent(AndroidService.class.getName());

  private CheckBox taskRunning;

  private EditText taskRate;

  @Override
  public void onCreate(
    Bundle savedInstanceState) {
  
    super.onCreate(savedInstanceState);
    Log.d(LOG_TAG, "onCreate()");
    setContentView(R.layout.main);

    startService(serviceIntent);

    taskRunning = (CheckBox) findViewById(R.id.serviceRunning);
    taskRunning.setChecked(true);
    taskRunning.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      public void onCheckedChanged(
        CompoundButton compoundButton,
        boolean        isChecked) {

        if (isChecked) {
          startService(serviceIntent);
        }
        else {
          stopService(serviceIntent);
        }
      }
    });

    taskRate = (EditText) findViewById(R.id.taskRate);
    taskRate.setText("5");
    taskRate.setOnFocusChangeListener(new OnFocusChangeListener() {
      public void onFocusChange(
        View    view,
        boolean hasFocus) {

        if (hasFocus == false) {
          int value = Integer.parseInt(taskRate.getText().toString());
          serviceIntent.putExtra("taskRate", value);
        }
      }
    });
  }
}