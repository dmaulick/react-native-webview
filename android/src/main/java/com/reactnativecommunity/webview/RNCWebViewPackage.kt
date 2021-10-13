package com.reactnativecommunity.webview

import android.util.Log
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.ReactApplicationContext
import com.reactnativecommunity.webview.RNCWebViewManager.TAG
import java.util.*


public class RNCWebViewPackage: ReactPackage {

  private lateinit var viewManagerSingleton: RNCWebViewManager;

  override fun createNativeModules(reactContext: ReactApplicationContext): List<RNCWebViewModule> {
    Log.d(TAG, "createNativeModules")

    if (!this::viewManagerSingleton.isInitialized) {
      viewManagerSingleton = RNCWebViewManager(reactContext);
    }

    // same as before:
    return listOf(
      RNCWebViewModule(reactContext, viewManagerSingleton)
    );
  }

  @Throws(Exception::class)
  override fun createViewManagers(reactContext: ReactApplicationContext): List<RNCWebViewManager> {
    Log.d(TAG, "createViewManagers")

    if (!this::viewManagerSingleton.isInitialized) {
      throw Exception("in createViewManagers `viewManagerSingleton` was null.")
    }

    return listOf(viewManagerSingleton);

    // OLD:
//    return listOf(
//      RNCWebViewManager()
//    )
  }
}

/**
 *
 * Things to test:
 * 1. No matter what, the above log only fire once on cold start no matter how many times we swithc button to new webview
 *    - both fire again if you colds start again but not background
 * 2. Before the View is even rendered the fsollowing functions fire
 * ** Remember this is for the original package set up.
 *
 *      createNativeModules
 *      createViewManagers
 *      getExportedCustomDirectEventTypeConstants:
 *      getCommandsMap
 *
 *    Then when I show the following trigger (don't have all of the props logging**):
 *        getExportedCustomDirectEventTypeConstants:
 *        getCommandsMap:
 *
 *        createViewInstance:
 *        eagerlyCreateViewInstance:
 *        configureRNCWebView:
 *
 *        addEventEmitters:
 *        setNestedScrollEnabled:
 *        setSource:
 *
 *
 *
 *
 *
 */
