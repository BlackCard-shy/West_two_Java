import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class APIGet {
    static String GetData(String uri) {
        // (1) 创建HttpGet实例
        HttpGet get = new HttpGet(uri);

        // (2) 使用HttpClient发送get请求，获得返回结果HttpResponse
        CloseableHttpClient http = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = http.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // (3) 读取返回结果
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            try (InputStream in = entity.getContent()) {
                return readResponse(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //readResponse(in);
        }
        return null;
    }

    private static String readResponse(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        StringBuilder response = new StringBuilder();
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            response.append(line);
            //System.out.println(line);
        }
        return response.toString();
    }
}
