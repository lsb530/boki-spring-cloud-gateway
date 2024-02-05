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
                        "books_route",
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
                        "books_route",
                        r -> r.path("/api/books/{bookId}")
                                .and()
                                .method(HttpMethod.GET)
                                .filters(f -> f
                                        .stripPrefix(1)
                                        .rewritePath("/books/(?<bookId>.*)", "/rest/v2/api/books/${bookId}")
                                        .addRequestHeader("api-key", apiKey)
                                )
                                .uri(httpUrl))

                .build();
    }
}
