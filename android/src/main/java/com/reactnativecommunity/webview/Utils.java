package com.reactnativecommunity.webview;

import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.react.uimanager.ReactStylesDiffMap;

public class Utils {
  public static void printInitialProps(@Nullable ReactStylesDiffMap initialProps) {
    String propsStr = "NullInitialProps";
    if (initialProps != null) {
      propsStr = initialProps.toString();
    }
    Log.d(RNCWebViewManager.TAG, "createViewInstance#printInitialProps: "+ propsStr);
  }
}
