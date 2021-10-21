package com.reactnativecommunity.webview;

import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.uimanager.ReactStylesDiffMap;

import java.util.ArrayList;
import java.util.List;

public class Utils {
  public static void printInitialProps(String callSite, @Nullable ReactStylesDiffMap initialProps) {
    String propsStr = "NullInitialProps";
    if (initialProps != null) {
      propsStr = initialProps.toString();
    }
  }

  public static void printReadableMap(String callSite, @Nullable ReadableMap source) {
    String str = "unsetSource";
    if (source != null) {
      str = source.toString();
    }
    Log.d(RNCWebViewManager.TAG, callSite + "#printReadableMap:" + str);
  }

  // Shopify article good for bridge serialization:
  // https://medium.com/shoutem/ways-to-pass-objects-between-native-and-javascript-in-react-native-c3dcae7bf4f5
  public static WritableArray convertListToWritableArray(List<String>
                                                     strArray) {
    WritableArray array = new WritableNativeArray();
    for (String str : strArray) {
      array.pushString(str);
    }
    return array;
  }

  public static WritableArray convertStrToWritableArray(String str) {
    List<String> list = new ArrayList<String>();
    list.add(str);
    return Utils.convertListToWritableArray(list);
  }
}
