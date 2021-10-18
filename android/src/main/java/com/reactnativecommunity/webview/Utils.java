package com.reactnativecommunity.webview;

import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ReactStylesDiffMap;

public class Utils {
  public static void printInitialProps(String callSite, @Nullable ReactStylesDiffMap initialProps) {
    String propsStr = "NullInitialProps";
    if (initialProps != null) {
      propsStr = initialProps.toString();
    }
    Log.d(RNCWebViewManager.TAG, callSite + "#printInitialProps: "+ propsStr);
  }

  public static void printReadableMap(String callSite, @Nullable ReadableMap source) {
    String str = "unsetSource";
    if (source != null) {
      str = source.toString();
    }
    Log.d(RNCWebViewManager.TAG, callSite + "#printReadableMap:" + str);
  }
}
