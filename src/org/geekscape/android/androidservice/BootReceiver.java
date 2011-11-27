/**
 * Please do not remove the following notices.
 * Copyright (c) 2011 by Geekscape Pty. Ltd.
 * License: AGPLv3 http://geekscape.org/static/aiko_license.html
 */ 

package org.geekscape.android.androidservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(
    Context context,
    Intent  intent) {

    Intent serviceIntent = new Intent(AndroidService.class.getName());
    context.startService(serviceIntent);
  }
}
