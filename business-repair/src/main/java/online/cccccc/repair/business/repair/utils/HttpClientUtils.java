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
 * @create 2019/11/1 - 20:41
 */
@Component
public class HttpClientUtils {
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
