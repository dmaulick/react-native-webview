package com.reactnativecommunity.webview;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = TVWebViewModule.MODULE_NAME)
public class TVWebViewModule extends RNCWebViewModule {
  public static final String MODULE_NAME = "TVWebView";

  TVWebViewManager mTvWebViewManager;
  ReactApplicationContext mReactContext; // I don't think this will memory leak

  @NonNull
  @Override
  public String getName() {
    return MODULE_NAME;
  }

  public TVWebViewModule(ReactApplicationContext reactContext, TVWebViewManager tvWebViewManager) {
    super(reactContext);
    mReactContext = reactContext;
    mTvWebViewManager = tvWebViewManager;
  }

  //@Nullable ReadableMap source
  @ReactMethod
  public void createCachedTVWebView(@Nullable ReadableMap source) {
    Log.d(RNCWebViewManager.TAG, "TVWebViewModule.createCachedTVWebView");

    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run() {
        Log.d(RNCWebViewManager.TAG, "Handler(Looper.getMainLooper() in eagerInitRNCWebViewManager");

        // create:
        WebView webView = mTvWebViewManager.createCachedTVWebView(mReactContext);

        // initialize:
        mTvWebViewManager.setSource(webView, source);

      }
    });
  }
}
