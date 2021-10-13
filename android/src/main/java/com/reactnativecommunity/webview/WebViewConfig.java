package com.reactnativecommunity.webview;

import android.webkit.WebView;

/**
 * Implement this interface in order to config your {@link WebView}. An instance of that
 * implementation will have to be given as a constructor argument to {@link RNCWebViewManager}.
 */



// I don't think we actually need this.
public interface WebViewConfig {

  void configWebView(WebView webView);
}
