package boki.tistory.springcloudgatewayjava.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 원본 요청 URI 캡처
        String originalUri = exchange.getRequest().getURI().toString();
        String originalMethod = exchange.getRequest().getMethod().toString();

        // 필터 체인을 통해 요청을 전달하고 후처리 로직으로 로그 남김
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    // 후처리 로직에서 변경된 URI 로깅
                    if (exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR) != null) {
                        String rewrittenUri = Objects.requireNonNull(exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR)).toString();
                        String finalMethod = exchange.getAttribute("PATCHED_METHOD") != null ? Objects.requireNonNull(exchange.getAttribute("PATCHED_METHOD")).toString() : originalMethod;

                        logger.info("==> gateway path: {} {}", originalMethod, originalUri);
                        logger.info("==> rewrite path: {} {}", finalMethod, rewrittenUri);
                    }
                }));
    }

    @Override
    public int getOrder() {
        // RewritePathFilter 이후에 실행
        return -1;
    }
}
