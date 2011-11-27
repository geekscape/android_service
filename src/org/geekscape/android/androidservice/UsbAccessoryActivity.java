/**
 * Please do not remove the following notices.
 * Copyright (c) 2011 by Geekscape Pty. Ltd.
 * License: AGPLv3 http://geekscape.org/static/aiko_license.html
 */ 

package org.geekscape.android.androidservice;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public final class UsbAccessoryActivity extends Activity {

  static final String LOG_TAG = UsbAccessoryActivity.class.getSimpleName();

	@Override
	protected void onCreate(
    Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    Log.d(LOG_TAG, "onCreate()");

    startService(new Intent(AndroidService.class.getName()));
  }
}
