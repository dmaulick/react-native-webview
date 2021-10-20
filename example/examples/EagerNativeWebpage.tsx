import React, {Component} from 'react';
import {View, Alert} from 'react-native';

import WebView from 'react-native-webview';

interface Props {}
interface State {}

export default class EagerNativeWebpage extends Component<Props, State> {
  state = {};

  render() {
    return (
      <View style={{height: 400}}>
        <WebView
          isCached
          style={{width: '100%', height: '100%'}}
          onMessage={(e: {nativeEvent: {data?: string}}) => {
            Alert.alert('Message received from JS: ', e.nativeEvent.data);
          }}
          // setSupportMultipleWindows={false}
        />
      </View>
    );
  }
}
