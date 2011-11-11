package org.geekscape.android.androidservice;

import java.util.*;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

// Note: Service can stop itself using the stopSelf() method

public class AndroidService extends Service {

  public static final String LOG_TAG = AndroidService.class.getSimpleName();

  private Message message = new Message("", "");

  private final Object messageLock = new Object();

  private List<MessageListener> messageListeners = new ArrayList<MessageListener>();

  private Timer timer;

  private int counter = 0;

  private TimerTask timerTask = new TimerTask() {
    @Override
    public void run() {
      Log.d(LOG_TAG, "timerTask.run()");

      try {
        Message newMessage = new Message("topic", "payload_" + (counter ++));

        synchronized (messageLock) {
          message = newMessage;
        }

        synchronized (messageListeners) {
          for (MessageListener messageListener : messageListeners) {
            try {
              messageListener.handleMessage();
            }
            catch (RemoteException remoteException) {
              Log.w(LOG_TAG, "Failed to notify message listener " + messageListener);

              apiEndpoint.removeListener(messageListener);
            }
          }
        }
      }
      catch (Throwable throwable) {
        Log.e(LOG_TAG, "Failed to generate new message", throwable);
      }
    }
  };

  private MessageApi.Stub apiEndpoint = new MessageApi.Stub() {
    public Message getMessage()
      throws RemoteException {

      synchronized (messageLock) {
        return(message);
      }
    }

    public void addListener(
      MessageListener messageListener)
      throws RemoteException {

      synchronized (messageListeners) {
        messageListeners.add(messageListener);
      }
    }

    public void removeListener(
      MessageListener messageListener)
      throws RemoteException {

      synchronized (messageListeners) {
        messageListeners.remove(messageListener);
      }
    }
  };  

  @Override
  public IBinder onBind(
    Intent intent) {

    if (AndroidService.class.getName().equals(intent.getAction())) {
      Log.d(LOG_TAG, "onBind(intent): " + intent);
      return(apiEndpoint);
    }
    else {
      return(null);
    }
  }

  @Override
  public void onCreate() {
    super.onCreate();
    Log.d(LOG_TAG, "onCreate()");

    timer = new Timer("timerTask");
    timer.schedule(timerTask, 1000L, 5 * 1000L);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Log.d(LOG_TAG, "onDestroy()");

    timer.cancel();
    timer = null;
  }
}