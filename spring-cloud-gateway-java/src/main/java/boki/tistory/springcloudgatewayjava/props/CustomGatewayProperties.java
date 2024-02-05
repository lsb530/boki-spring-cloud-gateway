package boki.tistory.springcloudgatewayjava.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "boki")
public class CustomGatewayProperties {

    private Map<String, ServiceConfig> svc;

    public static class ServiceConfig {
        @Override
        public String toString() {
            return "ServiceConfig{" +
                    "baseUrl='" + baseUrl + '\'' +
                    ", httpUrl='" + httpUrl + '\'' +
                    ", websocketUrl='" + websocketUrl + '\'' +
                    ", apiKey='" + apiKey + '\'' +
                    '}';
        }

        private String baseUrl;
        private String httpUrl;
        private String websocketUrl;
        private String apiKey;

        // getters and setters
        public String getBaseUrl() { return baseUrl; }
        public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }

        public String getHttpUrl() { return httpUrl; }
        public void setHttpUrl(String httpUrl) { this.httpUrl = httpUrl; }

        public String getWebsocketUrl() { return websocketUrl; }
        public void setWebsocketUrl(String websocketUrl) { this.websocketUrl = websocketUrl; }

        public String getApiKey() { return apiKey; }
        public void setApiKey(String apiKey) { this.apiKey = apiKey; }
    }

    public Map<String, ServiceConfig> getSvc() {
        return svc;
    }

    public void setSvc(Map<String, ServiceConfig> svc) {
        this.svc = svc;
    }
}
