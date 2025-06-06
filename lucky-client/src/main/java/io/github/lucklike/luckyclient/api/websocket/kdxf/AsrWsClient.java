package io.github.lucklike.luckyclient.api.websocket.kdxf;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class AsrWsClient extends WebSocketClient {

    private final StringBuilder content = new StringBuilder();
    private final CountDownLatch connectClose = new CountDownLatch(1);
    private final CountDownLatch open = new CountDownLatch(1);

    public AsrWsClient(URI serverUri) {
        super(serverUri);
        if (serverUri.toString().contains("wss")) {
            trustAllHosts();
        }
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        log.info("连接建立成功！");
        open.countDown();
    }

    @Override
    public void onMessage(String message) {
        log.info("【科大ASR-Ws】收到消息：{}", message);
        content.append(getContent(message));
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        connectClose.countDown();
    }

    @Override
    public void onError(Exception ex) {
        log.error(ex.getMessage(), ex);
    }

    @Override
    public void connect() {
        try {
            super.connect();
            open.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析音频文件并返回文本数据
     *
     * @param wavData WAV格式的音频数据
     * @return 音频数据对应的文本数据
     * @throws Exception 可能发生的异常
     */
    public String parseAudioFile(byte[] wavData) throws Exception {
        // 建立连接
        connect();
        // 发送数据
        sendData(wavData, 4096);
        // 返回解析结果
        return getContent();
    }


    /**
     * 获取解析内容
     *
     * @return 解析内容
     */
    private String getContent() {
        try {
            connectClose.await();
            return content.toString();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置SSL相关的配置
     */
    public void trustAllHosts() {
        System.out.println("wss");
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                // TODO Auto-generated method stub

            }

            @Override
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                // TODO Auto-generated method stub

            }
        }};

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            setSocket(sc.getSocketFactory().createSocket());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Ws返回的消息内容
     *
     * @param message 原始信息
     * @return 消息信息
     */
    private String getContent(String message) {
        StringBuilder resultBuilder = new StringBuilder();
        JSONObject jsonObject = JSONObject.parseObject(message);
        String msgtype = jsonObject.getString("msgtype");
        if (!jsonObject.getBoolean("ls") && Objects.equals("sentence", msgtype)) {
            JSONArray wsArr = jsonObject.getJSONArray("ws");
            for (int j = 0; j < wsArr.size(); j++) {
                JSONObject wsArrObj = wsArr.getJSONObject(j);
                JSONArray cwArr = wsArrObj.getJSONArray("cw");
                for (int k = 0; k < cwArr.size(); k++) {
                    JSONObject cwArrObj = cwArr.getJSONObject(k);
                    String wStr = cwArrObj.getString("w");
                    resultBuilder.append(wStr);
                }
            }
        }
        return resultBuilder.toString();
    }

    /**
     * 发送Ws消息
     *
     * @param data 要发送的数据
     * @param fs   数据包的大小
     * @throws Exception 可能发生的异常
     */
    public void sendData(byte[] data, int fs) throws Exception {
        int i = 0;
        int size = data.length;
        while (i < size) {
            int sv = size - i;
            byte[] sData = new byte[Math.min(fs, sv)];
            System.arraycopy(data, i, sData, 0, sData.length);
            send(sData);
            i = i + fs;


            // 每隔40毫秒发送一次数据
            Thread.sleep(40);
        }
        send(new byte[0]);
    }

}

