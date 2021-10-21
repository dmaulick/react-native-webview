// eslint-disable-next-line import/prefer-default-export
export const HTML = `<!DOCTYPE html>\n
<html>
  <head>
    <title>Messaging</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=320, user-scalable=no">
    <style type="text/css">
      body {
        margin: 0;
        padding: 0;
        font: 62.5% arial, sans-serif;
        background: #ccc;
      }
    </style>
  </head>
  <body>
    <button onclick="sendPostMessage()">Send post message from JS to WebView</button>
    <p id="demo"></p>    
    <script>
      function sendPostMessage() {
        window.ReactNativeWebView.postMessage('Message from JS');
      }

      window.addEventListener('message',function(event){
        console.log("Message received from RN: ",event.data)
      },false);

    </script>
  </body>
</html>`;

export const INJECTED_JAVASCRIPT_COLORBACKGROUND_AND_POST_WEBVIEW_MESSAGE = `(function() {
  document.body.style.backgroundColor = 'blue';
  window.ReactNativeWebView.postMessage('useWebViewInjection|DONE|');
  true
})();`;

