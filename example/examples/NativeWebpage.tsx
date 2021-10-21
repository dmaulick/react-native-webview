import React, {Component} from 'react';
import {Alert, NativeModules, StyleSheet, Text, TouchableOpacity, View} from 'react-native';

import WebView from 'react-native-webview';
import { INJECTED_JAVASCRIPT_COLORBACKGROUND_AND_POST_WEBVIEW_MESSAGE } from '../Utils';

interface Props {}
interface State {}


const styles = StyleSheet.create({
  btnStyle: {
    padding: 6,
    fontSize: 16,
    borderRadius: 5,
    backgroundColor: '#d3bdfb',
    alignItems: 'center',
    justifyContent: 'center',
    alignSelf: 'flex-end',
  },
});
export default class NativeWebpage extends Component<Props, State> {
  state = {};

  constructor(props) {
    super(props);
    this.webView = React.createRef();
  }

  triggerJS = () => {
    this.webView.current.injectJavaScript(INJECTED_JAVASCRIPT_COLORBACKGROUND_AND_POST_WEBVIEW_MESSAGE);
  }

  render() {
    return (
      <View style={{height: 400}}>

        <TouchableOpacity
          onPress={this.triggerJS}
          style={styles.btnStyle}
          activeOpacity={0.6}>
          <Text>Imperative Run JS</Text>
        </TouchableOpacity>

        <WebView
          ref={this.webView}
          source={{uri: 'https://github.com/react-native-webview/react-native-webview'}}
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