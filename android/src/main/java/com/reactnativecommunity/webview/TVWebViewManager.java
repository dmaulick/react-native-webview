package com.reactnativecommunity.webview;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;


@ReactModule(name = TVWebViewManager.REACT_CLASS)
public class TVWebViewManager extends RNCWebViewManager {

  protected static final String REACT_CLASS = "TVWebView";

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  // This overrides a basically a hook on parent to do custom creation logic (this is not right for what we need to do)
  @Override
  protected TVWebView createRNCWebViewInstance(ThemedReactContext reactContext) {
    return new TVWebView(reactContext);
  }

//  @Override
//  protected void addEventEmitters(ThemedReactContext reactContext, WebView view) {
//    view.setWebViewClient(new CustomWebViewClient());
//  }


//  protected static class CustomWebViewClient extends RNCWebViewClient {
//    // TODO: if cannot get confirmation from imperative call that webview is ready may need to create event
//  }

  protected static class TVWebView extends RNCWebView {
    public TVWebView(ThemedReactContext reactContext) {
      super(reactContext);
    }
  }

}