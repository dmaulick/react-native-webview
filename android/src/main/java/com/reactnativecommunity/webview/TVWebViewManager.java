package com.reactnativecommunity.webview;

import static com.reactnativecommunity.webview.Utils.printInitialProps;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;


@ReactModule(name = TVWebViewManager.REACT_CLASS)
public class TVWebViewManager extends RNCWebViewManager {

  protected static final String REACT_CLASS = "TVWebView";

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  private WebView mCachedWebView;

  // Learn more here: https://github.com/facebook/react-native/blob/678f2cb936ae4cf10e1fa4b032b91bfe9b77efaf/ReactAndroid/src/main/java/com/facebook/react/uimanager/ViewManager.java#L134
  // Chiefly that this is upstream of the other overload which is originally used by the lib
  @NonNull
  @Override
  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  protected WebView createViewInstance(
    int reactTag,
    @NonNull ThemedReactContext reactContext,
    @Nullable ReactStylesDiffMap initialProps,
    @Nullable StateWrapper stateWrapper) {

    printInitialProps(initialProps);

    try {
      if (initialProps != null && initialProps.getBoolean("isCached", false)) {
        if (mCachedWebView == null) {
          throw new Exception("Cached TVWebView is null.");
        }
        return mCachedWebView;
      }
    } catch (Exception e) {
      Log.d(TAG, "Exception thrown attempting to access cached WebView. Exception: " + e.toString());
    }
    return super.createViewInstance(reactTag, reactContext, initialProps, stateWrapper);
  }

  @NonNull
  @Override
  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  protected WebView createViewInstance(ThemedReactContext reactContext) {
    return helpCreateViewInstance(reactContext);
  }

  protected WebView createCachedTVWebView(ReactContext reactContext) {
    mCachedWebView = helpCreateViewInstance(reactContext);
    return mCachedWebView;
  }

  protected static class TVWebView extends RNCWebView {
    public TVWebView(ThemedReactContext reactContext) {
      super(reactContext);
    }
  }

}