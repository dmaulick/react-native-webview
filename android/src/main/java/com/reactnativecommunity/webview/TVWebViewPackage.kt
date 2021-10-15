package com.reactnativecommunity.webview

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.ReactApplicationContext

class TVWebViewPackage: ReactPackage {

  override fun createNativeModules(reactContext: ReactApplicationContext) = listOf(
    TVWebViewModule(reactContext)
  )

  override fun createViewManagers(reactContext: ReactApplicationContext) = listOf(
    TVWebViewManager()
  )
}
