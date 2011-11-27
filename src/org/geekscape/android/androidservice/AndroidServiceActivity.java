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

import org.geekscape.android.androidservice.R;

public class AndroidServiceActivity extends Activity {

  public static String LOG_TAG = AndroidServiceActivity.class.getSimpleName();

  @Override
  public void onCreate(
    Bundle savedInstanceState) {
  
    super.onCreate(savedInstanceState);
    Log.d(LOG_TAG, "onCreate()");
    setContentView(R.layout.main);

    startService(new Intent(AndroidService.class.getName()));
  }
}
