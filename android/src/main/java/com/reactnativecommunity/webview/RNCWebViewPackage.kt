package com.reactnativecommunity.webview

import android.util.Log
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.ReactApplicationContext
import com.reactnativecommunity.webview.RNCWebViewManager.TAG


public class RNCWebViewPackage: ReactPackage {


  override fun createNativeModules(reactContext: ReactApplicationContext): List<RNCWebViewModule> {
    Log.d(TAG, "createNativeModules")
    return listOf(
      RNCWebViewModule(reactContext)
    );
  }

  override fun createViewManagers(reactContext: ReactApplicationContext): List<RNCWebViewManager> {
    Log.d(TAG, "createViewManagers")
    return listOf(
      RNCWebViewManager()
    )
  }
}

/**
 *
 * Things to test:
 * 1. No matter what, the above log only fire once on cold start no matter how many times we swithc button to new webview
 *    - both fire again if you colds start again but not background
 * 2.
 *
 */
