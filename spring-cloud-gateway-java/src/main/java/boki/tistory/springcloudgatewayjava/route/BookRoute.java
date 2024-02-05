package boki.tistory.springcloudgatewayjava.route;

import boki.tistory.springcloudgatewayjava.props.CustomGatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class BookRoute {
    private final String httpUrl;
    private final String apiKey;

    public BookRoute(CustomGatewayProperties props) {
        CustomGatewayProperties.ServiceConfig bookServerConfig = props.getSvc().get("book");
        this.httpUrl = bookServerConfig.getHttpUrl();
        this.apiKey = bookServerConfig.getApiKey();
    }

    @Bean
    public RouteLocator bookRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // path: GET /api/books
                .route(
                        "get_books_route",
                        r -> r.path("/api/books")
                                .and()
                                .method(HttpMethod.GET)
                                .filters(f -> f
                                        .stripPrefix(1)
                                        .rewritePath("/books", "/rest/v2/api/books")
                                        .addRequestHeader("api-key", apiKey)
                                )
                                .uri(httpUrl))

                // path: GET /api/books/:id
                .route(
                        "get_book_route",
                        r -> r.path("/api/books/{bookId}")
                                .and()
                                .method(HttpMethod.GET)
                                .filters(f -> f
                                        .stripPrefix(1)
                                        .rewritePath("/books/(?<bookId>.*)", "/rest/v2/api/books/${bookId}")
                                        .addRequestHeader("api-key", apiKey)
                                )
                                .uri(httpUrl))

                // Test - Method Transform
                // path: Delete /api/books/:id
//                .route(
//                        "update_books_route",
//                        r -> r.path("/api/books/{bookId}")
//                                .and()
//                                .method(HttpMethod.DELETE)
//                                .filters(f -> f
//                                        .stripPrefix(1)
//                                        .rewritePath("/books/(?<bookId>.*)", "/rest/v2/api/books/${bookId}")
//                                        .filter(((exchange, chain) -> {
//                                            ServerHttpRequest patchedRequest = exchange.getRequest().mutate().method(HttpMethod.PATCH).build();
//                                            exchange.getAttributes().put("PATCHED_METHOD", HttpMethod.PATCH.toString()); // 변경된 메서드를 속성에 추가
//                                            return chain.filter(exchange.mutate().request(patchedRequest).build());
//                                        }))
//                                        .addRequestHeader("api-key", apiKey)
//                                )
//                                .uri(httpUrl))



                .build();
    }
}
