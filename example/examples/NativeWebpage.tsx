import React, {Component, useCallback, useRef, useState} from 'react';
import {View} from 'react-native';

import WebView from 'react-native-webview';

import {
  WebViewMessageEvent,

  NativeWebViewAndroid,
} from 'react-native-webview/lib/WebViewTypes';


interface Props {}
interface State {}

// onLoadingStart
// onLoadingError 
// onHttpError
// onLoadingFinish
// onMessage
// onLoadingProgress
// injectJavaScript
// postMessage
export const NativeWebpage = () => {
  const webViewRef = useRef<WebView>(null);

  // postMessage
  /** in code its postMessageToChart */
  const postMessage = useCallback(
    (message: String /* in code export type MessageToChart = ToChart | ToWidget; */) => {
      webViewRef.current?.postMessage(JSON.stringify(message));
    },
    [],
  );


  // injectJavaScript
  /** in code you can find usage in useInjectFunction */
  const sourceArgs = [true];
  const source = `(function (returnVal){
      const window = this;
      try {
        window.ReactNativeWebView.postMessage('useWebViewInjection|DONE|');
      } catch (error) {
        window.ReactNativeWebView.postMessage('useWebViewInjection|ERROR|');
      }
      return returnVal;
    }).apply(window, ${JSON.stringify(sourceArgs)});
  `;
  const injectJavascript = useCallback(
    (/* in code they don't have a callback like this */) => {
      webViewRef.current?.injectJavaScript(source);
    },
    [],
  );

  // This is what gets called in response to webview calling `window.ReactNativeWebView.postMessage`
  const onMessage = useCallback(
    (event: WebViewMessageEvent) => {
      const {
        nativeEvent: { data },
      } = event;
      if (data.startsWith('useWebViewInjection|DONE')) {
        console.log('onMessage called with useWebViewInjection|DONE')
        // Old
        // const [, , injectId] = data.split('|');
        // promises.current?.get(injectId)?.resolve(true);
      } else if (data.startsWith('useWebViewInjection|ERROR')) {
        console.log('onMessage called with useWebViewInjection|ERROR')
        // const [, , injectId, ...valueParts] = data.split('|');
        // const value = valueParts.join('|');
        // promises.current?.get(injectId)?.reject(value);
      } else {
        console.log('onMessage is in else and would call handleMessage')
        // handleMessage?.(event, inject);
      }
    },
    [],
  );

  const [startTime, setStartTime] = useState<number | undefined>(undefined);

  return (
    <View style={{height: 400}}>
      <WebView
        style={{width: '100%', height: '100%'}}

        ref={webViewRef}

        // source={{uri: 'https://infinite.red'}}

        
        // postMessage={} - imperative
        // injectJavaScript={} - imperative 
        onMessage={onMessage}
        onLoadStart={(event) =>  {
          console.log('onLoadStart')
          if (startTime === undefined) {
            setStartTime(Date.now())
          }
        }} // , event)}
        onLoadEnd={(event) =>  {
          console.log('onLoadEnd with elapsed since start load;', startTime !== undefined ? Date.now() - startTime : undefined)
        }} // , event)}
        onLoadError={(event) =>  console.log('onLoadError')} // , event)}
        onLoadProgress={(event) =>  console.log('onLoadProgress')} // , event)}
        onHttpError={(event) =>  console.log('onHttpError')} // , event)}


        // setSupportMultipleWindows={false}
      />
    </View>
  );
}

export default NativeWebpage
