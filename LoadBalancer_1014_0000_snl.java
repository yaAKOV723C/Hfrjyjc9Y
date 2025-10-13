// 代码生成时间: 2025-10-14 00:00:28
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Random;

@Component
public class LoadBalancer {

    private final Random random = new Random();
    private final String[] servers = {
        "http://server1",
        "http://server2",
        "http://server3"
    };

    /**
     * 使用RestTemplate调用服务器，并实现负载均衡
     * @param request 请求参数
     * @return 服务器响应
     */
    public String callServer(String request) {
        try {
            // 选择一个服务器进行请求
            String server = servers[random.nextInt(servers.length)];
            URI uri = UriComponentsBuilder.fromHttpUrl(server).build().toUri();

            // 使用RestTemplate发送请求
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(uri, String.class);
        } catch (Exception e) {
            // 错误处理
            System.out.println("Error occurred while calling server: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
}