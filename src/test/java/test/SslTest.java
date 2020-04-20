package test;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.ws.WebSocket;
import org.asynchttpclient.ws.WebSocketUpgradeHandler;
import org.junit.AfterClass;
import org.junit.Test;

import static org.asynchttpclient.DefaultAsyncHttpClientConfig.Builder;

public class SslTest {

    private final static AsyncHttpClient client = new DefaultAsyncHttpClient(
            new Builder().setUseInsecureTrustManager(true).build()
    );

    @Test
    public void testWSCall() throws Exception {
        WebSocket websocket = client.prepareGet("wss://127.0.0.1:8181/test")
                .execute(new WebSocketUpgradeHandler.Builder().build()).get();
        websocket.sendTextFrame("Data");
        websocket.sendCloseFrame();
    }

    @Test
    public void testHttpCall() throws Exception {
        client.prepareGet("https://localhost:8080/test").execute().get();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        client.close();
    }
}