package com.reactnativecommunity.webview;

import static com.reactnativecommunity.webview.Utils.printInitialProps;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;


@ReactModule(name = TVWebViewManager.REACT_CLASS)
public class TVWebViewManager extends RNCWebViewManager {

  protected static final String REACT_CLASS = "TVWebView";

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  private WebView mCachedWebView;

  protected @NonNull WebView codePulledFromBaseViewManager(
    WebView view,
    int reactTag,
    @NonNull ThemedReactContext reactContext,
    @Nullable ReactStylesDiffMap initialProps,
    @Nullable StateWrapper stateWrapper) {
    // T view = createViewInstance(reactContext); - This is the only piece that needs to be pulled...
    view.setId(reactTag);
    addEventEmitters(reactContext, view);
    if (initialProps != null) {
      updateProperties(view, initialProps);
    }
    // Only present in Fabric; but always present in Fabric.
    if (stateWrapper != null) {
      Object extraData = updateState(view, initialProps, stateWrapper);
      if (extraData != null) {
        updateExtraData(view, extraData);
      }
    }
    return view;
  }

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

    printInitialProps("createViewInstance", initialProps);

    try {
      if (initialProps != null && initialProps.getBoolean("isCached", false)) {
        if (mCachedWebView == null) {
          throw new Exception("Cached TVWebView is null.");
        }
        return codePulledFromBaseViewManager(mCachedWebView, reactTag, reactContext, initialProps, stateWrapper);
      }
    } catch (Exception e) {
      // TODO: ideally we let exception bubble up
      Log.d(TAG, "Exception thrown attempting to access cached WebView. Exception: " + e.toString());
    }
    WebView view = createViewInstance(reactContext); // Single line pulled from ViewManager.createViewInstance()
    return codePulledFromBaseViewManager(view, reactTag, reactContext, initialProps, stateWrapper);
  }

  @NonNull
  @Override
  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  protected WebView createViewInstance(ThemedReactContext reactContext) {
    Log.d(TAG, "createViewInstance:  should not be called if eager init");
    return helpCreateViewInstance(reactContext);
  }

  protected WebView createCachedTVWebView(ReactContext reactContext) {
    mCachedWebView = helpCreateViewInstance(reactContext);
    return mCachedWebView;
  }

  @ReactProp(name = "isCached")
  public void setIsCached(
    WebView view,
    @Nullable Boolean isCached) {
    // Should not do anything. Just here for createView overload
  }


  // TODO: reolve this
  // FOR NOW - we are going to aggressively override props so we can just not pass anything
  // However I think we will want to make this a bit more intelligent
  @Override
  @ReactProp(name = "source")
  public void setSource(WebView view, @Nullable ReadableMap source) {
    Utils.printReadableMap("TVWebViewManager#setSource", source);
    if (source != null) {
      super.setSource(view, source);
    }
  }

  protected static class TVWebView extends RNCWebView {
    public TVWebView(ThemedReactContext reactContext) {
      super(reactContext);
    }
  }

}