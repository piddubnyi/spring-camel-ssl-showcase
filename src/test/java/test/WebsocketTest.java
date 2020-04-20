package test;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.ws.WebSocket;
import org.asynchttpclient.ws.WebSocketUpgradeHandler;
import org.junit.Test;

import static org.asynchttpclient.DefaultAsyncHttpClientConfig.Builder;

public class WebsocketTest {

    @Test
    public void testWSHttpCall() throws Exception {
        AsyncHttpClient client = new DefaultAsyncHttpClient(
                new Builder().setUseInsecureTrustManager(true).build()
        );
        WebSocket websocket = client.prepareGet("wss://127.0.0.1:8181/test")
                .execute(new WebSocketUpgradeHandler.Builder().build()).get();
        websocket.sendTextFrame("Data");
        websocket.sendCloseFrame();
        client.close();
    }

}