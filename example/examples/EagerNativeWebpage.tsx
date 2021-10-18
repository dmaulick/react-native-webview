import React, {Component} from 'react';
import {View} from 'react-native';

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
          // source={{uri: 'https://infinite.red'}}
          style={{width: '100%', height: '100%'}}
          // setSupportMultipleWindows={false}
        />
      </View>
    );
  }
}
