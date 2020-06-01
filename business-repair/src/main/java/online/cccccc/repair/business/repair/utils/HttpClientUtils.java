package online.cccccc.repair.business.repair.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 你是电脑
 * @date 2019/11/1 - 20:41
 */
public class HttpClientUtils {
    private HttpClientUtils(){}
    private static  HttpClientUtils INSTANCE;
    public static final byte[] LOCK= new byte[0];
    public static HttpClientUtils getInstance(){
        if (INSTANCE == null) {
            synchronized (LOCK){
                if (INSTANCE == null) {
                    INSTANCE = new HttpClientUtils();
                }
            }
        }
        return INSTANCE;
    }
    public CloseableHttpResponse post(String url, String body) throws IOException {
        // 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建 HttpPost 请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type","application/json");
        // 设置 HttpPost 参数
        httpPost.setEntity(new StringEntity(body,"UTF-8"));
        //发送请求
        return httpClient.execute(httpPost);
    }
}
