package org.geekscape.android.androidservice;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class AndroidService extends Service {

  public static final String LOG_TAG = AndroidService.class.getSimpleName();

  private Timer timer;

  private TimerTask timerTask = new TimerTask() {
    @Override
    public void run() {
      Log.i(LOG_TAG, "timerTask.run()");
    }
  };

  @Override
  public IBinder onBind(Intent intent) {
    return(null);
  }

  @Override
  public void onCreate() {
    super.onCreate();
    Log.i(LOG_TAG, "onCreate()");

    timer = new Timer("timerTask");
    timer.schedule(timerTask, 1000L, 5 * 1000L);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Log.i(LOG_TAG, "onDestroy()");

    timer.cancel();
    timer = null;
  }
}