package com.reactnativecommunity.webview;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = TVWebViewModule.MODULE_NAME)
public class TVWebViewModule extends RNCWebViewModule {
  public static final String MODULE_NAME = "TVWebView";

  @Override
  public String getName() {
    return MODULE_NAME;
  }

  public TVWebViewModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @ReactMethod
  public void imperativeCreateTVWebViewManager(@Nullable ReadableMap source) {
    Log.d(RNCWebViewManager.TAG, "imperativeCreateTVWebViewManager");

    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run() {
        Log.d(RNCWebViewManager.TAG, "Handler(Looper.getMainLooper() in eagerInitRNCWebViewManager");
//        mRNCWebViewManager.eagerlyCreateViewInstance(mReactContext, source);
      }
    });
  }
}
