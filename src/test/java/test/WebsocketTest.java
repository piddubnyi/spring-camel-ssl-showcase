package test;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.ws.WebSocket;
import org.asynchttpclient.ws.WebSocketListener;
import org.asynchttpclient.ws.WebSocketUpgradeHandler;
import org.junit.Test;

public class WebsocketTest {

    @Test
    public void testWSHttpCall() throws Exception {
        AsyncHttpClient client = new DefaultAsyncHttpClient();

        WebSocket websocket = client.prepareGet("ws://127.0.0.1:8181/test").execute(
                new WebSocketUpgradeHandler.Builder()
                        .addWebSocketListener(new WebSocketListener() {
                            @Override
                            public void onOpen(WebSocket websocket) {
                            }

                            @Override
                            public void onClose(WebSocket websocket, int code, String reason) {

                            }

                            @Override
                            public void onError(Throwable t) {
                                t.printStackTrace();
                            }

                            @Override
                            public void onBinaryFrame(byte[] payload, boolean finalFragment, int rsv) {
                                System.out.println(new String(payload));
                            }

                            @Override
                            public void onTextFrame(String payload, boolean finalFragment, int rsv) {
                                System.out.println(payload);
                            }

                            @Override
                            public void onPingFrame(byte[] payload) {

                            }

                            @Override
                            public void onPongFrame(byte[] payload) {

                            }
                        }).build()).get();

        websocket.sendTextFrame("Data");
        websocket.sendCloseFrame();
        client.close();
    }

}