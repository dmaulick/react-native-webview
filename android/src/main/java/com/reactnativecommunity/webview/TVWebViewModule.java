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


  /**
   * Default props on initial render that are not set prior to render:
   * (Make sure to compare with jsx before believing this for gospel)
   * {
   *   "width": "100%",
   *   "javaScriptEnabled": true,
   *   "saveFormDataDisabled": false,
   *   "messagingEnabled": false,
   *   "backgroundColor": -1,
   *   "height": "100%",
   *   "thirdPartyCookiesEnabled": true,
   *   "allowsFullscreenVideo": false,
   *   "setDisplayZoomControls": false,
   *   "nestedScrollEnabled": false,
   *   "overScrollMode": "always",
   *   "cacheEnabled": true,
   *   "setBuiltInZoomControls": true,
   *   "allowFileAccess": false,
   *   "androidHardwareAccelerationDisabled": false,
   *   "scalesPageToFit": true,
   *   "androidLayerType": "none",
   *   "setSupportMultipleWindows": true,
   *   "messagingModuleName": "WebViewMessageHandler2",
   *   "overflow": "hidden",
   *   "source": null,
   *   "flex": 1
   * }
   */
  //@Nullable ReadableMap source
  @ReactMethod
  public void createCachedTVWebView(@Nullable ReadableMap source) {

    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run() {

        try {
          Log.d(RNCWebViewManager.TAG, "Handler(Looper.getMainLooper() in eagerInitRNCWebViewManager");

          // create:
          WebView webView = mTvWebViewManager.createCachedTVWebView(mReactContext);

          // initialize:
          mTvWebViewManager.setSource(webView, source);
          mTvWebViewManager.setJavaScriptEnabled(webView, true);
//        mTvWebViewManager.setMessagingEnabled(webView, true);
        } catch (Exception e) {
          Log.d(RNCWebViewManager.TAG, "run: with exception: "+ e.toString());
        }
      }
    });
  }

  @ReactMethod
  public void imperativeDropViewInstance() {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run() {
        Log.d(RNCWebViewManager.TAG, "imperativeDropViewInstance: ");
        mTvWebViewManager.imperativeDropViewInstance();
      }
    });
  }


  // TODO: this is still not hooked up to the RN side
  @ReactMethod
  public void postMessage(String message) {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run() {
        Log.d(RNCWebViewManager.TAG, "postMessage: " + message);
        mTvWebViewManager.imperativePostMessage(Utils.convertStrToWritableArray(message));
      }
    });
  }

  @ReactMethod
  public void injectJavascript(String jsCommand) {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run() {
        Log.d(RNCWebViewManager.TAG, "injectJavascript: " + jsCommand);
        mTvWebViewManager.imperativeInjectJavascript(Utils.convertStrToWritableArray(jsCommand));
      }
    });
  }
}
