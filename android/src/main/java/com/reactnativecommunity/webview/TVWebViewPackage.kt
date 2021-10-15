package com.reactnativecommunity.webview

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.ReactApplicationContext

class TVWebViewPackage: ReactPackage {
  private lateinit var viewManagerSingleton: TVWebViewManager;

  private fun getViewManagerSingleton(): TVWebViewManager {
    if (!this::viewManagerSingleton.isInitialized) {
      viewManagerSingleton = TVWebViewManager();
    }
    return viewManagerSingleton;
  }

  override fun createNativeModules(reactContext: ReactApplicationContext): List<TVWebViewModule> {
    val viewManager = getViewManagerSingleton();
    return listOf(
      TVWebViewModule(reactContext, viewManager)
    )
  }

  override fun createViewManagers(reactContext: ReactApplicationContext) = listOf(
    getViewManagerSingleton()
  );
}
